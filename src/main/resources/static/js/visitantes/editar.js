const form = document.getElementById('edit-form');
const btnCancelar = document.getElementById('btn-cancelar-ingresso'); // Botão de deletar

// --- LÓGICA DE ATUALIZAÇÃO (PUT) ---
form.addEventListener('submit', (event) => {
    event.preventDefault();

    const idIngresso = form.dataset.id;
    const formData = new FormData(form);

    const data = {
        dataVisita: formData.get('dataVisita'),
        horaVisita: formData.get('horaVisita') + ":00",
    };

    fetch(`/api/ingressos/atualizar/${idIngresso}`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    })
    .then(response => {
        if (response.ok) {
            alert('Ingresso reagendado com sucesso!');
            window.location.href = '/ingressos'; 
        } else {
            return response.json().then(errData => {
                throw new Error(errData.message || 'Erro desconhecido');
            });
        }
    })
    .catch(error => {
        console.error('Erro na requisição:', error);
        alert('Erro ao atualizar o ingresso: ' + error.message);
    });
});

if (btnCancelar) {
    btnCancelar.addEventListener('click', () => {
        const idIngresso = form.dataset.id;

        if (confirm('Tem certeza que deseja cancelar este ingresso? Essa ação não pode ser desfeita.')) {
            
            fetch(`/api/ingressos/deletar/${idIngresso}`, { 
                method: 'DELETE'
            })
            .then(response => {
                if (response.ok || response.status === 204) {
                    alert('Ingresso cancelado com sucesso.');
                    window.location.href = '/ingressos';
                } else {
                    alert('Erro ao cancelar o ingresso. Tente novamente.');
                }
            })
            .catch(error => {
                console.error('Erro ao cancelar:', error);
                alert('Erro de conexão ao cancelar o ingresso.');
            });
        }
    });
}