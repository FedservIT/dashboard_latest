


var arr = [];
var quizObject;

var i=0;
var j=0;
function addNew() {


    var isChecked=document.getElementById("anser_1_"+j).checked;
    if(isChecked==true)
    {
        var ans1=document.getElementById("option1_"+j).value

    }else if ((document.getElementById("anser_2_"+j).checked)==true)
    {
        var ans1=document.getElementById("option2_"+j).value
    }else if ((document.getElementById("anser_3_"+j).checked)==true){
        var ans1=document.getElementById("option3_"+j).value
    }
    // else {
    //     var ans1=document.getElementById("option4_"+j).value
    // }
   quizObject =
    {
        "answer":ans1,

        "option1": document.getElementById("option1_"+j).value,
        "option2": document.getElementById("option2_"+j).value,
        "option3": document.getElementById("option3_"+j).value,
        // "option4":document.getElementById("option4_"+j).value,
        "question": document.getElementById("question_"+j).value,

        "quizId":"q"+i
    }

    arr.push(quizObject);
     // console.log("sdfgh________"+JSON.stringify(arr))


i++;
j++;
}

///  add quizdata

function addQuizData() {
addNew();
    // console.log("hai");
    // console.log(arr.length)
    var data = JSON.stringify({
        "data":   arr,
        "published_date": document.getElementById("quizDate").value

    });

    var xhr = new XMLHttpRequest();
    xhr.withCredentials = true;

    xhr.addEventListener("readystatechange", function () {
        // console.log(this.readyState)

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


    xhr.open("POST", "/pulse/addQuizData");
    xhr.setRequestHeader("Content-Type", "application/json");

    xhr.send(data);
}

function addQuizInfo() {


    var data = JSON.stringify({
        "description": document.getElementById("content").value,
        "info": document.getElementById("information").value,
        "title": document.getElementById("title").value
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

    xhr.open("POST", "/pulse/addQuizInfo");
    xhr.setRequestHeader("Content-Type", "application/json");

    xhr.send(data);
}



function addNewCard() {
    addNew();
    // console.log("dfg"+j);
    // const quizAddCard = document.querySelector('#quiz-add-form');
    var div = document.createElement("div");
    div.className="quiz-card-list";
    var quiz_card_list=document.getElementById("newquizform")
    div.innerHTML = '<div class="form-row mb-10">' +
        '<div class="label-row mb-10">' +
        '<label class="ctrl-label">' +
        // '<span></span>' +
        'Question' + '</label>' +
        '</div >' +
        '<div class="form-row">'+
        '<input type="text" class="input-ctrl" id=question_'+j+'>' +
        '</div>' +
        '</div>'+
        // '<div class="answer-row">'+
        // '<div class="class="form-row">'+
        // '<label class="ctrl-label">'+'Date'+'</label>'+
        // ' <input type="date" class="input-ctrl" id=quizDate'+j+'>'+
        // '</div>'+
        // '</div>'+
        '<div class="answer-row">' +
        '<div class="answer-list">' +
        '<div class="sl-no">' + '1' + '</div>' +
        '<div class="form-row">' +
        '<input type="text" class="input-ctrl" id=option1_'+j+'>' + '</div>' +
        '<div class="answer-ctrl">' +
        '<div class="chack-box">' +
        '<div class="toggle-btn">' +
        '<input id=anser_1_'+j+' type="radio" name="answertype_1">' +
        '<label for="anser_1_0">' + '</label>' +
        '</div>' +
        '<label>' + 'true' + '</label>' +
        '</div>' +
        '</div>' +
        '</div>' +
        '<div class="answer-list">' +
        '<div class="sl-no">' + '2' + '</div>' +
        '<div class="form-row">' +
        '<input type="text" class="input-ctrl" id=option2_'+j+'>' + '</div>' +
        '<div class="answer-ctrl">' +
        '<div class="chack-box">' +
        '<div class="toggle-btn">' +
        '<input id=anser_2_'+j+' type="radio" name="answertype_2">' +
        '<label for="anser_2_0">' + '</label>' +
        '</div>' +
        '<label>' + 'true' + '</label>' +
        '</div>' +
        '</div>' +
        '</div>' +
        '<div class="answer-list">' +
        '<div class="sl-no">' + '3' + '</div>' +
        '<div class="form-row">' +
        '<input type="text" class="input-ctrl" id=option3_'+j+'>' + '</div>' +
        '<div class="answer-ctrl">' +
        '<div class="chack-box">' +
        '<div class="toggle-btn">' +
        '<input id=anser_3_'+j+' type="radio" name="answertype_3">' +
        '<label for="anser_3_0">' + '</label>' +
        '</div>' +
        '<label>' + 'true' + '</label>' +
        '</div>' +
        '</div>' +
        '</div>' +
        // '<div class="answer-list">' +
        // '<div class="sl-no">' + '4' + '</div>' +
        // '<div class="form-row">' +
        // '<input type="text" class="input-ctrl" id=option4_'+j+'>' + '</div>' +
        // '<div class="answer-ctrl">' +
        // '<div class="chack-box">' +
        // '<div class="toggle-btn">' +
        // '<input id=anser_4_'+j+' type="radio" name="answertype_4">' +
        // '<label for="anser_4_0">' + '</label>' +
        // '</div>' +
        // '<label>' + 'true' + '</label>' +
        // '</div>' +
        // '</div>' +
        // '</div>' +
        '</div>'+
        '<div class="quiz-submit">'+
        '<div class="button-row">'+
        '<button class="btn-1" onclick="addNewCard()">'+'AddNew'+'</button>'+
        '<button class="btn-2" onclick="addQuizData()">'+'sumbit'+'</button>'+
        '</div>'+
        '</div>'+
        '</div>'
    quiz_card_list.appendChild(div);
    // console.log("div ",div);
    // console.log("quiz_card_list ",quiz_card_list);
}