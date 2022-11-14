<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!doctype html>
<html lang="en">
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Hello, world!</title>

    <script>
        function registerCheck() {
            var memId = $("#memId").val();
            $.ajax({
                url: "/memRegisterCheck",
                type: "get",
                data: {"memId": memId},
                success: function (result) {
                    if (result == 0) {
                        alert("가능",result);
                        console.log(result)
                        $("#idchk").attr("value", "사용이 가능합니다.");
                        $("#idchk").css("color", "blue");
                        $("#idchk").attr("type", "text");
                    } else {
                        alert("불가능",result);
                        console.log(result);
                    }
                }
            });
        }

        function  passwordCheck(){
            var password1=$("#password1").val();
            var password2=$("#password2").val();

            if(password1 != password2){
                $("#passMessage").html("비밀번호가 서로 일치하지 않습니다.");
                $("#password1").attr("value","$#@!");
            }else{
                $("#passMessage").html("비밀번호가 서로 같습니다..");
                $("#passMessage").css("color","blue");
            }

        }
    </script>


</head>
<body>
<form action="/register" method="get">
    <div>
        <input type="text" id="memId" value="아이디">
        <input type="button" value="중복버튼" onclick="registerCheck()">
    </div>
    <div>
        <input type="hidden" id="idchk" value="아이디가 중복" readonly>
    </div>
    <div>
        <input type="text" id="password1" value="비밀번호" onkeyup="passwordCheck()">
    </div>
    <div>
        <input type="text" id="password2" value="비밀번호확인" onkeyup="passwordCheck()">
    </div>
    <div>
       <span id="passMessage" style="color: red"/>
    </div>
    <div>
        <input type="text" value="주소">
    </div>
    <div>
        <input type="text" value="휴대폰">
    </div>
    <input type="text" value="나이">
    <div>
        <input type="submit" value="전송">
    </div>
</form>


</body>
</html>
