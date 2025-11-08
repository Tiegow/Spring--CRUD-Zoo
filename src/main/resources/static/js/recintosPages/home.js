const tabela = document.getElementById('tabela-recintos');

document.getElementById('filtro-tipo').addEventListener('change', function () {
    const tipo = this.value;

    const filtroArea = document.getElementById('area');
    const filtroPopulacao = document.getElementById('grupo'); 

    filtroArea.classList.add('d-none');
    filtroPopulacao.classList.add('d-none');

    switch (tipo) {
        case 'Area Habitavel':
            filtroArea.classList.remove('d-none');
            break;

        case 'Populacao':
            filtroPopulacao.classList.remove('d-none');
            break;
    }
});

document.getElementById('btn-aplicar-filtro').addEventListener('click', function () {
    const tipo = document.getElementById('filtro-tipo').value;

    if (tipo === 'Area Habitavel') {
        const maiorQue = document.getElementById('filtro-area-maior-que').value;
        const menorQue = document.getElementById('filtro-area-menor-que').value;
        
        fetch(`/api/recintos/area?maiorQue=${maiorQue}&menorQue=${menorQue}`)
            .then(response => response.json())
            .then(data => {
                criarTabela(data, tabela);
            });

    } else if (tipo === 'Populacao') {
        const minimo = document.getElementById('filtro-grupo-min').value; 
        const maximo = document.getElementById('filtro-grupo-max').value; 
        
        fetch(`/api/recintos/populacao?minimo=${minimo}&maximo=${maximo}`)
            .then(response => response.json())
            .then(data => {
                criarTabela(data, tabela);
            });
    }
});

document.getElementById('btn-limpar-filtro').addEventListener('click', function () {
    fetch('/api/recintos')
        .then(response => response.json())
        .then(data => {
            criarTabela(data, tabela);
        });
});


function criarTabela(data, tabela) {
    tabela.innerHTML = '';

    data.forEach(recinto => {
        const linha = document.createElement('tr');
        
        linha.innerHTML = `
            <td>${recinto.id}</td>
            <td>${recinto.nome}</td>
            <td>${recinto.areaHabitavel}</td>
            <td>${recinto.status}</td>
            <td>${recinto.populacao}</td>
        `;

        const infoTd = document.createElement('td');
        const a = document.createElement('a');
        a.href = `/recintos/${recinto.id}`; 
        a.title = "Ver detalhes";
        a.className = "btn btn-info btn-sm"; 

        const icon = document.createElement('i');
        icon.className = 'fa-solid fa-eye'; 

        a.appendChild(icon);
        infoTd.appendChild(a);
        
        linha.appendChild(infoTd);

        tabela.appendChild(linha);
    });
}
