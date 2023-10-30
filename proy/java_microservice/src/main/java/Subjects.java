	

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import org.eclipse.emf.common.util.EList;

import asignaturas.Career;
import asignaturas.CreditsPlan;
import asignaturas.Faculty;
import asignaturas.Root;
import asignaturas.Subject;
import asignaturas.SubjectPlan;

/**
 * Servlet implementation class Subjects
 */
public class Subjects extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Subjects() {
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
		String subject = request.getParameter("subject");
		for (Faculty facultad : rootElement.getFaculty()) {
			if(facultad.getName().equals(faculty)) {
				for(Career carrera : facultad.getCareers()) {
					if(carrera.getName().equals(career)) {
						for(asignaturas.Plan p : carrera.getPlan()) {
							if(p instanceof CreditsPlan) {
								if(p.getYear() == plan) {
									//TODO Logica a retornar de subject (buscar subject y imprimirlo)
									CreditsPlan planCreditos = (CreditsPlan) p;
									
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
