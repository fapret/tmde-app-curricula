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

    if (false) {
        console.log("::: JSON Dump of " + file);
        console.log(util.inspect(resource.to(Ecore.JSON), false, null));
    }

    if (false) {
        console.log("::: XMI Dump of " + file);
        console.log(resource.to(Ecore.XMI, true));
    }

}

function extract_classes(uri) {

    // llegar a esto (y el siguiente foreach) costó más de lo que parece
    var eClasses = Ecore.EPackage.Registry.getEPackage(uri).get('eClassifiers').filter(function (eClassifier) {
        return (eClassifier.eClass);
    });


    // para cada una de las eclasses registradas en el package, las guardo en un diccionario para más fácil acceso
    eClasses.forEach(function(eClassInstance) {
        var eClassName = eClassInstance.get('name');
        set_classes[eClassName] = eClassInstance;
    });

}

function main() {
    processFile('asignaturas.ecore')
    processFile('estudiantes-references.ecore')


    var metamodelos_URI = new Set(["asignaturasURI", "estudiantesURI"]);
    metamodelos_URI.forEach(function (current_uri) {
        extract_classes(current_uri);
    })
}

var set_classes = {};
main();