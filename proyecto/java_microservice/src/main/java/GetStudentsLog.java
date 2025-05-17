

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.Date;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

import Estudiantes.*;
import asignaturas.*;
import asignaturas.Root;

/**
 * Servlet implementation class GetStudentsLog
 */
@MultipartConfig
public class GetStudentsLog extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetStudentsLog() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Carga modelo
		ResourceSet resourceSet;
		resourceSet = new ResourceSetImpl();
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("xmi", new XMIResourceFactoryImpl());
		EPackage.Registry.INSTANCE.put(asignaturas.AsignaturasPackage.eNS_URI, asignaturas.AsignaturasPackage.eINSTANCE);
		EPackage.Registry.INSTANCE.put(Estudiantes.EstudiantesPackage.eNS_URI, Estudiantes.EstudiantesPackage.eINSTANCE);
		URI modelURI = URI.createFileURI("model.xmi");
		Resource resource = resourceSet.getResource(modelURI, true);
		
		Estudiantes.Root rootStudent;
		Part filePart;
		InputStream inputStream;
		
		try {
			filePart = request.getPart("file");
			inputStream = filePart.getInputStream();
			Resource resourceStudent = resourceSet.createResource(URI.createURI(Paths.get(filePart.getSubmittedFileName()).getFileName().toString()));
		
			resourceStudent.load(inputStream, null);
			
			rootStudent = (Estudiantes.Root) resourceStudent.getContents().get(0);
		} catch (IOException e) {
			response.getWriter().write(e.toString());
			return;
		}
		
		//Append header of CSV (ID, Activity, Timestamp, Career, Plan, Curricular Unit, Course Edition, Course Year, Grade)
		StringBuilder csvBuilder = new StringBuilder();
		csvBuilder.append("ID,Activity,Timestamp,Career,Plan,Curricular Unit,Course Edition,Course Year,Grade\n");
		
		//Itero por cada estudiante
		for (Student estudiante : rootStudent.getStudent()) {
			String ID = estudiante.getId();
			for(PlanInscription inscripcionPlan : estudiante.getStudentPlanInscription()) {
				Date TimeStamp = inscripcionPlan.getDate();
				String Career = inscripcionPlan.getPlan().getCareer_parent().getName();
				int Plan = inscripcionPlan.getPlan().getYear();
				String Activity = "Inscription to Plan";
				//Append to file each inscription
				csvBuilder.append(String.format("%s,%s,%s,%s,%d,,,,\n",
					    ID, Activity, TimeStamp, Career, Plan));
				
				for(CourseInscription inscripcionCurso : inscripcionPlan.getPlanCourseInscription()) {
					TimeStamp = inscripcionCurso.getDate();
					Activity = "Inscription to Course";
					int CourseEdition = inscripcionCurso.getCUCourse().getEdition();
					int CourseYear = inscripcionCurso.getCUCourse().getYear();
					String CurricularUnit = inscripcionCurso.getCUCourse().getCurricularunit().getId();
					//Append to file each inscription
					csvBuilder.append(String.format("%s,%s,%s,%s,%d,%s,%d,%d,\n",
						    ID, Activity, TimeStamp, Career, Plan, CurricularUnit, CourseEdition, CourseYear));
				}
				
				for(StudentEvaluation evaluacion : inscripcionPlan.getPlanStudentEvaluation()) {
					int Grade = evaluacion.getGrade();
					Activity = "Evaluation - ";
					Evaluation eval = evaluacion.getEvaluation();
					TimeStamp = eval.getDate();
					
					if (eval instanceof ExamEvaluation) {
						Activity += "Exam";
						ExamEvaluation evalExam = (ExamEvaluation) eval;
						String CurricularUnit = evalExam.getCurricularunit().getId();
						//Append to file each evaluation
						csvBuilder.append(String.format("%s,%s,%s,%s,%d,%s,,,%d\n",
							    ID, Activity, TimeStamp, Career, Plan, CurricularUnit, Grade));
						
					} else if (eval instanceof CourseEvaluation) {
						Activity += "Course";
						CourseEvaluation evalCourse = (CourseEvaluation) eval;
						String CurricularUnit = evalCourse.getCourse().getCurricularunit().getId();
						int CourseEdition = evalCourse.getCourse().getEdition();
						int CourseYear = evalCourse.getCourse().getYear();
						//Append to file each evaluation
						csvBuilder.append(String.format("%s,%s,%s,%s,%d,%s,%d,%d,%d\n",
							    ID, Activity, TimeStamp, Career, Plan, CurricularUnit, CourseEdition, CourseYear, Grade));
					}
				}
			}
		}
		response.setContentType("text/csv");
		response.setHeader("Content-Disposition", "attachment; filename=\"student_log.csv\"");
		response.getWriter().write(csvBuilder.toString());
	}

}
