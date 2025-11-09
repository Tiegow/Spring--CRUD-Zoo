const form = document.getElementById('novo-recinto-form');

form.addEventListener('submit', (event) => {
    event.preventDefault();

    const formData = new FormData(form);

    const data = {
        nome: formData.get('nome'),
        areaHabitavel: parseFloat(formData.get('areaHabitavel')) || 0,
        status: formData.get('status'),
        planoDieta: {
            quantidadeCarne: parseInt(formData.get('quantidadeCarne')) || 0,
            quantidadeVegetais: parseInt(formData.get('quantidadeVegetais')) || 0
        },
        animaisIds: formData.getAll('animaisIds').map(id => parseInt(id)),
        tratadorIds: formData.getAll('tratadorIds').map(id => parseInt(id))
    };

    fetch('/api/recintos', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(data)
    })
    .then(response => {
        if (response.ok) {
            window.location.href = '/recintos';
            console.log('Recinto registrado com sucesso.');
            return;
        }
        return response.json().then(errData => {
            const msg = errData.message || "Erro desconhecido";
            console.error('Erro ao adicionar recinto:', msg);
        });
    })
    .catch(error => {
        console.error('Erro na requisição:', error);
    });
});
