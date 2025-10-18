const filtro = document.getElementById('filtro-cargo');
const camposDinamicos = document.getElementById('campos-dinamicos');
const submitBtn = document.getElementById('add-submit');
const form = document.getElementById('add-form');

if (submitBtn) submitBtn.disabled = true;

filtro.addEventListener('change', () => {
    const cargo = filtro.value;
    camposDinamicos.innerHTML = '';

    if (submitBtn) submitBtn.disabled = (cargo === '' || cargo == null);

    if (cargo === 'Veterinário') {
        camposDinamicos.innerHTML = `
            <div class="mb-3">
                <label for="crmv" class="form-label">CRMV:</label>
                <input required type="text" class="form-control" id="crmv" name="crmv" placeholder="Ex: CRMV-SP 12345">
            </div>
        `;
    } else if (cargo === 'Tratador') {
        camposDinamicos.innerHTML = `
            <div class="mb-3">
                <label for="turno" class="form-label">Turno:</label>
                <select required id="turno" name="turno" class="form-select">
                    <option value="" selected>Selecione o turno</option>
                    <option value="Manhã">Manhã</option>
                    <option value="Tarde">Tarde</option>
                    <option value="Noite">Noite</option>
                </select>
            </div>
        `;
    }
});

form.addEventListener('submit', (event) => {
    event.preventDefault(); 

    const cargo = filtro.value;
    let endpointUrl = '';

    if (cargo === 'Veterinário') {
        endpointUrl = '/api/veterinarios/criar';
    } else if (cargo === 'Tratador') {
        endpointUrl = '/api/tratadores/criar';
    }
    
    const formData = new FormData(form);
    const data = Object.fromEntries(formData.entries());

    fetch(endpointUrl, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(data)
    })
    .then(response => {
        if (response.ok) {
            window.location.href = '/funcionarios';
            return;
        }
        return response.json()
            .then(errData => {
                const msg = JSON.stringify(errData.message);
                alert('Erro ao adicionar funcionário: ' + msg);
            });
    })
    .catch(error => {
        console.error('Erro na requisição:', error);
        alert('Ocorreu um erro de conexão.');
    });
});