/**
 */
package asignaturas;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Career</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link asignaturas.Career#getName <em>Name</em>}</li>
 *   <li>{@link asignaturas.Career#getPlan <em>Plan</em>}</li>
 * </ul>
 *
 * @see asignaturas.AsignaturasPackage#getCareer()
 * @model
 * @generated
 */
public interface Career extends EObject {
	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see asignaturas.AsignaturasPackage#getCareer_Name()
	 * @model unique="false" required="true"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link asignaturas.Career#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Plan</b></em>' containment reference list.
	 * The list contents are of type {@link asignaturas.Plan}.
	 * It is bidirectional and its opposite is '{@link asignaturas.Plan#getCareer_parent <em>Career parent</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Plan</em>' containment reference list.
	 * @see asignaturas.AsignaturasPackage#getCareer_Plan()
	 * @see asignaturas.Plan#getCareer_parent
	 * @model opposite="career_parent" containment="true"
	 * @generated
	 */
	EList<Plan> getPlan();

} // Career
