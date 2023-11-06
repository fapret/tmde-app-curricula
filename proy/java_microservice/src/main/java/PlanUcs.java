

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import asignaturas.Career;
import asignaturas.Faculty;
import asignaturas.Root;


/**
 * Servlet implementation class PlanUcs
 */
public class PlanUcs extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PlanUcs() {
        super();
        // TODO Auto-generated constructor stub
    }

    private String getSubjectUCs(asignaturas.Subject g) {
    	String res = "";
		for (asignaturas.CurricularUnit cu : g.getSubjectCurricularUnit())
			res += "\""+cu.getId()+"\", ";

    	for (asignaturas.Subject g2 : g.getGroupOfSubjects())
    		res += getSubjectUCs(g2);
    	
    	return res;
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// devuelve todas las unidades curriculares de un plan de carrera
		modelSingleton model = modelSingleton.getInstance();
		Root rootElement = model.getRootElement();
		String faculty = request.getParameter("faculty");
		String career = request.getParameter("career");
		String plan = request.getParameter("plan");
		String res = "[";
		for (Faculty facultad : rootElement.getFaculty()) {
			if(facultad.getName().equals(faculty)) {
				for(Career carrera : facultad.getCareers()) {
					if(carrera.getName().equals(career)) {
						for (asignaturas.Plan p : carrera.getPlan()) {
							if (String.valueOf(p.getYear()).equals(plan) && p instanceof asignaturas.CreditsPlan) {
								asignaturas.CreditsPlan pc = (asignaturas.CreditsPlan)(p);
								for (asignaturas.Subject g : pc.getGroupOfSubjects())
									res += getSubjectUCs(g);
							}
							else {
								asignaturas.SubjectPlan ps = (asignaturas.SubjectPlan)(p);
								for (asignaturas.CurricularUnit cu : ps.getCurricularUnit()) {
									res += "\""+cu.getId()+"\", ";
								}
							}
						}
					}
				}
			}
		}
		
		//le saco el último ", "
		res = res.substring(0, res.length()-2);
		response.getWriter().append(res+"]");
					
	}

}
