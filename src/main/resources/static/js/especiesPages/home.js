const tabela = document.getElementById('tabela-especies');

document.getElementById('filtro-tipo').addEventListener('change', function () {
    const tipo = this.value;

    const filtroExpectativa = document.getElementById('expectativa');
    const filtroGrupo = document.getElementById('grupo');

    filtroExpectativa.classList.add('d-none');
    filtroGrupo.classList.add('d-none');

    switch (tipo) {
        case 'Expectativa de Vida':
            filtroExpectativa.classList.remove('d-none');
            break;

        case 'Tamanho de Grupo':
            filtroGrupo.classList.remove('d-none');
            break;
    }
});

document.getElementById('btn-aplicar-filtro').addEventListener('click', function () {
    const tipo = document.getElementById('filtro-tipo').value;

    if (tipo === 'Expectativa de Vida') {
        const maiorQue = document.getElementById('filtro-expectativa-maior-que').value;
        const menorQue = document.getElementById('filtro-expectativa-menor-que').value;
        fetch(`/api/especies/expectativa?maiorQue=${maiorQue}&menorQue=${menorQue}`)
            .then(response => response.json())
            .then(data => {
                criarTabela(data, tabela);
            });

    } else if (tipo === 'Tamanho de Grupo') {
        const minimo = document.getElementById('filtro-grupo-minimo').value;
        const maximo = document.getElementById('filtro-grupo-maximo').value;
        fetch(`/api/especies/grupo?minimo=${minimo}&maximo=${maximo}`)
            .then(response => response.json())
            .then(data => {
                criarTabela(data, tabela);
            });
    }
});

document.getElementById('btn-limpar-filtro').addEventListener('click', function () {
    fetch('/api/especies')
        .then(response => response.json())
        .then(data => {
            criarTabela(data, tabela);
        });
});

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
        `;

        const infoTd = document.createElement('td');
        const a = document.createElement('a');
        a.href = `/especies/${especie.id}`;
        a.title = "Ver detalhes";

        const icon = document.createElement('i');
        icon.className = 'fa-solid fa-circle-info fa-lg';

        a.appendChild(icon);
        infoTd.appendChild(a);
        linha.appendChild(infoTd);

        tabela.appendChild(linha);
    });
}
