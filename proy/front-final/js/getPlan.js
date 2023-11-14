function consultarPlan() {
    const allFacultiesSelect = document.getElementById("facultades");
    const allCareersSelect = document.getElementById("carreras");
    const allPlansSelect = document.getElementById("planes");
    const faculty = allFacultiesSelect.value;
    const career = allCareersSelect.value;
    const plan = allPlansSelect.value;
    
    const url = `http://localhost:8080/curricula_microservice/Faculty/Carrera/Plan?faculty=${faculty}&career=${career}&plan=${plan}`;
    fetch(url)
	.then(response => response.json())
	.then(data => {
		const resultadoDiv = document.getElementById("resultado");
		 resultadoDiv.style.display = "block";
		 resultadoDiv.innerHTML = "";
		 resultadoDiv.innerHTML += `<p><strong>Carrera:</strong> ${data.Career}</p>`;
		 resultadoDiv.innerHTML += `<p><strong>Tipo:</strong> ${data.Type}</p>`;
		 resultadoDiv.innerHTML += `<p><strong>Year:</strong> ${data.Year}</p>`;
		 if(data.Type == "CreditsPlan"){
			 resultadoDiv.innerHTML += `<p><strong>Creditos necesarios:</strong> ${data.MinCredits}</p>`;
			 resultadoDiv.innerHTML += "<p><strong>Materias:</strong></p>";
			 data.Subjects.forEach(subject => {
              resultadoDiv.innerHTML += `<p>${subject}</p>`;
             });
		 } else if(data.Type == "SubjectPlan"){
			 resultadoDiv.innerHTML += "<p><strong>Unidades Curriculares:</strong></p>";
			 data["Curricular Units"].forEach(cu => {
              resultadoDiv.innerHTML += `<p>${cu}</p>`;
             });
		 }
	})
	.catch(error => {
    	console.error("Error al consultar la API:", error);
    	const resultadoDiv = document.getElementById("resultado");
    	resultadoDiv.innerHTML = "Error al consultar la API: "+error;
	});
}