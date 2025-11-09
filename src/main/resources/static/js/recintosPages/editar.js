const form = document.getElementById('editar-recinto-form');

form.addEventListener('submit', (event) => {
    event.preventDefault(); 

    const recintoId = form.dataset.id;

    const formData = new FormData(form);
    const data = Object.fromEntries(formData.entries());

    // Conversões
    data.areaHabitavel = parseFloat(data.areaHabitavel);
    data.populacao = parseInt(data.populacao);

    // Múltiplos
    data.tratadorIds = formData.getAll('tratadorIds').map(id => parseInt(id));
    data.animaisIds = formData.getAll('animaisIds').map(id => parseInt(id));

    fetch(`/api/recintos/${recintoId}`, {
        method: 'PUT',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(data)
    })
    .then(response => {
        if (response.ok) {
            window.location.href = '/recintos';
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
