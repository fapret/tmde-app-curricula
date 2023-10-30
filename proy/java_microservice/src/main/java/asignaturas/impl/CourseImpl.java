/**
 */
package asignaturas.impl;

import asignaturas.AsignaturasPackage;
import asignaturas.Course;
import asignaturas.CourseEvaluation;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Course</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link asignaturas.impl.CourseImpl#getYear <em>Year</em>}</li>
 *   <li>{@link asignaturas.impl.CourseImpl#getEdition <em>Edition</em>}</li>
 *   <li>{@link asignaturas.impl.CourseImpl#getCourseEvaluation <em>Course Evaluation</em>}</li>
 * </ul>
 *
 * @generated
 */
public class CourseImpl extends MinimalEObjectImpl.Container implements Course {
	/**
	 * The default value of the '{@link #getYear() <em>Year</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getYear()
	 * @generated
	 * @ordered
	 */
	protected static final int YEAR_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getYear() <em>Year</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getYear()
	 * @generated
	 * @ordered
	 */
	protected int year = YEAR_EDEFAULT;

	/**
	 * The default value of the '{@link #getEdition() <em>Edition</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEdition()
	 * @generated
	 * @ordered
	 */
	protected static final int EDITION_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getEdition() <em>Edition</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEdition()
	 * @generated
	 * @ordered
	 */
	protected int edition = EDITION_EDEFAULT;

	/**
	 * The cached value of the '{@link #getCourseEvaluation() <em>Course Evaluation</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCourseEvaluation()
	 * @generated
	 * @ordered
	 */
	protected EList<CourseEvaluation> courseEvaluation;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CourseImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return AsignaturasPackage.Literals.COURSE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int getYear() {
		return year;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setYear(int newYear) {
		int oldYear = year;
		year = newYear;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AsignaturasPackage.COURSE__YEAR, oldYear, year));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int getEdition() {
		return edition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setEdition(int newEdition) {
		int oldEdition = edition;
		edition = newEdition;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AsignaturasPackage.COURSE__EDITION, oldEdition, edition));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<CourseEvaluation> getCourseEvaluation() {
		if (courseEvaluation == null) {
			courseEvaluation = new EObjectContainmentEList<CourseEvaluation>(CourseEvaluation.class, this, AsignaturasPackage.COURSE__COURSE_EVALUATION);
		}
		return courseEvaluation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case AsignaturasPackage.COURSE__COURSE_EVALUATION:
				return ((InternalEList<?>)getCourseEvaluation()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case AsignaturasPackage.COURSE__YEAR:
				return getYear();
			case AsignaturasPackage.COURSE__EDITION:
				return getEdition();
			case AsignaturasPackage.COURSE__COURSE_EVALUATION:
				return getCourseEvaluation();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case AsignaturasPackage.COURSE__YEAR:
				setYear((Integer)newValue);
				return;
			case AsignaturasPackage.COURSE__EDITION:
				setEdition((Integer)newValue);
				return;
			case AsignaturasPackage.COURSE__COURSE_EVALUATION:
				getCourseEvaluation().clear();
				getCourseEvaluation().addAll((Collection<? extends CourseEvaluation>)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case AsignaturasPackage.COURSE__YEAR:
				setYear(YEAR_EDEFAULT);
				return;
			case AsignaturasPackage.COURSE__EDITION:
				setEdition(EDITION_EDEFAULT);
				return;
			case AsignaturasPackage.COURSE__COURSE_EVALUATION:
				getCourseEvaluation().clear();
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case AsignaturasPackage.COURSE__YEAR:
				return year != YEAR_EDEFAULT;
			case AsignaturasPackage.COURSE__EDITION:
				return edition != EDITION_EDEFAULT;
			case AsignaturasPackage.COURSE__COURSE_EVALUATION:
				return courseEvaluation != null && !courseEvaluation.isEmpty();
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuilder result = new StringBuilder(super.toString());
		result.append(" (Year: ");
		result.append(year);
		result.append(", Edition: ");
		result.append(edition);
		result.append(')');
		return result.toString();
	}

} //CourseImpl
