/**
 */
package asignaturas.tests;

import asignaturas.AsignaturasFactory;
import asignaturas.CreditsOnPlan;

import junit.textui.TestRunner;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Credits On Plan</b></em>'.
 * <!-- end-user-doc -->
 * @generated
 */
public class CreditsOnPlanTest extends RequirementTest {

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(CreditsOnPlanTest.class);
	}

	/**
	 * Constructs a new Credits On Plan test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CreditsOnPlanTest(String name) {
		super(name);
	}

	/**
	 * Returns the fixture for this Credits On Plan test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected CreditsOnPlan getFixture() {
		return (CreditsOnPlan)fixture;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see junit.framework.TestCase#setUp()
	 * @generated
	 */
	@Override
	protected void setUp() throws Exception {
		setFixture(AsignaturasFactory.eINSTANCE.createCreditsOnPlan());
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

} //CreditsOnPlanTest
