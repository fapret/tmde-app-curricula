/**
 */
package Estudiantes.tests;

import Estudiantes.EstudiantesFactory;
import Estudiantes.PlanInscription;

import junit.framework.TestCase;

import junit.textui.TestRunner;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Plan Inscription</b></em>'.
 * <!-- end-user-doc -->
 * @generated
 */
public class PlanInscriptionTest extends TestCase {

	/**
	 * The fixture for this Plan Inscription test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PlanInscription fixture = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(PlanInscriptionTest.class);
	}

	/**
	 * Constructs a new Plan Inscription test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PlanInscriptionTest(String name) {
		super(name);
	}

	/**
	 * Sets the fixture for this Plan Inscription test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void setFixture(PlanInscription fixture) {
		this.fixture = fixture;
	}

	/**
	 * Returns the fixture for this Plan Inscription test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PlanInscription getFixture() {
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
		setFixture(EstudiantesFactory.eINSTANCE.createPlanInscription());
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

} //PlanInscriptionTest
