/*
    tmde-app-curricula is a software that helps students build their curricula and
    see what curricular units they can register to, and track how their career was
    or will be.
    Copyright (C) 2025  Santiago Nicolás Díaz Conde

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <https://www.gnu.org/licenses/>.

	Santiago Nicolás Díaz Conde Email: sndc.33@gmail.com and contact@fapret.com
*/
function displayPlanStats(){
    const allDiscoveriesSelect = document.getElementById("discoveries");
    const uuid = allDiscoveriesSelect.value;
    const allPlansSelect = document.getElementById("planes");
    const plan = allPlansSelect.value;
    const allCareersSelect = document.getElementById("carreras");
    const career = allCareersSelect.value;
    const url = `http://127.0.0.1:9006/${uuid}/${career}/${plan}`;
    fetch(url)
    .then(response => response.json())
    .then(data => {
        const resultadoDiv = document.getElementById("resultado");
                resultadoDiv.innerHTML = `
            <label class="info-label" data-lang="discoverUUID">UUID de Descubrimiento:</label>
            <label class="info-value">${data.uuid}</label>
            <br>
            <label class="info-label" data-lang="uc">Carrera:</label>
            <label class="info-value">${data.career}</label>
            <br>
            <label class="info-label" data-lang="plan">Plan:</label>
            <label class="info-value">${data.plan}</label>
            <br>
            <br>
            <label class="info-value">${data.inscriptionsCounter}</label>
            <label class="info-label" data-lang="studentsInscriptions">Estudiantes se inscribieron al plan</label>
            
        `;
    });
}