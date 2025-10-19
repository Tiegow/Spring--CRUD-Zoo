const form = document.getElementById('add-form');

form.addEventListener('submit', (event) => {
    event.preventDefault();

    const formData = new FormData(form);

    const data = Object.fromEntries(formData.entries());

    fetch('/api/animais/criar', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data) 
    })
    .then(response => {
        if (response.ok) {
            alert('Animal salvo com sucesso!');
            window.location.href = '/animais';
        } else {
            alert('Erro ao salvar o animal. Verifique os dados.');
        }
    })
    .catch(error => {
        console.error('Erro na requisição:', error);
        alert('Erro de conexão. Não foi possível salvar o animal.');
    });
});