<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <style>
        * { box-sizing:border-box; }
        form {
            width:400px;
            height:600px;
            display : flex;
            flex-direction: column;
            align-items:center;
            position : absolute;
            top:50%;
            left:50%;
            transform: translate(-50%, -50%) ;
            border: 1px solid rgb(89,117,196);
            border-radius: 10px;
        }
        .input-field {
            width: 300px;
            height: 40px;
            border : 1px solid rgb(89,117,196);
            border-radius:5px;
            padding: 0 10px;
            margin-bottom: 10px;
        }

        label {
            width:300px;
            height:30px;
            margin-top :4px;
        }
        button {
            background-color: rgb(89,117,196);
            color : white;
            width:300px;
            height:50px;
            font-size: 17px;
            border : none;
            border-radius: 5px;
            margin : 20px 0 30px 0;
        }
        .title {
            font-size : 50px;
            margin: 40px 0 30px 0;
        }
        .msg {
            height: 30px;
            text-align:center;
            font-size:16px;
            color:red;
            margin-bottom: 20px;
        }

        .sns-chk {
            margin-top : 5px;
        }
    </style>
    <title>Register</title>
    <script>
        function registerCheck(){
            var email =$("#email").val();
            $.ajax({
                url:"/memRegisterCheck",
                type:"get",
                data: {"email": email},
                success: function (result) {
                    if (result == 0) {
                        alert("가능",result);
                        console.log(result)

                    } else {
                        alert("불가능",result);
                        console.log(result);
                        $("#email").val("");
                    }
                }
            });
        }


        function pwdCheck(){
            let password = $("#password").val();
            let password2 = $("#password2").val();
            if(password != password2) {
                $("#pwdChk").css("color", "red");
                $("#pwdChk").html( "비밀번호가 다릅니다..");com
                $("#btn").attr("disabled", true);
            }else{
                $("#pwdChk").css("color", "green");
                $("#pwdChk").attr("value", "비밀번호가 같다..");
                $("#btn").attr("disabled", false);
            }
        }



    </script>
</head>
<body>
<form action="/join" method="post" onsubmit="return formCheck(this)">
    <div class="title">Register</div>
    <div id="msg" class="msg"><form:errors path="id"/></div>
    <label for="">아이디</label>
    <input class="input-field" type="text"id="email" name="email" placeholder="8~12자리의 영대소문자와 숫자 조합 이메일" autofocus>
    <input type="button" value="중복체크" id="idBtn" onclick="registerCheck()">
    <div><input type="text" value="아이디가 다릅니다." id="pwdChk" name="pwdChk"></div>
    <label for="">비밀번호</label>
    <input class="input-field" type="text" id="password" name="password" placeholder="8~12자리의 영대소문자와 숫자 조합 비밀번호" onkeyup="pwdCheck()">
    <label for="">비밀번호</label>
    <input class="input-field" type="text" id="password2" name="password2" placeholder="8~12자리의 영대소문자와 숫자 조합 비밀번호"onkeyup="pwdCheck()">
    <label for="">이름</label>
    <input class="input-field" type="text" name="name" placeholder="이름">
    <label for="">나이</label>
    <input class="input-field" type="text" name="age" placeholder="나이">
    <button id="btn">회원 가입</button>
</form>
<script>
    function formCheck(frm) {
        let msg ='';
        if(frm.email.value.length<3) {
            setMessage('id의 길이는 3이상이어야 합니다.', frm.id);
            return false;
        }
        return true;
    }
    function setMessage(msg, element){
        document.getElementById("msg").innerHTML = `<i class="fa fa-exclamation-circle"> ${'${msg}'}</i>`;
        if(element) {
            element.select();
        }
    }
</script>
</body>
</html>