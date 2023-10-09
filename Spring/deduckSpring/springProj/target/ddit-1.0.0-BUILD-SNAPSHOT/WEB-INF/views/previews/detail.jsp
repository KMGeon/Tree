<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<script type="text/javascript" src="/resources/js/jquery-3.6.0.js"></script>
<script type="text/javascript">
function fn_chk(){
//    return false;
   let userPw = document.getElementById("userPw").value;
   let userPwCheck = document.getElementById("userPwCheck").value;
   let spanPwCheck = document.getElementById("spanPwCheck");
   
   console.log("userPw : " + userPw);
   console.log("userPwCheck : " + userPwCheck);
   
   if(userPw != userPwCheck){
      spanPwCheck.innerHTML = "비밀번호가 다릅니다.";
      return false;
   }
   
//    alert("비밀번호를 확인해주세요");
   return true;
}
</script>
<div class="container">

<div class="card o-hidden border-0 shadow-lg my-5">
    <div class="card-body p-0">
        <!-- Nested Row within Card Body -->
            <div class="row">
               <!-- 왼쪽 대표 이미지 -->
                <div class="col-lg-5 d-none d-lg-block bg-register-image"
                   <c:if test="${memVO.attachVOList[0].filename != null}"> 
                   style="background-image:url('/resources/upload${memVO.attachVOList[0].filename}');background-size:cover;"
                   </c:if>
                   ></div>
                <div class="col-lg-7">
                    <div class="p-5">
                        <div class="text-center">
                            <h1 class="h4 text-gray-900 mb-4">Create an Account!</h1>
                        </div>
                        <form:form modelAttribute="memVO" class="user" method="post" action="/previews/writePost" onsubmit="return fn_chk()">
                            <div class="form-group row">
                                <div class="col-sm-6 mb-3 mb-sm-0">
                                <!-- input type=text -> form:input -->
                                <!-- id=userId , name=userId -> path:userId -->
                                    <form:input class="form-control form-control-user" placeholder="userId" path="userId" readonly="true"/>
                                    <font color="red">
                                       <form:errors path="userId" />
                                    </font>
                                </div>
                                <div class="col-sm-6">
                                    <form:input class="form-control form-control-user" placeholder="userName" path="userName" readonly="true"/>
                                    <font color="red">
                                       <form:errors path="userName" />
                                    </font>
                                </div>
                            </div>
                            <div class="form-group">
                                <form:input class="form-control form-control-user" placeholder="userMail" path="userEmail" readonly="true"/>
                                <font color="red">
                                       <form:errors path="userEmail" />
                                </font>
                            </div>
                            <div class="form-group">
                                <form:input class="form-control form-control-user" placeholder="변경일자(yyyyMMdd)" path="updDate" readonly="true"/>
                                <font color="red">
                                       <form:errors path="updDate" />
                                </font>
                            </div>
                            <!-- 일반 모드 시작 -->
                            <div class="form-group-ab" id="attach0">
                            	<c:forEach var="attachVO" items="${memVO.attachVOList}">
                            	
                            	</c:forEach>
                            
                            	<c:set var="filename" value="${attachVO.filename}" />
                                   <c:set var="filenameLen" value="${fn:length(filename)}" />
                                   <img src="/resources/upload${fn:substring(filename,0,12)}s_${fn:substring(filename,12,filenameLen)}" />
                            	<form:input class="form-control form-control-user" path="attachVOList[0].filename" placeholder="첨부파일"/> 
 								<font id="font1" color="red">
 								<form:errors path="attachVOList[0].filename" />
 								</font>                           	
 
                            </div>
                            
                            <div id="spn1">
                               <p>
                                  <button type="button" id="edit" class="btn btn-warning btn-user btn-block" style="width: 50%; float: left;">수정</button>
                                  <button type="button" id="delete" class="btn btn-danger btn-user btn-block" style="width: 50%;">삭제</button>
                               </p>
                               <p>
                                  <a href="/previews/list" class="btn btn-primary btn-user btn-block">목록</button></a>
                               </p>
                            </div>
                            <!-- 일반 모드 끝 -->
                            <!-- 수정 모드 시작 -->
                            <div id="spn2" style="display: none;">
                            <!-- 첨부파일 추가 -->
                            <a class="btn btn-success btn-circle btn-sm" onclick="plusFile()">
                                        <i class="fas fa-check"></i>
                            </a>
                            <!-- 첨부파일 삭제 -->
                            <a class="btn btn-danger btn-circle btn-sm" onclick="minusFile()" >
                                        <i class="fas fa-trash"></i>
                            </a>
                            <div class="form-group">
                                <form:input class="form-control form-control-user" placeholder="첨부파일" path="attachVOList[0].filename"/>
                                <font color="red">
                                       <form:errors path="attachVOList[0].filename" />
                                </font>
                                <div id="fileForm"></div>
                            </div>
                            <div class="form-group row">
                                <div class="col-sm-6 mb-3 mb-sm-0">
                                    <form:password class="form-control form-control-user" placeholder="Password" path="userPw"/>
                                    <font color="red">
                                       <form:errors path="userPw" />
                                    </font>
                                </div>
                                <div class="col-sm-6">
                                    <input type="password" class="form-control form-control-user" id="userPwCheck" placeholder="Repeat Password" />
                                    <font color="red">
                                       <span id="spanPwCheck">
                                        </span>
                                    </font>
                                </div>
                            </div>
                            <button type="submit" class="btn btn-primary btn-user btn-block">확인</button>
                            <a href="/previews/detail?userNo=${param.userNo}" class="btn btn-primary btn-user btn-block">취소</a>
                            </div>
                            <!-- 수정 보드 끝 -->
                        </form:form>
                        <p /><p /><p /><p /><p /><p /><br /><br /><br /><br />
                        <p /><p /><p /><p /><p /><p /><br /><br /><br /><br />
                    </div>
                </div>
            </div>
        </div>
    </div>

</div>
<script type="text/javascript">
   let cnt = 1;
   
   function plusFile() {
      $("#fileForm").append("<input type='text' class='form-control form-control-user' id='fileNamePlus' name='attachVOList["+cnt+"].filename' placeholder='첨부파일' />");
      cnt++;
   }
   
   function minusFile() {
      $("input[id='fileNamePlus'][name='attachVOList["+(--cnt)+"].filename']").remove();
      console.log("cnt : " + cnt);
      if(cnt <= 0){
         cnt = 1;
      }
   }
   
   $(function() {
      $("#edit").on("click", function() {
         // 일반 모드는 가림
         $("#spn1").css("display", "none");
         // 수정 모드는 보임
         $("#spn2").css("display", "block");
         // 입력란 활성화
         $(".form-control-user").removeAttr("readonly");
      })   
   });
</script>