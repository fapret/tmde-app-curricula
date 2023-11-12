

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

import Estudiantes.PlanInscription;
import Estudiantes.Student;
import asignaturas.Career;
import asignaturas.Faculty;

/**
 * Servlet implementation class EstudianteAddPlan
 */
public class EstudianteAddPlan extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EstudianteAddPlan() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		asignaturas.Root asignaturasRoot;
		ResourceSet resourceSet;
		resourceSet = new ResourceSetImpl();
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("xmi", new XMIResourceFactoryImpl());
		EPackage.Registry.INSTANCE.put(asignaturas.AsignaturasPackage.eNS_URI, asignaturas.AsignaturasPackage.eINSTANCE);
		EPackage.Registry.INSTANCE.put(Estudiantes.EstudiantesPackage.eNS_URI, Estudiantes.EstudiantesPackage.eINSTANCE);
		URI modelURI = URI.createFileURI("model.xmi");
		Resource resource = resourceSet.getResource(modelURI, true);
		asignaturasRoot = (asignaturas.Root) resource.getContents().get(0);
		
		String faculty = request.getParameter("faculty");
		String career = request.getParameter("career");
		String plan = request.getParameter("plan");
		String date = request.getParameter("date");
		Estudiantes.Root rootStudent;
		Part filePart;
		InputStream inputStream;
		Resource resourceStudent = null;
		
		try {
			filePart = request.getPart("file");
			inputStream = filePart.getInputStream();
			resourceStudent = resourceSet.createResource(URI.createURI(Paths.get(filePart.getSubmittedFileName()).getFileName().toString()));
		
			resourceStudent.load(inputStream, null);
			
			rootStudent = (Estudiantes.Root) resourceStudent.getContents().get(0);
		} catch (IOException e) {
			response.getWriter().write(e.toString());
			return;
		}
		
		Student estudiante = rootStudent.getStudent().get(0);
		for(PlanInscription planInsc : estudiante.getStudentPlanInscription()) {
			if(planInsc.getPlan().getCareer_parent().getName().equals(career) && Integer.toString(planInsc.getPlan().getYear()).equals(plan)) {
				response.getWriter().append("El estudiante ya esta inscripto en el plan");
				return;
			}
		}
		for(Faculty facultad : asignaturasRoot.getFaculty()) {
			if(facultad.getName().equals(faculty)) {
				for (Career carrera : facultad.getCareers()) {
					if (carrera.getName().equals(career)) {
						for (asignaturas.Plan p : carrera.getPlan()) {
							if(Integer.toString(p.getYear()).equals(plan)) {
								PlanInscription pi = Estudiantes.EstudiantesFactory.eINSTANCE.createPlanInscription();
								pi.setPlan(p);
								SimpleDateFormat dateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");
								Date selectedDate = null;
								try {
									selectedDate = dateFormat.parse(date);
								} catch (ParseException e) {
									response.getWriter().write(e.toString());
									return;
								}
								pi.setDate(selectedDate);
								estudiante.getStudentPlanInscription().add(pi);
								
								Map<String, Object> saveOptions = new HashMap<>();
								saveOptions.put(XMLResource.OPTION_SCHEMA_LOCATION, true);
								response.setContentType("application/xml");
								response.setHeader("Content-Disposition", "attachment;filename=model.xmi");
								
								//resourceStudent
								try {
									resourceStudent.save(response.getOutputStream(), saveOptions);
								} catch (Exception e) {
									response.getWriter().write(e.toString());
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
