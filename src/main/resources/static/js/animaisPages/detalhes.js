const deleteBtn = document.getElementById('delete-btn');

deleteBtn.addEventListener('click', () => {
    const vetId = deleteBtn.dataset.id;
    fetch('/api/animais/deletar/' + vetId, {
        method: 'DELETE'
    }).then(response => {
        if (response.ok) {
            window.location.href = '/animais'; 
        } else {
            alert('Falha ao deletar o registro.');
        }
    });
})