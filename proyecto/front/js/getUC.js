function printRequirements(data) {
    let res = "";
        for (const clave in data) {
            // Verifica si la propiedad realmente pertenece al objeto (y no a su cadena de prototipo)
            if (data.hasOwnProperty(clave)) {
                // Imprime la clave y su valor
                console.log(data[clave]);
                if (clave === "Exam" || clave === "Coursed")
                res += clave+": "+data[clave]+", ";
                if (clave === "SomeOf") {
                    res += "Some Of "+data[clave][0]+": ["
                    for (let j = 1; j < data[clave].length; j++) {
                        res += printRequirements(data[clave][j]);
                    }
                    res = res.substring(0,res.length-2);
                    res += "], ";
            }
        }
    }
    return res;
}

function consultarUC() {
const allFacultiesSelect = document.getElementById("facultades");
const facultyName = allFacultiesSelect.value;
const allUCSelect = document.getElementById("ucs");
const uc = allUCSelect.value;
const url = `http://localhost:8080/curricula_microservice/Faculty/ucs?faculty=${facultyName}&curricularUnit=${uc}`;
fetch(url)
.then(response => response.json())
.then(data => {
    let reqs = "";
    for (let i = 0; i < data.Requirement.length; i++)
        reqs += printRequirements(data.Requirement[i]);
    reqs = reqs.substring(0,reqs.length-2);
    const resultadoDiv = document.getElementById("resultado");
    resultadoDiv.style.display = "block";
    resultadoDiv.innerHTML = "";
    
            resultadoDiv.innerHTML += `<p><strong>Curricular Unit Name:</strong> ${data.Name}</p>`;
            resultadoDiv.innerHTML += `<p><strong>Curricular Unit ID:</strong> ${data.Id}</p>`;
            resultadoDiv.innerHTML += `<p><strong>Credits:</strong> ${data.Cred}</p>`;
            resultadoDiv.innerHTML += `<p><strong>Requirements:</strong> ${reqs || 'N/A'}</p>`;
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
