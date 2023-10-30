

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.*;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.*;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

import Estudiantes.*;
import asignaturas.*;

/**
 * Servlet implementation class EvalUC
 */
public class EvalUC extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EvalUC() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		modelSingleton model = modelSingleton.getInstance();
		asignaturas.Root rootElement = model.getRootElement();
		String faculty = request.getParameter("faculty");
		String curricularUnit = request.getParameter("curricularUnit");
		
		ResourceSet resourceSet = new ResourceSetImpl();
		Resource resource = resourceSet.createResource(URI.createURI("tempStudent.xmi"));
		Estudiantes.Root rootObject;
		try {
			ServletInputStream inputStream = request.getInputStream();
			resource.load(inputStream, null);
			
			rootObject = (Estudiantes.Root) resource.getContents().get(0);
		} catch (IOException e) {
			response.getWriter().write("Error at reading student model");
			return;
		}
		
		Student estudiante = rootObject.getStudent().get(0);
		//TODO
		
		for (Faculty facultad : rootElement.getFaculty()) {
			if(facultad.getName().equals(faculty)) {
				for(CurricularUnit cu : facultad.getFacultyCU()) {
					if(cu.getId().equals(curricularUnit)) {
						Requirement r = cu.getRequirement();
						//TODO
					}
				}
			}
		}
	}

}
