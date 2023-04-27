function addhomeScreen() {
    var currentdate = document.getElementById("bannerDate").value;
    function formatDate(date) {
        var d = new Date(date),
            month = '' + (d.getMonth() + 1),
            day = '' + d.getDate(),
            year = d.getFullYear();

        if (month.length < 2)
            month = '0' + month;
        if (day.length < 2)
            day = '0' + day;

        return [day, month, year].join('');
    }
    formatDate(currentdate);
    // console.log( formatDate(currentdate));

    const bannerImgElement = document.getElementById("homeImage")
    var data = new FormData();
    data.append("file", bannerImgElement.files[0]);

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

    xhr.open("POST", "/pulse/bannerImage?date=" + formatDate(currentdate));

    xhr.send(data);
}