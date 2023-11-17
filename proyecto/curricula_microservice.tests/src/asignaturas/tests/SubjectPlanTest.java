/**
 */
package asignaturas.tests;

import asignaturas.AsignaturasFactory;
import asignaturas.SubjectPlan;

import junit.textui.TestRunner;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Subject Plan</b></em>'.
 * <!-- end-user-doc -->
 * @generated
 */
public class SubjectPlanTest extends PlanTest {

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(SubjectPlanTest.class);
	}

	/**
	 * Constructs a new Subject Plan test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SubjectPlanTest(String name) {
		super(name);
	}

	/**
	 * Returns the fixture for this Subject Plan test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected SubjectPlan getFixture() {
		return (SubjectPlan)fixture;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see junit.framework.TestCase#setUp()
	 * @generated
	 */
	@Override
	protected void setUp() throws Exception {
		setFixture(AsignaturasFactory.eINSTANCE.createSubjectPlan());
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

} //SubjectPlanTest
