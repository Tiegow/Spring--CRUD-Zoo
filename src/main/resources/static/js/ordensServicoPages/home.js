// ELEMENTOS
const filtroSelect = document.getElementById("filtro-select");

const filtroStatusContainer = document.getElementById("filtro-status-container");
const filtroDataContainer = document.getElementById("filtro-data-container");

const btnFiltrar = document.getElementById("btn-filtrar");
const btnLimpar = document.getElementById("btn-limpar");


// ALTERAR CAMPOS VISÍVEIS
filtroSelect.addEventListener("change", () => {

    filtroStatusContainer.classList.add("d-none");
    filtroDataContainer.classList.add("d-none");

    if (filtroSelect.value === "status") {
        filtroStatusContainer.classList.remove("d-none");
    }

    if (filtroSelect.value === "data") {
        filtroDataContainer.classList.remove("d-none");
    }
});


// BOTÃO FILTRAR
btnFiltrar.addEventListener("click", () => {

    let url = "/ordens?";

    if (filtroSelect.value === "status") {
        const status = document.getElementById("status-select").value;
        if (status) url += `status=${status}&`;
    }

    if (filtroSelect.value === "data") {

        const inicio = document.getElementById("data-inicio").value;
        const fim = document.getElementById("data-fim").value;

        if (inicio) url += `dataInicio=${inicio}&`;
        if (fim) url += `dataFim=${fim}&`;
    }

    window.location.href = url;
});


// BOTÃO LIMPAR
btnLimpar.addEventListener("click", () => {
    window.location.href = "/ordens";
});
