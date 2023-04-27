document.addEventListener('DOMContentLoaded', function() {
    //CEO Message Post
    tinymce.init({
        selector: '#textarea',
        mode : "specific_textareas",
        plugins: 'image code'

    });

    //Get Submit Button
    const messageSubmitBtn = document.querySelector("#message-submit");

    //Handle Click function
    messageSubmitBtn.addEventListener("click", () => handleClick(), false);
    function handleClick() {

        let value = {}
        let i = 0;
        const ceoPost = (tinyMCE.get('textarea')?.getContent());
        value  = {
            "empid": "",
            "msg": `<html><body>${ceoPost}</body></html>`,
            "name": "",

        };

        // console.log("RES", value);
        var data = JSON.stringify({
            "empid": value.empid,
            "msg": `<html><body>${ceoPost}</body></html>`,
            "name": value.name
        });

        var xhr = new XMLHttpRequest();n
        xhr.withCredentials = true;

        xhr.addEventListener("readystatechange", function() {
            if(this.readyState === 4) {
                // console.log(this.responseText);
            }
        });

        xhr.open("POST", "/pulse/addCeoSpeak");
        xhr.setRequestHeader("Content-Type", "application/json");

        xhr.send(data);
    }
}, false);

