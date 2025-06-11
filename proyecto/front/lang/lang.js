function translatePage(lang) {
  const elements = document.querySelectorAll("[data-lang]");
  elements.forEach(el => {
    const key = el.getAttribute("data-lang");
    if (translations[lang] && translations[lang][key]) {
      el.textContent = translations[lang][key];
    }
  });
  document.documentElement.lang = lang;
  localStorage.setItem("lang", lang);
}

document.addEventListener("DOMContentLoaded", () => {
  const selector = document.getElementById("languageSelector");
  const savedLang = localStorage.getItem("lang") || "es";

  selector.value = savedLang;
  translatePage(savedLang);

  selector.addEventListener("change", () => {
    translatePage(selector.value);
  });
});

const translations = {
  es: {
    welcome: "¡Bienvenido a Currícula App!",
    selectOption: "Seleccione una opción del menú de la izquierda para comenzar.",
    createModel: "Crear/Expandir Modelo",
    addPlan: "Agregar Inscripción a plan",
    addCourse: "Agregar Inscripción a curso",
    addEvaluation: "Agregar Evaluación",
    evalUC: "Evaluar Unidad Curricular",
    viewCurriculum: "Ver Currícula",
    getLog: "Obtener Log de Estudiantes",
    consultFaculty: "Consultar Facultad",
    consultPlan: "Consultar Plan",
    consultSubject: "Consultar Materia",
    consultUC: "Consultar Unidad Curricular",
    viewPlanCurriculum: "Ver Currícula de Plan",
    discoverDFG: "Descubrir procesos (DFG)",
    discoverFreqDFG: "Descubrir procesos (Freq DFG)",
    discoverNetDFG: "Descubrir procesos (Net DFG)",
    viewDocsEM: "Ver Documentación (EM)",
    viewDocsPM: "Ver Documentación (PM)",
    processMining: "MINERIA DE PROCESOS",
    student: "ESTUDIANTE",
    model: "MODELO",
    ci: "Cedula (Opcional):",
    faculty: "Facultad:",
    selectFaculty: "Seleccionar Facultad",
    selectCareer: "Seleccionar Carrera",
    selectPlan: "Seleccionar Plan",
    career: "Carrera:",
    plan: "Plan:",
    course: "Curso:",
    uc: "Unidad Curricular:",
    evalType: "Tipo evaluación:",
    eval: "Evaluacion:",
    courseOpt: "Curso",
    examOpt: "Examen",
    partialOpt: "Parcial",
    selectUC: "Seleccionar Unidad Curricular",
    addEval: "Agregar Evaluacion",
    studentModel: "Modelo de Estudiantes:",
    addInsCourse: "Agregar inscripción a curso",
    addPlan: "Agregar Plan",
    baseModel: "Modelo Base (Opcional):",
    name: "Nombre:",
    ID: "CI:",
    downloadStudent: "Descargar Modelo Estudiante",
    evalPrev: "Evaluar previas",
    selectSubject: "Consultar Materia",
    seeCurricula: "Ver currícula"
  },
  en: {
    welcome: "Welcome to Curricula App!",
    selectOption: "Select an option from the left menu to get started.",
    createModel: "Create/Expand Model",
    addPlan: "Add Enrollment to Plan",
    addCourse: "Add Enrollment to Course",
    addEvaluation: "Add Evaluation",
    evalUC: "Evaluate Course Unit",
    viewCurriculum: "View Curriculum",
    getLog: "Get Student Log",
    consultFaculty: "Consult Faculty",
    consultPlan: "Consult Plan",
    consultSubject: "Consult Subject",
    consultUC: "Consult Course Unit",
    viewPlanCurriculum: "View Plan Curriculum",
    discoverDFG: "Discover Processes (DFG)",
    discoverFreqDFG: "Discover Processes (Freq DFG)",
    discoverNetDFG: "Discover Processes (Net DFG)",
    viewDocsEM: "View Documentation (EM)",
    viewDocsPM: "View Documentation (PM)",
    processMining: "PROCESS MINING",
    student: "STUDENT",
    model: "MODEL",
    ci: "ID (Optional):",
    faculty: "Faculty:",
    selectFaculty: "Select Faculty",
    selectCareer: "Select Career",
    selectPlan: "Select Plan",
    career: "Career:",
    plan: "Plan:",
    course: "Course:",
    eval: "Evaluation:",
    uc: "Curricular Unit:",
    evalType: "Evaluation Type:",
    courseOpt: "Course",
    examOpt: "Exam",
    partialOpt: "Partial",
    selectUC: "Select Curricular Unit",
    addEval: "Add Evaluation",
    studentModel: "Student Model:",
    addInsCourse: "Add inscription to course",
    addPlan: "Add Plan",
    baseModel: "Base Model (Optional):",
    name: "Name:",
    ID: "ID:",
    downloadStudent: "Download Student Model",
    evalPrev: "Evaluate previatures",
    selectSubject: "Select Subject",
    seeCurricula: "See Curriculum"
  }
};