/**
 */
package Estudiantes;

import asignaturas.Evaluation;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Student Evaluation</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link Estudiantes.StudentEvaluation#getGrade <em>Grade</em>}</li>
 *   <li>{@link Estudiantes.StudentEvaluation#getEvaluation <em>Evaluation</em>}</li>
 * </ul>
 *
 * @see Estudiantes.EstudiantesPackage#getStudentEvaluation()
 * @model
 * @generated
 */
public interface StudentEvaluation extends EObject {
	/**
	 * Returns the value of the '<em><b>Grade</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Grade</em>' attribute.
	 * @see #setGrade(int)
	 * @see Estudiantes.EstudiantesPackage#getStudentEvaluation_Grade()
	 * @model
	 * @generated
	 */
	int getGrade();

	/**
	 * Sets the value of the '{@link Estudiantes.StudentEvaluation#getGrade <em>Grade</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Grade</em>' attribute.
	 * @see #getGrade()
	 * @generated
	 */
	void setGrade(int value);

	/**
	 * Returns the value of the '<em><b>Evaluation</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Evaluation</em>' reference.
	 * @see #setEvaluation(Evaluation)
	 * @see Estudiantes.EstudiantesPackage#getStudentEvaluation_Evaluation()
	 * @model required="true"
	 * @generated
	 */
	Evaluation getEvaluation();

	/**
	 * Sets the value of the '{@link Estudiantes.StudentEvaluation#getEvaluation <em>Evaluation</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Evaluation</em>' reference.
	 * @see #getEvaluation()
	 * @generated
	 */
	void setEvaluation(Evaluation value);

} // StudentEvaluation
