fs = require('fs');
util = require('util');
//Ecore = require('ecore/dist/ecore.xmi');
Ecore = require('ecore/dist/ecore.xmi');

var resourceSet = Ecore.ResourceSet.create();

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
processFile('estudiante.ecore')
//processFile('asignaturas.ecore')