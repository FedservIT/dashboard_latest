window.onload = function(){
    // console.log("hi inside window onload");
    getApi("/pulse/getExploreDetails", function (response) {
        page_and_image_list = JSON.parse(response);
        // console.log(JSON.parse(response));
        getCards(page_and_image_list.exploreDetails);
    });
}

//get api
function getApi(url, callback) {
    var xhr = new XMLHttpRequest();
    xhr.withCredentials = true;
    xhr.addEventListener("readystatechange", function () {
        if (this.readyState === 4) {
            callback(this.responseText);
        }
    });
    xhr.open("GET", url);
    xhr.send();
}

function getCards(exploreDetails) {
    // console.log("hi inside getcards");
    const exploreCardRow = document.querySelector("#explore-card-row");
    for (let i = 0; i < exploreDetails.length; i++) {
        var div = document.createElement("div");
        var pageName = exploreDetails[i].pageName;
        var text_Str = exploreDetails[i].text;
        var imagUrl = exploreDetails[i].imageLink;
        // console.log("getcards imagUrl--"+imagUrl);
        div.className = "explore-card-list";
        div.innerHTML = '<div class="card-link">' +
            '<img src=' + imagUrl + ' class="card-image" alt="">' +
            '<strong id=contentdiv_' + i + '>' + text_Str + '</strong>' +
            '<div class="card-ctrl">' +
            '<button class="ctrl-button edit-ctrl" id=edit-' + pageName + ' onclick="openSelectedTile(this)">' +
            '<svg width="20px" height="20px" xmlns="http://www.w3.org/2000/svg" class="ionicon" viewBox="0 0 512 512"><path d="M384 224v184a40 40 0 01-40 40H104a40 40 0 01-40-40V168a40 40 0 0140-40h167.48" fill="none" stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="32"/><path fill="#fafafa" d="M459.94 53.25a16.06 16.06 0 00-23.22-.56L424.35 65a8 8 0 000 11.31l11.34 11.32a8 8 0 0011.34 0l12.06-12c6.1-6.09 6.67-16.01.85-22.38zM399.34 90L218.82 270.2a9 9 0 00-2.31 3.93L208.16 299a3.91 3.91 0 004.86 4.86l24.85-8.35a9 9 0 003.93-2.31L422 112.66a9 9 0 000-12.66l-9.95-10a9 9 0 00-12.71 0z"/></svg>' +
            'Edit' +
            '</button>' +
            '<button class="ctrl-button delete-ctrl" id=delete-' + pageName.toString() + ' onclick="deleteSelectedTile(this)">' +
            '<svg width="20px" height="20px" xmlns="http://www.w3.org/2000/svg" class="ionicon" viewBox="0 0 512 512"><path d="M112 112l20 320c.95 18.49 14.4 32 32 32h184c17.67 0 30.87-13.51 32-32l20-320" fill="none" stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="32"/><path stroke="currentColor" stroke-linecap="round" stroke-miterlimit="10" stroke-width="32" d="M80 112h352"/><path d="M192 112V72h0a23.93 23.93 0 0124-24h80a23.93 23.93 0 0124 24h0v40M256 176v224M184 176l8 224M328 176l-8 224" fill="none" stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="32"/></svg>' +
            'Delete' +
            '</button>' +
            '</div>' +
            '</div>'
        exploreCardRow.appendChild(div);
    }
}

function openSelectedTile(editElement) {
    const editArray = editElement.id.split("-");
    localStorage.setItem("pageName", editArray[1]);
    location.href = "/getExploreDocument";

}
//
// function deleteSelectedTile(deleteElement) {
//     // console.log("hi inside");
//     document.getElementById('modal-widgrt').classList.add('show-c')
//
//     var delButton = document.getElementById("deleteButton");
//     // console.log("delButton"+delButton);
//     delButton.onclick = function () {
//         const deleteArray = deleteElement.id.split("-");
//         console.log(deleteArray[1]);
//
//
//         deleteApi(deleteArray[1]);
//         window.location.reload();
//         setTimeout(() => {
//             document.getElementById('modal-widgrt').classList.remove('show-c');
//         }, 2000);
//     }
// }
//
// function deleteApi(pageName) {
//     // console.log("pageName==" + pageName);
//     var xhr = new XMLHttpRequest();
//     xhr.withCredentials = true;
//
//     xhr.addEventListener("readystatechange", function() {
//         if(this.readyState === 4) {
//             // console.log(this.responseText);
//         }
//     });
//
//     xhr.open("GET", "/pulse/deleteDocumentByPageName?pageName="+pageName);
//
//     xhr.send();
// }
//
// var canButton = document.getElementById("closeButton");
//
// canButton.onclick = function () {
//     document.getElementById('modal-widgrt').classList.remove('show-c');
// }
function deleteSelectedTile(deleteElement) {
    console.log("hi inside");
    document.getElementById('modal-widgrt').classList.add('show-c')
    let delButton = document.getElementById("deleteButton");
    console.log("delButton: " + delButton);
    delButton.onclick = function () {
        const deleteArray = deleteElement.id.split("-");
        deleteApi2(deleteArray[1]);
        window.location.reload();
        setTimeout(() => {
            document.getElementById('modal-widgrt').classList.remove('show-c');
        }, 2000);
    }
}
function deleteApi2(pageType) {
    console.log("pageType: " + pageType);
    var xhr = new XMLHttpRequest();
    xhr.withCredentials = true;
    xhr.addEventListener("readystatechange", function () {
        if (this.readyState === 4) {
            // console.log(this.responseText);
        }
    });
    xhr.open("GET", "/pulse/deleteDocumentByPageName?pageName=" + pageType);
    xhr.send();
}
var canButton = document.getElementById("closeButton");

canButton.onclick = function () {
    document.getElementById('modal-widgrt').classList.remove('show-c');
}


