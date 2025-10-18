const tabela = document.getElementById('tabela-funcionarios');

document.getElementById('filtro-tipo').addEventListener('change', function() {
    const tipo = this.value;
    const filtroDinamico = document.getElementById('filtro-dinamico');
    filtroDinamico.querySelectorAll('div').forEach(div => div.classList.add('d-none'));

    switch (tipo) {
        case 'Especialização': {
            const filtro = document.getElementById('espec');
            filtro.classList.remove('d-none');
            break;
        }
        case 'Remuneração': {
            const filtro = document.getElementById('remun');
            filtro.classList.remove('d-none');
            break;
        }
        default:
            document.getElementById('espec').classList.add('d-none');
            document.getElementById('remun').classList.add('d-none');
    }
});

document.getElementById('btn-aplicar-filtro').addEventListener('click', function() {
    const tipo = document.getElementById('filtro-tipo').value;

    if (tipo === 'Especialização') {
        const especializacao = document.getElementById('filtro-especializacao').value;
        fetch(`/api/funcionarios/especializacao/${especializacao}`)
            .then(response => response.json())
            .then(data => {
                criarTabela(data, tabela);
            });
    } else if (tipo === 'Remuneração') {
        const maiorQue = document.getElementById('filtro-remuneracao-maior-que').value;
        const menorQue = document.getElementById('filtro-remuneracao-menor-que').value;
        fetch(`/api/funcionarios/remuneracao?maiorQue=${maiorQue}&menorQue=${menorQue}`)
            .then(response => response.json())
            .then(data => {
                criarTabela(data, tabela);
            });
    } else if (tipo === "Ociosos") {
        fetch(`/api/tratadores/listarOciosos`)
            .then(response => response.json())
            .then(data => {
                criarTabela(data, tabela);
            });
    }
});

document.getElementById('btn-limpar-filtro').addEventListener('click', function() {
    fetch('/api/funcionarios')
        .then(response => response.json())
        .then(data => {
            criarTabela(data, tabela);
        });
});

function criarTabela(data, tabela) {
    tabela.innerHTML = '';
    data.forEach(funcionario => {
        const linha = document.createElement('tr');
        linha.innerHTML = `
            <td>${funcionario.id}</td>
            <td>${funcionario.nome}</td>
            <td>${funcionario.cpf}</td>
            <td>${funcionario.especializacao}</td>
            <td>${funcionario.nascimento}</td>
            <td>${funcionario.dataIngresso}</td>
            <td>${funcionario.remuneracao}</td>
        `;

        const infoTd = document.createElement('td');
        const a = document.createElement('a');
        const icon = document.createElement('i');
        icon.className = 'fa-solid fa-circle-info fa-lg';

        if (funcionario.cargo === "Veterinario") {
            a.href = `/funcionarios/veterinario/${funcionario.id}`;
        } else if (funcionario.cargo === "Tratador") {
            a.href = `/funcionarios/tratador/${funcionario.id}`;
        }

        infoTd.appendChild(a);
        a.appendChild(icon);

        linha.appendChild(infoTd);        
        tabela.appendChild(linha);
    });
}

