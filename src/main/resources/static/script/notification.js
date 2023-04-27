function send_notification(){
    let heading = document.getElementById("title").value;
    let message = document.getElementById("msg").value;
    let xhr = new XMLHttpRequest();
    xhr.withCredentials = true;

    xhr.addEventListener("readystatechange", function() {
        if(this.readyState === 4) {
            console.log(this.responseText);
        }
    });

    xhr.open("GET", "/pulse/getNotifications?title="+heading+"&message="+message);
    xhr.send();

}