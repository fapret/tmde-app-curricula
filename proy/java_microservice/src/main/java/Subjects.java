	

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

    private String getSubject(asignaturas.Subject g) {
    	String out = "\"" + g.getId() + "\",";
    	for(asignaturas.Subject gc : g.getGroupOfSubjects()) {
    		out += getSubject(gc);
    	}
    	return out;
    }
    
    private String getSubject(asignaturas.Subject g, String id) {
		if(g.getId() == Integer.parseInt(id)) {
			String responseText = "";
			responseText = "{ \"Id\": \"" + g.getId() + "\",";
			responseText += "\"Name\": \"" + g.getName() + "\",";
			responseText += "\"MinCredits\": " + g.getMinCredits() + ",";
			responseText += "\"Subjects\": [";
			boolean f = false;
			for(asignaturas.Subject cg : g.getGroupOfSubjects()) {
				responseText += "\"" + cg.getId() + "\",";
				f = true;
			}
			if(f) {
				responseText = responseText.substring(0, responseText.lastIndexOf(','));
			}
			responseText += "]}";
			return responseText;
		}
		for(asignaturas.Subject gc : g.getGroupOfSubjects()) {
			String out = getSubject(gc, id);
			if(out != null) {
				return out;
			}
		}
		return null;
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
									CreditsPlan planCreditos = (CreditsPlan) p;
									if(subject == null || subject.isBlank()) {
										String out = "[";
										for (asignaturas.Subject g : planCreditos.getGroupOfSubjects()) {
											out += getSubject(g);
										}
										if(!out.equals("[")) {
											out = out.substring(0, out.lastIndexOf(','));
										}
										out += "]";
										response.getWriter().append(out);
										return;
									} else {
										for (asignaturas.Subject g : planCreditos.getGroupOfSubjects()) {
											String out = getSubject(g, subject);
											if (out != null) {
												response.getWriter().append(out);
												return;
											}
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
	}

}
