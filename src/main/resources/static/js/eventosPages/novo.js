const form = document.getElementById('form-novo-evento');

form.addEventListener('submit', (event) => {
    event.preventDefault();

    const formData = new FormData(form);
    const data = Object.fromEntries(formData.entries());

    // múltiplos recintos → vira array
    data.recintosIds = formData.getAll("recintosIds");

    fetch("/api/eventos/criar", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(data)
    })
    .then(response => {
        if (response.ok) {
            alert("Evento criado com sucesso!");
            window.location.href = "/eventos";
            return;
        }

        return response.json().then(err => alert("Erro: " + err.message));
    })
    .catch(err => {
        console.error(err);
        alert("Erro de conexão.");
    });
});
