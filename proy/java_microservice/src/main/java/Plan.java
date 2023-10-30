

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
		for (Faculty facultad : rootElement.getFaculty()) {
			if(facultad.getName().equals(faculty)) {
				for(Career carrera : facultad.getCareers()) {
					if(carrera.getName().equals(career)) {
						for(asignaturas.Plan p : carrera.getPlan()) {
							if(p.getYear() == plan) {
								if(p instanceof CreditsPlan) {
									//TODO Logica a retornar de plan creditos
									CreditsPlan planCreditos = (CreditsPlan) p;
								}
								if(p instanceof SubjectPlan) {
									//TODO Logica a retornar de plan materias
									SubjectPlan planMaterias = (SubjectPlan) p;
								}
								return;
							}
						}
					}
				}
			}
		}
	}

}
