/**
 */
package asignaturas;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Subject Plan</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link asignaturas.SubjectPlan#getCurricularUnit <em>Curricular Unit</em>}</li>
 * </ul>
 *
 * @see asignaturas.AsignaturasPackage#getSubjectPlan()
 * @model
 * @generated
 */
public interface SubjectPlan extends Plan {
	/**
	 * Returns the value of the '<em><b>Curricular Unit</b></em>' reference list.
	 * The list contents are of type {@link asignaturas.CurricularUnit}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Curricular Unit</em>' reference list.
	 * @see asignaturas.AsignaturasPackage#getSubjectPlan_CurricularUnit()
	 * @model
	 * @generated
	 */
	EList<CurricularUnit> getCurricularUnit();

} // SubjectPlan
