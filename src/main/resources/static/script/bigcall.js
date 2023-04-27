window .onload= function() {
    var data = "";

    var xhr = new XMLHttpRequest();
    xhr.withCredentials = true;

    xhr.addEventListener("readystatechange", function () {
        if (this.readyState === 4) {

            var bigcall=JSON.parse(this.responseText);
            // console.log(bigcall.isActive);
            document.getElementById("urldepartment").value=bigcall.url;
             document.getElementById("status").checked=bigcall.isActive;
        }
    });

    xhr.open("GET", "/pulse/getBigcallUrl");

    xhr.send(data);



}

function bigcalladd(){

    var data = JSON.stringify({
        "url": document.getElementById("urldepartment").value,

        "isActive": document.getElementById("status").checked
    });

    var xhr = new XMLHttpRequest();
    xhr.withCredentials = true;

    xhr.addEventListener("readystatechange", function() {
        if(this.readyState === 4) {
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

    xhr.open("POST", "/pulse/addBigcallStatus");
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.send(data);
}
