function findKeyByValue(obj, value) {
    for (let key in obj) {
        if (obj[key]['materia_id'] === value) {
            return obj[key]['id']; // Return the key if the value is found
        }
    }
    return null; // Return null if the value is not found
}

function mostrar_trayectoria() {
    const allFacultiesSelect = document.getElementById("facultades");
    const allCareersSelect = document.getElementById("carreras");
    const allPlansSelect = document.getElementById("planes");

    const facultyName = allFacultiesSelect.value;
    const career = allCareersSelect.value;
    const plan = allPlansSelect.value;
    const model_file = document.getElementById("file").files[0];

    var nodos = [];
    var nodos_0 = [];
    var nodos_process = {};
    var nodos_final = [];
    var aristas = [];

    const url = `http://localhost:8080/curricula_microservice/Faculty/Carrera/Plan/eval`;
    var formData = new FormData();
    formData.append('faculty', facultyName);
    formData.append('career', career);
    formData.append('plan', plan);
    formData.append('file', model_file);

    // Configurar las opciones de la solicitud
    var options = {
        method: 'POST',
        body: formData

    };

    fetch(url, options)
        .then(response => response.json())
        .then(materias => {
            var formDataCopy = formData;

            for (let i = 0; i < materias.length; i++) {
                nodos_0.push({id: i + 1, materia_id: materias[i]});
            }

            for (let i = 0; i < materias.length; i++) {
                let apiUrl = `http://localhost:8080/curricula_microservice/Faculty/ucs?faculty=${facultyName}&curricularUnit=` + materias[i];
                fetch(apiUrl)
                    .then(response => {
                        if (!response.ok) {
                            throw new Error('No se pudo obtener la respuesta de la API');
                        }
                        return response.json(); // Parsear la respuesta JSON
                    })
                    .then(data => {
                        nodos.push({id: i + 1, materia_id: materias[i], materia_name: data['Name']});
                        nodos_process[materias[i]] = null;

                        if ((data.Requirement).length > 0) {
                            //PENDIENTE: si es un someof, tengo que ver cuales de las N se cumple,
                            //y mostrar solo las aristas de ella. por ejemplo, tprog y sus 2 vias de cursado
                            if (Object.keys(data.Requirement[0])[0] === "SomeOf") {
                                for (let j = 1; j < (data.Requirement[0]['SomeOf']).length; j++) {
                                    let a = data.Requirement[0]['SomeOf'][j];
                                    let b = Object.keys(a);
                                    let req = b[0];
                                    if (req === "Coursed") {
                                        let source_index = findKeyByValue(nodos_0, a['Coursed']);
                                        let dest_index = findKeyByValue(nodos_0, materias[i]);
                                        let aaa = {
                                            from: source_index,
                                            to: dest_index,
                                            color: "#0000FF",
                                            arrows: "to",
                                            dashes: true
                                        };
                                        aristas.push(aaa);
                                    } else if (req === "Exam") {
                                        let source_index = findKeyByValue(nodos_0, a['Exam']);
                                        let dest_index = findKeyByValue(nodos_0, materias[i]);
                                        let aaa = {
                                            from: source_index,
                                            to: dest_index,
                                            color: "#0000FF",
                                            arrows: "to"
                                        };
                                        aristas.push(aaa);
                                    }
                                }
                            }
                            else {
                                let a = data.Requirement[0];
                                let b = Object.keys(a);
                                let req = b[0];
                                if (req === "Coursed") {
                                    let source_index = findKeyByValue(nodos_0, a['Coursed']);
                                    let dest_index = findKeyByValue(nodos_0, materias[i]);
                                    let aaa = {
                                        from: source_index,
                                        to: dest_index,
                                        color: "#0000FF",
                                        arrows: "to",
                                        dashes: true
                                    };
                                    aristas.push(aaa);
                                } else if (req === "Exam") {
                                    let source_index = findKeyByValue(nodos_0, a['Exam']);
                                    let dest_index = findKeyByValue(nodos_0, materias[i]);
                                    let aaa = {
                                        from: source_index,
                                        to: dest_index,
                                        color: "#0000FF",
                                        arrows: "to"
                                    };
                                    aristas.push(aaa);
                                }
                            }
                        }

                        //solo hago la solicitud al final
                        if (i === materias.length-1) {
                            // Realiza la segunda solicitud AJAX con formDataCopy
                            var xhr2 = new XMLHttpRequest();
                            xhr2.open('POST', 'http://localhost:8080/curricula_microservice/Faculty/Carrera/Plan/evaluations', true);
                            xhr2.onload = async function () {
                                if (xhr2.status >= 200 && xhr2.status < 300) {
                                    // Procesa la respuesta de la segunda solicitud aquí
                                    var notas = JSON.parse(xhr2.responseText);

                                    for (let i = 0; i < notas.length; i++) {
                                        //solo reemplazo el estado de aprobación si no tenía datos o es de curso aprobado a examen
                                        if (nodos_process[notas[i]['id']] == null) {
                                            nodos_process[notas[i]['id']] = notas[i]['type'];
                                        }
                                        //si tengo registrado un curso aprobado pero aparece un examen
                                        //o curso exonerado, cuenta como examen
                                        if ((nodos_process[notas[i]['id']] === "CourseEvaluation" && notas[i]['type'] === "ExamEvaluation") || (notas[i]['type'] === "CourseEvaluation" && notas[i]['grade'] >= 6)) {
                                            nodos_process[notas[i]['id']] = "ExamEvaluation";
                                        }
                                    }

                                    for (let i = 0; i < materias.length; i++) {
                                        let nodo_id = "";
                                        let nodo_name = "";
                                        let nodo_materia_id = "";
                                        for (let j = 0; j < nodos.length; j++)
                                            if (nodos[j]['materia_id'] === materias[i]) {
                                                nodo_id = nodos[j]['id'];
                                                nodo_name = nodos[j]['materia_name'];
                                                nodo_materia_id = nodos[j]['materia_id']
                                                break;
                                            }


                                        const result = await getMaxRequirementLevel(facultyName, materias[i]);

                                        if (nodos_process[materias[i]] == null)
                                            nodos_final.push({
                                                id: nodo_id,
                                                label: nodo_name,
                                                level: result,
                                                materia_id: nodo_materia_id,
                                                shape: "box",
                                                color: "#FFFFFF"
                                            });
                                        else if (nodos_process[materias[i]] === "CourseEvaluation")
                                            nodos_final.push({
                                                id: nodo_id,
                                                label: nodo_name,
                                                level: result,
                                                materia_id: nodo_materia_id,
                                                shape: "box",
                                                color: "#FFFF00"
                                            });
                                        else
                                            nodos_final.push({
                                                id: nodo_id,
                                                label: nodo_name,
                                                level: result,
                                                materia_id: nodo_materia_id,
                                                shape: "box",
                                                color: "#008000",
                                                font: {color: "white"}
                                            });

                                    }
                                    let nodos_dt = new vis.DataSet(nodos_final);
                                    let aristas_dt = new vis.DataSet(aristas);
                                    var container = document.getElementById("resultado");

                                    var data2 = {
                                        nodes: nodos_dt,
                                        edges: aristas_dt,
                                    };
                                    directionInput = "DU";
                                    var options = {
                                        edges: {
                                            smooth: {
                                                type: "cubicBezier",
                                                forceDirection: "horizontal",
                                                roundness: 0.4,
                                            },
                                        },
                                        layout: {
                                            hierarchical: {
                                                direction: directionInput,
                                                sortMethod: 'directed',
                                                nodeSpacing: 200, //separacion horizontal
                                                levelSeparation: 150, // separacion vertical
                                            },
                                        },
                                        physics: false,
                                    };
                                    var network = new vis.Network(container, data2, options);

                                } else {
                                    console.error('Error en la segunda solicitud: ' + xhr2.response);
                                }
                            };
                            xhr2.send(formDataCopy);
                        }
                    })
                    .catch(error => {
                        console.error('Error:', error);
                    });
            }
        })
}

async function getMaxRequirementLevel(facultyName, cu_id) {
    let apiUrl = `http://localhost:8080/curricula_microservice/Faculty/ucs?faculty=${facultyName}&curricularUnit=${cu_id}`;

    try {
        const response = await fetch(apiUrl);

        if (!response.ok) {
            throw new Error('No se pudo obtener la respuesta de la API');
        }

        const data = await response.json();

        if (data['Requirement'].length === 0)
            if (data['Id'] === "P1") // porque P1 se dicta en el segundo semestre
                return 1;
            else
                return 0;
        else {
            const req_values = [];

            for (let i = 0; i < data['Requirement'].length; i++) {
                const subRequirement = data['Requirement'][i];

                if (subRequirement.Exam) {
                    req_values.push(await getMaxRequirementLevel(facultyName, subRequirement.Exam));
                } else if (subRequirement.Coursed) {
                    req_values.push(await getMaxRequirementLevel(facultyName, subRequirement.Coursed));
                }
                else if (subRequirement.SomeOf) {
                    for (let j = 0; j < subRequirement.SomeOf.length; j++) {
                        if (subRequirement.SomeOf[j].Exam)
                            req_values.push(await getMaxRequirementLevel(facultyName, subRequirement.SomeOf[j].Exam));
                        else if (subRequirement.SomeOf[j].Coursed)
                            req_values.push(await getMaxRequirementLevel(facultyName, subRequirement.SomeOf[j].Coursed));

                    }
                }
            }
            let base = 1;
            if (data['Id'] === "P4" || data['Id'] === "LOGICA" || data["Id"] === "METNUM" || data["Id"] === "FBD" || data["Id"] === "PROLOG" || data["Id"] === "PROGFUN" || data["Id"] === "IIO")
                base = 2;

            return base + Math.max(...req_values);
        }
    } catch (error) {
        console.error(error);
        return 0;
    }
}