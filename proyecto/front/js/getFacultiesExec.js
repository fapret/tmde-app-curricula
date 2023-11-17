const allFacultiesSelect = document.getElementById("facultades");
const consultBtn = document.getElementById("markFaculty");

getFaculties(allFacultiesSelect);
consultBtn.addEventListener("click", () => {
  consultarFacultad();
});
