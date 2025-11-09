const form = document.getElementById('editar-recinto-form');

form.addEventListener('submit', (event) => {
    event.preventDefault(); 

    const recintoId = form.dataset.id;

    const formData = new FormData(form);
    
    const data = {
        nome: formData.get('nome'),
        areaHabitavel: parseFloat(formData.get('areaHabitavel')) || 0,
        status: formData.get('status'),
        
        planoDieta: {
            quantidadeCarne: parseInt(formData.get('quantidadeCarne')) || 0,
            quantidadeVegetais: parseInt(formData.get('quantidadeVegetais')) || 0
        },

        tratadorIds: formData.getAll('tratadorIds').map(id => parseInt(id))
    };
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
