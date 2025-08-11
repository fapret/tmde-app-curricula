/**
 */
package Estudiantes;

import asignaturas.Plan;

import java.util.Date;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Plan Inscription</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link Estudiantes.PlanInscription#getDate <em>Date</em>}</li>
 *   <li>{@link Estudiantes.PlanInscription#getPlan <em>Plan</em>}</li>
 *   <li>{@link Estudiantes.PlanInscription#getPlanCourseInscription <em>Plan Course Inscription</em>}</li>
 *   <li>{@link Estudiantes.PlanInscription#getPlanStudentEvaluation <em>Plan Student Evaluation</em>}</li>
 *   <li>{@link Estudiantes.PlanInscription#getDegree <em>Degree</em>}</li>
 * </ul>
 *
 * @see Estudiantes.EstudiantesPackage#getPlanInscription()
 * @model
 * @generated
 */
public interface PlanInscription extends EObject {
	/**
	 * Returns the value of the '<em><b>Date</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Date</em>' attribute.
	 * @see #setDate(Date)
	 * @see Estudiantes.EstudiantesPackage#getPlanInscription_Date()
	 * @model
	 * @generated
	 */
	Date getDate();

	/**
	 * Sets the value of the '{@link Estudiantes.PlanInscription#getDate <em>Date</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Date</em>' attribute.
	 * @see #getDate()
	 * @generated
	 */
	void setDate(Date value);

	/**
	 * Returns the value of the '<em><b>Plan</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Plan</em>' reference.
	 * @see #setPlan(Plan)
	 * @see Estudiantes.EstudiantesPackage#getPlanInscription_Plan()
	 * @model required="true"
	 * @generated
	 */
	Plan getPlan();

	/**
	 * Sets the value of the '{@link Estudiantes.PlanInscription#getPlan <em>Plan</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Plan</em>' reference.
	 * @see #getPlan()
	 * @generated
	 */
	void setPlan(Plan value);

	/**
	 * Returns the value of the '<em><b>Plan Course Inscription</b></em>' containment reference list.
	 * The list contents are of type {@link Estudiantes.CourseInscription}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Plan Course Inscription</em>' containment reference list.
	 * @see Estudiantes.EstudiantesPackage#getPlanInscription_PlanCourseInscription()
	 * @model containment="true"
	 * @generated
	 */
	EList<CourseInscription> getPlanCourseInscription();

	/**
	 * Returns the value of the '<em><b>Plan Student Evaluation</b></em>' containment reference list.
	 * The list contents are of type {@link Estudiantes.StudentEvaluation}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Plan Student Evaluation</em>' containment reference list.
	 * @see Estudiantes.EstudiantesPackage#getPlanInscription_PlanStudentEvaluation()
	 * @model containment="true"
	 * @generated
	 */
	EList<StudentEvaluation> getPlanStudentEvaluation();

	/**
	 * Returns the value of the '<em><b>Degree</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Degree</em>' containment reference.
	 * @see #setDegree(Degree)
	 * @see Estudiantes.EstudiantesPackage#getPlanInscription_Degree()
	 * @model containment="true"
	 * @generated
	 */
	Degree getDegree();

	/**
	 * Sets the value of the '{@link Estudiantes.PlanInscription#getDegree <em>Degree</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Degree</em>' containment reference.
	 * @see #getDegree()
	 * @generated
	 */
	void setDegree(Degree value);

} // PlanInscription
