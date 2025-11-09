const form = document.getElementById("novo-recinto-form");

form.addEventListener("submit", (e) => {
    e.preventDefault();

    const formData = new FormData(form);
    const data = Object.fromEntries(formData.entries());

    // Listas múltiplas
    data.tratadorIds = formData.getAll("tratadorIds").map(id => parseInt(id));
    data.animaisIds = formData.getAll("animaisIds").map(id => parseInt(id));

    // Conversões
    if (data.areaHabitavel) data.areaHabitavel = parseFloat(data.areaHabitavel);
    if (data.populacao) data.populacao = parseInt(data.populacao);

    if (data.planoDietaId === "") data.planoDietaId = null;

    fetch("/api/recintos", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(data)
    })
    .then(resp => {
        if (resp.ok) {
            alert("Recinto criado com sucesso!");
            window.location.href = "/recintos";
            return;
        }
        return resp.json().then(err => alert(err.message));
    })
    .catch(err => alert("Erro de conexão com o servidor."));
});
