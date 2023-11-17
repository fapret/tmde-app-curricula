const allFacultiesSelect = document.getElementById("facultades");
const selectFacultyBtn = document.getElementById("markFaculty");
const allCareersSelect = document.getElementById("carreras");
const selectCareerBtn = document.getElementById("markCarrera");
const allPlansSelect = document.getElementById("planes");
const selectPlanBtn = document.getElementById("markPlan");
const allUCSSelect = document.getElementById("ucs");
const selectUCBtn = document.getElementById("markUC");
const allEvaluationSelect = document.getElementById("evaluations");
const selectEvaluationBtn = document.getElementById("markEvaluation");
const model_file = document.getElementById("file");
const type = document.getElementById("checkbox");

selectCareerBtn.disabled = true;
selectPlanBtn.disabled = true;
selectUCBtn.disabled = true;
selectEvaluationBtn.disabled = true;
type.disabled = true;

getFaculties(allFacultiesSelect);
selectFacultyBtn.addEventListener("click", () => {
    allCareersSelect.innerHTML ="";
    allPlansSelect.innerHTML ="";
    allUCSSelect.innerHTML ="";
    allEvaluationSelect.innerHTML ="";
    getCareers(allCareersSelect, allFacultiesSelect.value);
    selectCareerBtn.disabled = false;
    selectPlanBtn.disabled = true;
	selectUCBtn.disabled = true;
	selectEvaluationBtn.disabled = true;
	type.disabled = true;
});
selectCareerBtn.addEventListener("click", () => {
    allPlansSelect.innerHTML ="";
    allUCSSelect.innerHTML ="";
    allEvaluationSelect.innerHTML ="";
    getPlans(allPlansSelect, allFacultiesSelect.value, allCareersSelect.value);
    selectPlanBtn.disabled = false;
    selectUCBtn.disabled = true;
    selectEvaluationBtn.disabled = true;
    type.disabled = true;
});
selectPlanBtn.addEventListener("click", () => {
    allUCSSelect.innerHTML ="";
    allEvaluationSelect.innerHTML ="";
    getPlanUCS(allUCSSelect, allFacultiesSelect.value, allCareersSelect.value, allPlansSelect.value);
    selectUCBtn.disabled = false;
    selectEvaluationBtn.disabled = true;
    type.disabled = false;
});
selectUCBtn.addEventListener("click", () => {
    allEvaluationSelect.innerHTML ="";
    getEvaluations(allEvaluationSelect, type.checked, allUCSSelect.value, allFacultiesSelect.value);
    selectEvaluationBtn.disabled = false;
    type.disabled = true;
});
selectEvaluationBtn.addEventListener("click", () => {
    agregar_Evaluacion();
});