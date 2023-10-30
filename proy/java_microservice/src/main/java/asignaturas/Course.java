/**
 */
package asignaturas;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Course</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link asignaturas.Course#getYear <em>Year</em>}</li>
 *   <li>{@link asignaturas.Course#getEdition <em>Edition</em>}</li>
 *   <li>{@link asignaturas.Course#getCourseEvaluation <em>Course Evaluation</em>}</li>
 * </ul>
 *
 * @see asignaturas.AsignaturasPackage#getCourse()
 * @model
 * @generated
 */
public interface Course extends EObject {
	/**
	 * Returns the value of the '<em><b>Year</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Year</em>' attribute.
	 * @see #setYear(int)
	 * @see asignaturas.AsignaturasPackage#getCourse_Year()
	 * @model
	 * @generated
	 */
	int getYear();

	/**
	 * Sets the value of the '{@link asignaturas.Course#getYear <em>Year</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Year</em>' attribute.
	 * @see #getYear()
	 * @generated
	 */
	void setYear(int value);

	/**
	 * Returns the value of the '<em><b>Edition</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Edition</em>' attribute.
	 * @see #setEdition(int)
	 * @see asignaturas.AsignaturasPackage#getCourse_Edition()
	 * @model
	 * @generated
	 */
	int getEdition();

	/**
	 * Sets the value of the '{@link asignaturas.Course#getEdition <em>Edition</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Edition</em>' attribute.
	 * @see #getEdition()
	 * @generated
	 */
	void setEdition(int value);

	/**
	 * Returns the value of the '<em><b>Course Evaluation</b></em>' containment reference list.
	 * The list contents are of type {@link asignaturas.CourseEvaluation}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Course Evaluation</em>' containment reference list.
	 * @see asignaturas.AsignaturasPackage#getCourse_CourseEvaluation()
	 * @model containment="true"
	 * @generated
	 */
	EList<CourseEvaluation> getCourseEvaluation();

} // Course
