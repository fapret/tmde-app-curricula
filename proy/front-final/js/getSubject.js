function consultarMateria() {
	const allFacultiesSelect = document.getElementById("facultades");
    const allCareersSelect = document.getElementById("carreras");
    const allPlansSelect = document.getElementById("planes");
    const allMateriasSelect = document.getElementById("materias");
    const faculty = allFacultiesSelect.value;
    const career = allCareersSelect.value;
    const plan = allPlansSelect.value;
    const materia = allMateriasSelect.value;
    
    const url = `http://localhost:8080/curricula_microservice/Faculty/Carrera/Plan/Subjects?faculty=${faculty}&career=${career}&plan=${plan}&subject=${materia}`;
    fetch(url)
	.then(response => response.json())
	.then(data => {
		const resultadoDiv = document.getElementById("resultado");
		 resultadoDiv.style.display = "block";
		 resultadoDiv.innerHTML = "";
		 resultadoDiv.innerHTML += `<p><strong>Id:</strong> ${data.Id}</p>`;
		 resultadoDiv.innerHTML += `<p><strong>Nombre:</strong> ${data.Name}</p>`;
		 resultadoDiv.innerHTML += `<p><strong>MinCredits:</strong> ${data.MinCredits}</p>`;
		 resultadoDiv.innerHTML += `<p><strong>Materias:</strong></p>`;
		 data.Subjects.forEach(subject => {
         	resultadoDiv.innerHTML += `<p>${subject}</p>`;
         });
	})
	.catch(error => {
    	console.error("Error al consultar la API:", error);
    	const resultadoDiv = document.getElementById("resultado");
    	resultadoDiv.innerHTML = "Error al consultar la API: "+error;
	});
}