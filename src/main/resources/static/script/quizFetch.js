window.onload=function () {

    var data = new FormData();
// data.append("", "");
// data.append("", fileInput.files[0], "/path/to/file");
    var xhr = new XMLHttpRequest();
    xhr.withCredentials = true;
    let quiz = {};
    xhr.addEventListener("readystatechange", function () {
        if (this.readyState === 4) {
            quiz = JSON.parse(this.responseText);
            // console.log("Log1--",this.responseText);
            // console.log("Log2--",quiz.data.length);
            getQuiz(quiz);
        }
    });
    xhr.open("GET", "/pulse/getQuizDetails?quiz_id=" + localStorage.getItem("quizID"));
    xhr.send(data);

    var data = new FormData();

    var xhr = new XMLHttpRequest();
    xhr.withCredentials = true;
    xhr.addEventListener("readystatechange", function() {
        if(this.readyState === 4) {
            //  console.log(this.responseText);
            var quizinfo=JSON.parse(this.responseText);
            document.getElementById("quiztitile").innerHTML=quizinfo.title;
            document.getElementById("quizinfo").innerHTML=quizinfo.info;
            document.getElementById("quizdescription").innerHTML=quizinfo.description;
        }
    });
    xhr.open("GET", "/pulse/getQuizContent");
    xhr.send(data);

    var data1 = new FormData();

    var xhr = new XMLHttpRequest();
    xhr.withCredentials = true;

    xhr.addEventListener("readystatechange", function() {
        let  quizdataLen={};

        if(this.readyState === 4) {
            quizdataLen=JSON.parse(this.responseText);

            var qData = quizdataLen.quizScore;
            let sortedStudents = qData.sort((a, b) => {
                if (a.score > b.score) {
                    return -1;
                }
                if (a.score < b.score) {
                    return 1;
                }
                return 0;
            });

            // console.log("Quiz participants",qData);

            getScoreList(quizdataLen);

            //WINNER VALUE
            const postName = document.getElementById("winEmpName");
            const postId = document.getElementById("winEmpId");
            const postImg = document.getElementById("winEmpImg");

            const confirmFirstWinner = document.getElementById("confirm-winner1");
            const confirmSecondWinner = document.getElementById("confirm-winner2");
            const confirmThirdWinner = document.getElementById("confirm-winner3");
            //  const confirmWinner = document.getElementById("confirm-winner");
            const winnerModal = document.getElementById("winner-modal");
            const winnerName = document.getElementById("winner-name");
            const winnerId = document.getElementById("winner-id");
            const winnerTitle = document.getElementById("winner-title");

            let first = document.querySelectorAll('input[type=radio][name="first"]');
            let second = document.querySelectorAll('input[type=radio][name="second"]');
            let third = document.querySelectorAll('input[type=radio][name="third"]');
            let prev = null;

            //WINNER TOGGLE ---1
            for(let i =0; i< first.length; i++){
                first[i].addEventListener('change', function () {
                    if(this !== prev){
                        prev = this;
                    }
                    first[i].parentNode.parentNode.parentNode.classList.add('active');
                    confirmFirstWinner.classList.add('active');
                    confirmSecondWinner.classList.remove('active');
                    confirmThirdWinner.classList.remove('active');
                    winnerTitle.innerText ="First Winner";
                    winnerId.innerHTML = this.value;
                    winnerModal.classList.toggle('show-c');
                    postId.value = this.value;
                    // console.log(this.value);
                    console.log("Post ID", postId.value);
                    //GET IMAGE

                    let qWinnerImage = new XMLHttpRequest();
                    qWinnerImage.withCredentials = true;
                    qWinnerImage.addEventListener("readystatechange", function() {
                        if(this.readyState === 4) {
                            const wFirstImg = this.responseText;
                            const img = wFirstImg;
                            postImg.value = img;
                            console.log("RES ",wFirstImg);
                        }

                    });
                    qWinnerImage.open("GET", "/pulse/getQuizMasterImages?empID="+this.value);
                    qWinnerImage.send();

                    //GET NAME
                    var qWinnerName = new XMLHttpRequest();
                    qWinnerName.withCredentials = true;
                    qWinnerName.addEventListener("readystatechange", function () {
                         if(this.readyState == 4){
                            const wFirstName = this.responseText;
                            const name = wFirstName;
                            winnerName.innerHTML = name;
                            postName.value = name;
                         }
                    });
                    qWinnerName.open("GET", "/pulse/getQuizMasterNames?empID="+this.value);
                    qWinnerName.send();

                });
            }

            //WINNER TOGGLE ---2
            for(let i =0; i< second.length; i++){
                second[i].addEventListener('change', function () {

                    if(this !== prev){
                        prev = this;
                    }
                    second[i].parentNode.parentNode.parentNode.classList.add('active');
                    confirmSecondWinner.classList.toggle('active');
                    confirmFirstWinner.classList.remove('active');
                    confirmSecondWinner.classList.add('active');
                    confirmThirdWinner.classList.remove('active');
                    winnerTitle.innerText ="Second Winner";
                    winnerId.innerHTML = this.value;
                    winnerModal.classList.toggle('show-c');
                    postId.value = this.value;

                    //GET IMAGE
                    let qWinnerImage = new XMLHttpRequest();
                    qWinnerImage.withCredentials = true;
                    qWinnerImage.addEventListener("readystatechange", function() {
                        if(this.readyState === 4) {
                            const wFirstImg = this.responseText;
                            const img = wFirstImg;
                            postImg.value = img;
                        }
                    });
                    qWinnerImage.open("GET", "/pulse/getQuizMasterImages?empID="+this.value);
                    qWinnerImage.send();

                    //GET NAME
                    let qWinnerName = new XMLHttpRequest();
                    qWinnerName.withCredentials = true;
                    qWinnerName.addEventListener("readystatechange", function () {
                        if(this.readyState == 4){
                            const wFirstName = this.responseText;
                            const name = wFirstName;
                            winnerName.innerHTML = name;
                            postName.value = name;
                        }
                    });
                    qWinnerName.open("GET", "/pulse/getQuizMasterNames?empID="+this.value);
                    qWinnerName.send();

                });
            }

            //WINNER TOGGLE ---3
            for(let i =0; i< third.length; i++){
                third[i].addEventListener('change', function () {
                    if(this !== prev){
                        prev = this;
                    }
                    third[i].parentNode.parentNode.parentNode.classList.add('active');

                    confirmFirstWinner.classList.remove('active');
                    confirmSecondWinner.classList.remove('active');
                    confirmThirdWinner.classList.add('active');
                    winnerTitle.innerText ="Third Winner";
                    winnerId.innerHTML = this.value;
                    winnerModal.classList.toggle('show-c');
                    postId.value = this.value;

                    //GET IMAGE
                    let qWinnerImage = new XMLHttpRequest();
                    qWinnerImage.withCredentials = true;
                    qWinnerImage.addEventListener("readystatechange", function() {
                        if(this.readyState === 4) {
                            const wFirstImg = this.responseText;
                            const img = wFirstImg;
                            postImg.value =img;
                        }
                    });
                    qWinnerImage.open("GET", "/pulse/getQuizMasterImages?empID="+this.value);
                    qWinnerImage.send();

                    //GET NAME
                    let qWinnerName = new XMLHttpRequest();
                    qWinnerName.withCredentials = true;
                    qWinnerName.addEventListener("readystatechange", function () {
                        if(this.readyState == 4){
                            const wThirdName = this.responseText;
                            const name = wThirdName;

                            winnerName.innerHTML = name;
                            postName.value = name;
                        }
                    });
                    qWinnerName.open("GET", "/pulse/getQuizMasterNames?empID="+this.value);
                    qWinnerName.send();
                });
            }

            //MODAL CLOSE
            const closeBtn = document.getElementById("clodeModal");
            closeBtn.addEventListener("click", modalClose);
            function modalClose() {
                document.getElementById("winner-modal").classList.remove('show-c');
            }

            confirmFirstWinner.addEventListener("click", submitFWinner);

            function submitFWinner() {
                let data = JSON.stringify({
                    name: postName.value,
                    pf_no: postId.value,
                    img_url: postImg.value
                });
                let firstWinner = new XMLHttpRequest();
                firstWinner.withCredentials = true;

                firstWinner.addEventListener("readystatechange", function () {

                });
                firstWinner.open("POST", "/pulse/addQuizFirst");
                firstWinner.setRequestHeader("Content-Type", "application/json");
                firstWinner.send(data);
                document.getElementById("name1stWinner").innerHTML = postName.value;
                document.getElementById("pfNum1stWinner").innerHTML = postId.value;
                document.getElementById("img1stWinner").src = `data:image/png;base64,${postImg.value}`;

                modalClose();
            }

            confirmSecondWinner.addEventListener("click", submitSWinner);
            function submitSWinner() {
                let data = JSON.stringify({
                    name: postName.value,
                    pf_no: postId.value,
                    img_url: postImg.value
                });
                let secondWinner = new XMLHttpRequest();
                secondWinner.withCredentials = true;
                secondWinner.addEventListener("readystatechange", function () {

                });
                secondWinner.open("POST", "/pulse/addQuizSecond");
                secondWinner.setRequestHeader("Content-Type", "application/json");
                secondWinner.send(data);
                document.getElementById("name2ndWinner").innerHTML = postName.value;
                document.getElementById("pfNum2ndWinner").innerHTML = postId.value;
                document.getElementById("img2ndWinner").src = `data:image/png;base64,${postImg.value}`;
                modalClose();
            }
            confirmThirdWinner.addEventListener("click", submitTWinner);
            function submitTWinner() {
                let data =JSON.stringify({
                    name: postName.value,

                    pf_no: postId.value,
                    img_url: postImg.value
                });

                let thirdWinner = new XMLHttpRequest();
                thirdWinner.withCredentials = true;
                thirdWinner.addEventListener("readystatechange", function () {

                });
                thirdWinner.open("POST", "/pulse/addQuizThird");
                thirdWinner.setRequestHeader("Content-Type", "application/json");
                thirdWinner.send(data);
                document.getElementById("name3rdWinner").innerHTML = postName.value;
                document.getElementById("pfNum3rdWinner").innerHTML = postId.value;
                document.getElementById("img3rdWinner").src = `data:image/png;base64,${postImg.value}`;
                modalClose();
            }
        }

    });
    var Quiz_ID=localStorage.getItem("quizID");
    // console.log(Quiz_ID);
    console.log("QID",Quiz_ID);
    xhr.open("GET", "/pulse/getQuizMasterAnswers?quiz_id=" +Quiz_ID);
    xhr.send(data1);

    winnerList();
}

function getQuiz(quiz) {

    const quizCardrow = document.getElementById("view-quiz-row");
    for (let i = 0; i < quiz.data.length; i++) {
        // console.log(quiz.data.length);
        var div = document.createElement("div");
        var Answer = quiz.data[i].answer;
        var Option1 = quiz.data[i].option1;
        var Option2 = quiz.data[i].option2;
        var Option3 = quiz.data[i].option3;
        // var Option4 = quiz.data[i].option4;
        var Question = quiz.data[i].question;
        var QuizId = quiz.data[i].quizId;

        div.innerHTML = '<div class="view-list active">' +
            '<div class="quiz-view-title">' +
            '<strong>' + Question + '</strong>' +
            '<div class="control-row"> ' + '<ion-icon name="chevron-down-outline">' + '</ion-icon>' +
            '</div>' +
            '</div>' +
            '<div class="quiz-answer-view">' + '<p class="answer-list">' + Option1 + '</p>' +
            '<p class="answer-list">' + Option2 + '</p>' +
            '<p class="answer-list">' + Option3 + '</p>' +
            // '<p class="answer-list">' + Option4 + '</p>' +
            '</div>' +
            '</div>'

        quizCardrow.appendChild(div);
    }
}

function  getScoreList(quizdataLen) {
    const quizscore = document.getElementById("scorelist");
    for (let i = 0; i < quizdataLen.quizScore.length; i++) {
        var emp_id = quizdataLen.quizScore[i].empID;
        var score_list = quizdataLen.quizScore[i].score;
        let tableRow = document.getElementById("table")
        let tableList = document.createElement('tr');
        tableList.innerHTML= '<td><span class="table-text">' + i + '</span></td>' +
            '<td><span class="table-text">' + emp_id + '</span></td>' +
            '<td><span class="badge-label">' + score_list + '</span></td>'+
            '<td><div class="price-ctrl">' +
            '<div class="check-box">'+
            '<div class="winner-btn">'+
            '<input id="first_'+ +i +'" type="radio" name="first" value="'+ emp_id +'">'+
            '<label class="first" for="first_'+ +i +'">First</label>'+
            '</div></div>'+
            '<div class="check-box ">'+
            '<div class="winner-btn">'+
            '<input id="second_'+ +i +'" type="radio" name="second" value="'+ emp_id +'">'+
            '<label class="second" for="second_'+ +i +'">Second</label>'+
            '</div></div>'+
            '<div class="check-box ">'+
            '<div class="winner-btn">'+
            '<input id="third_'+ +i +'" type="radio" name="third" value="'+ emp_id +'">'+
            '<label class="third" for="third_'+ +i +'">Third</label>'+
            '</div></div>'+
            '</div>' +
            '</td>'
        // quizscore.appendChild(div)
        tableRow.append(tableList);
    }

}


//FETCH WINNER
function winnerList() {
    var xhr = new XMLHttpRequest();
    xhr.withCredentials = true;

    xhr.addEventListener("readystatechange", function() {
        if(this.readyState === 4) {
            var firstprize=JSON.parse(this.responseText)
            document.getElementById("name1stWinner").innerHTML=firstprize.name;
            document.getElementById("pfNum1stWinner").innerHTML=firstprize.pf_no;
            document.getElementById("img1stWinner").src = `data:image/png;base64,${firstprize.img_url}`;
            // document.getElementById("img1st").innerHTML=
        }
    });

    xhr.open("GET", "/pulse/getQuizWinnerFirst");
    xhr.send();

    //=====================
    var xhr = new XMLHttpRequest();
    xhr.withCredentials = true;

    xhr.addEventListener("readystatechange", function() {
        if(this.readyState === 4) {
            // console.log(this.responseText);
            var secondprize=JSON.parse(this.responseText)
            // console.log("RES", this.responseText);
            document.getElementById("name2ndWinner").innerHTML=secondprize.name;
            document.getElementById("pfNum2ndWinner").innerHTML=secondprize.pf_no;
            document.getElementById("img2ndWinner").src = `data:image/png;base64,${secondprize.img_url}`;
        }
    });

    xhr.open("GET", "/pulse/getQuizWinnerSecond");
    xhr.send();

    //=====================
    var xhr = new XMLHttpRequest();
    xhr.withCredentials = true;

    xhr.addEventListener("readystatechange", function() {
        if(this.readyState === 4) {
            //console.log("Winner third",this.responseText);
            var thirdprize=JSON.parse(this.responseText);
            document.getElementById("name3rdWinner").innerHTML=thirdprize.name;
            document.getElementById("pfNum3rdWinner").innerHTML=thirdprize.pf_no;
            document.getElementById("img3rdWinner").src = `data:image/png;base64,${thirdprize.img_url}`;
        }
    });
    xhr.open("GET", "/pulse/getQuizWinnerThird");
    xhr.send();

}










