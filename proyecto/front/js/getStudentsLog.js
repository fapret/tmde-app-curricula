function get_log() {
    const model_file = document.getElementById("file").files[0];

    //const url = `https://tmde-api.fapret.com:8443/curricula_microservice/GetStudentsLog`;
    const url = `http://localhost:8080/curricula_microservice/GetStudentsLog`;
    var formData = new FormData();
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

            resultadoDiv.innerHTML = "Log obtenido.";

            var blob = new Blob([data], { type: 'text/plain' });

            // Create a link element
            var link = document.createElement('a');

            // Set the download attribute and href for the link
            link.download = 'StudentsLog.csv';
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