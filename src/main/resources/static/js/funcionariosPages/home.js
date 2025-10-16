document.getElementById('filtro-tipo').addEventListener('change', function() {
    const tipo = this.value;
    const filtroDinamico = document.getElementById('filtro-dinamico');
    filtroDinamico.querySelectorAll('div').forEach(div => div.classList.add('d-none'));

    if (tipo === 'Especialização') {
        const filtro = document.getElementById('espec');
        filtro.classList.remove('d-none');
    } else if (tipo === 'Remuneração') {
        const filtro = document.getElementById('remun');
        filtro.classList.remove('d-none');
    } else {
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
                const tabela = document.getElementById('tabela-funcionarios');
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
                    tabela.appendChild(linha);
                });
            });
    } else if (tipo === 'Remuneração') {
        const maiorQue = document.getElementById('filtro-remuneracao-maior-que').value;
        const menorQue = document.getElementById('filtro-remuneracao-menor-que').value;
        fetch(`/api/funcionarios/remuneracao?maiorQue=${maiorQue}&menorQue=${menorQue}`)
            .then(response => response.json())
            .then(data => {
                const tabela = document.getElementById('tabela-funcionarios');
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
                    tabela.appendChild(linha);
                });
            });
    }
});

document.getElementById('btn-limpar-filtro').addEventListener('click', function() {
    fetch('/api/funcionarios')
        .then(response => response.json())
        .then(data => {
            const tabela = document.getElementById('tabela-funcionarios'); 
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
                tabela.appendChild(linha);
            });
        });
});
