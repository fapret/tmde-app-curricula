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
		EPackage.Registry.INSTANCE.put(Estudiantes.EstudiantesPackage.eNS_URI, Estudiantes.EstudiantesPackage.eINSTANCE);
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
	
	public ResourceSet getResourceSet() {
		return resourceSet;
	}
}
