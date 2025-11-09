const form = document.getElementById('editar-recinto-form');

form.addEventListener('submit', (event) => {
    event.preventDefault(); 

    const recintoId = form.dataset.id;

    const formData = new FormData(form);
    const data = Object.fromEntries(formData.entries());

    // Converte campos numéricos
    data.areaHabitavel = parseFloat(data.areaHabitavel);
    data.populacao = parseInt(data.populacao);

    // Tratadores múltiplos
    data.tratadorIds = formData.getAll('tratadorIds').map(id => parseInt(id));

    fetch(`/api/recintos/${recintoId}`, {
        method: 'PUT',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(data)
    })
    .then(response => {
        if (response.ok) {
            window.location.href = '/recintos';
            console.log('Recinto atualizado com sucesso.');
            return;
        }
        
        return response.json().then(errData => {
            const msg = errData.message || "Verifique os dados preenchidos.";
            console.error('Erro ao atualizar recinto:', msg);
        });
    })
    .catch(error => {
        console.error('Erro na requisição:', error);
    });
});
