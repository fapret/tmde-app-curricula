/**
 */
package asignaturas.tests;

import asignaturas.AsignaturasFactory;
import asignaturas.CreditsPlan;

import junit.textui.TestRunner;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Credits Plan</b></em>'.
 * <!-- end-user-doc -->
 * @generated
 */
public class CreditsPlanTest extends PlanTest {

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(CreditsPlanTest.class);
	}

	/**
	 * Constructs a new Credits Plan test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CreditsPlanTest(String name) {
		super(name);
	}

	/**
	 * Returns the fixture for this Credits Plan test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected CreditsPlan getFixture() {
		return (CreditsPlan)fixture;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see junit.framework.TestCase#setUp()
	 * @generated
	 */
	@Override
	protected void setUp() throws Exception {
		setFixture(AsignaturasFactory.eINSTANCE.createCreditsPlan());
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

} //CreditsPlanTest
