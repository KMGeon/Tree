


console.log(v_lgh);

var v_cha = document.querySelectorAll(".child")[1];
function f_remove() {
    if (!v_cha.style.left) {

        //만약에 inline style로 정의되어 있지 않으면 
        v_cha.style.left = "80px";
    }
    alert("ppp" + v_cha.style.left + "ppp");
    v_cha.style.left = parseInt( v_cha.style.left + 10+"px");
}