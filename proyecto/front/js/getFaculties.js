function consultarFacultad() {
const allFacultiesSelect = document.getElementById("facultades");
const facultyName = allFacultiesSelect.value;
const url = `https://tmde-api.fapret.com:8443/curricula_microservice/Faculty?faculty=${facultyName}`;
fetch(url)
.then(response => response.json())
.then(data => {
    const resultadoDiv = document.getElementById("resultado");
    resultadoDiv.style.display = "block";
    resultadoDiv.innerHTML = "";

    const name = data.Name;
    const careers = data.Careers;
    const facultyCU = data.FacultyCU;

    resultadoDiv.innerHTML += `<p><strong>Faculty name:</strong> ${name}</p>`;
    resultadoDiv.innerHTML += `<p><strong>Careers:</strong> ${careers.join(", ")}</p>`;
    resultadoDiv.innerHTML += `<p><strong>Curricular Units:</strong> ${facultyCU.join(", ")}</p>`;
})
.catch(error => {
    console.error("Error al consultar la API:", error);
    const resultadoDiv = document.getElementById("resultado");
    resultadoDiv.innerHTML = "Error al consultar la API: "+error;
});
}
