/*
    tmde-app-curricula is a software that helps students build their curricula and
    see what curricular units they can register to, and track how their career was
    or will be.
    Copyright (C) 2023  Santiago Nicolás Díaz Conde, Santiago Freire López
	Copyright (C) 2025  Santiago Nicolás Díaz Conde

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <https://www.gnu.org/licenses/>.
*/
function agregar_Evaluacion() {
	const allFacultiesSelect = document.getElementById("facultades");
	const allUCSSelect = document.getElementById("ucs");
	const allEvaluationSelect = document.getElementById("evaluations");
	const allCareersSelect = document.getElementById("carreras");
	const allPlansSelect = document.getElementById("planes");
	const facultyName = allFacultiesSelect.value;
	const ucId = allUCSSelect.value;
	const type = document.getElementById("checkbox").checked;
	const evaluation = allEvaluationSelect.value;
	const nota = document.getElementById("nota").value;
	const model_file = document.getElementById("file").files[0];
	const career = allCareersSelect.value;
	const plan = allPlansSelect.value;


	const url = `https://tmde-api.fapret.com:8443/curricula_microservice/Estudiante/AddEvaluation`;
    var formData = new FormData();
    formData.append('faculty', facultyName);
    formData.append('curricularunit', ucId);
    formData.append('type', type);
    formData.append('file', model_file);
    formData.append('evaluation', evaluation);
    formData.append('nota', nota);
    formData.append('career', career);
    formData.append('plan', plan);

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

            resultadoDiv.innerHTML = "Evaluacion agregada exitosamente, si ya poseia dicha evaluacion, nota actualizada exitosamente.";

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