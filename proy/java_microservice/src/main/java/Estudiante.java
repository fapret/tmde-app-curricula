

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

import Estudiantes.Root;
import Estudiantes.Student;

/**
 * Servlet implementation class Estudiante
 */
public class Estudiante extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Estudiante() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Carga modelo
		asignaturas.Root asignaturasRoot;
		ResourceSet resourceSet;
		resourceSet = new ResourceSetImpl();
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("xmi", new XMIResourceFactoryImpl());
		EPackage.Registry.INSTANCE.put(asignaturas.AsignaturasPackage.eNS_URI, asignaturas.AsignaturasPackage.eINSTANCE);
		EPackage.Registry.INSTANCE.put(Estudiantes.EstudiantesPackage.eNS_URI, Estudiantes.EstudiantesPackage.eINSTANCE);
		URI modelURI = URI.createFileURI("model.xmi");
		Resource resource = resourceSet.getResource(modelURI, true);
		asignaturasRoot = (asignaturas.Root) resource.getContents().get(0);
		
		String name = request.getParameter("name");
		String id = request.getParameter("id");
		if(name == null || name.isBlank() || id == null || id.isBlank()) {
			response.getWriter().append("Error: name or id is empty");
		}
		Root studentModel = Estudiantes.EstudiantesFactory.eINSTANCE.createRoot();
		Student student = Estudiantes.EstudiantesFactory.eINSTANCE.createStudent();
		student.setName(name);
		student.setId(id);
		studentModel.getStudent().add(student);
		Resource outputResource = resourceSet.createResource(URI.createURI("EstudianteModel"));
		outputResource.getContents().add(studentModel);
		Map<String, Object> saveOptions = new HashMap<>();
		saveOptions.put(XMLResource.OPTION_SCHEMA_LOCATION, true);
		
		response.setContentType("application/xml");
		response.setHeader("Content-Disposition", "attachment;filename=model.xmi");
		
		try {
			outputResource.save(response.getOutputStream(), saveOptions);
		} catch (Exception e) {
			response.getWriter().write(e.toString());
			return;
		}
		
	}

}
