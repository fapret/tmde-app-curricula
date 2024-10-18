

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
public class EvalPlan extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EvalPlan() {
        super();
        // TODO Auto-generated constructor stub
    }

    private List<String> getUC(Student e, Subject s){
    	List<String> uc = new ArrayList<>();
    	for(CurricularUnit cu : s.getSubjectCurricularUnit()) {
    		if(evaluateRequirements(e, cu.getRequirement())) {
    			uc.add(cu.getId());
    		}
    	}
    	for(Subject s2 : s.getGroupOfSubjects()) {
    		uc.addAll(getUC(e, s2));
    	}
    	return uc;
    }
    
    private List<String> get_valid_UC(Student e, Subject s){
    	List<String> uc = new ArrayList<>();
    	for(CurricularUnit cu : s.getSubjectCurricularUnit()) {
    		if (cu.isValid()) {
    			if(evaluateRequirements(e, cu.getRequirement())) {
    				uc.add(cu.getId());
    			}
    		}
    	}
    	for(Subject s2 : s.getGroupOfSubjects()) {
    		uc.addAll(get_valid_UC(e, s2));
    	}
    	return uc;
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
    				} else if (eval.getEvaluation() instanceof ExamEvaluation && eval.getGrade() >= 3) {
    					ExamEvaluation c = (ExamEvaluation) eval.getEvaluation();
    					if(c.getCurricularunit().getId().equals(req.getCurricularUnit().getId())) {
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
    				
    				//un curso con nota más de 6 también cuenta como examen
    				if(eval.getEvaluation() instanceof CourseEvaluation && eval.getGrade() >= 6) {
    					CourseEvaluation c = (CourseEvaluation) eval.getEvaluation();
    					if(c.getCourse().getCurricularunit().getId().equals(req.getCurricularUnit().getId())) {
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
    		
    		if(numOfTrue >= req.getN()) 
    			return true;
    		 else 
    			return false;
    		
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
		boolean valid_flag = false;
		if (request.getParameter("valid_flag") != null)
			valid_flag = true;
		
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
		
		List<String> uc = new ArrayList<>();
		for (Faculty facultad : asignaturasRoot.getFaculty()) {
			if(facultad.getName().equals(faculty)) {
				for (Career carrera : facultad.getCareers()) {
					if (carrera.getName().equals(career)) {
						for (asignaturas.Plan p : carrera.getPlan()) {
							if(Integer.toString(p.getYear()).equals(plan)) {
								if(p instanceof CreditsPlan) {
									CreditsPlan cp = (CreditsPlan) p;
									for(Subject sub : cp.getGroupOfSubjects()) {
										if (valid_flag)
											uc.addAll(get_valid_UC(estudiante, sub));
										else
											uc.addAll(getUC(estudiante, sub));
									}
								} else { //Es plan de materias
									SubjectPlan sp = (SubjectPlan) p;
									for(CurricularUnit cu : sp.getCurricularUnit()) {
										if (valid_flag) {
											if(cu.isValid() && evaluateRequirements(estudiante, cu.getRequirement())) {
												uc.add(cu.getId());
											}
										}
										else {
											if(evaluateRequirements(estudiante, cu.getRequirement())) {
												uc.add(cu.getId());
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
		for (int i = 0; i < uc.size(); i++) 
			uc.set(i, "\""+uc.get(i)+"\"");
		
		response.getWriter().append(uc.toString());
	}

}
