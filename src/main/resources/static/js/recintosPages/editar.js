<<<<<<< Updated upstream
const form = document.getElementById('edit-form');

form.addEventListener('submit', (event) => {
    event.preventDefault(); 

    const recintoId = form.dataset.id;

    const formData = new FormData(form);
    
    const data = Object.fromEntries(formData.entries());

    data.areaHabitavel = parseFloat(data.areaHabitavel);
    data.planoDietaId = parseInt(data.planoDietaId);

    data.tratadorIds = formData.getAll('tratadorIds').map(id => parseInt(id));

    fetch(`/api/recintos/${recintoId}`, {
        method: 'PUT',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(data)
    })
    .then(response => {
        if (response.ok) {
            window.location.href = '/recintos';
            console.log('Recinto atualizado com sucesso.');
            return;
        }
        
        return response.json().then(errData => {
            const msg = errData.message || "Verifique os dados preenchidos.";
            console.error('Erro ao atualizar recinto:', msg);
        });
    })
    .catch(error => {
        console.error('Erro na requisição:', error);
    });
=======
const form = document.getElementById("editar-recinto-form");
const id = form.dataset.id;

form.addEventListener("submit", (e) => {
    e.preventDefault();

    const formData = new FormData(form);
    const data = Object.fromEntries(formData.entries());
    data.tratadoresIds = formData.getAll("tratadoresIds");

    fetch("/api/recintos/atualizar/" + id, {
        method: "PUT",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(data)
    })
    .then(resp => {
        if (resp.ok) {
            alert("Recinto atualizado!");
            window.location.href = "/recintos";
            return;
        }
        return resp.json().then(err => alert(err.message));
    })
    .catch(() => alert("Erro de conexão."));
>>>>>>> Stashed changes
});
