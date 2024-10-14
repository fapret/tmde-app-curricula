function consultarPlan() {
	const allFacultiesSelect = document.getElementById("facultades");
	const faculty = allFacultiesSelect.value;
	const allCareersSelect = document.getElementById("carreras");
	const career = allCareersSelect.value;
	const allPlansSelect = document.getElementById("planes");
	const plan = allPlansSelect.value;

	const url = `https://tmde-api.fapret.com:8443/curricula_microservice/Faculty/Carrera/Plan/ucs?faculty=${faculty}&career=${career}&plan=${plan}&valid_flag=true`;
    var nodos = [];
    var nodos_0 = [];
    var nodos_process = {};
    var nodos_final = [];
    var aristas = [];
    
    fetch(url)
            .then(response => response.json())
            .then(materias => {

			const facultyName = document.getElementById("facultades").value;
			
              for (let i = 0; i < materias.length; i++) {
                nodos_0.push({id: i + 1, materia_id: materias[i]});
              }

              for (let i = 0; i < materias.length; i++) {
                let apiUrl = `https://tmde-api.fapret.com:8443/curricula_microservice/Faculty/ucs?faculty=${faculty}&curricularUnit=` + materias[i];
                fetch(apiUrl)
                        .then(response => {
                          if (!response.ok) {
                            throw new Error('No se pudo obtener la respuesta de la API');
                          }
                          return response.json(); // Parsear la respuesta JSON
                        })
                        .then(async data => {
                          const result = await getMaxRequirementLevel(facultyName, materias[i]);
                          nodos.push({
                            id: i + 1,
                            materia_id: materias[i],
                            materia_name: data['Name'],
                            level: result,
                            label: data['Name'],
                            shape: "box",
                            color: "#F28C28",
                            font: {color: "white"}
                          });
                          nodos_process[materias[i]] = null;

                          if ((data.Requirement).length > 0) {
                            //todo: si es un someof, tengo que ver cuales de las N se cumple, y mostrar solo las aristas de ella. por ejemplo, tprog y sus 2 vias de cursado
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
                            } else {
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
                          let nodos_dt = new vis.DataSet(nodos);
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
                        })

                        .catch(error => {
                          console.error('Error:', error);
                        });
              }

            });
}