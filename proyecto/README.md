# Modelado de Currículo y Trayectoria

Enfoque MDE para la definición de carreras, planes de estudio, estudiantes, avances en sus carreras y más, todo con una interfaz gráfica que permite interactuar de forma amigable.

Requisitos de ejecución:
- Eclipse
- Tomcat
- Librería de Ecore
- Navegador con soporte de JavaScript

Se debe colocar el modelo de la universidad (model.xmi, ubicado en model/model/model.xmi) en el directorio según corresponda:
- En Linux: Carpeta home del usuario.
- En Mac: /Applications/Eclipse/Contents/MacOS (reemplazar /Applications/Eclipse por otro nombre si Eclipse está instalado en otra ruta).

Correr el proyecto _curricula_microservice_ en Tomcat y acceder a la aplicación en la dirección https://tmde.pages.fing.edu.uy/tmde04 o abriendo el archivo index.html en el directorio _front_.

Para hacer consultas directas a la API, se accede desde _http://localhost:8080/curricula_microservice_. Para detalles sobre las operaciones disponibles ver la declaración de OpenAPI en el archivo openapi.yml o la documentación en HTML en front/docs.html.

Para más información sobre el funcionamiento de la aplicación o su enfoque MDE, ver el archivo informe_gr04.pdf.

Proyecto realizado por:
- Santiago Nicolás Díaz Conde <santiago.nicolas.diaz.conde [at] fing.edu.uy>
- Santiago Freire López <santiago.freire [at] fing.edu.uy>
