/**
 */
package Estudiantes.tests;

import Estudiantes.CourseInscription;
import Estudiantes.EstudiantesFactory;

import junit.framework.TestCase;

import junit.textui.TestRunner;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Course Inscription</b></em>'.
 * <!-- end-user-doc -->
 * @generated
 */
public class CourseInscriptionTest extends TestCase {

	/**
	 * The fixture for this Course Inscription test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CourseInscription fixture = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(CourseInscriptionTest.class);
	}

	/**
	 * Constructs a new Course Inscription test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CourseInscriptionTest(String name) {
		super(name);
	}

	/**
	 * Sets the fixture for this Course Inscription test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void setFixture(CourseInscription fixture) {
		this.fixture = fixture;
	}

	/**
	 * Returns the fixture for this Course Inscription test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CourseInscription getFixture() {
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
		setFixture(EstudiantesFactory.eINSTANCE.createCourseInscription());
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

} //CourseInscriptionTest
