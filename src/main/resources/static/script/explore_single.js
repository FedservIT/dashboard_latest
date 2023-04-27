window.onload = function () {
    var pageName = localStorage.getItem("pageName");
    // console.log(localStorage.getItem("pageName"));
    getApi1("/pulse/getExploreDetailsByPageName?pageName=" + pageName, function (response) {
        var responseResult = JSON.parse(response);
        // console.log(responseResult);

        // console.log(document.getElementById("pageName"));
        document.getElementById("pageName").value = responseResult.pageName;
        document.getElementById("deepLink").value = responseResult.deepLinkURL;
        document.getElementById("imgURL").value = responseResult.imgURL;

        //setting is internal value as true or false

        var isInternalValue = responseResult.isInternal;

        if (isInternalValue === "false") {
            document.getElementById("isInternal").selectedIndex = [1];
        } else {
            document.getElementById("isInternal").selectedIndex = [0];
        }

        //setting is menuType  as internal or external

        var menuTypeValue = responseResult.menuType;

        if (menuTypeValue === "internal") {
            document.getElementById("menuType").selectedIndex = [0];
        } else {
            document.getElementById("menuType").selectedIndex = [1];
        }

        document.getElementById("text").value = responseResult.text;

        //setting is tileType

        var tileTypeValue = responseResult.tileType;
        if (tileTypeValue === "html") {
            document.getElementById("tileType").selectedIndex = [0];
        } else if (tileTypeValue === "pdf") {
            document.getElementById("tileType").selectedIndex = [1];
        } else if (tileTypeValue === "jpeg") {
            document.getElementById("tileType").selectedIndex = [2];
        } else if (tileTypeValue === "url") {
            document.getElementById("tileType").selectedIndex = [3];
        } else if (tileTypeValue === "internal") {
            document.getElementById("tileType").selectedIndex = [4];
        }

        // const heading=document.querySelector("#template-head");
        // const path=

        ////////////////////////////For federal corner//////////////////////////////////


        if (document.getElementById("pageName").value == "federalCorner") {
            // console.log("inside federal corner");
            const fedCornerParent1 = document.querySelector("#template-head");
            const fedcornerheading = document.createElement("h2");

            // fedcornerheading.classList.add('add-explore-card');
            fedcornerheading.innerHTML = ' <div class="template-title">\n' +
                '<h2>FederalCorner Details</h2>\n' + '</div>\n' + '<hr>'
            fedCornerParent1.appendChild(fedcornerheading);

            const fedCornerParent = document.querySelector("#explore-card-row");
            const fedCornerAdd = document.createElement("a");
            fedCornerAdd.setAttribute('href', "federalCorNew");
            fedCornerAdd.classList.add('add-explore-card');
            fedCornerAdd.innerHTML = '<ion-icon name="add-circle-outline"></ion-icon>' +
                '<strong>Add New</strong>'

            fedCornerParent.appendChild(fedCornerAdd);

            getApi2("/pulse/getFederalExploreDetails", function (response) {
                page_and_image_list = JSON.parse(response);
                getCards2(page_and_image_list.federalDetails);
            });

            // getapi

            function getApi2(url, callback) {
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

            function getCards2(federalDetails) {
                const exploreCardRow = document.querySelector("#explore-card-row");
                for (let i = 0; i < federalDetails.length; i++) {
                    var div = document.createElement("div");
                    // console.log(federalDetails[i].imageLink);
                    var pageType = federalDetails[i].pageType;
                    var imagUrl = federalDetails[i].imageLink;
                    var title = federalDetails[i].title;
                    // console.log("getcards2--" + imagUrl);
                    div.className = "explore-card-list";
                    div.innerHTML = '<div class="card-link">' +
                        '<img src=' + imagUrl + ' class="card-image" alt="">' +
                        '<strong id=contentdiv_' + i + '>' + title + '</strong>' +
                        '<div class="card-ctrl">' +
                        '<button class="ctrl-button edit-ctrl" id=edit-' + pageType + ' onclick="openSelectedTile2(this)">' +
                        '<svg width="20px" height="20px" xmlns="http://www.w3.org/2000/svg" class="ionicon" viewBox="0 0 512 512"><path d="M384 224v184a40 40 0 01-40 40H104a40 40 0 01-40-40V168a40 40 0 0140-40h167.48" fill="none" stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="32"/><path fill="#fafafa" d="M459.94 53.25a16.06 16.06 0 00-23.22-.56L424.35 65a8 8 0 000 11.31l11.34 11.32a8 8 0 0011.34 0l12.06-12c6.1-6.09 6.67-16.01.85-22.38zM399.34 90L218.82 270.2a9 9 0 00-2.31 3.93L208.16 299a3.91 3.91 0 004.86 4.86l24.85-8.35a9 9 0 003.93-2.31L422 112.66a9 9 0 000-12.66l-9.95-10a9 9 0 00-12.71 0z"/></svg>' +
                        'Edit' +
                        '</button>' +
                        '<button class="ctrl-button delete-ctrl" id=delete-' + pageType + ' onclick="deleteSelectedTile2(this)">' +
                        '<svg width="20px" height="20px" xmlns="http://www.w3.org/2000/svg" class="ionicon" viewBox="0 0 512 512"><path d="M112 112l20 320c.95 18.49 14.4 32 32 32h184c17.67 0 30.87-13.51 32-32l20-320" fill="none" stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="32"/><path stroke="currentColor" stroke-linecap="round" stroke-miterlimit="10" stroke-width="32" d="M80 112h352"/><path d="M192 112V72h0a23.93 23.93 0 0124-24h80a23.93 23.93 0 0124 24h0v40M256 176v224M184 176l8 224M328 176l-8 224" fill="none" stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="32"/></svg>' +
                        'Delete' +
                        '</button>' +
                        '</div>' +
                        '</div>'
                    exploreCardRow.appendChild(div);
                }
            }
        }
         else if (document.getElementById("pageName").value == "contest") {
            // console.log("inside contest");
            contestDetails();
        }
    });
}
function openSelectedTile2(editElement) {

    const editArray = editElement.id.split("-");
    localStorage.setItem("pageType", editArray[1]);
    location.href = "/federalcorner";
    // console.log(localStorage.getItem("pageType"));

}
function deleteSelectedTile2(deleteElement) {
    // console.log("hi inside");
    document.getElementById('modal-widgrt').classList.add('show-c')
    var delButton = document.getElementById("deleteButton1");
     // console.log("delButton" + delButton);
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
    // console.log("pageType==" + pageType);
    var xhr = new XMLHttpRequest();
    xhr.withCredentials = true;
    xhr.addEventListener("readystatechange", function () {
        if (this.readyState === 4) {
            // console.log(this.responseText);
        }
    });
    xhr.open("GET", "/pulse/deleteFedDocumentByPageName?pageType=" + pageType);
    xhr.send();
}
var canButton = document.getElementById("closeButton1");

canButton.onclick = function () {
    document.getElementById('modal-widgrt').classList.remove('show-c');
}


///////////////////////////////////////////

//get api
function getApi1(url, callback) {
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


///////////////////for SUBMIT BUTTON in Explore Edit details////////////////////


var imageDataUrl1;
function uploadImage1(callback) {
    // const imageElement1 = document.getElementById("imgURL").value;
    const imageElement = document.getElementById("imagUrl");
    var data = new FormData();
    data.append("File", imageElement.files[0]);
    var xhr = new XMLHttpRequest();
    xhr.withCredentials = true;
    xhr.addEventListener("readystatechange", function () {
        if (this.readyState === 4) {
            imageDataUrl1 = this.responseText;
            // console.log("imageDataUrl1--->" + imageDataUrl1);
            document.getElementById("imgURL").value = imageDataUrl1;
        }
    });
    xhr.open("POST", "/pulse/getDownloadUrl");
    xhr.send(data);
}

const submitBtn = document.querySelector("#form-submit");
new_pageName= document.getElementById("pageName").value,

submitBtn.addEventListener('click', function () {
    var data;
    data = JSON.stringify({
        "deepLinkURL": document.getElementById("deepLink").value,
        "imgURL": document.getElementById("imgURL").value,
        "isInternal": document.getElementById("isInternal").value,
        "menuType": document.getElementById("menuType").value,
        "pageName": document.getElementById("pageName").value,
        "text": document.getElementById("text").value,
        "tileType": document.getElementById("tileType").value,
        "index": "1"
    });

    var xhr = new XMLHttpRequest();
    xhr.withCredentials = true;

    xhr.addEventListener("readystatechange", function () {
        if (this.readyState === 4) {
            if (this.responseText != null) {
                // console.log(this.responseText);
                alert("Submitted Sucessfully")
            }
            else{
                alert("Failure")
            }
        }
    });
    xhr.open("POST", "/pulse/addDocumentData");
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.send(data);
});

