const form = document.getElementById('form-ingresso');

form.addEventListener('submit', (event) => {
    event.preventDefault();

    const formData = new FormData(form);

    const agora = new Date();
    
    const dataCompra = agora.toISOString().split('T')[0];
    
    const horaCompra = agora.toTimeString().split(' ')[0];

    const data = {
        dataVisita: formData.get('dataVisita'),
        horaVisita: formData.get('horaVisita') + ":00", 

        dataCompra: dataCompra,
        horaCompra: horaCompra,
        
        custo: 40.00
    };

    fetch('/api/ingressos/criar', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    })
    .then(response => {
        if (response.ok) {
            alert('Ingresso comprado com sucesso! Verifique seu e-mail.');
            window.location.href = '/login'; 
        } else {
            return response.json().then(errData => {
                alert('Erro ao realizar a compra: ' + (errData.message || 'Tente novamente.'));
            }).catch(() => {
                alert('Erro ao realizar a compra. Verifique os dados.');
            });
        }
    })
    .catch(error => {
        console.error('Erro na requisição:', error);
        alert('Erro de conexão. Não foi possível finalizar a compra.');
    });
});

const formConsulta = document.getElementById('form-consulta');
const resultadoDiv = document.getElementById('resultado-consulta');

formConsulta.addEventListener('submit', (event) => {
    event.preventDefault();

    const id = document.getElementById('consultaId').value;
    const dataVisita = document.getElementById('consultaData').value;

    resultadoDiv.innerHTML = '<div class="text-center text-muted"><i class="fa-solid fa-spinner fa-spin"></i> Buscando...</div>';

    fetch(`/api/ingressos/consultar?id=${id}&dataVisita=${dataVisita}`)
    .then(response => {
        if (response.ok) {
            return response.json();
        } else {
            throw new Error('Ingresso não encontrado');
        }
    })
    .then(ingresso => {
        const horaFormatada = ingresso.horaVisita.substring(0, 5);
        
        resultadoDiv.innerHTML = `
            <div class="alert alert-success shadow-sm" role="alert">
                <div class="d-flex justify-content-between align-items-center flex-wrap gap-3">
                    <div class="d-flex align-items-center">
                        <i class="fa-solid fa-circle-check fa-2x me-3"></i>
                        <div>
                            <h5 class="alert-heading fw-bold mb-1">Ingresso Válido!</h5>
                            <p class="mb-0">
                                <strong>ID:</strong> #${ingresso.idIngresso} <br>
                                <strong>Visita:</strong> ${formatarData(ingresso.dataVisita)} às ${horaFormatada} <br>
                                <strong>Status:</strong> Confirmado
                            </p>
                        </div>
                    </div>
                    
                    <!-- BOTÃO DE EDITAR ADICIONADO -->
                    <a href="/ingressos/editar/${ingresso.idIngresso}" class="btn btn-light text-success fw-bold border-success btn-sm">
                        <i class="fa-solid fa-pen-to-square me-1"></i>Reagendar
                    </a>
                </div>
            </div>
        `;
    })
    .catch(error => {
        resultadoDiv.innerHTML = `
            <div class="alert alert-danger d-flex align-items-center" role="alert">
                <i class="fa-solid fa-circle-xmark fa-2x me-3"></i>
                <div>
                    <strong>Ingresso não encontrado.</strong><br>
                    Verifique o ID e a Data informados.
                </div>
            </div>
        `;
    });
});

function formatarData(dataISO) {
    if (!dataISO) return '';
    const partes = dataISO.split('-');
    return `${partes[2]}/${partes[1]}/${partes[0]}`;
}