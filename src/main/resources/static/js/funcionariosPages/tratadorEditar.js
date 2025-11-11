const form = document.getElementById('edit-form');
const id = form.dataset.id;
const errorMessageContainer = document.getElementById('form-error-message');

form.addEventListener('submit', (event) => {
    event.preventDefault();

    if (errorMessageContainer) {
        errorMessageContainer.classList.add('d-none');
        errorMessageContainer.textContent = ''; 
    }

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
            alert('Informações atualizadas com sucesso.');
            return;
        } else {
            response.json().then(errData => {
                const mensagemDeErro = errData.message || 'Verifique os dados informados.';
                
                console.error('Erro ao salvar o funcionário: ' + mensagemDeErro);

                if (errorMessageContainer) {
                    errorMessageContainer.textContent = mensagemDeErro;
                    errorMessageContainer.classList.remove('d-none'); 
                }
                
            }).catch(() => {
                console.error('Erro ao salvar o funcionário. Verifique os dados.');
                if (errorMessageContainer) {
                    errorMessageContainer.textContent = 'Erro no servidor. Tente novamente mais tarde.';
                    errorMessageContainer.classList.remove('d-none');
                }
            });
        }
        return response.json()
            .then(errData => {
                const msg = JSON.stringify(errData.message);
                alert('Erro ao editar funcionário: ' + msg);
            });
    })
    .catch(error => {
        console.error('Erro na requisição:', error);
        if (errorMessageContainer) {
            errorMessageContainer.textContent = 'Não foi possível conectar ao servidor.';
            errorMessageContainer.classList.remove('d-none');
        }
    });
});