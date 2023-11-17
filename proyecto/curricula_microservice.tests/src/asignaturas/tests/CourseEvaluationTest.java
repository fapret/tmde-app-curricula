/**
 */
package asignaturas.tests;

import asignaturas.AsignaturasFactory;
import asignaturas.CourseEvaluation;

import junit.textui.TestRunner;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Course Evaluation</b></em>'.
 * <!-- end-user-doc -->
 * @generated
 */
public class CourseEvaluationTest extends EvaluationTest {

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(CourseEvaluationTest.class);
	}

	/**
	 * Constructs a new Course Evaluation test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CourseEvaluationTest(String name) {
		super(name);
	}

	/**
	 * Returns the fixture for this Course Evaluation test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected CourseEvaluation getFixture() {
		return (CourseEvaluation)fixture;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see junit.framework.TestCase#setUp()
	 * @generated
	 */
	@Override
	protected void setUp() throws Exception {
		setFixture(AsignaturasFactory.eINSTANCE.createCourseEvaluation());
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

} //CourseEvaluationTest
