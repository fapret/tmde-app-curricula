function inscribir_plan() {
    const allFacultiesSelect = document.getElementById("facultades");
    const allCareersSelect = document.getElementById("carreras");
    const allPlansSelect = document.getElementById("planes");

    const faculty = allFacultiesSelect.value;
    const career = allCareersSelect.value;
    const plan = allPlansSelect.value;
    const date = document.getElementById("date").value;
    const model_file = document.getElementById("file").files[0];

    const url = `http://localhost:8080/curricula_microservice/Estudiante/AddPlan`;
    var formData = new FormData();
    formData.append('faculty', faculty);
    formData.append('career', career);
    formData.append('plan', plan);
    formData.append('date', date);
    formData.append('file', model_file);

    // Configurar las opciones de la solicitud
    var options = {
        method: 'POST',
        body: formData

    };

    fetch(url, options)
        .then(response => response.text())
        .then(data => {
            const resultadoDiv = document.getElementById("resultado");
            resultadoDiv.style.display = "block";
            if (data == "El estudiante ya esta inscripto en el plan") {
                resultadoDiv.innerHTML = "El estudiante ya está inscripto en el plan.";
                return;
            }

            resultadoDiv.innerHTML = "Inscripto exitosamente.";

            var blob = new Blob([data], { type: 'text/plain' });

            // Create a link element
            var link = document.createElement('a');

            // Set the download attribute and href for the link
            link.download = 'model.xmi';
            link.href = window.URL.createObjectURL(blob);

            // Append the link to the body
            document.body.appendChild(link);

            // Trigger a click on the link to start the download
            link.click();

            // Remove the link from the DOM
            document.body.removeChild(link);

        })
        .catch(error => {
            console.error("Error al consultar la API:", error);
            const resultadoDiv = document.getElementById("resultado");
            resultadoDiv.innerHTML = "Error al consultar la API: "+error;
        });
}