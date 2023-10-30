import asignaturas.*;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.*;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.*;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

public class modelSingleton {
	private static modelSingleton instance;
	private Root rootElement;
	private ResourceSet resourceSet;
	
	private modelSingleton() {
		resourceSet = new ResourceSetImpl();
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("xmi", new XMIResourceFactoryImpl());
		EPackage.Registry.INSTANCE.put(asignaturas.AsignaturasPackage.eNS_URI, asignaturas.AsignaturasPackage.eINSTANCE);
		URI modelURI = URI.createFileURI("model.xmi");
		Resource resource = resourceSet.getResource(modelURI, true);
		
		if(!resource.getContents().isEmpty()) {
			rootElement = (Root) resource.getContents().get(0);
		}
	}
	
	public static modelSingleton getInstance() {
		if(instance == null) {
			instance = new modelSingleton();
		}
		return instance;
	}
	
	public Root getRootElement() {
		return rootElement;
	}
}
