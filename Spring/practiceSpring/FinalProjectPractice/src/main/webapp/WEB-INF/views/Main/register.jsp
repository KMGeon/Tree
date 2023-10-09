<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <script src="/resources/js/jquery.min.js"></script>
    <script>
        function registerCheck(){
            var memEmail =$("#memEmail").val();
            $.ajax({
                url:"/memRegisterCheck",
                type:"get",
                data: {"memEmail": memEmail},
                success: function (result) {
                    if (result == 0) {
                        alert("가능",result);
                        console.log(result)
                    } else {
                        alert("불가능",result);
                        console.log(result);
                        $("#memEmail").val("");
                    }
                }
            });
        }

        function  passwordCheck(){
            var password=$("#memPasswd").val();
            var password2=$("#memPasswd2").val();
            if(password != password2){
                $("#passMessage").html("비밀번호가 서로 일치하지 않습니다.");
                $("#passMessage").css("color","red");
                $("#password").attr("value","$#@!");
                $("#sbtBtn").attr("disabled",true);
                return false;
            }
            else{
                $("#passMessage").html("비밀번호가 서로 같습니다..");
                $("#passMessage").css("color","blue");
                $("#sbtBtn").attr("disabled",false);
                return true;
            }
        }

    </script>
    <title>회원가입</title>
</head>
<body>
<form  action="/registerPost" method="post">
<input type="text" id="memEmail" name="memEmail" placeholder="이메일">
    <input type="button" id="emailChk" name="emailChk" value="이메일 중복체크" onclick="registerCheck()">
<br>
<input type="password" id="memPasswd" name="memPasswd" placeholder="비밀번호" onkeyup="passwordCheck()">
<br>
<input type="password" id="memPasswd2" name="memPasswd2" placeholder="비밀번호 확인"onkeyup="passwordCheck()">

<br>
<input type="text" id="memName" name="memName" placeholder="이름">
<br>
<input type="tel" id="memPhoneNumber" name="memPhoneNumber" placeholder="전화번호" >
    <br>
    <div>
        <span id="passMessage" style="color: red"/>
    </div>
    <input type="submit" id="sbtBtn" value="회원가입">
</form>
</body>
</html>
