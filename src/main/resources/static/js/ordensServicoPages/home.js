document.addEventListener("DOMContentLoaded", () => {

    const filtroSelect = document.getElementById("filtro-select");
    const statusContainer = document.getElementById("filtro-status-container");
    const dataContainer = document.getElementById("filtro-data-container");

    // MOSTRAR/ESCONDER FILTROS
    filtroSelect.addEventListener("change", () => {
        statusContainer.classList.add("d-none");
        dataContainer.classList.add("d-none");

        if (filtroSelect.value === "status") {
            statusContainer.classList.remove("d-none");
        } 
        else if (filtroSelect.value === "data") {
            dataContainer.classList.remove("d-none");
        }
    });

    // BOTÃO FILTRAR
    document.getElementById("btn-filtrar").addEventListener("click", () => {
        let url = "/ordensServico?";

        if (filtroSelect.value === "status") {
            const status = document.getElementById("status-select").value;
            if (!status) return alert("Selecione um status!");

            url += `status=${status}`;
        }

        else if (filtroSelect.value === "data") {
            const inicio = document.getElementById("data-inicio").value;
            const fim = document.getElementById("data-fim").value;

            if (!inicio || !fim) return alert("Informe as duas datas!");

            url += `inicio=${inicio}&fim=${fim}`;
        }

        window.location.href = url;
    });

    // BOTÃO LIMPAR
    document.getElementById("btn-limpar").addEventListener("click", () => {
        window.location.href = "/ordensServico";
    });
});
