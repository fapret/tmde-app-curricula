/**
 */
package asignaturas;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Subject</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link asignaturas.Subject#getId <em>Id</em>}</li>
 *   <li>{@link asignaturas.Subject#getName <em>Name</em>}</li>
 *   <li>{@link asignaturas.Subject#getMinCredits <em>Min Credits</em>}</li>
 *   <li>{@link asignaturas.Subject#getGroupOfSubjects <em>Group Of Subjects</em>}</li>
 *   <li>{@link asignaturas.Subject#getGroupOfSubjectsOpposite <em>Group Of Subjects Opposite</em>}</li>
 *   <li>{@link asignaturas.Subject#getSubjectCurricularUnit <em>Subject Curricular Unit</em>}</li>
 * </ul>
 *
 * @see asignaturas.AsignaturasPackage#getSubject()
 * @model
 * @generated
 */
public interface Subject extends EObject {
	/**
	 * Returns the value of the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Id</em>' attribute.
	 * @see #setId(int)
	 * @see asignaturas.AsignaturasPackage#getSubject_Id()
	 * @model id="true" required="true"
	 * @generated
	 */
	int getId();

	/**
	 * Sets the value of the '{@link asignaturas.Subject#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(int value);

	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see asignaturas.AsignaturasPackage#getSubject_Name()
	 * @model required="true"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link asignaturas.Subject#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Min Credits</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Min Credits</em>' attribute.
	 * @see #setMinCredits(int)
	 * @see asignaturas.AsignaturasPackage#getSubject_MinCredits()
	 * @model
	 * @generated
	 */
	int getMinCredits();

	/**
	 * Sets the value of the '{@link asignaturas.Subject#getMinCredits <em>Min Credits</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Min Credits</em>' attribute.
	 * @see #getMinCredits()
	 * @generated
	 */
	void setMinCredits(int value);

	/**
	 * Returns the value of the '<em><b>Group Of Subjects</b></em>' containment reference list.
	 * The list contents are of type {@link asignaturas.Subject}.
	 * It is bidirectional and its opposite is '{@link asignaturas.Subject#getGroupOfSubjectsOpposite <em>Group Of Subjects Opposite</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Group Of Subjects</em>' containment reference list.
	 * @see asignaturas.AsignaturasPackage#getSubject_GroupOfSubjects()
	 * @see asignaturas.Subject#getGroupOfSubjectsOpposite
	 * @model opposite="GroupOfSubjectsOpposite" containment="true"
	 * @generated
	 */
	EList<Subject> getGroupOfSubjects();

	/**
	 * Returns the value of the '<em><b>Group Of Subjects Opposite</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link asignaturas.Subject#getGroupOfSubjects <em>Group Of Subjects</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Group Of Subjects Opposite</em>' container reference.
	 * @see #setGroupOfSubjectsOpposite(Subject)
	 * @see asignaturas.AsignaturasPackage#getSubject_GroupOfSubjectsOpposite()
	 * @see asignaturas.Subject#getGroupOfSubjects
	 * @model opposite="GroupOfSubjects" transient="false"
	 * @generated
	 */
	Subject getGroupOfSubjectsOpposite();

	/**
	 * Sets the value of the '{@link asignaturas.Subject#getGroupOfSubjectsOpposite <em>Group Of Subjects Opposite</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Group Of Subjects Opposite</em>' container reference.
	 * @see #getGroupOfSubjectsOpposite()
	 * @generated
	 */
	void setGroupOfSubjectsOpposite(Subject value);

	/**
	 * Returns the value of the '<em><b>Subject Curricular Unit</b></em>' reference list.
	 * The list contents are of type {@link asignaturas.CurricularUnit}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Subject Curricular Unit</em>' reference list.
	 * @see asignaturas.AsignaturasPackage#getSubject_SubjectCurricularUnit()
	 * @model
	 * @generated
	 */
	EList<CurricularUnit> getSubjectCurricularUnit();

} // Subject
