<%@ page import="java.util.Random" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>비밀번호 찾기</title>
    <script src="/resources/js/jquery.min.js"></script>
    <script>
  $(function(){
      var text2 = $("#text").val();
      var text3 = $("#emailNum").val();

      $("#btn").on("click", function(){
          alert("Click");
          // document.getElementById('frm').submit();
          let fData = $("#frm").serialize();
          $.ajax({
              url: "/mail/sendMailProcess",
              type: "post",
              data: fData,
              success: function (data) { //여기서 data는 controller에 list이다.
                  alert("성공");
              }
          })
      })
      $("#btn2").on("click",function (text44){
          alert(JSON.stringify(text44));
         console.log(JSON.stringify(text44));
      })
  })

    </script>
    <title>웹 메일 보내기</title>
</head>
<body>
<h1>이메일 인증</h1>
${param.text2};
<div>
    <input type="button" name="btn" id="btn" value="이메일 보내기">
</div>

<div id="layer2">
인증번호를 입력하세요
    <input type="text" id="emailNum" name="emailNum">
    <input type="button"  id="btn2" name="btn2" value="인증번호 확인">
</div>


<form action="<%=request.getContextPath()%>/mail/sendMailProcess" id="frm" name="frm" method="post" >
    <div>
        <input type="text" id="from" name="from" placeholder="보재는 사람" value="pos04167@naver.com" readonly>
    </div>
    <div>
        <input type="text" id="to" name="to"
               placeholder="받는 사람" value="pos04167@naver.com" required="required"/>
    </div>
    <div>
        <% Random random = new Random();
            int checkNum = random.nextInt(888888) + 111111;
        %>
        <input type="text" id="subject" name="subject"
               placeholder="제목" value="이메일 인증번호" required="required"/>
        <textarea rows="7" cols="5" id="text" name="text"><%= checkNum%></textarea>
    </div>
    <button type="submit">메일 전송하기</button>
</form>
</body>