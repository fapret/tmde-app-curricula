const fs = require('fs');
const util = require('util');
Ecore = require('ecore/dist/ecore.xmi');
//Ecore = require('ecore/dist/ecore.xmi');
//const Ecore = require('ecore');
//import Ecore from './src/ecore.mjs';
const resourceSet = Ecore.ResourceSet.create();

function processFile(file) {

    var resource = resourceSet.create({uri : file});

    var fileContents = fs.readFileSync(file, 'utf8');

    try {
        resource.parse(fileContents, Ecore.XMI);
    } catch(err) {
        console.log('*** Failed parsing file: ' + file);
        console.trace(err);
        return;
    }

    var firstElement = resource.get('contents').first();
    if(firstElement.eClass.values.name === 'EPackage') {
        // This is an EPackage, so add it to the registry
        console.log("::: Adding to registry: " + firstElement.get('name'));
        Ecore.EPackage.Registry.register(firstElement);
    }

    // llegar a esto (y el siguiente foreach) costó más de lo que parece
    var eClasses = Ecore.EPackage.Registry.getEPackage('estudiantesURI').get('eClassifiers').filter(function (eClassifier) {
        return (eClassifier.eClass);
    });

    var set_classes = {};

    // para cada una de las eclasses registradas en el package, las guardo en un diccionario para más fácil acceso
    eClasses.forEach(function(eClassInstance) {
        var eClassName = eClassInstance.get('name');
        set_classes[eClassName] = eClassInstance;
    });

    let estudiantes = set_classes['Student'];

    // estudiante de prueba
    var juancito = estudiantes.create({Id : '1', Name : 'Juancito'});
    console.log(juancito.get('Name'));

    var su = {};
  //  console.log(estudiantes.eAllStructuralFeatures);

    // cómo hago para obtener las referencias?
    /*
    estudiantes.get('eStructuralFeatures').forEach(function (eClassifier) {

        if (eClassifier.isTypeOf(Ecore.EStructuralFeature)) {
            console.log('a');
        }
    })
    */
    if (false) {
        console.log("::: JSON Dump of " + file);
        console.log(util.inspect(resource.to(Ecore.JSON), false, null));
    }

    if (false) {
        console.log("::: XMI Dump of " + file);
        console.log(resource.to(Ecore.XMI, true));
    }

}

// to-do: unir ambos metamodelos, esta libreria no soporta el uso de referencias

processFile('estudiante.ecore')
//processFile('asignaturas.ecore')