function crear_estudiante() {
    // Obtener los valores de los campos de entrada
    var nombre = document.getElementById("name").value;
    var ci = document.getElementById("ci").value;

    // Construir la URL con los parámetros
    var url = "http://localhost:8080/curricula_microservice/Estudiante?name=" + encodeURIComponent(nombre) + "&id=" + encodeURIComponent(ci);

    // Crear un enlace para descargar el archivo
    var a = document.createElement('a');
    a.href = url;
    a.download = 'data.txt';

    // Agregar el enlace al DOM y simular un clic para iniciar la descarga
    document.body.appendChild(a);
    a.click();

    // Limpiar el enlace después de la descarga
    document.body.removeChild(a);
}
