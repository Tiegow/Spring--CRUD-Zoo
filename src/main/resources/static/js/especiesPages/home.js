const tabela = document.getElementById('tabela-especies');
const filtroTipo = document.getElementById('filtro-tipo');

// Containers dos filtros
const filtroExpectativa = document.getElementById('expectativa');
const filtroGrupo = document.getElementById('grupo');


const btnLimpar = document.getElementById("btn-limpar-filtro");


// Alternar filtros visuais
filtroTipo.addEventListener('change', function () {

    filtroExpectativa.classList.add('d-none');
    filtroGrupo.classList.add('d-none');

    if (this.value === 'Expectativa de Vida') {
        filtroExpectativa.classList.remove('d-none');
    }

    if (this.value === 'Tamanho de Grupo') {
        filtroGrupo.classList.remove('d-none');
    }
});


// Aplicar filtro
document.getElementById('btn-aplicar-filtro').addEventListener('click', function () {
    const tipo = filtroTipo.value;

    if (tipo === 'Expectativa de Vida') {

        const min = document.getElementById('filtro-expectativa-maior-que').value || 0;
        const max = document.getElementById('filtro-expectativa-menor-que').value || 9999;

        fetch(`/api/especies/expectativa?maiorQue=${min}&menorQue=${max}`)
            .then(r => r.json())
            .then(d => criarTabela(d, tabela));

    } else if (tipo === 'Tamanho de Grupo') {

        const min = document.getElementById('filtro-grupo-min').value || 0;
        const max = document.getElementById('filtro-grupo-max').value || 9999;

        fetch(`/api/especies/grupo?minimo=${min}&maximo=${max}`)
            .then(r => r.json())
            .then(d => criarTabela(d, tabela));
    }
});


// BOTÃO LIMPAR
btnLimpar.addEventListener("click", () => {
    window.location.href = "/especies";
});


// Atualizar tabela
function criarTabela(data, tabela) {
    tabela.innerHTML = '';

    data.forEach(especie => {
        const linha = document.createElement('tr');

        linha.innerHTML = `
            <td>${especie.id}</td>
            <td>${especie.nome}</td>
            <td>${especie.expectativaVida}</td>
            <td>${especie.areaAdequada}</td>
            <td>${especie.tamanhoMinimoGrupo}</td>
            <td>${especie.tamanhoMaximoGrupo}</td>
            <td>
                <a href="/especies/${especie.id}">
                    <i class="fa-solid fa-circle-info fa-lg"></i>
                </a>
            </td>
        `;

        tabela.appendChild(linha);
    });
}
