/*
    tmde-app-curricula is a software that helps students build their curricula and
    see what curricular units they can register to, and track how their career was
    or will be.
    Copyright (C) 2023  Santiago Nicolás Díaz Conde, Santiago Freire López

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <https://www.gnu.org/licenses/>.

	Santiago Nicolás Díaz Conde Email: sndc.33@gmail.com and contact@fapret.com
*/

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import org.eclipse.emf.common.util.EList;

import asignaturas.Career;
import asignaturas.CreditsPlan;
import asignaturas.CurricularUnit;
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
			responseText += "], \"CurricularUnits\": [";
			f = false;
			for(CurricularUnit cu : g.getSubjectCurricularUnit()) {
				responseText += "\"" + cu.getId() + "\",";
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
