const tabelaCorpo = document.getElementById("tbody-recintos");
const btnLimpar = document.getElementById("limparFiltroBtn");

document.getElementById("filtroTipo").addEventListener("change", function () {
    const tipo = this.value;

    const campoArea = document.getElementById("campoArea");
    const campoPop = document.getElementById("campoPopulacao");

    campoArea.classList.add("d-none");
    campoPop.classList.add("d-none");

    if (tipo === "area") {
        campoArea.classList.remove("d-none");
    } else if (tipo === "populacao") {
        campoPop.classList.remove("d-none");
    }
});

document.getElementById("aplicarFiltroBtn").addEventListener("click", function () {
    const tipo = document.getElementById("filtroTipo").value;

    if (tipo === "area") {
        const min = document.getElementById("areaMin").value;
        const max = document.getElementById("areaMax").value;

        fetch(`/api/recintos/area?minimo=${min}&maximo=${max}`)
            .then(r => r.json())
            .then(data => criarTabela(data, tabelaCorpo));

    } else if (tipo === "populacao") {
        const min = document.getElementById("popMin").value;
        const max = document.getElementById("popMax").value;

        fetch(`/api/recintos/populacao?minimo=${min}&maximo=${max}`)
            .then(r => r.json())
            .then(data => criarTabela(data, tabelaCorpo));
    }
});

// Botão Limpar
btnLimpar.addEventListener("click", () => {
    window.location.href = "/recintos";
});

function criarTabela(data, tabelaCorpo) {
    tabelaCorpo.innerHTML = "";

    data.forEach(recinto => {
        const linha = document.createElement("tr");

        linha.innerHTML = `
            <td>${recinto.id}</td>
            <td>${recinto.nome}</td>
            <td>${recinto.status}</td>
            <td>${recinto.tipo}</td>
            <td>${recinto.areaHabitavel}</td>
            <td>${recinto.populacao}</td>
        `;

        const infoTd = document.createElement("td");
        const a = document.createElement("a");
        a.href = `/recintos/${recinto.id}`;
        a.title = "Ver detalhes";

        const icon = document.createElement("i");
        icon.className = "fa-solid fa-circle-info fa-lg";

        a.appendChild(icon);
        infoTd.appendChild(a);

        linha.appendChild(infoTd);
        tabelaCorpo.appendChild(linha);
    });
}


