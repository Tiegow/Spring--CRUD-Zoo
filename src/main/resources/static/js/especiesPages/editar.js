const form = document.getElementById('edit-especie-form');
const id = form.dataset.id;

form.addEventListener('submit', (event) => {
    event.preventDefault();

    const formData = new FormData(form);
    const data = Object.fromEntries(formData.entries());

    fetch("/api/especies/atualizar/" + id, {
        method: 'PUT',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(data)
    })
    .then(response => {
        if (response.ok) {
            alert("Espécie atualizada com sucesso!");
            window.location.href = '/especies';
            return;
        }
        return response.json().then(err => {
            alert("Erro ao atualizar espécie: " + JSON.stringify(err.message));
        });
    })
    .catch(error => {
        console.error("Erro na requisição:", error);
        alert("Ocorreu um erro de conexão.");
    });
});
