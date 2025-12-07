const filtroTipo = document.getElementById('filtro-tipo');
const btnFiltrar = document.getElementById('btn-aplicar-filtro');
const btnLimpar = document.getElementById('btn-limpar-filtro');

const divPreco = document.getElementById('filtro-preco');
const divDataCompra = document.getElementById('filtro-data-compra');
const divDataVisita = document.getElementById('filtro-data-visita');

const inputPrecoMin = document.getElementById('precoMin');
const inputPrecoMax = document.getElementById('precoMax');
const inputInicioCompra = document.getElementById('inicioCompra');
const inputFimCompra = document.getElementById('fimCompra');
const inputInicioVisita = document.getElementById('inicioVisita');
const inputFimVisita = document.getElementById('fimVisita');

function esconderInputs() {
    divPreco.classList.add('d-none');
    divDataCompra.classList.add('d-none');
    divDataVisita.classList.add('d-none');
}

filtroTipo.addEventListener('change', function() {
    esconderInputs();
    const valor = this.value;

    if (valor === 'Preco') {
        divPreco.classList.remove('d-none');
    } else if (valor === 'DataCompra') {
        divDataCompra.classList.remove('d-none');
    } else if (valor === 'DataVisita') {
        divDataVisita.classList.remove('d-none');
    }
});

btnFiltrar.addEventListener('click', function() {
    const tipo = filtroTipo.value;
    const params = new URLSearchParams();

    if (tipo === 'Preco') {
        if (inputPrecoMin.value) params.append('precoMin', inputPrecoMin.value);
        if (inputPrecoMax.value) params.append('precoMax', inputPrecoMax.value);
    } 
    else if (tipo === 'DataCompra') {
        if (inputInicioCompra.value) params.append('inicioCompra', inputInicioCompra.value);
        if (inputFimCompra.value) params.append('fimCompra', inputFimCompra.value);
    } 
    else if (tipo === 'DataVisita') {
        if (inputInicioVisita.value) params.append('inicioVisita', inputInicioVisita.value);
        if (inputFimVisita.value) params.append('fimVisita', inputFimVisita.value);
    }

    if ([...params].length > 0) {
        window.location.href = `/ingressos?${params.toString()}`;
    } else {
        window.location.href = '/ingressos';
    }
});

btnLimpar.addEventListener('click', function() {
    window.location.href = '/ingressos';
});

document.addEventListener('DOMContentLoaded', () => {
    const params = new URLSearchParams(window.location.search);
    if (params.has('precoMin') || params.has('precoMax')) {
        filtroTipo.value = 'Preco';
        divPreco.classList.remove('d-none');
        inputPrecoMin.value = params.get('precoMin');
        inputPrecoMax.value = params.get('precoMax');
    } else if (params.has('inicioCompra') || params.has('fimCompra')) {
        filtroTipo.value = 'DataCompra';
        divDataCompra.classList.remove('d-none');
        inputInicioCompra.value = params.get('inicioCompra');
        inputFimCompra.value = params.get('fimCompra');
    } else if (params.has('inicioVisita') || params.has('fimVisita')) {
        filtroTipo.value = 'DataVisita';
        divDataVisita.classList.remove('d-none');
        inputInicioVisita.value = params.get('inicioVisita');
        inputFimVisita.value = params.get('fimVisita');
    }
});