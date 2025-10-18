const form = document.getElementById('edit-form');
const id = form.dataset.id;

form.addEventListener('submit', (event) => {
    event.preventDefault();

    const formData = new FormData(form);
    const data = Object.fromEntries(formData.entries());

    fetch("/api/tratadores/atualizar/" + id, {
        method: 'PUT',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(data)
    })
    .then(response => {
        if (response.ok) {
            window.location.href = '/funcionarios';
            return;
        }
        return response.json()
            .then(errData => {
                const msg = JSON.stringify(errData.message);
                alert('Erro ao editar funcionário: ' + msg);
            });
    })
    .catch(error => {
        console.error('Erro na requisição:', error);
        alert('Ocorreu um erro de conexão.');
    });
});