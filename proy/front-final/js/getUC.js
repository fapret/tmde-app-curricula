function consultarUC() {
const allFacultiesSelect = document.getElementById("facultades");
const facultyName = allFacultiesSelect.value;
const allUCSelect = document.getElementById("ucs");
const uc = allUCSelect.value;
const url = `http://localhost:8080/curricula_microservice/Faculty/ucs?faculty=${facultyName}&curricularUnit=${uc}`;
fetch(url)
.then(response => response.json())
.then(data => {
    const resultadoDiv = document.getElementById("resultado");
    resultadoDiv.style.display = "block";
    resultadoDiv.innerHTML = "";
    
            resultadoDiv.innerHTML += `<p><strong>Curricular Unit Name:</strong> ${data.Name}</p>`;
            resultadoDiv.innerHTML += `<p><strong>Curricular Unit ID:</strong> ${data.Id}</p>`;
            resultadoDiv.innerHTML += `<p><strong>Credits:</strong> ${data.Cred}</p>`;
            resultadoDiv.innerHTML += `<p><strong>Requirements:</strong> ${data.Requirement || 'N/A'}</p>`;
            resultadoDiv.innerHTML += `<p><strong>Exam evaluations:</strong> ${data.ExamEvaluation.join(', ')}</p>`;

            resultadoDiv.innerHTML += "<p><strong>Courses:</strong></p>";
            data.Course.forEach(course => {
              resultadoDiv.innerHTML += `<p><strong>Year:</strong> ${course.Year}, <strong>Edition:</strong> ${course.Edition}, <strong>Course evaluations:</strong> ${course.CourseEvaluation.join(', ')}</p>`;
            });
})
.catch(error => {
    console.error("Error al consultar la API:", error);
    const resultadoDiv = document.getElementById("resultado");
    resultadoDiv.innerHTML = "Error al consultar la API: "+error;
});
}