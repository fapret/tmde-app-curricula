import jakarta.servlet.ServletException;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.*;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.*;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

import Estudiantes.*;
import asignaturas.*;
import asignaturas.Root;

/**
 * Servlet implementation class EvalUC
 */
@MultipartConfig
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
		// Carga modelo
		Root asignaturasRoot;
		ResourceSet resourceSet;
		resourceSet = new ResourceSetImpl();
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("xmi", new XMIResourceFactoryImpl());
		EPackage.Registry.INSTANCE.put(asignaturas.AsignaturasPackage.eNS_URI, asignaturas.AsignaturasPackage.eINSTANCE);
		EPackage.Registry.INSTANCE.put(Estudiantes.EstudiantesPackage.eNS_URI, Estudiantes.EstudiantesPackage.eINSTANCE);
		URI modelURI = URI.createFileURI("model.xmi");
		Resource resource = resourceSet.getResource(modelURI, true);
		asignaturasRoot = (Root) resource.getContents().get(0);
		
		String faculty = request.getParameter("faculty");
		String curricularUnit = request.getParameter("curricularUnit");
		Estudiantes.Root rootStudent;
		Part filePart;
		InputStream inputStream;
		
		
		try {
			filePart = request.getPart("file");
			inputStream = filePart.getInputStream();
			Resource resourceStudent = resourceSet.createResource(URI.createURI(Paths.get(filePart.getSubmittedFileName()).getFileName().toString()));
		
			resourceStudent.load(inputStream, null);
			
			rootStudent = (Estudiantes.Root) resourceStudent.getContents().get(0);
		} catch (IOException e) {
			response.getWriter().write(e.toString());
			return;
		}
		
		//Test
		Student estudiante = rootStudent.getStudent().get(0);
		response.getWriter().append("Estudiante: " + estudiante.getName() + "\n");
		PlanInscription i = estudiante.getStudentPlanInscription().get(0);
		response.getWriter().append("PlanInscription Date: " + i.getDate().toString() + "\n");
		CourseInscription c = i.getPlanCourseInscription().get(0);
		response.getWriter().append("CourseInscription Date: " + c.getDate().toString() + "\n");
		Course a = c.getCUCourse();
		response.getWriter().append("CourseYear: " + Integer.toString(a.getYear()) + "\n");
		CurricularUnit uc = a.getCurricularunit();
		response.getWriter().append("CourseUC: " + uc.getId() + "\n");
		
		
		//TODO respuesta
		for (Faculty facultad : asignaturasRoot.getFaculty()) {
			if(facultad.getName().equals(faculty)) {
				for(CurricularUnit cu : facultad.getFacultyCU()) {
					if(cu.getId().equals(curricularUnit)) {
						Requirement req = cu.getRequirement();
						//TODO
					}
				}
			}
		}
	}

}
