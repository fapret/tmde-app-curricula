/**
 */
package asignaturas;

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
 *   <li>{@link asignaturas.Root#getFaculty <em>Faculty</em>}</li>
 * </ul>
 *
 * @see asignaturas.AsignaturasPackage#getRoot()
 * @model
 * @generated
 */
public interface Root extends EObject {
	/**
	 * Returns the value of the '<em><b>Faculty</b></em>' containment reference list.
	 * The list contents are of type {@link asignaturas.Faculty}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Faculty</em>' containment reference list.
	 * @see asignaturas.AsignaturasPackage#getRoot_Faculty()
	 * @model containment="true"
	 * @generated
	 */
	EList<Faculty> getFaculty();

} // Root
