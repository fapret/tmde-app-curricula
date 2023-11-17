/**
 */
package Estudiantes.tests;

import Estudiantes.EstudiantesFactory;
import Estudiantes.StudentEvaluation;

import junit.framework.TestCase;

import junit.textui.TestRunner;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Student Evaluation</b></em>'.
 * <!-- end-user-doc -->
 * @generated
 */
public class StudentEvaluationTest extends TestCase {

	/**
	 * The fixture for this Student Evaluation test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected StudentEvaluation fixture = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(StudentEvaluationTest.class);
	}

	/**
	 * Constructs a new Student Evaluation test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StudentEvaluationTest(String name) {
		super(name);
	}

	/**
	 * Sets the fixture for this Student Evaluation test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void setFixture(StudentEvaluation fixture) {
		this.fixture = fixture;
	}

	/**
	 * Returns the fixture for this Student Evaluation test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected StudentEvaluation getFixture() {
		return fixture;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see junit.framework.TestCase#setUp()
	 * @generated
	 */
	@Override
	protected void setUp() throws Exception {
		setFixture(EstudiantesFactory.eINSTANCE.createStudentEvaluation());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see junit.framework.TestCase#tearDown()
	 * @generated
	 */
	@Override
	protected void tearDown() throws Exception {
		setFixture(null);
	}

} //StudentEvaluationTest
