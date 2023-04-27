
// Example POST method implementation:
async function makeRequest(url = '', method, data) {
    // Default options are marked with *
    const response = await fetch(url, {
        method: method,
        mode: 'cors',
        cache: 'no-cache',
        credentials: 'same-origin',
        headers: {
            'Content-Type': 'application/json'
            // 'Content-Type': 'application/x-www-form-urlencoded',
        },
        redirect: 'follow',
        referrerPolicy: 'no-referrer',

    });

    if (method === "POST") response.body = JSON.stringify(data)

    return response.json()

}

// FETCH DATA

const fetchData = async () => {
    const url = '/pulse/getQuizInfo';
    const res = await makeRequest(url, "GET");
    if(res?.quizInfoList.length > 0)  renderHtml(res.quizInfoList)
}

fetchData();

// RENDER TABLE ROW HTML
const renderHtml = (e) => {
    const tbodyRef = document.querySelector("#quiz_table").getElementsByTagName('tbody')[0];

    for (let i = 0; i < e.length; i++) {
        // console.log("OUT", e);
        const element = e[i];
        const row = tbodyRef.insertRow();

        row.insertCell(0).innerHTML = i + 1;

        row.insertCell(1).innerHTML = element.doc_Id;

        localStorage.setItem("elementid",element.doc_Id);
        // console.log(localStorage.getItem("elementid"));
        // formate date

        // const date = new Date( element.publishDate * 1000);
        //  row.insertCell(2).innerHTML = `${date.getDate()}-${date.getMonth()}-${date.getFullYear()}`;

        row.insertCell(2).innerHTML=element.publishDate;
        const buttonRow = document.createElement('div');
        buttonRow.classList.add('btn-row');
        // View Button
        const viewBtn = document.createElement('button');
        viewBtn.classList.add('view-btn');
        viewBtn.innerHTML = '<ion-icon name="eye-outline"></ion-icon> View';
        viewBtn.onclick = () => handleClick(e, i);

        //Edit Button
       // const editBtn = document.createElement('button');
      //  editBtn.classList.add('edit-btn');
       // editBtn.innerHTML = '<ion-icon name="create-outline"></ion-icon> Edit';

        buttonRow.append(viewBtn);
        // append btn to td
        row.insertCell(3).appendChild(buttonRow);
    }
}

// HANDLE CLICK
const handleClick = (e, i) => {

    localStorage.setItem("quizID", e[i].doc_Id);
    console.log(localStorage.getItem("quizID"))
    location.href = "/quiz-view";
    console.log("e", e);
}