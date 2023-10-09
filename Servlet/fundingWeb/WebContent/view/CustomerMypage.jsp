<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@page import="kr.or.funding.member.VO.MemberVO"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>INU Kurly</title>
<link rel="stylesheet" href="/Funding/view/css/reset.css">
<link rel="stylesheet" href="./css/common.css">
<!-- common.css : header, footer 메인 페이지 -->
<link rel="stylesheet" href="css/visual.css">
<!-- visual.css : header 바로 밑 이미지 슬라이드 파트 -->
<link rel="stylesheet" href="css/contents.css">
<!-- header와 footer를 제외한 콘텐츠 메인 페이지 -->
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script defer src="js/common.js"></script>
<script defer src="js/contents.js"></script>
<script defer src="js/visual.js"></script>
<link rel="shortcut icon" href="./img/favicon.png">
<link rel="icon" href="./img/favicon.png">
<link rel="stylesheet" href="./css/admin_common.css">
<link rel="stylesheet" href="./css/admin_page_order_manage.css">
<script src="./js/admin_common.js"></script>
<script src="./js/admin_order_manage.js"></script>
</head>
<style>
* {
	margin: 0;
	padding: 0;
}

#mem_location {
	width: 98vh;
	height: 98vh;
	border: solid black 1px;
}

#member_menu {
	height: 100%;
	width: 25%;
	position: absolute;
	border-right: black 1px solid;
}

#member_page {
	height: 45%;
	width: 75%;
	/* background-color: rgb(240, 240, 240); */
	position: absolute;
	right: 0%;
}

#middle {
	height: 10%;
	width: 75%;
	top: 46%;
	right: 0%;
	position: absolute;
	/* background-color: rgb(240, 240, 240); */
}

#foot {
	height: 43%;
	width: 75%;
	position: absolute;
	right: 0%;
	top: 57%;
	background-color: rgb(240, 240, 240);
}

#num1 {
	margin-top: 15%;
	margin-bottom: 4%;
}

#num2 {
	margin-top: 8%;
}

.num {
	display: inline;
	position: relative;
	margin-left: 15%;
	margin-right: 15px;
	width: 00px;
	height: 30px;
	font-size: 4em;
}

.page_line {
	border: 1px solid black;
	margin-top: 7%;
	width: 559px;
}

.page_li {
	display: inline-block;
	position: relative;
	margin-left: 45px;
	width: 130px;
	height: 30px;
	font-size: 25px;
}

.foot_all {
	margin-top: 10%;
	/* padding: 100PX;*/
	padding-left: 7%;
}

.foot_menu {
	display: inline-block;
	vertical-align: top;
	/*border: 1px solid black;*/
	margin: 5px;
	margin-left: 25px;
	position: relative;
	width: 100px;
	height: 30px;
	font-size: 20px;
}

.foot_img {
	display: inline-block;
	vertical-align: top;
}

.foot_p {
	margin-top: 10%;
	margin-left: 35px;
	font-size: 32px;
	font-weight: bolder;
	font-family: Georgia, "맑은 고딕", serif;
}
/* 마이페이지 로고 */
#memberlogo {
	position: absolute;
	inline-size: 75%;
	top: 20%;
	right: 10%;
	/* right: inherit; */
	/* position:; */
}

/* 회원부분 글씨 */
#memMenufoot {
	position: absolute;
	font-size: large;
	font-family: 맑은고딕;
	top: 42%;
	right: 32%;
	margin-trim: inherit;
}

/* 로그아웃 버튼부분 */
#memberlogout {
	position: absolute;
	width: 120px;
	height: 45px;
	border: 1px solid #ddd;
	right: 18%;
	/* vertical-align: middle; */
	top: 62%;
	font-size: large;
	font-family: 맑은고딕;
}
/* 마이페이지 로고 */
#memberlogo {
	position: absolute;
	inline-size: 75%;
	top: 20%;
	right: 10%;
	/* right: inherit; */
	/* position:; */
}

/* 회원부분 글씨 */
#memMenufoot {
	position: absolute;
	font-size: large;
	font-family: 맑은고딕;
	top: 42%;
	right: 32%;
	margin-trim: inherit;
}

#memberlogout2 {
	position: absolute;
	width: 120px;
	height: 45px;
	border: 1px solid #ddd;
	right: 18%;
	/* vertical-align: middle; */
	top: 53%;
	font-size: large;
	font-family: 맑은고딕;
}

#middle_mem {
	font-size: large;
	font-family: 맑은고딕;
	position: absolute;
	left: 1%;
	top: 50%;
}
</style>
<body>
	<%
	MemberVO memVo = (MemberVO) session.getAttribute("memList");
	%>
	<div id="wrap">
		<div id="header">
			<div class="user_menu">

				<ul class="sign_menu">
					<!--login class 추가-->


					<%
					if (memVo == null) {
					%>
					<li class="link"><a href="./joinselect.jsp"
						class="item after join">회원가입</a></li>
					<li class="link"><a href="./login.jsp"
						class="item after login_none">로그인</a></li>
					<%
					} else {
					%>
					<li class="link"><a href="#" class="item after join"><%=memVo.getMbsNm()%>님
							어서오세요</a></li>
					<%
					if (memVo.getMbsAhy() == 2) {
					%>
					<li class="link"><a href="/Funding/view/sellerMypage.jsp"
						class="item after login_none">판매자페이지</a></li>
					<%
					}
					%>
					<%
					if (memVo.getMbsAhy() == 1) {
					%>
					<li class="link"><a href="/Funding/view/notice_seller.jsp"
						class="item after login_none">마이페이지</a></li>
					<%
					}
					%>
					<%
					if (memVo.getMbsAhy() == 0) {
					%>
					<li class="link"><a href="/Funding/view/notice_seller.jsp"
						class="item after login_none">관리자페이지</a></li>
					<%
					}
					%>
					<li class="link"><a href="./logout.jsp" onclick="logout()"
						class="item after login_none">로그아웃 </a></li>
					<%
					}
					%>
					
				<!-- .sign_menu -->
			</div>
			<div class="header_logo">
				<h1 class="logo">
					<a href="/Funding/ptimfor/ptimforlist.do" class="link_main"> <span class="gnb_logo_container"></span>
						<img src="/Funding/test/img/logo.jpg" alt="로고" class="logo_img"
						onclick="location.href='/Funding/view/main.jsp'">
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
						<form action="/Funding/approveSeller.do">
							<input type="text" id="keyword" value="건강 기원 새해맞이 보양식 레시피"
								title="검색어입력" class="inp_search"> <input type="image"
								src="https://res.kurly.com/pc/service/common/1908/ico_search_x2.png"
								class="btn_search">
							<div class="init">
								<button type="button" id="search_init" class="btn_delete">검색어
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
						<h2 class="tit_snb">Mypage</h2>
						<div class="inner_snb">
							<ul class="list_menu">
								<li><a href="#">공지사항</a></li>
								<li><a href="faq.html">자주묻는질문</a></li>
								<li><a href="one_iq.html">1:1문의</a></li>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div id="mem_location"
			style="border: 1px solid black; position: absolute; height: 78%; width: 750px; top: 200px; left: 35%">
			<div id="member_menu">
				<img id="memberlogo" src="/Funding/view/prof.png"
					alt="회원들 이미지 로고">
				<div id="memMenufoot">
					<strong>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						일반회원&nbsp;</strong> <br> <br>
					<p>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<%=memVo.getMbsNm()%></p>
					<br>
				</div>
				<input id="memberlogout2" type="button" value="개인정보수정"
					onclick="location.href = './판매자개인정보수정.html'"> <input
					id="memberlogout" type="button" value="로그아웃"
					onclick="move_f_main()">
				<script>
					function move_f_main() {
						alert("로그아웃되셧습니다.");
					};
				</script>
			</div>
			<div id="member_page">
				<ul id="num1">
					<li class="num">0</li>
					<li class="num">&nbsp;&nbsp;&nbsp; 0</li>
					<li class="num">&nbsp;&nbsp;&nbsp; 0</li>
				</ul>
				<ul>
					<li class="page_li"><a href="/Funding/view/CusUpdateInfo.jsp">회원관리</a>
					</li>
					<li class="page_li"><a href="/Funding/view/admin/approve.jsp">판매자
							승인</a></li>
					<li class="page_li">등록된 펀딩</li>
				</ul>
				<hr class="page_line">
				<ul id="num2">
					<li class="page_li">수수료 관리</li>
					<li class="page_li">리뷰관리</li>
					<li class="page_li">신고회원</li>
				</ul>
			</div>
			<div id="middle">
				<a id="middle_mem" href="../main/main.html"><strong> 신규
						구매 / 리뷰를 통하여 배송비 3000원 마일리지 쿠폰을 알아보세요</strong></a>
			</div>
			<div id="foot">
				<p class="foot_p">나의 활동</p>
				<ul class="foot_all">
					<img class="foot_img" src="img/찜목록.png">
					<li class="foot_menu">찜목록</li>
					<img class="foot_img" src="img/문의사항.png">
					<li class="foot_menu">문의사항</li>
					<img class="foot_img" src="img/사업계획서].png">
					<li class="foot_menu">사업계획서</li>
				</ul>
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