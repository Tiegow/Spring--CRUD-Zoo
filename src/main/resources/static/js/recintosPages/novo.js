const form = document.getElementById('add-form');

form.addEventListener('submit', (event) => {
    event.preventDefault(); 

    const formData = new FormData(form);
    
    const data = Object.fromEntries(formData.entries());

    data.areaHabitavel = parseFloat(data.areaHabitavel);
    data.planoDietaId = parseInt(data.planoDietaId);

    data.tratadorIds = formData.getAll('tratadorIds').map(id => parseInt(id));

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
            const msg = JSON.stringify(errData.message);
            console.error('Erro ao adicionar recinto:', msg);
        });
    })
    .catch(error => {
        console.error('Erro na requisição:', error);
    });
});
