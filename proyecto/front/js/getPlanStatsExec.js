const allFacultiesSelect = document.getElementById("facultades");
const allCareersSelect = document.getElementById("carreras");
const allPlansSelect = document.getElementById("planes");
const allDiscoveriesSelect = document.getElementById("discoveries");
const selectFacultyBtn = document.getElementById("markFaculty");
const selectCareerBtn = document.getElementById("markCarrera");
const selectBtn = document.getElementById("seeStatistics");

selectBtn.disabled = true;
selectCareerBtn.disabled = true;

getFaculties(allFacultiesSelect);
getDiscoveries(allDiscoveriesSelect);
selectFacultyBtn.addEventListener("click", () => {
    allCareersSelect.innerHTML ="";
    allPlansSelect.innerHTML ="";
    getCareers(allCareersSelect, allFacultiesSelect.value);
    selectCareerBtn.disabled = false;
});
selectCareerBtn.addEventListener("click", () => {
    allPlansSelect.innerHTML ="";
    getPlans(allPlansSelect, allFacultiesSelect.value, allCareersSelect.value);
    selectBtn.disabled = false;
});
selectBtn.addEventListener("click", () => {
    graphList.style.display = "flex";
    result.style.display = "block";
    displayPlanStats();
});