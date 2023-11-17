/**
 */
package Estudiantes;

import asignaturas.Course;

import java.util.Date;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Course Inscription</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link Estudiantes.CourseInscription#getCUCourse <em>CU Course</em>}</li>
 *   <li>{@link Estudiantes.CourseInscription#getDate <em>Date</em>}</li>
 * </ul>
 *
 * @see Estudiantes.EstudiantesPackage#getCourseInscription()
 * @model
 * @generated
 */
public interface CourseInscription extends EObject {
	/**
	 * Returns the value of the '<em><b>CU Course</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>CU Course</em>' reference.
	 * @see #setCUCourse(Course)
	 * @see Estudiantes.EstudiantesPackage#getCourseInscription_CUCourse()
	 * @model required="true"
	 * @generated
	 */
	Course getCUCourse();

	/**
	 * Sets the value of the '{@link Estudiantes.CourseInscription#getCUCourse <em>CU Course</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>CU Course</em>' reference.
	 * @see #getCUCourse()
	 * @generated
	 */
	void setCUCourse(Course value);

	/**
	 * Returns the value of the '<em><b>Date</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Date</em>' attribute.
	 * @see #setDate(Date)
	 * @see Estudiantes.EstudiantesPackage#getCourseInscription_Date()
	 * @model required="true"
	 * @generated
	 */
	Date getDate();

	/**
	 * Sets the value of the '{@link Estudiantes.CourseInscription#getDate <em>Date</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Date</em>' attribute.
	 * @see #getDate()
	 * @generated
	 */
	void setDate(Date value);

} // CourseInscription
