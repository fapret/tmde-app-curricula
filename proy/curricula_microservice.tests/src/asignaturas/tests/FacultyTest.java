/**
 */
package asignaturas.tests;

import asignaturas.AsignaturasFactory;
import asignaturas.Faculty;

import junit.framework.TestCase;

import junit.textui.TestRunner;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Faculty</b></em>'.
 * <!-- end-user-doc -->
 * @generated
 */
public class FacultyTest extends TestCase {

	/**
	 * The fixture for this Faculty test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected Faculty fixture = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(FacultyTest.class);
	}

	/**
	 * Constructs a new Faculty test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FacultyTest(String name) {
		super(name);
	}

	/**
	 * Sets the fixture for this Faculty test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void setFixture(Faculty fixture) {
		this.fixture = fixture;
	}

	/**
	 * Returns the fixture for this Faculty test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected Faculty getFixture() {
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
		setFixture(AsignaturasFactory.eINSTANCE.createFaculty());
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

} //FacultyTest
