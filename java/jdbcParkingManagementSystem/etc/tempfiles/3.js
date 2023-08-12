var v_ball = document.querySelector("#id_ball");
var v_bar = document.querySelector("#id_bar");
var v_myWidth = 10; //수평 이동거리
var v_myHeight = 5;


console.log("width: ", window.innerWidth);//사용자가 보는 화면 넓이 값
console.log("height: ", window.innerHeight);//사용자가 보는 화면 높이 값

//오른쪽 벽 맞고 튀어 나온다



var v_startGak = 0;
function f_move() {
    if (!v_ball.style.left) {
        v_ball.style.left = "10px";
        v_ball.style.top = "30px";
    }
    v_ball.style.left = parseInt(v_ball.style.left) + v_myWidth + "px";
    v_ball.style.top = parseInt(v_ball.style.top) + v_myHeight + "px";
    v_gakdo = v_gakdo + 5;
    v_ball.style.transform = "rotateX(" + +"deg)";
    var v_ballLeft = parseInt(v_ball.style.left);
    var v_ballTop = parseInt(v_ball.style.top);

    var v_ballRight = v_ballLeft + 100; //ball
    var v_ballBottom = v_ballTop + 100;

    //좌우 충돌
    if (v_ballRight > window.innerWidth || v_ballLeft < 0) {
        v_myWidth = -v_myWidth;
    }
    if (v_ballBottom > window.innerHeight || v_ballTop < 0) {
        v_myHeight = -v_myWidth;
    }

    setTimeout(f_move, 50);
}
//자바스크립트에서 브라우져의 탭 하나 하나를 window라는 키워드로 부른다.
//javascript는 window 안에서 돌아감, window안에 url이 바뀌면
//이전껀 다(선언되었던 변수 함수 등등 ) 없어짐

window.onkeydown = function () {
    //alert(Event.keyCode); // 누른 키값 확인
    if (Event.keyCode == "37") {//왼쪽
        v_bar.style.left = parseInt(v_bar.style.left) - 10 + "px";
    }
    if (Event.keyCode == "39") {//오른쪽
        v_bar.style.left = parseInt(v_bar.style.left) + 10 + "px";
    }
}
