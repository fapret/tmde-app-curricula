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
