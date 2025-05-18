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
*/

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Iterator;
import org.eclipse.emf.ecore.*;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.*;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.*;
import asignaturas.*;

/**
 * Servlet implementation class UnidadCurricular
 */
public class UnidadCurricular extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UnidadCurricular() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    private String getRequeriment(Requirement requerimiento) {
    	if(requerimiento instanceof NOT) {
    		NOT req = (NOT) requerimiento;
    		return "{ \"NOT\": " + getRequeriment(req.getRequirement()) + "}";
    	}
    	if(requerimiento instanceof Coursed) {
    		Coursed req = (Coursed) requerimiento;
    		return "{ \"Coursed\": \"" + req.getCurricularUnit().getId() + "\"}";
    	}
    	if(requerimiento instanceof Exam) {
    		Exam req = (Exam) requerimiento;
    		return "{ \"Exam\": \"" + req.getCurricularUnit().getId() + "\"}";
    	}
    	if(requerimiento instanceof RegisteredTo) {
    		RegisteredTo req = (RegisteredTo) requerimiento;
    		return "{ \"RegisteredTo\": \"" + req.getCurricularUnit().getId() + "\"}";
    	}
    	if(requerimiento instanceof SomeOf) {
    		SomeOf req = (SomeOf) requerimiento;
    		String reqs = "";
    		for(Requirement requer : req.getRequirement()) {
    			reqs += getRequeriment(requer) + ",";
    		}
    		reqs = reqs.substring(0, reqs.lastIndexOf(','));
    		return "{ \"SomeOf\": ["+String.valueOf(req.getN())+", "+ reqs + "]}";
    	}
    	if(requerimiento instanceof CreditsOnPlan) {
    		CreditsOnPlan req = (CreditsOnPlan) requerimiento;
    		return "{ \"CreditsOnPlan\": \"" + req.getCreditsPlan().getYear() + "," + req.getCred() + "\"}";
    	}
    	if(requerimiento instanceof CreditsOnSubject) {
    		CreditsOnSubject req = (CreditsOnSubject) requerimiento;
    		return "{ \"CreditsOnSubject\": \"" + req.getGroupOfSubjects().getId() + "," + req.getCred() + "\"}";
    	}
    	return "";
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		modelSingleton model = modelSingleton.getInstance();
		Root rootElement = model.getRootElement();
		String faculty = request.getParameter("faculty");
		String curricularUnit = request.getParameter("curricularUnit");
		String responseText = "";
		
		if(curricularUnit == null || curricularUnit.isBlank()) {
			for (Faculty facultad : rootElement.getFaculty()) {
				if(facultad.getName().equals(faculty)) {
					response.getWriter().append("[");
					Boolean auxBool = false;
					for(CurricularUnit cu : facultad.getFacultyCU()) {
						responseText += "\"" + cu.getId() + "\", ";
						auxBool = true;
					}
					if(auxBool) {
						responseText = responseText.substring(0, responseText.lastIndexOf(','));
					}
					response.getWriter().append(responseText).append("]");
					return;
				}
			}
		}
		
		for (Faculty facultad : rootElement.getFaculty()) {
			if(facultad.getName().equals(faculty)) {
				for(CurricularUnit cu : facultad.getFacultyCU()) {
					if(cu.getId().equals(curricularUnit)) {
						responseText = "{ \"Id\": \"" + cu.getId() + "\", \"Name\": \"" + cu.getName() + "\", " + "\"Cred\": " + cu.getCred() + ", ";
						responseText += "\"Requirement\": [" + getRequeriment(cu.getRequirement()) + "], ";
						responseText += "\"ExamEvaluation\": [";
						Boolean auxBool = false;
						for(ExamEvaluation exam : cu.getExamEvaluation()) {
							auxBool = true;
							responseText += "\"" + exam.getDate().toString() + "\",";
						}
						if(auxBool) {
							responseText = responseText.substring(0, responseText.lastIndexOf(','));
						}
						responseText += "], \"Course\": [";
						auxBool = false;
						for(Course curso : cu.getCourse()) {
							auxBool = true;
							responseText += "{ \"Year\": " + curso.getYear() + ", \"Edition\": " + curso.getEdition() + ", \"CourseEvaluation\": [";
							Boolean auxBool2 = false;
							for(CourseEvaluation ce : curso.getCourseEvaluation()) {
								responseText += "\"" + ce.getDate().toString() + "\",";
								auxBool2 = true;
							}
							if(auxBool2) {
								responseText = responseText.substring(0, responseText.lastIndexOf(','));
							}
							responseText += "]},";
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
