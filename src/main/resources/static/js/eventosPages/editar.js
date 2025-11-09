const form = document.getElementById("form-editar-evento");
const id = form.dataset.id;

form.addEventListener("submit", (event) => {
    event.preventDefault();

    const formData = new FormData(form);
    const data = Object.fromEntries(formData.entries());

    data.recintosIds = formData.getAll("recintosIds");

    fetch("/api/eventos/atualizar/" + id, {
        method: "PUT",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(data)
    })
    .then(resp => {
        if (resp.ok) {
            alert("Evento atualizado com sucesso!");
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
