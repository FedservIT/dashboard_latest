function addTodayForYou()
{
    const todayimg = document.getElementById("todayImage");
    const todayname=document.getElementById("collname").value;

    var data = new FormData();
    data.append("file", todayimg.files[0]);

    var xhr = new XMLHttpRequest();
    xhr.withCredentials = true;

    xhr.addEventListener("readystatechange", function() {
        if(this.readyState === 4) {
            if (this.responseText != null) {
                // console.log(this.responseText);
                alert("Submitted Sucessfully")
            }
            else{
                alert("Failure")
            }
             // console.log(this.responseText);
        }
    });
console.log(todayname);
    xhr.open("POST", "pulse/readExcel?collName="+todayname);

    xhr.send(data);
}

function todayForYou() {

    var todayDate=document.getElementById("todaydate").value;
    // console.log(todayDate);
    var todayCollName=document.getElementById("collname1").value;
    var data = JSON.stringify({
        "title": document.getElementById("title1").value,
        "data1": document.getElementById("data1").value
    });

    var xhr = new XMLHttpRequest();
    xhr.withCredentials = true;

    xhr.addEventListener("readystatechange", function() {
        if(this.readyState === 4) {
            if (this.responseText != null) {
                // console.log(this.responseText);
                alert("Submitted Sucessfully")
            }
            else{
                alert("Failure")
            }
        }
    });

    xhr.open("POST", "/pulse/addTrivia?date="+todayDate+"&ColletionName="+todayCollName);
    xhr.setRequestHeader("Content-Type", "application/json");

    xhr.send(data);

}