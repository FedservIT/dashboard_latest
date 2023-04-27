function contestDetails() {
    // console.log("inside contest");
    const contestParent1 = document.querySelector("#template-headcon");
    const contestheading = document.createElement("h2");
    // fedcornerheading.classList.add('add-explore-card');
    contestheading.innerHTML = ' <div class="template-title">\n' +
        '<h2>Contest Details</h2>\n' + '</div>\n' + '<hr>'
    contestParent1.appendChild(contestheading);

    const contestParent = document.querySelector("#explore-card-rowcon");
    const contestAdd = document.createElement("a");
    contestAdd.setAttribute('href', "contest");
    contestAdd.classList.add('add-explore-card');
    contestAdd.innerHTML = '<ion-icon name="add-circle-outline"></ion-icon>' +
        '<strong>AddNewContest</strong>'

    contestParent.appendChild(contestAdd);
}

    // getApi2("/pulse/getFederalExploreDetails", function (response) {
    //     page_and_image_list = JSON.parse(response);
    //     getCards2(page_and_image_list.federalDetails);
    // });
    //
    // getapi
    //
    // function getApi2(url, callback) {
    //     var xhr = new XMLHttpRequest();
    //     xhr.withCredentials = true;
    //     xhr.addEventListener("readystatechange", function () {
    //         if (this.readyState === 4) {
    //             callback(this.responseText);
    //         }
    //     });
    //     xhr.open("GET", url);
    //     xhr.send();

var imageDataUrl;
function uploadImage2() {
    const imageElement = document.getElementById("contestimagUrl");
    var data = new FormData();
    data.append("file", imageElement.files[0]);
    var xhr = new XMLHttpRequest();
    xhr.withCredentials = true;
    xhr.addEventListener("readystatechange", function () {
        if (this.readyState === 4) {
            imageDataUrl = this.responseText;
            // console.log("imageDataUrl--->"+imageDataUrl);
        }
    });
    xhr.open("POST", "/pulse/contestImage");
    xhr.send(data);
}


function addContestData(callback) {

    data = JSON.stringify({
        "department": document.getElementById("Department").value,
        "description": document.getElementById("Description").value,
        "endDate": document.getElementById("EndDate").value,
        "formURL": document.getElementById("FormUrl").value,
        "imgUrl":imageDataUrl,
        "startDate": document.getElementById("StartDate").value,
        "title": document.getElementById("Title").value,
    });

    var xhr = new XMLHttpRequest();
    xhr.withCredentials = true;

    xhr.addEventListener("readystatechange", function () {
        if (this.readyState === 4) {
            // console.log(this.responseText);
            callback(this.responseText);
            if (this.responseText != null) {
                // console.log(this.responseText);
                alert("Submitted Sucessfully")
            }
            else{
                alert("Failure")
            }
        }
    });
    xhr.open("POST", "/pulse/addContest");
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.send(data);

}





