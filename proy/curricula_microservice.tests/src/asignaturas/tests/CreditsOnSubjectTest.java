/**
 */
package asignaturas.tests;

import asignaturas.AsignaturasFactory;
import asignaturas.CreditsOnSubject;

import junit.textui.TestRunner;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Credits On Subject</b></em>'.
 * <!-- end-user-doc -->
 * @generated
 */
public class CreditsOnSubjectTest extends RequirementTest {

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(CreditsOnSubjectTest.class);
	}

	/**
	 * Constructs a new Credits On Subject test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CreditsOnSubjectTest(String name) {
		super(name);
	}

	/**
	 * Returns the fixture for this Credits On Subject test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected CreditsOnSubject getFixture() {
		return (CreditsOnSubject)fixture;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see junit.framework.TestCase#setUp()
	 * @generated
	 */
	@Override
	protected void setUp() throws Exception {
		setFixture(AsignaturasFactory.eINSTANCE.createCreditsOnSubject());
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

} //CreditsOnSubjectTest
