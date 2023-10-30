/**
 */
package Estudiantes;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Student</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link Estudiantes.Student#getId <em>Id</em>}</li>
 *   <li>{@link Estudiantes.Student#getName <em>Name</em>}</li>
 *   <li>{@link Estudiantes.Student#getStudentPlanInscription <em>Student Plan Inscription</em>}</li>
 * </ul>
 *
 * @see Estudiantes.EstudiantesPackage#getStudent()
 * @model
 * @generated
 */
public interface Student extends EObject {
	/**
	 * Returns the value of the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Id</em>' attribute.
	 * @see #setId(String)
	 * @see Estudiantes.EstudiantesPackage#getStudent_Id()
	 * @model id="true" required="true"
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link Estudiantes.Student#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see Estudiantes.EstudiantesPackage#getStudent_Name()
	 * @model required="true"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link Estudiantes.Student#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Student Plan Inscription</b></em>' containment reference list.
	 * The list contents are of type {@link Estudiantes.PlanInscription}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Student Plan Inscription</em>' containment reference list.
	 * @see Estudiantes.EstudiantesPackage#getStudent_StudentPlanInscription()
	 * @model containment="true"
	 * @generated
	 */
	EList<PlanInscription> getStudentPlanInscription();

} // Student
