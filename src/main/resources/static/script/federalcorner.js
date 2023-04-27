window.onload = function () {
    var pageType = localStorage.getItem("pageType");
    // console.log(localStorage.getItem("pageType"));
    // var pageType1 = localStorage.getItem("pageType1");
    // console.log(localStorage.getItem("pageType1"));

    getApi("/pulse/getFederalCorner?pageType="+ pageType, function (response) {
        var responseResult = JSON.parse(response);
        // console.log(responseResult);
        // console.log("forebase>>>>>>"+responseResult.pageType);

        document.getElementById("pageType1").value = responseResult.pageType;
        document.getElementById("DeeplinkUrl1").value = responseResult.deeplinkUrl;
        document.getElementById("imageFile2").value = responseResult.imgUrl;
        document.getElementById("pdfFile1").value = responseResult.pdfURL;
        document.getElementById("title1").value = responseResult.title;
        document.getElementById("videoFile1").value = responseResult.videoURL;
        document.getElementById("imgFile1").value=responseResult.titleURL;

        var isInternalValue = responseResult.isInternal;

        if (isInternalValue === "false") {
            document.getElementById("isInternal1").selectedIndex = [1];
        } else {
            document.getElementById("isInternal1").selectedIndex = [0];
        }
    });
}


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
var pdf_Url;
var video_Url;
var title_Url;
var img_Url;
function uploadtitle(callback) {

    const imageElement = document.getElementById("imgfile");
    var data = new FormData();
    data.append("file", imageElement.files[0]);
    var xhr = new XMLHttpRequest();
    xhr.withCredentials = true;
    if (imageElement.files[0] != null){
        // console.log("i am good");
        xhr.addEventListener("readystatechange", function () {
            if (this.readyState === 4) {
                callback(this.responseText);
                // console.log("data"+this.responseText);
            }
        });
        xhr.open("POST", "/pulse/federalImage");
        xhr.send(data);
    }
else if ((document.getElementById("imgFile1"))== null && (document.getElementById("imgfile"))== null ){
        callback("");
    }else
    {
        title_Url=document.getElementById("imgFile1").value;

        callback(title_Url);
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
    else if ((document.getElementById("pdfFile1"))== null && (document.getElementById("pdffile"))== null ){
        callback("");
    }else
    {
        pdf_Url=document.getElementById("pdfFile1").value;
        callback(pdf_Url);
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
    }
    else if ((document.getElementById("videoFile1"))== null && (document.getElementById("videofile"))== null ){
        callback("");
    }else
    {
        video_Url=document.getElementById("videoFile1").value;
        callback(video_Url);
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
    }
    else if ((document.getElementById("imageFile2"))== null && (document.getElementById("imagefile"))== null ){
        callback("");
    }else
    {
        img_Url=document.getElementById("imageFile2").value;
        callback(img_Url);
    }


}

function updateFederalCorner() {

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
        "titleURL":title_url,
        "pdfURL": pdf_url,
        "videoURL": video_url,
        "imgUrl":image_url,

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

console.log("value",document.getElementById("pageType1").value);
