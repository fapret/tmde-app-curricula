function addToSelect(select, idelement){
  let option = document.createElement('option');
  option.setAttribute('value', idelement);
  option.appendChild(document.createTextNode(idelement));
  select.appendChild(option);
}

function getFaculties(select) {
  const url = `http://localhost:8080/curricula_microservice/Faculty`;
  fetch(url)
  .then(response => response.json())
  .then(data => {
	for (var i = 0; i < data.length; i++){
		addToSelect(select, data[i]);
	}
  })
  .catch(error => {
      console.error("Error al consultar la API:", error);
  });
}

function getUCS(select, faculty){
	const url = `http://localhost:8080/curricula_microservice/Faculty/ucs?faculty=${faculty}`;
	fetch(url)
  .then(response => response.json())
  .then(data => {
	for (var i = 0; i < data.length; i++){
		addToSelect(select, data[i]);
	}
  })
  .catch(error => {
      console.error("Error al consultar la API:", error);
  });
}