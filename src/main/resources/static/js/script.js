const tableEl = document.querySelector('.table');
const tbodyEl = tableEl.querySelector('tbody');
const theadEl = tableEl.querySelector('thead');
const btnOpenModalEl = document.querySelector('.open-modal');

btnOpenModalEl.addEventListener('click', ev => {
    document.querySelector('.backdrop').classList.add('active')
});

const api = axios.create({
    baseURL: 'http://localhost:9045/api/users'},{ 
        headers: {"Access-Control-Allow-Origin" : "*"}
});

api.get('').then(response => {
    
    console.log(response);
})

const columns = ['#', 'Name', 'Model']

const templateColumns = columns.map(column =>(
    `
    <th>${column}</th>
    `
));

theadEl.rows.item(0).innerHTML = templateColumns.join('');

let selectedId = null;

async function renderRows(){
    tbodyEl.innerHTML = '';
    const {data: rows} = await api.get('');
    let templateRows = '';
    if(rows.length === 0){
        templateRows = `
        <tr>
            <td style="text-align: center" colspan="${columns.length}">No data available</td>
        </tr>
        `
        tbodyEl.innerHTML = templateRows;
    }
    else{
        templateRows = rows.map(row => (
            `
            <tr data-id="${row.id}">
                <td>${row.id}</td>
                <td>${row.name}</td>
                <td>${row.model}</td>
                <td class= "actions">
                <a href="javascript:void(0)" class="delete">
                    <i class="fas fa-trash"></i>
                 </a>
                 <a href="javascript:void(0)" class="edit">
                    <i class="fas fa-pen"></i>
                  </a>
                </td>
                </tr>     
            `
        ))

        tbodyEl.innerHTML = templateRows.join('');

        tbodyEl.querySelectorAll('tr').forEach(tr =>{
            const linkDeleteEl = tr.querySelector('.delete');
            const linkEditEl = tr.querySelector('.edit');
            const id = Number.parseInt(tr.dataset.id);
            linkDeleteEl.addEventListener('click', async ev =>{
                console.log(id);
                await axios.delete(`http://localhost:9045/api/deleteuser/${id}`);
                renderRows();
            });

            linkEditEl.addEventListener('click', ev => {
                selectedId = id;
                const formUserEl = document.querySelector('#form-user');
                formUserEl.name.value = tr.cells.item(1).innerText;
                formUserEl.model.value = tr.cells.item(2).innerText;
                document.querySelector('.backdrop').classList.add('active');
            });

        });
    }
}

renderRows();