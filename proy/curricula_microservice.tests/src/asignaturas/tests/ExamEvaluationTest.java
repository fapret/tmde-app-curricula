/**
 */
package asignaturas.tests;

import asignaturas.AsignaturasFactory;
import asignaturas.ExamEvaluation;

import junit.textui.TestRunner;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Exam Evaluation</b></em>'.
 * <!-- end-user-doc -->
 * @generated
 */
public class ExamEvaluationTest extends EvaluationTest {

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(ExamEvaluationTest.class);
	}

	/**
	 * Constructs a new Exam Evaluation test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExamEvaluationTest(String name) {
		super(name);
	}

	/**
	 * Returns the fixture for this Exam Evaluation test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected ExamEvaluation getFixture() {
		return (ExamEvaluation)fixture;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see junit.framework.TestCase#setUp()
	 * @generated
	 */
	@Override
	protected void setUp() throws Exception {
		setFixture(AsignaturasFactory.eINSTANCE.createExamEvaluation());
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

} //ExamEvaluationTest
