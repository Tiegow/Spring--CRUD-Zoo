const form = document.getElementById('form-nova-ordensServico');

form.addEventListener('submit', (event) => {
    event.preventDefault();

    const formData = new FormData(form);
    const data = Object.fromEntries(formData.entries());

    // funcionários múltiplos
    data.funcionariosIds = formData.getAll("funcionariosIds");

    fetch("/api/ordensServico/criar", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(data)
    })
    .then(response => {
        if (response.ok) {
            alert("Ordem de Serviço criada com sucesso!");
            window.location.href = "/ordensServico";
            return;
        }

        return response.json().then(err => alert("Erro: " + err.message));
    })
    .catch(err => {
        console.error(err);
        alert("Erro de conexão.");
    });
});
