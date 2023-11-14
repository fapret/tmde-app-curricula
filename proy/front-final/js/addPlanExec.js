const allFacultiesSelect = document.getElementById("facultades");
const selectFacultyBtn = document.getElementById("markFaculty");
const allCareersSelect = document.getElementById("carreras");
const selectCareerBtn = document.getElementById("markCarrera");
const allPlansSelect = document.getElementById("planes");
const selectPlanBtn = document.getElementById("markPlan");
const model_file = document.getElementById("file");

selectCareerBtn.disabled = true;
selectPlanBtn.disabled = true;

getFaculties(allFacultiesSelect);
selectFacultyBtn.addEventListener("click", () => {
    allCareersSelect.innerHTML ="";
    allPlansSelect.innerHTML ="";
    getCareers(allCareersSelect, allFacultiesSelect.value);
    selectCareerBtn.disabled = false;
    selectPlanBtn.disabled = true;

});
selectCareerBtn.addEventListener("click", () => {
    allPlansSelect.innerHTML ="";
    getPlans(allPlansSelect, allFacultiesSelect.value, allCareersSelect.value);
    selectPlanBtn.disabled = false;
});
selectPlanBtn.addEventListener("click", () => {
    inscribir_plan();
});