

function uploadtitle(callback) {

    const imageElement = document.getElementById("imgfile");
    var data = new FormData();
    data.append("file", imageElement.files[0]);
    var xhr = new XMLHttpRequest();
    xhr.withCredentials = true;
    if (imageElement.files[0] != null){
        console.log("i am good");
        xhr.addEventListener("readystatechange", function () {
            if (this.readyState === 4) {
                callback(this.responseText);
            }
        });
        xhr.open("POST", "/pulse/federalImage");
        xhr.send(data);
    }else{
        // console.log("this is hell");
        callback("");
    }

}

function uploadpdf(callback) {

    const imageElement = document.getElementById("pdffile");
    var data = new FormData();
    data.append("file", imageElement.files[0]);
    if (imageElement.files[0] != null) {

        var xhr = new XMLHttpRequest();
        xhr.withCredentials = true;
        xhr.addEventListener("readystatechange", function () {
            if (this.readyState === 4) {

                callback(this.responseText);
            }

        });


        xhr.open("POST", "/pulse/federalImage");
        xhr.send(data);

    }
    else{
        callback("");
    }
}

function uploadvideo(callback) {

    const imageElement = document.getElementById("videofile");
    var data = new FormData();
    data.append("file", imageElement.files[0]);
    if (imageElement.files[0] != null) {
        var xhr = new XMLHttpRequest();
        xhr.withCredentials = true;
        xhr.addEventListener("readystatechange", function () {
            if (this.readyState === 4) {
                callback(this.responseText);
            }
        });

        xhr.open("POST", "/pulse/federalImage");
        xhr.send(data);
    }else{
        callback("");
    }

}
function uploadimage(callback) {

    const imageElement = document.getElementById("imagefile");
    var data = new FormData();
    data.append("file", imageElement.files[0]);
    if (imageElement.files[0] != null) {
        var xhr = new XMLHttpRequest();
        xhr.withCredentials = true;
        xhr.addEventListener("readystatechange", function () {
            if (this.readyState === 4) {
                callback(this.responseText);
            }
        });

        xhr.open("POST", "/pulse/federalImage");
        xhr.send(data);
    }else{
        callback("");
    }

}

function addNewConer() {

    var image_url;
    var pdf_url;
    var video_url;
    var title_url;

    uploadtitle(function (titleurl) {

        // console.log(titleurl);
        title_url = titleurl;

        uploadpdf(function (pdfurl) {

            // console.log(pdfurl);
            pdf_url = pdfurl;
            uploadvideo(function (videoUrl) {

                // console.log(videoUrl);
                video_url = videoUrl;
                uploadimage(function (imageUrl) {
                    // console.log(imageUrl);
                    image_url = imageUrl;


                    submitFederalCorner(title_url, image_url, pdf_url, video_url);
                });
            });

        });

    });


}


function submitFederalCorner(title_url,image_url,pdf_url,video_url) {
    var data = JSON.stringify({
        "deeplinkUrl": document.getElementById("DeeplinkUrl1").value,
        "isInternal": document.getElementById("isInternal1").value,
        "pageType": document.getElementById("pageType1").value,
        "imgUrl":image_url,
        "pdfURL": pdf_url,
        "videoURL": video_url,
        "titleURL":title_url,
        "title": document.getElementById("title1").value
    });

    // console.log(data);


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
    xhr.open("POST", "/pulse/addFederalCorner?pageType=" + document.getElementById("pageType1").value);
    xhr.setRequestHeader("Content-Type", "application/json");

    xhr.send(data);
}
function clearFederal(){
    location.reload();
}

