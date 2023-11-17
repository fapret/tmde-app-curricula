/**
 */
package asignaturas.tests;

import asignaturas.AsignaturasFactory;
import asignaturas.EvaluacionExamen;

import junit.textui.TestRunner;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Evaluacion Examen</b></em>'.
 * <!-- end-user-doc -->
 * @generated
 */
public class EvaluacionExamenTest extends EvaluationTest {

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(EvaluacionExamenTest.class);
	}

	/**
	 * Constructs a new Evaluacion Examen test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EvaluacionExamenTest(String name) {
		super(name);
	}

	/**
	 * Returns the fixture for this Evaluacion Examen test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EvaluacionExamen getFixture() {
		return (EvaluacionExamen)fixture;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see junit.framework.TestCase#setUp()
	 * @generated
	 */
	@Override
	protected void setUp() throws Exception {
		setFixture(AsignaturasFactory.eINSTANCE.createEvaluacionExamen());
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

} //EvaluacionExamenTest
