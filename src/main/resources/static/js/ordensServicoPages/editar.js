const form = document.getElementById("form-editar-os");
const id = form.dataset.id;

form.addEventListener("submit", (event) => {
    event.preventDefault();

    const formData = new FormData(form);
    const data = Object.fromEntries(formData.entries());

    // MULTISELECT: pega todos os selecionados
    data.funcionariosIds = formData.getAll("funcionariosIds");

    fetch("/api/ordensServico/atualizar/" + id, {
        method: "PUT",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(data)
    })
    .then(resp => {
        if (resp.ok) {
            alert("Ordem de Serviço atualizada com sucesso!");
            window.location.href = "/ordensServico";
            return;
        }

        return resp.json()
            .then(err => alert("Erro: " + (err.message || "Falha no servidor")));
    })
    .catch(err => {
        console.error(err);
        alert("Erro de conexão.");
    });
});
