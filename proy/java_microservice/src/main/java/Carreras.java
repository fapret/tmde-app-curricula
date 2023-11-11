

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.eclipse.emf.ecore.*;

import asignaturas.Career;
import asignaturas.CreditsPlan;
import asignaturas.Faculty;
import asignaturas.Root;
import asignaturas.SubjectPlan;

/**
 * Servlet implementation class Carreras
 */
public class Carreras extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Carreras() {
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
		String responseText = "";
		for (Faculty facultad : rootElement.getFaculty()) {
			if(facultad.getName().equals(faculty)) {
				for(Career carrera : facultad.getCareers()) {
					if(carrera.getName().equals(career)) {
						responseText += "{ \"Name\": \"" + carrera.getName() + "\", \"Plans\": [";
						Boolean auxBool = false;
						for(asignaturas.Plan plan : carrera.getPlan()) {
							auxBool = true;
							responseText += plan.getYear() + ",";
						}
						if(auxBool) {
							responseText = responseText.substring(0, responseText.lastIndexOf(','));
						}
						responseText += "]}";
						response.getWriter().append(responseText);
						return;
					}
				}
			}
		}
	}

}
