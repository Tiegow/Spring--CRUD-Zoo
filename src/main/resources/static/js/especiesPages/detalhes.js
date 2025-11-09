document.getElementById("delete-btn").addEventListener("click", async () => {
    const id = document.getElementById("delete-btn").dataset.id;

    if (!confirm("Tem certeza que deseja deletar esta espécie?"))
        return;

    await fetch(`/api/especies/deletar/${id}`, { method: "DELETE" });

    window.location.href = "/especies";
});
