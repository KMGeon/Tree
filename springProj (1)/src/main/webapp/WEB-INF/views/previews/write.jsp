<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                <div class="col-lg-5 d-none d-lg-block bg-register-image"></div>
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
                                    <form:input class="form-control form-control-user" placeholder="userId" path="userId"/>
                                    <font color="red">
                                       <form:errors path="userId" />
                                    </font>
                                </div>
                                <div class="col-sm-6">
                                    <form:input class="form-control form-control-user" placeholder="userName" path="userName"/>
                                    <font color="red">
                                       <form:errors path="userName" />
                                    </font>
                                </div>
                            </div>
                            <div class="form-group">
                                <form:input class="form-control form-control-user" placeholder="userEmail" path="userEmail"/>
                                <font color="red">
                                       <form:errors path="userEmail" />
                                </font>
                            </div>
                            <div class="form-group">
                                <form:input class="form-control form-control-user" placeholder="변경일자(yyyyMMdd)" path="updDate"/>
                                <font color="red">
                                       <form:errors path="updDate" />
                                </font>
                            </div>
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
                            <input type="submit" class="btn btn-primary btn-user btn-block" value="Register Account" />
                        </form:form>
                        <p /><p /><p /><p /><p /><p /><br /><br /><br /><br />
                        <p /><p /><p /><p /><p /><p /><br /><br /><br /><br />
                    </div>
                </div>
            </div>
        </div>
    </div>

</div>