const selectEspecie = document.getElementById('select-especie');
const selectRecinto = document.getElementById('select-recinto');
const avisoArea = document.getElementById('recinto-aviso-area');
const errorMessageContainer = document.getElementById('form-error-message');

function verificarAreas() {
    const especieOption = selectEspecie.options[selectEspecie.selectedIndex];
    const recintoOption = selectRecinto.options[selectRecinto.selectedIndex];

    if (!especieOption || !recintoOption || !especieOption.value || !recintoOption.value) {
        avisoArea.classList.add('d-none');
        return;
    }

    const areaAdequada = parseFloat(especieOption.dataset.areaAdequada);
    const areaHabitavel = parseFloat(recintoOption.dataset.areaHabitavel);

    if (!isNaN(areaAdequada) && !isNaN(areaHabitavel) && areaHabitavel < areaAdequada) {
        avisoArea.classList.remove('d-none');
    } else {
        avisoArea.classList.add('d-none');
    }
}

selectEspecie.addEventListener('change', verificarAreas);
selectRecinto.addEventListener('change', verificarAreas);

const form = document.getElementById('add-form');

form.addEventListener('submit', (event) => {
    event.preventDefault();

    if (errorMessageContainer) {
        errorMessageContainer.classList.add('d-none');
        errorMessageContainer.textContent = ''; 
    }

    const formData = new FormData(form);

    const data = {
        nome: formData.get('nome'),
        sexo: formData.get('sexo'),
        nascimento: formData.get('nascimento'),
        origem: formData.get('origem'),
        especieId: parseInt(formData.get('especieId')) || null,
        recintoId: parseInt(formData.get('recintoId')) || null,
        veterinarioId: parseInt(formData.get('veterinarioId')) || null
    };

    fetch('/api/animais/criar', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    })
    .then(response => {
        if (response.ok) {
            console.log('Animal salvo com sucesso!');
            window.location.href = '/animais';
        } else {
            response.json().then(errData => {
                const mensagemDeErro = errData.message || 'Verifique os dados informados.';
                
                console.error('Erro ao salvar o animal: ' + mensagemDeErro);

                if (errorMessageContainer) {
                    errorMessageContainer.textContent = mensagemDeErro;
                    errorMessageContainer.classList.remove('d-none'); 
                }
            }).catch(() => {
                console.error('Erro ao salvar o animal. Verifique os dados.');
                if (errorMessageContainer) {
                    errorMessageContainer.textContent = 'Erro no servidor. Tente novamente mais tarde.';
                    errorMessageContainer.classList.remove('d-none');
                }
            });
        }
    })
    .catch(error => {
        console.error('Erro na requisição:', error);
    });
});
