const form = document.getElementById('add-form');

form.addEventListener('submit', (event) => {
    event.preventDefault(); 

    const formData = new FormData(form);
    const data = Object.fromEntries(formData.entries());

    // Converter valores numéricos (pois FormData devolve tudo como string)
    data.expectativaVida = parseFloat(data.expectativaVida);
    data.tamanhoMinimoGrupo = parseInt(data.tamanhoMinimoGrupo);
    data.tamanhoMaximoGrupo = parseInt(data.tamanhoMaximoGrupo);

    fetch('/api/especies/criar', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(data)
    })
    .then(response => {
        if (response.ok) {
            window.location.href = '/especies';
            alert('Espécie registrada com sucesso.');
            return;
        }
        return response.json().then(errData => {
            const msg = JSON.stringify(errData.message);
            alert('Erro ao adicionar espécie: ' + msg);
        });
    })
    .catch(error => {
        console.error('Erro na requisição:', error);
        alert('Ocorreu um erro de conexão.');
    });
});
