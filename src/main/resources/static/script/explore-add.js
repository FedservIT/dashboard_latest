///////For uploading image which is given in DeeplinkUrl//////////////////
var imageDeepLinkUrl;
function uploadUrlImage() {
    const imageElement = document.getElementById("inputfile1");
    var data = new FormData();
    data.append("File", imageElement.files[0]);
    var xhr = new XMLHttpRequest();
    xhr.withCredentials = true;
    xhr.addEventListener("readystatechange", function () {
        if (this.readyState === 4) {
            imageDeepLinkUrl = this.responseText;
            // console.log("imageDeepLinkUrl--->"+imageDeepLinkUrl);
        }
    });
    xhr.open("POST", "/pulse/getDownloadUrl");
    xhr.send(data);
}


///////For uploading image which is given in imgURL//////////////////
var imageDataUrl;
function uploadImage() {
    const imageElement = document.getElementById("inputfile");
    var data = new FormData();
    data.append("File", imageElement.files[0]);
    var xhr = new XMLHttpRequest();
    xhr.withCredentials = true;
    xhr.addEventListener("readystatechange", function () {
        if (this.readyState === 4) {
            imageDataUrl = this.responseText;
            // console.log("imageDataUrl--->"+imageDataUrl);
        }
    });
    xhr.open("POST", "/pulse/getDownloadUrl");
    xhr.send(data);
}


function addExploreData() {
    var data;
    var deeplinkUrl;
    var isChecked = document.getElementById("chk").checked;
    if (isChecked == true) {
        deeplinkUrl = imageDeepLinkUrl;
    } else if ((document.getElementById("chk1").checked) == true) {
        deeplinkUrl = document.getElementById("url").value;
    } else {
        deeplinkUrl = "";
    }

    data = JSON.stringify({
        "deepLinkURL": deeplinkUrl,
        "imgURL": imageDataUrl,
        "isInternal": document.getElementById("isInternal").value,
        "menuType": document.getElementById("MenuType").value,
        "pageName": document.getElementById("pagename").value,
        "text": document.getElementById("text").value,
        "tileType": document.getElementById("TileType").value,
        "index": "1"
    });



    var xhr = new XMLHttpRequest();
    xhr.withCredentials = true;

    xhr.addEventListener("readystatechange", function () {
        if (this.readyState === 4) {
            // console.log(this.responseText);
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
 }
 function clearExploreData() {

    location.reload();
    //
    // document.getElementById("docName").reset();
    // document.getElementById("inputfile").reset();
    // document.getElementById("inputfile1").reset();
    // document.getElementById("url").reset();
    // document.getElementById("isInternal").reset();
    // document.getElementById("Menu Type").reset();
    // document.getElementById("pagename").reset();
    // document.getElementById("text").reset();
    // document.getElementById("tileType").reset();



}


