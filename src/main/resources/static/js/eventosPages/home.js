const filtroSelect = document.getElementById("filtro-select");
const filtroCapacidadeContainer = document.getElementById("filtro-capacidade-container");

const btnFiltrar = document.getElementById("btn-filtrar");
const btnLimpar = document.getElementById("btn-limpar");


// EXIBIR OU ESCONDER CAMPOS
filtroSelect.addEventListener("change", () => {

    filtroCapacidadeContainer.classList.add("d-none");

    if (filtroSelect.value === "capacidade") {
        filtroCapacidadeContainer.classList.remove("d-none");
    }
});


// BOTÃO FILTRAR
btnFiltrar.addEventListener("click", () => {

    let url = "/eventos?";

    if (filtroSelect.value === "capacidade") {

        const min = document.getElementById("cap-min").value;
        const max = document.getElementById("cap-max").value;

        if (min) url += `minCap=${min}&`;
        if (max) url += `maxCap=${max}&`;
    }

    window.location.href = url;
});


// BOTÃO LIMPAR
btnLimpar.addEventListener("click", () => {
    window.location.href = "/eventos";
});
