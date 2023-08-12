<%@page import="kr.or.funding.member.VO.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>회원가입</title>
    <link rel="stylesheet" href="/Funding/view/css/reset.css">
    <link rel="stylesheet" href="/Funding/view/css/common.css">
    <link rel="stylesheet" href="/Funding/view/css/join.css">

    <script src="http://code.jquery.com/jquery-latest.min.js"></script>
    <script src="/Funding/view/js/join.js"></script>
    <script src="/Funding/view/main/js/common.js"></script>

    <script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>

    <script src="/Funding/view/js/join.js"></script>

    <SCRIPT LANGUAGE="JavaScript">

        if (top.document.location.search != "?mobile") //접속할때 모바일이면 아래 strGoto 주소의 html로 접속됨
        {
            var strGoto = "mobile_join.html";
            var str = window.navigator.userAgent.toLowerCase();
            if (/iphone/.test(str) || /android/.test(str) || /opera/.test(str) || /bada/.test(str) || /iemobile/.test(str) || /lgtelecom/.test(str) || /ppc/.test(str) || /sonyericsson/.test(str) || /sonyericsson/.test(str) || /blackberry/.test(str)) {
                top.document.location.replace(strGoto);
            }
        }

    </SCRIPT>

    <script>
        function id_check() {
            var id = $(".field_id input").val();             // 변수 id에  id값 대입

            // var num = /[0-9]/;
            // var eng = /[a-zA-Z]/;
            var spe = /[~!@#$%^&*()_+|<>?:{}]/;



            if (id.length < 6) {                     //만약에 id의 길이가 6보다 작으면
                $(".field_id .txt_guide .txt_case1").css('color', '#b3130b');   //글자색 변경
            } else if (spe.test(id) == 1) {    //spe.test(id) --> id안에 특수문자가 있으면 true반환 없으면 false반환
                $(".field_id .txt_guide .txt_case1").css('color', '#b3130b');   //글자색 변경
            } else {          // 모든 조건이 일치하지 않다면  
                $(".field_id .txt_guide .txt_case1").css('color', '#0f851a');  //글자색 초록색으로 변경
            }
        }

        function pw_check() {
            var pw = $(".field_pw input").val();                   // 변수 pw에 pw값 대입

            var num = /[0-9]/;
            var eng = /[a-zA-Z]/;
            var spe = /[~!@#$%^&*()_+|<>?:{}]/;



            if (pw.length < 10) {                                  //pw의 길이가 10 이하일 때
                $(".field_pw .txt_guide .txt_case1").css('color', '#b3130b');
            }

            if (pw.length >= 10) {                                  //pw의 길이가 10 이상일 때
                $(".field_pw .txt_guide .txt_case1").css('color', '#0f851a');
            }

            if (num.test(pw) == 0 || eng.test(pw) == 0 || spe.test(pw) == 0) {    // pw의 숫자가 없거나 , 영어가 없거나, 특수문자가 없을경우 실패
                $(".field_pw .txt_guide .txt_case2").css('color', '#b3130b');
            }


            if (num.test(pw) == 1 && eng.test(pw) == 1 && spe.test(pw) == 1) { // pw의 숫자,영어,특수문자가 1개이상씩 있을경우 성공
                $(".field_pw .txt_guide .txt_case2").css('color', '#0f851a');
            }

        }



        function pw2_check() {
            var pwd1 = $(".field_pw input").val();
            var pwd2 = $(".field_repw input").val();

            if (pwd1 != '' && pwd2 == '') {          //둘다 빈칸일 경우 아무것도 하지 않음
                null;
            } else if (pwd1 != "" || pwd2 != "") {     // 빈칸이 아닐 경우
                if (pwd1 == pwd2) {                    // 비교해서 같으면
                    $(".field_repw .txt_guide .txt_case1").css('color', '#0f851a');
                    $(".field_repw .txt_guide .txt_case1").text("비밀번호가 동일합니다.");


                } else {        // 비교해서 같지 않으면
                    $(".field_repw .txt_guide .txt_case1").css('color', '#b3130b');
                    $(".field_repw .txt_guide .txt_case1").text("비밀번호가 일치하지 않습니다.");


                }
            }



        }






    </script>

    <script>
        // onsubmit 관련된 코드짜기      

        function on_id_check() {          //id 검사 함수
            var id = $(".field_id input").val();
            var spe = /[~!@#$%^&*()_+|<>?:{}]/;
            if (id.length < 6) {
                alert('아이디를 정확히 입력해주세요');
                return false;
            } else if (spe.test(id) == 1) {

                alert('아이디를 정확히 입력해주세요');
                return false;
            }
            else {

                return true;
            }
        }

        function on_pw_check() {           //비밀번호 검사 함수
            var pw = $(".field_pw input").val();
            var num = /[0-9]/;
            var eng = /[a-zA-Z]/;
            var spe = /[~!@#$%^&*()_+|<>?:{}]/;
            if (pw.length < 10) {

                alert('비밀번호를 정확히 입력해주세요');
                return false;
            } else if (num.test(pw) == 0 || eng.test(pw) == 0 || spe.test(pw) == 0) {

                alert('비밀번호를 정확히 입력해주세요');
                return false;
            }
            else {

                return true;
            }
        }

        function on_pw2_check() {           //비밀번호 확인 검사 함수
            var pw = $(".field_pw input").val();
            var pw2 = $(".field_repw input").val();
            if (pw2.length == 0) {

                alert('비밀번호 확인을 다시해주세요');
                return false;
            } else if (pw != pw2) {

                alert('비밀번호 확인을 다시해주세요');
                return false;
            }
            else {

                return true;
            }
        }

        function on_name_check() {             //이름 검사 함수
            var name = $(".field_name input").val();
            if (name.length == 0) {

                alert('이름을 입력해주세요');
                return false;
            } else {

                return true;
            }
        }

        function on_email_check() {             //이메일 검사 함수
            var email = $(".field_email input").val();
            if (email.length == 0) {

                alert('이메일을 정확히 입력해주세요');
                return false;
            } else {

                return true;
            }
        }

        function on_phone_check() {               //휴대폰번호 검사함수  
            var phone = $(".field_phone input").val();
            if (phone.length == 0) {

                alert('휴대폰 번호를 입력해주세요');
                return false;
            } else {

                return true;
            }
        }



        function on_submit_check() {               // fomr에 onsubmit으로 최종적으로 넘겨야될 함수

            var check = false;                   //check변수에 false를 넣어주고 다른 함수들이 false를 반환하면 다음페이지로 못넘어가게 설정
            if (on_id_check() == check) {
                return false;
            } else if (on_pw_check() == check) {
                return false;
            } else if (on_pw2_check() == check) {
                return false;
            } else if (on_name_check() == check) {
                return false;
            } else if (on_email_check() == check) {
                return false;
            } else if (on_phone_check() == check) {
                return false;
            }
            else {
                return true;
            }
        }



    </script>
</head>

<body>


    <div id="container">
        <div id="header">
            <div class="user_menu">

               <ul class="sign_menu">
                    <!--login class 추가-->
                     <% MemberVO memVo = (MemberVO) session.getAttribute("memList");%>
            
                    <% if(memVo == null){ %>
                    <li class="link">
                        <a href="./joinselect.jsp" class="item after join">회원가입</a>
                    </li>
                    <li class="link">
                        <a href="./login.jsp" class="item after login_none">로그인</a>
                    </li>
                        <%} else{ %>
                        <li class="link">
                        <a href="./joinselect.html" class="item after join"><%=memVo.getMbsNm()%>님 어서오세요</a>
                    </li>
                        <%if(memVo.getMbsAhy() == 2){ %>
                    <li class="link">
                        <a href="/Funding/view/notice_seller.jsp" class="item after login_none">판매자페이지</a>
                    </li> 
                    	<%} %>
                        <%if(memVo.getMbsAhy() == 1){ %>
                    <li class="link">
                        <a href="/Funding/view/notice_seller.jsp" class="item after login_none">마이페이지</a>
                    </li> 
                    	<%} %>
                        <%if(memVo.getMbsAhy() == 0){ %>
                    <li class="link">
                        <a href="/Funding/view/notice_seller.jsp" class="item after login_none">관리자페이지</a>
                    </li> 
                    	<%} %>
                    <li class="link">
                        <a href="./logout.jsp" onclick="logout()" class="item after login_none">로그아웃
                        </a>
                    </li> 
                        <%} %>
                    <li class="link">

                        <!-- 고객센터 hover 시 sub_menu 등장 -->
                        <ul class="sub_menu">
                            <li class="list">
                                <a href="#" class="list_item">공지사항</a>
                            </li>
                            <li class="list">
                                <a href="#" class="list_item">자주하는 질문</a>
                            </li>
                            <li class="list">
                                <a href="#" class="list_item">1:1 문의</a>
                            </li>
                            <li class="list">
                                <a href="#" class="list_item">상품 제안</a>
                            </li>
                            <li class="list">
                                <a href="#" class="list_item">에코포장 피드백</a>
                            </li>
                        </ul>
                    </li>
                </ul>
                <!-- .sign_menu -->
            </div>
            <div class="header_logo">
                <h1 class="logo">
                    <a href="/Funding/ptimfor/ptimforlist.do" class="link_main">
                        <span class="gnb_logo_container"></span>
                        <img src="/Funding/view/img/logo.jpg" alt="로고" class="logo_img"
                            onclick="location.href='/Funding/ptimfor/ptimforlist.do'">
                    </a>
                </h1>
            </div>
            <div class="gnb">
                <!-- gnb = global nav bar = 최상위 메뉴  -->
                <h2 class="blind">메뉴</h2>
                <ul class="gnb_menu">
                    <li class="list gnb_main">
                        <a href="#" class="link">
                            <span class="ico"></span>
                            <!-- ico는 css에서 bg url로 처리 -->
                            <span class="txt">전체 카테고리</span>
                        </a>
                        <div class="gnb_sub">
                            <ul class="menu">
                                <li class="current">
                                    <!-- hover나 active시 current가 옮겨가도록 설정해야 함 -->
                                    <a href="#" class="main_item">
                                        <span class="ico">
                                            <img src="https://img-cf.kurly.com/shop/data/category/icon_newyear_inactive_pc@2x.1609722514.png"
                                                alt="" class="ico off">
                                            <img src="https://img-cf.kurly.com/shop/data/category/icon_newyear_active_pc@2x.1609722514.png"
                                                alt="" class="ico on">
                                            <!--카테고리 별 아이콘은 직관적이게 img src로 바로 삽입 
                                                클릭X(off): 기본, 클릭(on): 보라색 아이콘-->
                                        </span>
                                        <span class="tit">
                                            <span class="txt">블라블라블라</span>


                                            <span class="ico_new" alt="new"></span>
                                            <!-- ico_nex: bg url로 처리 -->
                                        </span>
                                        <!-- tit: text + ico -->
                                    </a>
                                </li>
                            </ul>
                        </div>
                    </li>
                    <li class="list">
                        <a href="#" class="link">
                            <span class="txt">펀딩</span>
                        </a>
                    </li>
                    <li class="list">
                        <a href="#" class="link">
                            <span class="txt">공지사항</span>
                        </a>
                    </li>
                    <li class="list">
                        <a href="#" class="link">
                            <span class="txt">회사소개</span>
                        </a>
                    </li>
                    <div class="gnb_search">
                        <form action="">
                            <input type="text" id="keyword" value="건강 기원 새해맞이 보양식 레시피" title="검색어입력" class="inp_search">
                            <input type="image" src="https://res.kurly.com/pc/service/common/1908/ico_search_x2.png"
                                class="btn_search">
                            <div class="init">
                                <button type="button" id="search_init" class="btn_delete">검색어 삭제하기</button>
                            </div>
                        </form>
                    </div>

                </ul>
            </div>
        </div>
        <div id="main">
            <div id="content">
                <div class="page_aticle">
                    <div class="type_form member_join">
                        <form id="form" action="/Funding/member/insertSel.do" method="POST" onsubmit="return on_submit_check();" enctype="multipart/form-data">
                            <div class="field_head">
                                <h3 class="tit">판매자회원가입</h3>
                                <p class="sub">
                                    <span class="ico">*</span>
                                    필수입력사항
                                </p>
                            </div>

                            <table class="tbl_comm">
                                <tbody>
                                    <tr class="fst field_id">
                                        <th>아이디
                                            <span class="ico">
                                                *
                                                <span class="screen_out">필수항목</span>
                                            </span>
                                        </th>
                                        <td>
                                            <input type="text" name="mbsId" id="id" maxlength="16" required="" option="regId"
                                                onkeyup="id_check()" placeholder="6자 이상의 영문 혹은 영문과 숫자를 조합">
                                            <a class="btn default"  id="checkId" href="#">중복확인</a>
                                            <p class="txt_guide square">
                                                <span class="txt txt_case1">
                                                    6자 이상의 영문 혹은 영문과 숫자를 조합
                                                </span>
                                                <span class="txt txt_case2">
                                                    아이디 중복확인
                                                </span>
                                            </p>
                                        </td>
                                    </tr>

                                    <tr class="field_pw">
                                        <th>
                                            비밀번호
                                            <span class="ico">*
                                                <span class="screen_out">필수항목</span>
                                            </span>
                                        </th>
                                        <td>
                                            <input type="password" name="mbsPw" required="" option="regPass"
                                                onkeyup="pw_check()" maxlength="16" class="reg_pw bad"
                                                placeholder="비밀번호를 입력해주세요">
                                            <p class="txt_guide square">
                                                <span class="txt txt_case1">
                                                    10자 이상 입력
                                                </span>
                                                <span class="txt txt_case2">
                                                    영문/숫자/특수문자(공백 제외)만 허용하며, 2개 이상 조합
                                                </span>
                                            </p>
                                        </td>
                                    </tr>

                                    <tr class="member_pwd field_repw">
                                        <th>
                                            비밀번호확인
                                            <span class="ico">
                                                *
                                                <span class="screen_out">필수항목</span>
                                            </span>
                                        </th>
                                        <td>
                                            <input type="password" required="" option="regPass" onkeyup="pw2_check()"
                                                maxlength="16" class="confirm_pw" placeholder="비밀번호를 한번 더 입력해주세요">
                                            <p class="txt_guide square">
                                                <span class="txt txt_case1">
                                                    동일한 비밀번호를 입력해주세요.
                                                </span>
                                            </p>
                                        </td>
                                    </tr>

                                    <tr class="field_name">
                                        <th>
                                            이름
                                            <span class="ico">
                                                *
                                                <span class="screen_out">필수항목</span>
                                            </span>
                                        </th>
                                        <td>
                                            <input type="text" name="mbsNm" required="" label="이름"
                                                placeholder="이름을 입력해주세요">
                                        </td>
                                    </tr>

                                    <tr class="field_email">
                                        <th>
                                            이메일
                                            <span class="ico">
                                                *
                                                <span class="screen_out">필수항목</span>
                                            </span>
                                        </th>

                                        <td>
                                            <input type="text" name="mbsMail" size="30" required="" option="regEmail"
                                                label="이메일" placeholder="예: marketkurly@kurly.com">
                                            
                                        </td>
                                    </tr>


                                    <tr class="field_phone">
                                        <th>
                                            휴대폰
                                            <span class="ico">
                                                *
                                                <span class="screen_out">필수항목</span>
                                            </span>
                                        </th>

                                        <td>
                                            <div class="phone_num">
                                                <input type="text" value="" pattern="[0-9]*" name="mbsPh"
                                                    placeholder="숫자만 입력해주세요" class="inp">

                                            </div>
                                        </td>
                                    </tr>

                                    <tr>
                                        <th>
                                            주소
                                            <span class="ico">
                                                *
                                                <span class="screen_out">필수항목</span>
                                            </span>
                                        </th>
                                        <td class="field_address">
                                            <a href="#" id="addressSearch" class="search">
                                                <span id="addressNo" class="address_no">주소검색</span>
                                            </a>
                                            <input type="text" id="user_address" name="mbsAddr1" placeholder="주소">
                                            <input type="text" id="user_detail_address" name="mbsAddr2"
                                                placeholder="상세주소">
                                            <p class="txt_guide" style="display:block;">
                                                <span class="txt txt_case1">배송지에 따라 상품 정보가 달라질 수 있습니다.</span>

                                            </p>
                                        </td>
                                    </tr>

                                    <tr class="birth field_birth">
                                        <th>생년월일</th>

                                        <td>
                                            <div class="birth_day">
                                                <input type="text" name="brDt1" id="birth_year" pattern="[0-9]*"
                                                    label="생년월일" size="4" maxlength="4" placeholder="YYYY">
                                                <span class="bar">

                                                </span>
                                                <input type="text" name="brDt2" id="birth_month" pattern="[0-9]*"
                                                    label="생년월일" size="2" maxlength="2" placeholder="MM">
                                                <span class="bar"></span>
                                                <input type="text" name="brDt3" id="birth_day" pattern="[0-9]*"
                                                    label="생년월일" size="2" maxlength="2" placeholder="DD">

                                            </div>
                                        </td>
                                    </tr>
                                    <tr class="field_code">
                                        <th>
                                            판매자관련<br>
                                            첨부파일
                                            <span class="ico">
                                                <span class="screen_out">필수항목</span>
                                            </span>
                                        </th>
                                        <td>
                                            <div class="insert">
                                                  <input type="file" name="atchfile"  >
                                                    
                                            </div>
                                            <!-- <input type="text" name="name" required="" label="이름"
                                                placeholder="관련 첨부파일을 선택하세요"> -->
                                        </td>
                                    </tr>
                                </tbody>
                            </table>


                            <div id="formSubmit" class="form_footer" style="border-top: 1px solid #333;">
                                <button type="submit" class="btn active btn_join">가입하기</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>

        </div>


        <div id="footer">
            <div class="inner_footer">
                <div class="cc_footer">
                    <!-- cc = company calls -->
                    <h2 class="cc_tit">고객행복센터</h2>
                    <div class="cc_view cc_call">
                        <h3>
                            <span class="tit">1644-1107</span>
                        </h3>
                        <dl class="list">
                            <dt>365고객센터</dt>
                            <dd>오전 7시 - 오후 7시</dd>
                        </dl>
                    </div>
                    <div class="cc_view cc_kakao">
                        <h3>
                            <a href="#" class="tit">카카오톡 문의</a>
                        </h3>
                        <dl class="list">
                            <dt>365고객센터</dt>
                            <dd>오전 7시 - 오후 7시</dd>
                        </dl>
                    </div>
                    <div class="cc_view cc_qna">
                        <h3>
                            <a href="#" class="tit">1:1 문의</a>
                        </h3>
                        <dl class="list">
                            <dt>24시간 접수 가능</dt>
                            <dd>고객센터 운영시간에 순차적으로 답변해드리겠습니다.</dd>
                        </dl>
                    </div>
                </div>

                <div class="company_info">
                    <ul class="list">
                        <li>
                            <a href="#" class="link">펀딩소개</a>
                        </li>
                        <li>
                            <a href="#" class="link">펀딩소개영상</a>
                        </li>
                        <li>
                            <a href="#" class="link">인재채용</a>
                        </li>
                        <li>
                            <a href="#" class="link">이용약관</a>
                        </li>
                        <li>
                            <a href="#" class="link">개인정보처리방침</a>
                        </li>
                        <li>
                            <a href="#" class="link">이용안내</a>
                        </li>
                    </ul>
                    <div class="spec_info">
                        법인명 (상호) : 주식회사 컬리
                        <span class="bar">I</span>
                        사업자등록번호 : 000-00-0000
                        <a href="#" class="link">사업자정보확인</a><br>
                        통신판매업 : 제 2018-서울강남-00000 호
                        <span class="bar">I</span>
                        개인정보보호책임자 : 000 <br>
                        주소 : 서울시 도산대로 16길 20, 이래빌딩 B1 ~ 4F
                        <span class="bar">I</span>
                        대표이사 : 000 <br>
                        입점문의 : <a href="#" class="link">입점문의하기</a>
                        제휴문의 : <a href="#" class="link">ghkdvnfld345@naver.com</a><br>
                        채용문의 : <a href="#" class="link">ghkdvnfld345@naver.com</a><br>
                        팩스 : 000 - 0000 - 0000
                        <span class="bar">I</span>
                        이메일 : <a href="#" class="link">ghkdvnfld345@naver.com</a><br>
                        <br>
                        <strong class="copy">© KURLY CORP. ALL RIGHTS RESERVED</strong>
                        <ul class="sns">
                            <li>
                                <a href="#" class="link_sns insta" target="_blank">
                                    <img src="https://res.kurly.com/pc/ico/1810/ico_instagram.png"
                                        alt="마켓컬리 인스타그램 바로가기">
                                </a>
                                <!-- bg url 넣기 -->
                            </li>
                            <li>
                                <a href="#" class="link_sns fb" target="_blank">
                                    <img src="https://res.kurly.com/pc/ico/1810/ico_fb.png" alt="마켓컬리 페이스북 바로가기">
                                </a>
                            </li>
                            <li>
                                <a href="#" class="link_sns naver_blog" target="_blank">
                                    <img src="https://res.kurly.com/pc/ico/1810/ico_blog.png" alt="마켓컬리 네이버블로그 바로가기">
                                </a>
                            </li>
                            <li>
                                <a href="#" class="link_sns naver_post" target="_blank">
                                    <img src="https://res.kurly.com/pc/ico/1810/ico_naverpost.png" alt="마켓컬리 포스트 바로가기">
                                </a>
                            </li>
                            <li>
                                <a href="#" class="link_sns yt" target="_blank">
                                    <img src="https://res.kurly.com/pc/ico/1810/ico_youtube.png" alt="마켓컬리 유튜브 바로가기">
                                </a>

                            </li>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="link_footer">
                <div class="authentication">
                    <a href="#" class="mark" target="_blank">
                        <img src="https://res.kurly.com/pc/ico/2001/logo_isms.png" alt="isms 로고" class="logo">
                        <p class="txt">
                            [인증범위] 마켓컬리 쇼핑몰 서비스 개발 · 운영<br>
                            [유효기간] 2019.04.01 ~ 2022.03.31
                        </p>
                    </a>
                    <a href="#" class="mark" target="_blank">
                        <img src="https://res.kurly.com/pc/ico/2001/logo_eprivacyplus.png" alt="eprivacy plus 로고"
                            class="logo">
                        <p class="txt">
                            개인정보보호 우수 웹사이트 ·<br>
                            개인정보처리시스템 인증 (ePRIVACY PLUS)
                        </p>
                    </a>
                    <a href="#" class="lguplus mark" target="_blank">
                        <img src="https://res.kurly.com/pc/service/main/2009/logo_payments.png" alt="payments 로고"
                            class="logo">
                        <p class="txt">
                            고객님의 안전거래를 위해 현금 등으로 결제 시 저희 쇼핑몰에서 가입한<br>
                            토스 페이먼츠 구매안전(에스크로) 서비스를 이용하실 수 있습니다.
                        </p>
                    </a>
                </div>
            </div>
        </div>
    </div>
	<script type="text/javascript">
	$('#checkId').click(function(){
		 
		
		var v_id = $("#id").val();
		console.log("넘길 값: "+v_id);
		if ($('.field_id input').val() == '') {    //만약에 아무것도 입력 되지 않은 상태면
	        alert('아이디를 입력해주세요');
	        $(".field_id .txt_guide .txt_case2").css('color', '#b3130b');
	        return;
	    }
		$.ajax({
			type:'POST',
			url:"/Funding/member/idCheck.do",
			data: {userId : v_id},
			success:function (data) {
	            console.log("결과값: ",data);
	            if (data == 'OK') {
	              alert("이미 존재하는 아이디 입니다.");
	              $(".field_id .txt_guide .txt_case2").css('color', '#b3130b');
	             return; 
	              
	            } 
				if(data == 'NO'){
	              alert("사용가능한 아이디 입니다.");
	              $(".field_id .txt_guide .txt_case2").css('color', '#0f851a');
	             return;
	            }
	          }
		
		});
		});
	</script>
</body>

</html>