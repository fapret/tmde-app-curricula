/**
 */
package asignaturas;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Some Of</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link asignaturas.SomeOf#getN <em>N</em>}</li>
 *   <li>{@link asignaturas.SomeOf#getRequirement <em>Requirement</em>}</li>
 * </ul>
 *
 * @see asignaturas.AsignaturasPackage#getSomeOf()
 * @model
 * @generated
 */
public interface SomeOf extends Requirement {
	/**
	 * Returns the value of the '<em><b>N</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>N</em>' attribute.
	 * @see #setN(int)
	 * @see asignaturas.AsignaturasPackage#getSomeOf_N()
	 * @model required="true"
	 * @generated
	 */
	int getN();

	/**
	 * Sets the value of the '{@link asignaturas.SomeOf#getN <em>N</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>N</em>' attribute.
	 * @see #getN()
	 * @generated
	 */
	void setN(int value);

	/**
	 * Returns the value of the '<em><b>Requirement</b></em>' containment reference list.
	 * The list contents are of type {@link asignaturas.Requirement}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Requirement</em>' containment reference list.
	 * @see asignaturas.AsignaturasPackage#getSomeOf_Requirement()
	 * @model containment="true" required="true"
	 * @generated
	 */
	EList<Requirement> getRequirement();

} // SomeOf
