const deleteBtn = document.getElementById('delete-btn');
const atrBtn = document.getElementById('atr-btn');

deleteBtn.addEventListener('click', () => {
    const vetId = deleteBtn.dataset.id;
    fetch('/api/veterinarios/deletar/' + vetId, {
        method: 'DELETE'
    }).then(response => {
        if (response.ok) {
            window.location.href = '/funcionarios'; 
        } else {
            alert('Falha ao deletar o registro.');
        }
    });
})

atrBtn.addEventListener('click', () => {
    const vetId = deleteBtn.dataset.id;
    window.location.href = `/funcionarios/veterinario/atribuir/${vetId}`
})