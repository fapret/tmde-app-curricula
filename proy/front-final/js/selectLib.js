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
    for (var faculty in data) {
      addToSelect(select, faculty);
    }
  })
  .catch(error => {
      console.error("Error al consultar la API:", error);
  });
}
