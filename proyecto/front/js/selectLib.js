function addToSelect(select, idelement){
  let option = document.createElement('option');
  option.setAttribute('value', idelement);
  option.appendChild(document.createTextNode(idelement));
  select.appendChild(option);
}

function getFaculties(select) {
  const url = `https://tmde-api.fapret.com:8443/curricula_microservice/Faculty`;
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
	const url = `https://tmde-api.fapret.com:8443/curricula_microservice/Faculty/ucs?faculty=${faculty}`;
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

function getCareers(select, faculty){
  const url = `https://tmde-api.fapret.com:8443/curricula_microservice/Faculty?faculty=${faculty}`;
  fetch(url)
  .then(response => response.json())
  .then(data => {
	for (var i = 0; i < data["Careers"].length; i++){
		addToSelect(select, data["Careers"][i]);
	}
  })
  .catch(error => {
      console.error("Error al consultar la API:", error);
  });
}

function getPlans(select, faculty, career){
  const url = `https://tmde-api.fapret.com:8443/curricula_microservice/Faculty/Carrera?faculty=${faculty}&career=${career}`;
  fetch(url)
  .then(response => response.json())
  .then(data => {
	for (var i = 0; i < data["Plans"].length; i++){
		addToSelect(select, data["Plans"][i]);
	}
  })
  .catch(error => {
      console.error("Error al consultar la API:", error);
  });
}

function getPlanUCS(select, faculty, career, plan){
	const url = `https://tmde-api.fapret.com:8443/curricula_microservice/Faculty/Carrera/Plan/ucs?faculty=${faculty}&career=${career}&plan=${plan}`;
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

function getEvaluations(select, typeBoolean, UC, faculty){
	const url = `https://tmde-api.fapret.com:8443/curricula_microservice/Faculty/ucs?faculty=${faculty}&curricularUnit=${UC}`;
	fetch(url)
	.then(response => response.json())
	.then(data => {
		if(typeBoolean){
			var evaluations = data.ExamEvaluation;
			for (var i = 0; i < evaluations.length; i++){
				addToSelect(select, evaluations[i]);
			}
		} else {
			for (var i = 0; i < data.Course.length; i++){
				for(var j = 0; j < data.Course[i].CourseEvaluation.length; j++){
					addToSelect(select, data.Course[i].CourseEvaluation[j]);
				}
			}
		}
	})
	.catch(error => {
      console.error("Error al consultar la API:", error);
  	});
}

function getMaterias(select, faculty, career, plan){
	const url = `https://tmde-api.fapret.com:8443/curricula_microservice/Faculty/Carrera/Plan/Subjects?faculty=${faculty}&career=${career}&plan=${plan}`;
	fetch(url)
	.then(response => response.json())
	.then(data => {
		for(var i = 0; i < data.length; i++){
			addToSelect(select, data[i]);
		}
	})
	.catch(error => {
      console.error("Error al consultar la API:", error);
  	});
}

function findKeyByValue(obj, value) {
    for (let key in obj) {
      if (obj[key]['materia_id'] === value) {
        return obj[key]['id']; // Return the key if the value is found
      }
    }
    return null; // Return null if the value is not found
}

async function processSomeOf(someOfArray, req_values, facultyName) {
  for (let j = 0; j < someOfArray.length; j++) {
    const subReq = someOfArray[j];

    if (subReq.Exam) {
      req_values.push(await getMaxRequirementLevel(facultyName, subReq.Exam));
    } else if (subReq.Coursed) {
      req_values.push(await getMaxRequirementLevel(facultyName, subReq.Coursed));
    } else if (subReq.SomeOf) {
      // Recursive call for nested SomeOf
      await processSomeOf(subReq.SomeOf, req_values, facultyName);
    }
  }
}

async function getMaxRequirementLevel(facultyName, cu_id) {
  let apiUrl = `https://tmde-api.fapret.com:8443/curricula_microservice/Faculty/ucs?faculty=${facultyName}&curricularUnit=${cu_id}`;

  try {
    const response = await fetch(apiUrl);

    if (!response.ok) {
      throw new Error('No se pudo obtener la respuesta de la API');
    }

    const data = await response.json();

    if (!data['Requirement'] || data['Requirement'].length === 0)
        return 0;
    const req_values = [];

    for (let i = 0; i < data['Requirement'].length; i++) {
      const subRequirement = data['Requirement'][i];
      if (subRequirement.Exam) {
        req_values.push(await getMaxRequirementLevel(facultyName, subRequirement.Exam));
      } else if (subRequirement.Coursed) {
        req_values.push(await getMaxRequirementLevel(facultyName, subRequirement.Coursed));
      } else if (subRequirement.SomeOf) {
        await processSomeOf(subRequirement.SomeOf, req_values, facultyName);
      }
    }
    return req_values.length > 0 ? 1 + Math.max(...req_values) : 0;
  } catch (error) {
    console.error(error);
    return 0;
  }
}