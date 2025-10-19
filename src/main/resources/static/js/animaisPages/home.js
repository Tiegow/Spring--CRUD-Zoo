const tabela = document.getElementById('tabela-animais');

document.getElementById('filtro-tipo').addEventListener('change', function() {
    const tipo = this.value;
    
    const filtroEspecie = document.getElementById('especie');
    const filtroSexo = document.getElementById('sexo');
    
    filtroEspecie.classList.add('d-none');
    filtroSexo.classList.add('d-none');

    if (tipo === 'Especie') {
        filtroEspecie.classList.remove('d-none');
    } else if (tipo === 'Sexo') {
        filtroSexo.classList.remove('d-none');
    }
});

document.getElementById('btn-aplicar-filtro').addEventListener('click', function() {
    const tipo = document.getElementById('filtro-tipo').value;

    if (tipo === 'Especie') {
        const especie = document.getElementById('filtro-especie').value;
        fetch(`/api/animais/especie/${especie}`)
            .then(response => response.json())
            .then(data => {
                criarTabela(data, tabela);
            });
    } else if (tipo === 'Sexo') {
        const radioSelecionado = document.querySelector('input[name="filtro-sexo"]:checked');

        if (radioSelecionado) {
            const sexo = radioSelecionado.value; 

            fetch(`/api/animais/sexo/${sexo}`)
                .then(response => response.json())
                .then(data => {
                    criarTabela(data, tabela);
                });
        } else {
            alert('Por favor, selecione uma opção de sexo.');
        }
    }
});

document.getElementById('btn-limpar-filtro').addEventListener('click', function() {
    fetch('/api/animais')
        .then(response => response.json())
        .then(data => {
            criarTabela(data, tabela);
        });
});

function criarTabela(data, tabela) {
    tabela.innerHTML = '';
    data.forEach(animal => {
        const linha = document.createElement('tr');
        linha.innerHTML = `
            <td>${animal.id}</td>
            <td>${animal.nome}</td>
            
            <td>${animal.nascimento}</td>
            <td>${animal.sexo}</td>
            <td>${animal.origem}</td>
        `;

        const infoTd = document.createElement('td');
        const a = document.createElement('a');
        const icon = document.createElement('i');
        icon.className = 'fa-solid fa-circle-info fa-lg';

        a.href = `/animais/${animal.id}`;

        infoTd.appendChild(a);
        a.appendChild(icon);
        linha.appendChild(infoTd);           

        tabela.appendChild(linha);
    });
}

