window.onload = function(){
    // console.log("hi inside window onload");
    getApi("/pulse/getCeoSpeak", function (response) {

        getceospeak = JSON.parse(response);
      //   localStorage.setItem("speak_id",getceospeak.speakId);
      // console.log(localStorage.getItem("speak_id"));

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
      formatDate(getceospeak.id);
        localStorage.setItem("ceoSpeakdate",formatDate(getceospeak.id));
        // console.log(localStorage.getItem("ceoSpeakdate"));


        // console.log("Converted Date"+dateFormat(getceospeak.id,'DDMMYYYY'));

        // console.log(response);

        ceospeak(getceospeak)

    });

var ceospeakId=localStorage.getItem("ceoSpeakdate");

    getApi("/pulse/ceoSpeakViews?speak_id=ceo_speaks_"+ceospeakId, function (response) {

        viewsbyusers=response;
        // console.log(viewsbyusers);
        views(viewsbyusers);


    });



    //
    // var data = new FormData();
    //
    //
    // var xhr = new XMLHttpRequest();
    // xhr.withCredentials = true;
    //
    // xhr.addEventListener("readystatechange", function() {
    //     if(this.readyState === 4) {
    //         // console.log(this.responseText);
    //         ceoMsg=JSON.parse(this.responseText);
    //         ceoHistory(ceoMsg);
    //
    //
    //     }
    // });
    //
    // xhr.open("GET", "/pulse/getCeoSpeakHistory");
    //
    // xhr.send(data);

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

// function formatDate(date) {
//     var d = new Date(date),
//         month = '' + (d.getMonth() + 1),
//         day = '' + d.getDate(),
//         year = d.getFullYear();
//
//     if (month.length < 2)
//         month = '0' + month;
//     if (day.length < 2)
//         day = '0' + day;
//
//     return [day, month, year].join('');
// }


function ceospeak(getceospeak) {
    document.getElementById("date").innerHTML=getceospeak.id;
    document.getElementById("ceomsg").innerHTML=getceospeak.msg;
    // console.log(getceospeak.speakId);



}

function views(viewsbyusers) {

   // div.innerHTML = '<divclass="card-content">'+'<strong>viewsbyusers</strong>'+'<p>"views"</p>'+'</div>'

        document.getElementById("views").innerHTML = viewsbyusers;

}
// function ceoHistory()
// {
//     const ceoMsgHis = document.getElementById("ceoMsgHis");
//     for (let i = 0; i <ceoMsg.ceoSpeakHisList.length; i++)
//
//     {
//         var ceoMsg1 = ceoMsg.ceoSpeakHisList[i].msg;
//         var ceo_id =  ceoMsg.ceoSpeakHisList[i].ceo_id;
//         let tableRow = document.getElementById("table1")
//         tableRow.innerHTML='<thead>'+
//             '<tr>'+
//             ' <th style="width: 50px;">'+i+'</th>'+
//             '<th>'+ceo_id+'</th>'+
//             '<th>'+ceoMsg1+'</th>'+
//             '<th>'+"Reaction"+'</th>'+
//               '<th>'+"View"+'</th>'+
//               '<th>'+"Comments"+'</th>'+
//              '<th style="width:90px;">'+ '</th>'+
//             '</tr>'+
//             '  </thead>'
//
//         ceoMsgHis.appendChild(tableRow)
//     }

//}


