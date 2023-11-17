/**
 */
package asignaturas;

import java.util.Date;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Evaluation</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link asignaturas.Evaluation#getDate <em>Date</em>}</li>
 * </ul>
 *
 * @see asignaturas.AsignaturasPackage#getEvaluation()
 * @model abstract="true"
 * @generated
 */
public interface Evaluation extends EObject {
	/**
	 * Returns the value of the '<em><b>Date</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Date</em>' attribute.
	 * @see #setDate(Date)
	 * @see asignaturas.AsignaturasPackage#getEvaluation_Date()
	 * @model
	 * @generated
	 */
	Date getDate();

	/**
	 * Sets the value of the '{@link asignaturas.Evaluation#getDate <em>Date</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Date</em>' attribute.
	 * @see #getDate()
	 * @generated
	 */
	void setDate(Date value);

} // Evaluation
