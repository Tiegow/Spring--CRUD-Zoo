document.getElementById("btn-delete").addEventListener("click", () => {
    if (!confirm("Tem certeza que deseja excluir este evento?")) return;

    const id = document.getElementById("btn-delete").dataset.id;

    fetch("/api/eventos/deletar/" + id, {
        method: "DELETE"
    })
    .then(resp => {
        if (resp.ok) {
            alert("Evento removido!");
            window.location.href = "/eventos";
            return;
        }
        return resp.json().then(err => alert("Erro: " + err.message));
    })
    .catch(err => {
        console.error(err);
        alert("Erro de conexão.");
    });
});
