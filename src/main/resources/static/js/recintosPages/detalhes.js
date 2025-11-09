<<<<<<< Updated upstream
const deleteBtn = document.getElementById('delete-btn');

deleteBtn.addEventListener('click', () => {
    const recintoId = deleteBtn.dataset.id;

    if (!confirm('Tem certeza de que deseja deletar este recinto?')) {
        return; 
    }

    fetch('/api/recintos/' + recintoId, { 
        method: 'DELETE'
    })
    .then(response => {
        if (response.ok) {
            alert('Registro deletado com sucesso.');
            window.location.href = '/recintos'; 
        } else {
            response.json().then(errorData => {
                alert('Falha ao deletar: ' + errorData.message);
            }).catch(() => {
                alert('Falha ao deletar o registro. O servidor não retornou um erro legível.');
            });
        }
    })
    .catch(error => {
        console.error('Erro de conexão:', error);
        alert('Erro de conexão. Não foi possível deletar.');
    });
});
=======
document.getElementById("delete-btn").addEventListener("click", () => {
    if (!confirm("Tem certeza que deseja excluir este recinto?")) return;

    const id = document.getElementById("delete-btn").dataset.id;

    fetch("/api/recintos/deletar/" + id, {
        method: "DELETE"
    })
    .then(resp => {
        if (resp.ok) {
            alert("Recinto deletado.");
            window.location.href = "/recintos";
        } else {
            resp.json().then(err => alert(err.message));
        }
    })
    .catch(() => alert("Erro de conexão."));
});
>>>>>>> Stashed changes
