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
function crear_estudiante() {
    // Obtener los valores de los campos de entrada
    var nombre = document.getElementById("name").value;
    var ci = document.getElementById("ci").value;
	var model_file = document.getElementById("file");

    // Construir la URL con los parámetros
	var url = "https://tmde-api.fapret.com:8443/curricula_microservice/Estudiante";
    var url2 = url + "?name=" + encodeURIComponent(nombre) + "&id=" + encodeURIComponent(ci);

	if(model_file.files && model_file.files.length > 0){
		var formData = new FormData();
		formData.append('name',nombre);
		formData.append('id', ci);
		formData.append('file', model_file.files[0]);
		
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

			//TODO soporte de errores
		    resultadoDiv.innerHTML = "Estudiante agregado exitosamente, si ya existia se reseteo sus datos.";

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
	} else {
		// Crear un enlace para descargar el archivo
		var a = document.createElement('a');
		a.href = url2;
		a.download = 'data.txt';

		// Agregar el enlace al DOM y simular un clic para iniciar la descarga
		document.body.appendChild(a);
		a.click();

		// Limpiar el enlace después de la descarga
		document.body.removeChild(a);	
	}
}
