/**
 */
package Estudiantes;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Root</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link Estudiantes.Root#getStudent <em>Student</em>}</li>
 * </ul>
 *
 * @see Estudiantes.EstudiantesPackage#getRoot()
 * @model
 * @generated
 */
public interface Root extends EObject {
	/**
	 * Returns the value of the '<em><b>Student</b></em>' containment reference list.
	 * The list contents are of type {@link Estudiantes.Student}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Student</em>' containment reference list.
	 * @see Estudiantes.EstudiantesPackage#getRoot_Student()
	 * @model containment="true" required="true"
	 * @generated
	 */
	EList<Student> getStudent();

} // Root
