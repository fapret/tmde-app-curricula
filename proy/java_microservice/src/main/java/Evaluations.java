

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
import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

import Estudiantes.*;
import asignaturas.*;

/**
 * Servlet implementation class EvalPlan
 */
@MultipartConfig
public class Evaluations extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Evaluations() {
        super();
        // TODO Auto-generated constructor stub
    }

        
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Carga modelo
		asignaturas.Root asignaturasRoot;
		ResourceSet resourceSet;
		resourceSet = new ResourceSetImpl();
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("xmi", new XMIResourceFactoryImpl());
		EPackage.Registry.INSTANCE.put(asignaturas.AsignaturasPackage.eNS_URI, asignaturas.AsignaturasPackage.eINSTANCE);
		EPackage.Registry.INSTANCE.put(Estudiantes.EstudiantesPackage.eNS_URI, Estudiantes.EstudiantesPackage.eINSTANCE);
		URI modelURI = URI.createFileURI("model.xmi");
		Resource resource = resourceSet.getResource(modelURI, true);
		asignaturasRoot = (asignaturas.Root) resource.getContents().get(0);
		
		String faculty = request.getParameter("faculty");
		String career = request.getParameter("career");
		String plan = request.getParameter("plan");
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
		
		//de un estudiante, devuelve las evaluaciones que tiene aprobadas, distinguiendo si es CourseEvaluation o ExamEvaluation y su nota
		
		Student estudiante = rootStudent.getStudent().get(0);
		

		//O(nˆ8), creo que es un récord personal
		List<String> evals = new ArrayList<>();
		for (Faculty facultad : asignaturasRoot.getFaculty()) {
			if(facultad.getName().equals(faculty)) {
				for (Career carrera : facultad.getCareers()) {
					if (carrera.getName().equals(career)) {
						for (asignaturas.Plan p : carrera.getPlan()) {
							if(Integer.toString(p.getYear()).equals(plan)) {
								for (PlanInscription i : estudiante.getStudentPlanInscription()) {
									if (i.getPlan() == p) {
										for (StudentEvaluation se : i.getPlanStudentEvaluation()) {
											
											if (se.getEvaluation() instanceof CourseEvaluation && se.getGrade() >= 3) {
												//encuentro a qué CU corresponde la evaluación
												for (CurricularUnit cu : facultad.getFacultyCU())
													for (Course cur : cu.getCourse())
														for (CourseEvaluation coe : cur.getCourseEvaluation())
															if (coe == se.getEvaluation())
																evals.add("{\""+"id\": \""+cu.getId()+"\", \"type\": \"CourseEvaluation\", \"grade\": "+String.valueOf(se.getGrade())+"}");
											}
											else if (se.getEvaluation() instanceof ExamEvaluation && se.getGrade() >= 3) {
												for (CurricularUnit cu : facultad.getFacultyCU())
													for (ExamEvaluation ee : cu.getExamEvaluation())
														if (ee == se.getEvaluation())
															evals.add("{\""+"id\": \""+cu.getId()+"\", \"type\": \"ExamEvaluation\", \"grade\": "+String.valueOf(se.getGrade())+"}");
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
		
		String out = "[";
		if (evals.size() > 0) {
			for (int i = 0; i < evals.size()-1; i++)
				out += evals.get(i)+", ";
			
			out += evals.get(evals.size()-1);
		}
		out += "]";
		
		response.getWriter().append(out);
	}
}
