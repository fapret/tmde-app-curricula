

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.eclipse.emf.ecore.*;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.*;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.*;
import asignaturas.*;

/**
 * Servlet implementation class facultad
 */
public class Facultad extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Facultad() {
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
		String responseText = "";
		if(faculty == null || faculty.isBlank()) {
			response.getWriter().append("[");
			Boolean auxBool = false;
			for (Faculty facultad : rootElement.getFaculty()) {
				responseText += "\"" + facultad.getName() + "\", ";
				auxBool = true;
			}
			if(auxBool) {
				responseText = responseText.substring(0, responseText.lastIndexOf(','));
			}
			response.getWriter().append(responseText).append("]");
			return;
		}
		for (Faculty facultad : rootElement.getFaculty()) {
			if(facultad.getName().equals(faculty)) {
				response.getWriter().append("{ \"Name\": \"").append(facultad.getName()).append("\", ").append("\"Careers\": [");
				Boolean auxBool = false;
				for (Career carrera : facultad.getCareers()) {
					responseText += "\"" + carrera.getName() + "\"" + ",";
					auxBool = true;
				}
				if(auxBool) {
					responseText = responseText.substring(0, responseText.lastIndexOf(','));
				}
				response.getWriter().append(responseText);
				responseText = "";
				auxBool = false;
				response.getWriter().append("], \"FacultyCU\": [");
				for (CurricularUnit cu : facultad.getFacultyCU()) {
					responseText += "\"" + cu.getId() + "\"" + ",";
					auxBool = true;
				}
				if(auxBool) {
					responseText = responseText.substring(0, responseText.lastIndexOf(','));
				}
				response.getWriter().append(responseText);
				response.getWriter().append("] }");
				return;
			}
		}
	}

}
