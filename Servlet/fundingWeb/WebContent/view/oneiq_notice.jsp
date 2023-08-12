<%@page import="kr.or.funding.member.VO.MemberVO"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="kr.or.funding.oneIq.OneiqVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	List<OneiqVO> oneiqList = (List<OneiqVO>) request.getAttribute("oneiqList");
SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

int cnt = oneiqList.size();
System.out.println("질문수"+cnt);
//한 페이지에 출력될 글 수 
int pageSize = 10;

// 현 페이지 정보 설정
String pageNum = request.getParameter("pageNum");
if (pageNum == null) {
	pageNum = "1";
}

// 첫행번호를 계산
int currentPage = Integer.parseInt(pageNum);
int startRow = (currentPage - 1) * pageSize;
int endRow = startRow + (pageSize - 1);
%>
<%
	if (cnt != 0) {
	////////////////////////////////////////////////////////////////
	// 페이징 처리
	// 전체 페이지수 계산
	int pageCount = cnt / pageSize + (cnt % pageSize == 0 ? 0 : 1);

	// 한 페이지에 보여줄 페이지 블럭
	int pageBlock = 10;

	// 한 페이지에 보여줄 페이지 블럭 시작번호 계산
	int startPage = ((currentPage - 1) / pageBlock) * pageBlock + 1;

	// 한 페이지에 보여줄 페이지 블럭 끝 번호 계산
	int endPage = startPage + pageBlock - 1;
	if (endPage > pageCount) {
		endPage = pageCount;
	}
	if (endRow > (cnt - 1)) {
		endRow = cnt - 1;
		System.out.println(endRow);
	}
%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>funding</title>
<link rel="stylesheet" href="../view/notice/css/reset.css">
<link rel="stylesheet" href="../view/notice/css/common.css">
<!-- common.css : header, footer 메인 페이지 -->
<link rel="stylesheet" href="../view/notice/css/visual.css">
<!-- visual.css : header 바로 밑 이미지 슬라이드 파트 -->
<link rel="stylesheet" href="../view/notice/css/contents.css">
<!-- header와 footer를 제외한 콘텐츠 메인 페이지 -->
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script defer src="../view/notice/js/common.js"></script>
<script defer src="../view/notice/js/contents.js"></script>
<script defer src="../view/notice/js/visual.js"></script>
<link rel="shortcut icon" href="./img/favicon.png">
<link rel="icon" href="./img/favicon.png">
<link rel="stylesheet" href="../view/notice/css/admin_common.css">
<link rel="stylesheet"
	href="../view/notice/css/admin_page_order_manage.css">
<script src="../view/notice/js/admin_common.js"></script>
<script src="../view/notice/js/admin_order_manage.js"></script>

<style type="text/css">
table.notice_style {
	border-radius: 16px;
	text-align: left;
	line-height: 1.5;
	font-family: 맑은고딕;
}

table.notice_style thead th {
	font-size: x-large;
	padding: 10px;
	font-weight: bold;
	vertical-align: middle;
	color: black;
	border-bottom: 3px solid #036;
}

table.notice_style tbody tr {
	font-size: large;
	width: 100px;
	padding: 10px;
	font-weight: bold;
	vertical-align: middle;
	border-bottom: 1px solid #ccc;
	background: #f3f6f7;
}

#pagenum {
	font-size: medium;
	font-family: 맑은고딕;
}

body {
 
}
h1 {
  position: relative;
  text-align: center;
  color: #353535;
  font-size: 50px;
  font-family: "Cormorant Garamond", serif;
}

p {
  font-family: 'Lato', sans-serif;
  font-weight: 300;
  text-align: center;
  font-size: 18px;
  color: #676767;
}
.frame {
  width: 90%;
  margin: 40px auto;
  text-align: center;
}
button {
	
  margin: 20px;
  outline: none;
}
.custom-btn {
  width: 130px;
  height: 40px;
  padding: 10px 25px;
  border: 2px solid #000;
  font-family: 'Lato', sans-serif;
  font-weight: 500;
  background: transparent;
  cursor: pointer;
  transition: all 0.3s ease;
  position: relative;
  display: inline-block;
  float:right;
}

/* 1 */
.btn-1 {
  transition: all 0.3s ease;
}
.btn-1:hover {
   box-shadow:
   -7px -7px 20px 0px #fff9,
   -4px -4px 5px 0px #fff9,
   7px 7px 20px 0px #0002,
   4px 4px 5px 0px #0001;
}

/* 2 */
.btn-2 {
  
}
.btn-2:after {
  position: absolute;
  content: "";
  top: 5px;
  left: 6px;
  width: 90%;
  height: 70%;
  border: 1px solid #000;
  opacity: 0;
  transition: all 0.3s ease;
}
.btn-2:hover:after {
  opacity: 1;
}


/* 3 */
.btn-3 {
  line-height: 39px;
  padding: 0;
}
.btn-3:hover{
  background: transparent;
  color: #000;
}
.btn-3 span {
  position: relative;
  display: block;
  width: 100%;
  height: 100%;
}
.btn-3:before,
.btn-3:after {
  position: absolute;
  content: "";
  left: 0;
  top: 0;
  background: #000;
  transition: all 0.3s ease;
}
.btn-3:before {
  height: 0%;
  width: 2px;
}
.btn-3:after {
  width: 0%;
  height: 2px;
}
.btn-3:hover:before {
  height: 100%;
}
.btn-3:hover:after {
  width: 100%;
}
.btn-3 span:before,
.btn-3 span:after {
  position: absolute;
  content: "";
  right: 0;
  bottom: 0;
  background: #000;
  transition: all 0.3s ease;
}
.btn-3 span:before {
  width: 2px;
  height: 0%;
}
.btn-3 span:after {
  width: 0%;
  height: 2px;
}
.btn-3 span:hover:before {
  height: 100%;
}
.btn-3 span:hover:after {
  width: 100%;
}

/* 4 */
.btn-4 {
  position: relative;
  color: #000;
  z-index: 2;
  line-height: 40px;
  padding: 0;
}
.btn-4:hover{
  border: none;
}
.btn-4:before,
.btn-4:after {
  position: absolute;
  content: "";
  width: 0%;
  height: 0%;
  border: 2px solid;
  z-index: -1;
  transition: all 0.3s ease;
}
.btn-4:before {
  top: 0;
   left: 0;
   border-bottom-color: transparent;
   border-right-color: transparent;
   border-top-color: #000;
   border-left-color: #000;
}
.btn-4:after{
   bottom: 0;
   right: 0;
   border-top-color: transparent;
   border-left-color: transparent;
   border-bottom-color: #000;
   border-right-color: #000;
}
.btn-4:hover:before,
.btn-4:hover:after {
  border-color: #000;
  height: 100%;
  width: 100%;
}



/* 5 */
.btn-5 {
  background: #000;
  color: #fff;
  line-height: 42px;
  padding: 0;
  border: none;
}
.btn-5:hover {
  background: transparent;
  color: #000;
   box-shadow:
   -7px -7px 20px 0px #fff9,
   -4px -4px 5px 0px #fff9,
   7px 7px 20px 0px #0002,
   4px 4px 5px 0px #0001;
}
.btn-5:before,
.btn-5:after{
  content:'';
  position:absolute;
  top:0;
  right:0;
  height:2px;
  width:0;
  background: #000;
  transition:400ms ease all;
}
.btn-5:after{
  right:inherit;
  top:inherit;
  left:0;
  bottom:0;
}
.btn-5:hover:before,
.btn-5:hover:after{
  width:100%;
  transition:800ms ease all;
}


/* 6 */
.btn-6 {
   background: #000;
  color: #fff;
  line-height: 42px;
  padding: 0;
  border: none;
}
.btn-6 span {
  position: relative;
  display: block;
  width: 100%;
  height: 100%;
}
.btn-6:before,
.btn-6:after {
  position: absolute;
  content: "";
  height: 0%;
  width: 2px;
  background: #000;
}
.btn-6:before {
  right: 0;
  top: 0;
  transition: all 500ms ease;
}
.btn-6:after {
  left: 0;
  bottom: 0;
  transition: all 500ms ease;
}
.btn-6:hover{
  color: #000;
  background: transparent;
}
.btn-6:hover:before {
  transition: all 500ms ease;
  height: 100%;
}
.btn-6:hover:after {
  transition: all 500ms ease;
  height: 100%;
}
.btn-6 span:before,
.btn-6 span:after {
  position: absolute;
  content: "";
  background: #000;
}
.btn-6 span:before {
  left: 0;
  top: 0;
  width: 0%;
  height: 2px;
  transition: all 500ms ease;
}
.btn-6 span:after {
  right: 0;
  bottom: 0;
  width: 0%;
  height: 2px;
  transition: all 500ms ease;
}
.btn-6 span:hover:before {
  width: 100%;
}
.btn-6 span:hover:after {
  width: 100%;
}

/* 7 */
.btn-7 {
   background: #000;
  color: #fff;
  line-height: 42px;
  padding: 0;
  border: none;
  z-index: 1;
   -webkit-transition: all 0.3s linear;
  transition: all 0.3s linear;
}
.btn-7:hover {
  background: transparent;
  color: #000;
}
.btn-7:before,
.btn-7:after {
  position: absolute;
  content: "";
  left: 0;
  width: 100%;
  height: 50%;
  right: 0;
  z-index: -1;
  background: #000;
  transition: all 0.3s ease;
}
.btn-7:before {
  top: 0;
}
.btn-7:after {
  bottom: 0;
}
.btn-7:hover:before,
.btn-7:hover:after {
  height: 0;
  background-color: #000;
}




/* 8 */
.btn-8 {
   line-height: 40px;
  padding: 0;
  background: transparent;
  position: relative;
  z-index: 2;
  color: #fff;
  -webkit-perspective: 300px;
  perspective: 300px;
  -webkit-transform-style: preserve-3d;
  transform-style: preserve-3d;
}
.btn-8:hover{
  color: #000;
}
.btn-8:after {
  position: absolute;
  content: "";
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background: #000;
  z-index: -1;
  -webkit-transform-origin: center bottom;
  transform-origin: center bottom;
  -webkit-transform: rotateX(0);
  transform: rotateX(0);
  transition: all 0.3s ease;
}
.btn-8:hover:after {
  -webkit-transform: rotateX(-180deg);
  transform: rotateX(-180deg);
}
  

/* 9 */
.btn-9 {
  z-index: 2;
  transition: all 0.3s ease;
  overflow: hidden;
}
.btn-9:after {
  position: absolute;
  content: " ";
  z-index: -1;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  transition: all 0.3s ease;
}
.btn-9:hover {
  box-shadow:  4px 4px 6px 0 rgba(255,255,255,.5),
              -4px -4px 6px 0 rgba(116, 125, 136, .2), 
    inset -4px -4px 6px 0 rgba(255,255,255,.5),
    inset 4px 4px 6px 0 rgba(116, 125, 136, .3);
  color: #fff;
}
.btn-9:hover:after {
  -webkit-transform: scale(2) rotate(180deg);
  transform: scale(2) rotate(180deg);
  background: #000;
  box-shadow:  4px 4px 6px 0 rgba(255,255,255,.5),
              -4px -4px 6px 0 rgba(116, 125, 136, .2), 
    inset -4px -4px 6px 0 rgba(255,255,255,.5),
    inset 4px 4px 6px 0 rgba(116, 125, 136, .3);
}

/* 10 */
.btn-10 {
  transition: all 0.3s ease;
  overflow: hidden;
}
.btn-10:after {
  position: absolute;
  content: " ";
  top: 0;
  left: 0;
  z-index: -1;
  width: 100%;
  height: 100%;
  transition: all 0.3s ease;
  -webkit-transform: scale(.1);
  transform: scale(.1);
}
.btn-10:hover {
  color: #fff;
}
.btn-10:hover:after {
  background: #000;
  -webkit-transform: scale(1);
  transform: scale(1);
}

/* 11 */
.btn-11 {
  overflow: hidden;
  transition: all 0.3s ease;
}
.btn-11:hover {
   background: #000;
  color: #fff;
}
.btn-11:before {
    position: absolute;
    content: '';
    display: inline-block;
    top: -180px;
    left: 0;
    width: 30px;
    height: 100%;
    background-color: #fff;
    animation: shiny-btn1 3s ease-in-out infinite;
}
.btn-11:active{
  box-shadow:  4px 4px 6px 0 rgba(255,255,255,.3),
              -4px -4px 6px 0 rgba(116, 125, 136, .2), 
    inset -4px -4px 6px 0 rgba(255,255,255,.2),
    inset 4px 4px 6px 0 rgba(0, 0, 0, .2);
}


@-webkit-keyframes shiny-btn1 {
    0% { -webkit-transform: scale(0) rotate(45deg); opacity: 0; }
    80% { -webkit-transform: scale(0) rotate(45deg); opacity: 0.5; }
    81% { -webkit-transform: scale(4) rotate(45deg); opacity: 1; }
    100% { -webkit-transform: scale(50) rotate(45deg); opacity: 0; }
}


/* 12 */
.btn-12{
  position: relative;
  right: 20px;
  bottom: 20px;
  border:none;
  width: 130px;
  height: 40px;
  line-height: 40px;
  -webkit-perspective: 230px;
  perspective: 230px;
}
.btn-12 span {
  display: block;
  position: absolute;
  width: 130px;
  height: 40px;
  border: 2px solid #000;
  margin:0;
  text-align: center;
  -webkit-box-sizing: border-box;
  -moz-box-sizing: border-box;
  box-sizing: border-box;
  -webkit-transition: all .3s;
  transition: all .3s;
}
.btn-12 span:nth-child(1) {
  box-shadow:
   -7px -7px 20px 0px #fff9,
   -4px -4px 5px 0px #fff9,
   7px 7px 20px 0px #0002,
   4px 4px 5px 0px #0001;
  -webkit-transform: rotateX(90deg);
  -moz-transform: rotateX(90deg);
  transform: rotateX(90deg);
  -webkit-transform-origin: 50% 50% -20px;
  -moz-transform-origin: 50% 50% -20px;
  transform-origin: 50% 50% -20px;
}
.btn-12 span:nth-child(2) {
  -webkit-transform: rotateX(0deg);
  -moz-transform: rotateX(0deg);
  transform: rotateX(0deg);
  -webkit-transform-origin: 50% 50% -20px;
  -moz-transform-origin: 50% 50% -20px;
  transform-origin: 50% 50% -20px;
}
.btn-12:hover span:nth-child(1) {
  -webkit-transform: rotateX(0deg);
  -moz-transform: rotateX(0deg);
  transform: rotateX(0deg);
}
.btn-12:hover span:nth-child(2) {
  background: #e0e5ec;
  color: #e0e5ec;
  -webkit-transform: rotateX(-90deg);
  -moz-transform: rotateX(-90deg);
  transform: rotateX(-90deg);
}


/* 13 */
.btn-13 {
   background: #000;
  color: #fff;
  z-index: 1;
}
.btn-13:after {
  position: absolute;
  content: "";
  width: 100%;
  height: 0;
  bottom: 0;
  left: 0;
  z-index: -1;
   background: #e0e5ec;
  transition: all 0.3s ease;
}
.btn-13:hover {
  color: #000;
}
.btn-13:hover:after {
  top: 0;
  height: 100%;
}
.btn-13:active {
  top: 2px;
}


/* 14 */
.btn-14 {
   background: #000;
  color: #fff;
  z-index: 1;
}
.btn-14:after {
  position: absolute;
  content: "";
  width: 100%;
  height: 0;
  top: 0;
  left: 0;
  z-index: -1;
  background: #e0e5ec;
  transition: all 0.3s ease;
}
.btn-14:hover {
  color: #000;
}
.btn-14:hover:after {
  top: auto;
  bottom: 0;
  height: 100%;
}
.btn-14:active {
  top: 2px;
}

/* 15 */
.btn-15 {
   background: #000;
  color: #fff;
  z-index: 1;
}
.btn-15:after {
  position: absolute;
  content: "";
  width: 0;
  height: 100%;
  top: 0;
  right: 0;
  z-index: -1;
   background: #e0e5ec;
  transition: all 0.3s ease;
}
.btn-15:hover {
  color: #000;
}
.btn-15:hover:after {
  left: 0;
  width: 100%;
}
.btn-15:active {
  top: 2px;
}


/* 16 */
.btn-16 {
   background: #000;
  color: #fff;
  z-index: 1;
}
.btn-16:after {
  position: absolute;
  content: "";
  width: 0;
  height: 100%;
  top: 0;
  left: 0;
  direction: rtl;
  z-index: -1;
  background: #e0e5ec;
  transition: all 0.3s ease;
}
.btn-16:hover {
  color: #000;
}
.btn-16:hover:after {
  left: auto;
  right: 0;
  width: 100%;
}
</style>
</head>

<body>
	<div id="wrap">
		<div id="header">
			<div class="user_menu">

				
					<!--login class 추가-->
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
				<!-- .sign_menu -->
			</div>
			<div class="header_logo">
				<h1 class="logo">
					<a href="/Funding/ptimfor/ptimforlist.do" class="link_main"> <span class="gnb_logo_container"></span>
						<img src="/Funding/view/img/logo.jpg" alt="로고" class="logo_img"
						onclick="location.href='../main/main.html'">
					</a>
				</h1>
			</div>
			<div class="gnb">
				<!-- gnb = global nav bar = 최상위 메뉴  -->
				<h2 class="blind">메뉴</h2>
				<ul class="gnb_menu">
					<li class="list gnb_main"><a href="#" class="link"> <span
							class="ico"></span> <!-- ico는 css에서 bg url로 처리 --> <span
							class="txt">전체 카테고리</span>
					</a>
						<div class="gnb_sub">
							<ul class="menu">
								<li class="current">
									<!-- hover나 active시 current가 옮겨가도록 설정해야 함 --> <a href="#"
									class="main_item"> <span class="ico"> <img
											src="https://img-cf.kurly.com/shop/data/category/icon_newyear_inactive_pc@2x.1609722514.png"
											alt="" class="ico off"> <img
											src="https://img-cf.kurly.com/shop/data/category/icon_newyear_active_pc@2x.1609722514.png"
											alt="" class="ico on"> <!--카테고리 별 아이콘은 직관적이게 img src로 바로 삽입 
                                                클릭X(off): 기본, 클릭(on): 보라색 아이콘-->
									</span> <span class="tit"> <span class="txt">블라블라블라</span> <span
											class="ico_new" alt="new"></span> <!-- ico_nex: bg url로 처리 -->
									</span> <!-- tit: text + ico -->
								</a>
								</li>
							</ul>
						</div></li>
					<li class="list"><a href="#" class="link"> <span
							class="txt">펀딩</span>
					</a></li>
					<li class="list"><a href="#" class="link"> <span
							class="txt">공지사항</span>
					</a></li>
					<li class="list"><a href="#" class="link"> <span
							class="txt">회사소개</span>
					</a></li>
					<div class="gnb_search">
						<form action="">
							<input type="text" id="keyword" value="건강 기원 새해맞이 보양식 레시피"
								title="검색어입력" class="inp_search"> <input type="image"
								src="https://res.kurly.com/pc/service/common/1908/ico_search_x2.png"
								class="btn_search">
							<div class="init">
								<button type="button" id="search_init" class="btn_delete" style="float: right">검색어
									삭제하기</button>
							</div>
						</form>
					</div>

				</ul>
			</div>
		</div>

		<!-- 공지사항 넣기 -->
		<div id="main">
			<div id="content">
				<div class="page_aticle aticle_type2"
					style="boarder: 1px solid black">
					<div id="snb" class="snb_my">
						<h2 class="tit_snb">공지사항</h2>
						<div class="inner_snb">
							<ul class="list_menu">
								<li>
                                    <a href="/Funding/notice2/notice2List.do">공지사항</a>
                                </li>
                                <li>
                                    <a href="/Funding/view/board/FAQList.jsp">자주묻는질문</a>
                                </li>
                                <li>
                                    <a href="/Funding/oneiq/oneiqlist.do">1:1문의</a>
                                </li>


							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div
		style="position: absolute; height: 700px; width: 750px; top: 200px; left: 35%">

		<h2>1:1문의</h2>
		<br>
		<table class="notice_style" style="width: 100%; height: 600px">
			<thead>
				<tr>
					<th>문의번호</th>
					<th>제목</th>
					<th>작성일</th>
				</tr>
			</thead>
			<tbody>
				<%
				int j = 1;
					for (int i = startRow; i <= endRow; i++) {
				%>
				<tr>
					<td style="text-align: center"><%=j%></td>
					<td style="width: 60%">
					<a href="oneiqDetail.do?IqNum=<%=oneiqList.get(i).getIqNum()%>"><%=oneiqList.get(i).getIqTit()%></a></td>			
					<td style="font-size: 12px"><%=simpleDateFormat.format(oneiqList.get(i).getWtDt())%></td>
				</tr>

				<%	j++;
					}
				;
				%>

			</tbody>

		</table><br>
			<%if(memVo.getMbsAhy() == 1 || memVo.getMbsAhy() ==2){ %> 
				<td colspan="1">
				<input type="button" value="등록하기" class="custom-btn btn-1" onclick ="self.location='/Funding/view/oneiq_insert.jsp'">			
				</td><br>
				<%} %>
		<br>

		<%
			if (startPage > pageBlock) {
		%>
		<a id="pagenum" href="/oneiqlist.do?pageNum=<%=startPage - pageBlock%>">&nbsp;Prev</a>
		<%
			}
		%>

		<%
			for (int i = startPage; i <= endPage; i++) {
		%>
		<a id="pagenum" href="/oneiqlist.do?pageNum=<%=i%>"><%=i%>&nbsp;</a>
		<%
			}
		%>

		<%
			if (endPage < pageCount) {
		%>
		<a id="pagenum" href="/oneiqlist.do?pageNum=<%=startPage + pageBlock%>">&nbsp;Next</a>
		<%
			}
		%>
		<%
			}
		%>
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
					<li><a href="#" class="link">펀딩소개</a></li>
					<li><a href="#" class="link">펀딩소개영상</a></li>
					<li><a href="#" class="link">인재채용</a></li>
					<li><a href="#" class="link">이용약관</a></li>
					<li><a href="#" class="link">개인정보처리방침</a></li>
					<li><a href="#" class="link">이용안내</a></li>
				</ul>
				<div class="spec_info">
					법인명 (상호) : 주식회사 컬리 <span class="bar">I</span> 사업자등록번호 : 000-00-0000
					<a href="#" class="link">사업자정보확인</a><br> 통신판매업 : 제
					2018-서울강남-00000 호 <span class="bar">I</span> 개인정보보호책임자 : 000 <br>
					주소 : 서울시 도산대로 16길 20, 이래빌딩 B1 ~ 4F <span class="bar">I</span> 대표이사
					: 000 <br> 입점문의 : <a href="#" class="link">입점문의하기</a> 제휴문의 : <a
						href="#" class="link">ghkdvnfld345@naver.com</a><br> 채용문의 : <a
						href="#" class="link">ghkdvnfld345@naver.com</a><br> 팩스 : 000
					- 0000 - 0000 <span class="bar">I</span> 이메일 : <a href="#"
						class="link">ghkdvnfld345@naver.com</a><br> <br> <strong
						class="copy">© KURLY CORP. ALL RIGHTS RESERVED</strong>
					<ul class="sns">
						<li><a href="#" class="link_sns insta" target="_blank"> <img
								src="https://res.kurly.com/pc/ico/1810/ico_instagram.png"
								alt="마켓컬리 인스타그램 바로가기">
						</a> <!-- bg url 넣기 --></li>
						<li><a href="#" class="link_sns fb" target="_blank"> <img
								src="https://res.kurly.com/pc/ico/1810/ico_fb.png"
								alt="마켓컬리 페이스북 바로가기">
						</a></li>
						<li><a href="#" class="link_sns naver_blog" target="_blank">
								<img src="https://res.kurly.com/pc/ico/1810/ico_blog.png"
								alt="마켓컬리 네이버블로그 바로가기">
						</a></li>
						<li><a href="#" class="link_sns naver_post" target="_blank">
								<img src="https://res.kurly.com/pc/ico/1810/ico_naverpost.png"
								alt="마켓컬리 포스트 바로가기">
						</a></li>
						<li><a href="#" class="link_sns yt" target="_blank"> <img
								src="https://res.kurly.com/pc/ico/1810/ico_youtube.png"
								alt="마켓컬리 유튜브 바로가기">
						</a></li>
					</ul>
				</div>
			</div>
		</div>
		<div class="link_footer">
			<div class="authentication">
				<a href="#" class="mark" target="_blank"> <img
					src="https://res.kurly.com/pc/ico/2001/logo_isms.png" alt="isms 로고"
					class="logo">
					<p class="txt">
						[인증범위] 마켓컬리 쇼핑몰 서비스 개발 · 운영<br> [유효기간] 2019.04.01 ~
						2022.03.31
					</p>
				</a> <a href="#" class="mark" target="_blank"> <img
					src="https://res.kurly.com/pc/ico/2001/logo_eprivacyplus.png"
					alt="eprivacy plus 로고" class="logo">
					<p class="txt">
						개인정보보호 우수 웹사이트 ·<br> 개인정보처리시스템 인증 (ePRIVACY PLUS)
					</p>
				</a> <a href="#" class="lguplus mark" target="_blank"> <img
					src="https://res.kurly.com/pc/service/main/2009/logo_payments.png"
					alt="payments 로고" class="logo">
					<p class="txt">
						고객님의 안전거래를 위해 현금 등으로 결제 시 저희 쇼핑몰에서 가입한<br> 토스 페이먼츠 구매안전(에스크로)
						서비스를 이용하실 수 있습니다.
					</p>
				</a>
			</div>
		</div>
	</div>
	</div>
</body>

</html>