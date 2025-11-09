<<<<<<< Updated upstream
const form = document.getElementById('add-form');

form.addEventListener('submit', (event) => {
    event.preventDefault(); 

    const formData = new FormData(form);
    
    const data = Object.fromEntries(formData.entries());

    data.areaHabitavel = parseFloat(data.areaHabitavel);
    data.planoDietaId = parseInt(data.planoDietaId);

    data.tratadorIds = formData.getAll('tratadorIds').map(id => parseInt(id));

    fetch('/api/recintos', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(data)
    })
    .then(response => {
        if (response.ok) {
            window.location.href = '/recintos';
            console.log('Recinto registrado com sucesso.');
            return;
        }
        
        return response.json().then(errData => {
            const msg = JSON.stringify(errData.message);
            console.error('Erro ao adicionar recinto:', msg);
        });
    })
    .catch(error => {
        console.error('Erro na requisição:', error);
    });
=======
const form = document.getElementById("novo-recinto-form");

form.addEventListener("submit", (e) => {
    e.preventDefault();

    const formData = new FormData(form);
    const data = Object.fromEntries(formData.entries());
    data.tratadoresIds = formData.getAll("tratadoresIds");

    fetch("/api/recintos/criar", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(data)
    })
    .then(resp => {
        if (resp.ok) {
            alert("Recinto criado com sucesso!");
            window.location.href = "/recintos";
            return;
        }
        return resp.json().then(err => alert(err.message));
    })
    .catch(err => alert("Erro de conexão."));
>>>>>>> Stashed changes
});
