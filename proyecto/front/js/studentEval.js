function evaluar_uc() {
    const allFacultiesSelect = document.getElementById("facultades");
    const allUCSelect = document.getElementById("ucs");
    const facultyName = allFacultiesSelect.value;
    const uc_id = allUCSelect.value;
    const model_file = document.getElementById("file").files[0];

    const url = `https://tmde-api.fapret.com:8443/curricula_microservice/Faculty/ucs/eval`;
    var formData = new FormData();
    formData.append('faculty', facultyName);
    formData.append('curricularUnit', uc_id);
    formData.append('file', model_file);

    // Configurar las opciones de la solicitud
    var options = {
        method: 'POST',
        body: formData
    };

    fetch(url, options)
        .then(response => response.json())
        .then(data => {
            if (data)
                document.getElementById("resultado").innerHTML = "Está habilitado para cursar la materia."
            else
                document.getElementById("resultado").innerHTML = "No está habilitado para cursar la materia."
        })
}