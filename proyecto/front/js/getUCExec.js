const allFacultiesSelect = document.getElementById("facultades");
const allUCSelect = document.getElementById("ucs");
const selectFacultyBtn = document.getElementById("markFaculty");
const consultBtn = document.getElementById("markUC");
consultBtn.disabled = true;

getFaculties(allFacultiesSelect);
selectFacultyBtn.addEventListener("click", () => {
  getUCS(allUCSelect, allFacultiesSelect.value);
  consultBtn.disabled = false;
});
consultBtn.addEventListener("click", () => {
  consultarUC();
});