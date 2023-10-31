import jakarta.servlet.ServletException;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.*;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.*;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

import Estudiantes.*;
import asignaturas.*;
import asignaturas.Root;

/**
 * Servlet implementation class EvalUC
 */
@MultipartConfig
public class EvalUC extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EvalUC() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    private int getCredOnSubject(Student e, Subject s) {
    	int c = 0;
    	for(PlanInscription p : e.getStudentPlanInscription()) {
    		for(StudentEvaluation se : p.getPlanStudentEvaluation()) {
    			if(se.getEvaluation() instanceof ExamEvaluation && se.getGrade() >= 3) {
    				ExamEvaluation eval = (ExamEvaluation) se.getEvaluation();
    				if (s.getSubjectCurricularUnit().contains(eval.getCurricularunit())) {
    					c += eval.getCurricularunit().getCred();
    				}
    			}
    		}
    	}
    	for(Subject s2 : s.getGroupOfSubjects()) {
    		c += getCredOnSubject(e, s2);
    	}
		return c;
    }
    
    private Boolean evaluateRequirements(Student e, Requirement r) {
    	if(r instanceof NOT) {
    		NOT req = (NOT) r;
    		return !evaluateRequirements(e,req.getRequirement());
    	}
    	if(r instanceof Coursed) {
    		Coursed req = (Coursed) r;
    		for(PlanInscription p : e.getStudentPlanInscription()) {
    			for(StudentEvaluation eval : p.getPlanStudentEvaluation()) {
    				if(eval.getEvaluation() instanceof CourseEvaluation && eval.getGrade() >= 3) {
    					CourseEvaluation c = (CourseEvaluation) eval.getEvaluation();
    					if(c.getCourse().getCurricularunit().getId().equals(req.getCurricularUnit().getId())) {
    						return true;
    					}
    				}
    			}
    		}
    		return false;
    	}
    	if(r instanceof Exam) {
    		Exam req = (Exam) r;
    		for(PlanInscription p : e.getStudentPlanInscription()) {
    			for(StudentEvaluation eval : p.getPlanStudentEvaluation()) {
    				if(eval.getEvaluation() instanceof ExamEvaluation && eval.getGrade() >= 3) {
    					ExamEvaluation c = (ExamEvaluation) eval.getEvaluation();
    					if(c.getCurricularunit().getId().equals(req.getCurricularUnit().getId())) {
    						return true;
    					}
    				}
    			}
    		}
    		return false;
    	}
    	if(r instanceof RegisteredTo) {
    		RegisteredTo req = (RegisteredTo) r;
    		for(PlanInscription p : e.getStudentPlanInscription()) {
    			for(CourseInscription inscription : p.getPlanCourseInscription()) {
    				if(req.getCurricularUnit().getId().equals(inscription.getCUCourse().getCurricularunit().getId())) {
    					return true;
    				}
    			}
    		}
    		return false;
    	}
    	if(r instanceof SomeOf) {
    		SomeOf req = (SomeOf) r;
    		int numOfTrue = 0;
    		for(Requirement requer : req.getRequirement()) {
    			if(evaluateRequirements(e, requer)) {
    				numOfTrue++;
    			}
        		if(numOfTrue >= req.getN()) {
        			return true;//Corto si ya se que es true
        		}
    		}
    		if(numOfTrue >= req.getN()) {
    			return true;
    		} else {
    			return false;
    		}
    	}
    	if(r instanceof CreditsOnPlan) {
    		CreditsOnPlan req = (CreditsOnPlan) r;
    		CreditsPlan plan = req.getCreditsPlan();
    		int c = 0;
    		for(PlanInscription p : e.getStudentPlanInscription()) {
    			if (p.getPlan() instanceof CreditsPlan && p.getPlan().equals(plan)) {
    				for(StudentEvaluation eval : p.getPlanStudentEvaluation()) {
    					if(eval.getEvaluation() instanceof ExamEvaluation && eval.getGrade() >= 3) {
    						ExamEvaluation ev = (ExamEvaluation) eval.getEvaluation();
    						c += ev.getCurricularunit().getCred();
    					}
    					if(c >= req.getCred()) {
    						return true; //Corto antes si se es true
    					}
    				}
    			}
    		}
			if(c >= req.getCred()) {
				return true;
			} else {
				return false;
			}
    	}
    	if(r instanceof CreditsOnSubject) {
    		CreditsOnSubject req = (CreditsOnSubject) r;
    		if(getCredOnSubject(e, req.getGroupOfSubjects()) >= req.getCred()) {
    			return true;
    		}
    		return false;
    	}
		return true;
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Carga modelo
		Root asignaturasRoot;
		ResourceSet resourceSet;
		resourceSet = new ResourceSetImpl();
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("xmi", new XMIResourceFactoryImpl());
		EPackage.Registry.INSTANCE.put(asignaturas.AsignaturasPackage.eNS_URI, asignaturas.AsignaturasPackage.eINSTANCE);
		EPackage.Registry.INSTANCE.put(Estudiantes.EstudiantesPackage.eNS_URI, Estudiantes.EstudiantesPackage.eINSTANCE);
		URI modelURI = URI.createFileURI("model.xmi");
		Resource resource = resourceSet.getResource(modelURI, true);
		asignaturasRoot = (Root) resource.getContents().get(0);
		
		String faculty = request.getParameter("faculty");
		String curricularUnit = request.getParameter("curricularUnit");
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
		
		Student estudiante = rootStudent.getStudent().get(0);
		//Test
		/*
		response.getWriter().append("Estudiante: " + estudiante.getName() + "\n");
		PlanInscription i = estudiante.getStudentPlanInscription().get(0);
		response.getWriter().append("PlanInscription Date: " + i.getDate().toString() + "\n");
		CourseInscription c = i.getPlanCourseInscription().get(0);
		response.getWriter().append("CourseInscription Date: " + c.getDate().toString() + "\n");
		Course a = c.getCUCourse();
		response.getWriter().append("CourseYear: " + Integer.toString(a.getYear()) + "\n");
		CurricularUnit uc = a.getCurricularunit();
		response.getWriter().append("CourseUC: " + uc.getId() + "\n");
		*/
		//EndTest
		
		for (Faculty facultad : asignaturasRoot.getFaculty()) {
			if(facultad.getName().equals(faculty)) {
				for(CurricularUnit cu : facultad.getFacultyCU()) {
					if(cu.getId().equals(curricularUnit)) {
						Requirement req = cu.getRequirement();
						if (req == null) {
							response.getWriter().append("true");
							return;
						}
						response.getWriter().append(evaluateRequirements(estudiante, req).toString());
						return;
					}
				}
			}
		}
	}

}
