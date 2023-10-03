const fs = require('fs');
const startFile = './spanTexts.txt';

const ignoreTexts = ['Composición', 'Grupos', 'Unidades curriculares básicas'];

const gruposDeMaterias = ['2436', '4083', '4442', '3215', '4580', '4428', '4882', '4647', '5020', '4815', '5082', '4756', '4066', '3233', '5265', '3582'];

fs.readFile(startFile, 'utf8', (err, data) => {
    if (err) {
        console.error(err);
        return;
      }
    
      // Split the data into lines
      const lines = data.split('\n');
    
      // Process each line
      for (const line of lines) {
        // Ignore empty lines
        if (line.trim() === '') {
          continue;
        }
    
        // Ignore lines matching ignoreTexts
        if (ignoreTexts.includes(line.trim())) {
          continue;
        }
    
        // Check if the line matches any group name
        const matchingGroup = gruposDeMaterias.find(group => line.startsWith(group));
        if (matchingGroup) {
          currentGroup = matchingGroup;
          continue;
        }
    
        // Split the line by the first and last "-"
        const firstDashIndex = line.indexOf('-');
        const lastDashIndex = line.lastIndexOf('-');
        
        if (firstDashIndex === -1 || lastDashIndex === -1 || firstDashIndex === lastDashIndex) {
          console.error(`Invalid line: ${line}`);
          continue;
        }
    
        const arr = [
          line.substring(0, firstDashIndex).trim(),
          line.substring(firstDashIndex + 1, lastDashIndex).trim(),
          line.substring(lastDashIndex + 1).trim(),
        ];
    
        // Construct the XML element
        const xmlElement = `<materia grupodematerias="${currentGroup}" Id="${arr[0]}" Name="${arr[1]}" Cred="${arr[2].split(' ')[1]}" />`;
    
        // Append the XML element to a file (e.g., output.xml)
        fs.appendFileSync('output.xml', xmlElement + '\n');
      }
});