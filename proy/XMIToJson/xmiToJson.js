//Conversor de XMI a JSON, hecho con chatgpt
const fs = require('fs');
const xml2js = require('xml2js');

// Load the XMI file
const xmiFilePath = 'udelar.xmi'; // Replace with the path to your XMI file
const xmiData = fs.readFileSync(xmiFilePath, 'utf-8');

// Parse the XMI XML into a JavaScript object
xml2js.parseString(xmiData, { explicitArray: true }, (err, result) => {
  if (err) {
    console.error('Error parsing XMI:', err);
    return;
  }

  // Function to recursively remove $ properties
  const removeDollarProperties = (obj) => {
    if (typeof obj === 'object') {
      if (Array.isArray(obj)) {
        for (const item of obj) {
          removeDollarProperties(item);
        }
      } else {
        if ('$' in obj) {
          Object.assign(obj, obj.$);
          delete obj.$;
        }
        for (const key in obj) {
          removeDollarProperties(obj[key]);
        }
      }
    }
  };

  // Process the parsed data to remove $ properties
  removeDollarProperties(result);

  // Convert the processed data to JSON
  const jsonData = JSON.stringify(result, null, 2);

  // Save the JSON data to a file or use it as needed
  fs.writeFileSync('udelar.json', jsonData, 'utf-8');

  console.log('XMI to JSON conversion completed.');
});