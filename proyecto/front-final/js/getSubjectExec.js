const allFacultiesSelect = document.getElementById("facultades");
const allCareersSelect = document.getElementById("carreras");
const allPlansSelect = document.getElementById("planes");
const allMateriasSelect = document.getElementById("materias");
const selectFacultyBtn = document.getElementById("markFaculty");
const selectCareerBtn = document.getElementById("markCarrera");
const selectPlanBtn = document.getElementById("markPlan");
const selectMateriaBtn = document.getElementById("markMateria");

selectCareerBtn.disabled = true;
selectPlanBtn.disabled = true;
selectMateriaBtn.disabled = true;

getFaculties(allFacultiesSelect);
selectFacultyBtn.addEventListener("click", () => {
    allCareersSelect.innerHTML ="";
    allPlansSelect.innerHTML ="";
    allMateriasSelect.innerHTML ="";
    getCareers(allCareersSelect, allFacultiesSelect.value);
    selectCareerBtn.disabled = false;
    selectPlanBtn.disabled = true;
	selectMateriaBtn.disabled = true;
});
selectCareerBtn.addEventListener("click", () => {
    allPlansSelect.innerHTML ="";
    allMateriasSelect.innerHTML ="";
    getPlans(allPlansSelect, allFacultiesSelect.value, allCareersSelect.value);
    selectPlanBtn.disabled = false;
    selectMateriaBtn.disabled = true;
});
selectPlanBtn.addEventListener("click", () => {
    allMateriasSelect.innerHTML ="";
    getMaterias(allMateriasSelect, allFacultiesSelect.value, allCareersSelect.value, allPlansSelect.value);
    selectMateriaBtn.disabled = false;
});
selectMateriaBtn.addEventListener("click", () => {
	consultarMateria();
});