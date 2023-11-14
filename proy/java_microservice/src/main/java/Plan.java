

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import asignaturas.*;

/**
 * Servlet implementation class Plan
 */
public class Plan extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Plan() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		modelSingleton model = modelSingleton.getInstance();
		Root rootElement = model.getRootElement();
		String faculty = request.getParameter("faculty");
		String career = request.getParameter("career");
		int plan = Integer.parseInt(request.getParameter("plan"));
		String responseText = "";
		for (Faculty facultad : rootElement.getFaculty()) {
			if(facultad.getName().equals(faculty)) {
				for(Career carrera : facultad.getCareers()) {
					if(carrera.getName().equals(career)) {
						for(asignaturas.Plan p : carrera.getPlan()) {
							if(p.getYear() == plan) {
								if(p instanceof CreditsPlan) {
									//TODO Logica a retornar de plan creditos
									CreditsPlan planCreditos = (CreditsPlan) p;
									responseText += "{\"Type\": \"CreditsPlan\", \"MinCredits\": "+String.valueOf(planCreditos.getMinCredits())+", \"Year\": "+String.valueOf(p.getYear())+", \"Career\": "+carrera.getName();
									responseText += ", \"Subjects\": [";
									for (asignaturas.Subject s : planCreditos.getGroupOfSubjects()) {
										responseText += String.valueOf(s.getId())+", ";
										for (asignaturas.Subject s2 : s.getGroupOfSubjects())
											responseText += String.valueOf(s2.getId())+", ";
									}

									responseText = responseText.substring(0, responseText.length()-2);
									response.getWriter().append(responseText + "]}");
									return;
								}
								if(p instanceof SubjectPlan) {
									//TODO Logica a retornar de plan materias
									SubjectPlan planMaterias = (SubjectPlan) p;
									responseText += "{\"Type\": \"SubjectPlan\""+", \"Year\": "+String.valueOf(p.getYear())+", \"Career\": "+carrera.getName();
									responseText += ", \"Curricular Units\": [";
									for (asignaturas.CurricularUnit cu : planMaterias.getCurricularUnit())
										responseText += String.valueOf(cu.getId())+", ";
									
									responseText = responseText.substring(0, responseText.length()-2);
									response.getWriter().append(responseText + "]}");

									response.getWriter().append("}");
									return;
								}
							}
						}
					}
				}
			}
		}
		
	}

}
