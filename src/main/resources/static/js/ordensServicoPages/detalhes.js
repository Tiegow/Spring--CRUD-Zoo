document.getElementById("btn-delete").addEventListener("click", () => {
    if (!confirm("Tem certeza que deseja excluir esta ordem de serviço?")) return;

    const id = document.getElementById("btn-delete").dataset.id;

    fetch("/api/ordensServico/deletar/" + id, {
        method: "DELETE"
    })
    .then(resp => {
        if (resp.ok) {
            alert("Ordem de serviço removida!");
            window.location.href = "/ordensServico";
            return;
        }
        return resp.json().then(err => alert("Erro: " + err.message));
    })
    .catch(err => {
        console.error(err);
        alert("Erro de conexão.");
    });
});
