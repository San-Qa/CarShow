const backdropEl = document.querySelector('.backdrop');
const formUserEl = backdropEl.querySelector('#form-user');
const btnCloseModalEl = backdropEl.querySelector('.close-modal');

function closeModal() {
    backdropEl.classList.remove('active');
    formUserEl.name.value = '';
    formUserEl.model.value = '';
    selectedId = null;
}

btnCloseModalEl.addEventListener('click', ev => {
    closeModal();
});

formUserEl.addEventListener('submit', async ev => {
    ev.preventDefault()
    const user = {
        name: formUserEl.name.value,
        model: formUserEl.model.value
    }
    if(Number.isInteger(selectedId))
       await axios.put(`http://localhost:9045/api/updateuser/${selectedId}`, user);
    else
        await axios.post(`http://localhost:9045/api/addUser`, user);
       renderRows();
       closeModal();   
})