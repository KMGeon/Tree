<%@page import="kr.or.funding.member.VO.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>

<meta charset="utf-8">
<script src="http://code.jquery.com/jquery.min.js"></script>
<title>로그인</title>
<link rel="stylesheet" href="./css/reset.css">
<link rel="stylesheet" href="./css/common.css">
<link rel="stylesheet" href="./css/login.css">
<script src="./js/common.js"></script>
</head>



<body>
	<div id="container" class="container">
		<div id="header">
			<div class="user_menu">

				<ul class="sign_menu">
                    <!--login class 추가-->
                     <% MemberVO memVo = (MemberVO) session.getAttribute("memList");%>
                    <% if(memVo == null){ %>
                    <li class="link">
                        <a href="/Funding/view/joinselect.jsp" class="item after join">회원가입</a>
                    </li>
                    <li class="link">
                        <a href="/Funding/view/login.jsp" class="item after login_none">로그인</a>
                    </li>
                        <%} else{ %>
                        <li class="link">
                        <a href="./joinselect.jsp" class="item after join"><%=memVo.getMbsNm()%>님 어서오세요</a>
                    </li>
                    <li class="link">
                        <a href="./login.jsp" class="item after login_none">마이페이지</a>
                    </li> 
                    <li class="link">
                        <a href="./main.jsp" onclick="logout()" class="item after login_none">로그아웃
                        </a>
                    </li> 
                        <%} %>
                        </ul>
                        <script type="text/javascript">
                        function logout(){
                        	<% session.removeAttribute("memList");  %>
                        }
                        </script>
				<!-- .sign_menu -->
			</div>
			<div class="header_logo">
				<h1 class="logo">
					<a href="#" class="link_main"> <span class="gnb_logo_container"></span>
						<img src="/Funding/view/img/logo.jpg" alt="로고" class="logo_img"
						onclick="location.href='/Funding/ptimfor/ptimforlist.do'">
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
					<li class="list"><a href="/Funding/notice/list.do"
						class="link"> <span class="txt">공지사항</span>
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
								<button type="button" id="search_init" class="btn_delete">검색어
									삭제하기</button>
							</div>
						</form>
					</div>

				</ul>
			</div>
		</div>


		<div id="main">
			<div id="content">
				<div class="section_login">
					<h3 class="tit_login">LOGIN</h3>
					<div class="write_form">
						<div class="write_view login_view">
							<form method="post" name="login_form" id="form"
								action="/Funding/login.do">
								<input type="hidden" name="returnUrl" value=""> <input
									type="hidden" name="close" value> <input type="text"
									name="mbsId" size="20" tabindex="1" value placeholder="아이디 입력">
								<input type="password" name="mbsPw" size="20" tabindex="2"
									placeholder="비밀번호 입력">
								<div class="checkbox_save">
									<div class="login_search">
										<a href="find_id.html" class="link">아이디 찾기</a> <span
											class="bar"></span> <a href="find_pw.html" class="link">비밀번호
											찾기</a>
									</div>
								</div>
								
						
								
								<button type="button" onclick="check_input()" class="btn_type1">
									<span class="txt_type">로그인</span>
								</button>
							</form>
							<a href="/Funding/view/joinselect.jsp" class="btn_type2 btn_member"> <span
								class="txt_type">회원가입</span>
							</a>

						</div>

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
						<li><a href="#" class="link">펀딩소개</a></li>
						<li><a href="#" class="link">펀딩소개영상</a></li>
						<li><a href="#" class="link">인재채용</a></li>
						<li><a href="#" class="link">이용약관</a></li>
						<li><a href="#" class="link">개인정보처리방침</a></li>
						<li><a href="#" class="link">이용안내</a></li>
					</ul>
					<div class="spec_info">
						법인명 (상호) : 주식회사 컬리 <span class="bar">I</span> 사업자등록번호 :
						000-00-0000 <a href="#" class="link">사업자정보확인</a><br> 통신판매업 :
						제 2018-서울강남-00000 호 <span class="bar">I</span> 개인정보보호책임자 : 000 <br>
						주소 : 서울시 도산대로 16길 20, 이래빌딩 B1 ~ 4F <span class="bar">I</span> 대표이사
						: 000 <br> 입점문의 : <a href="#" class="link">입점문의하기</a> 제휴문의 :
						<a href="#" class="link">ghkdvnfld345@naver.com</a><br> 채용문의
						: <a href="#" class="link">ghkdvnfld345@naver.com</a><br> 팩스
						: 000 - 0000 - 0000 <span class="bar">I</span> 이메일 : <a href="#"
							class="link">ghkdvnfld345@naver.com</a><br> <br> <strong
							class="copy">© KURLY CORP. ALL RIGHTS RESERVED</strong>
						<ul class="sns">
							<li><a href="#" class="link_sns insta" target="_blank">
									<img src="https://res.kurly.com/pc/ico/1810/ico_instagram.png"
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
						src="https://res.kurly.com/pc/ico/2001/logo_isms.png"
						alt="isms 로고" class="logo">
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
							고객님의 안전거래를 위해 현금 등으로 결제 시 저희 쇼핑몰에서 가입한<br> 토스 페이먼츠
							구매안전(에스크로) 서비스를 이용하실 수 있습니다.
						</p>
					</a>
				</div>
			</div>
		</div>





	</div>




	<script>


     

        function check_input() {
            if (!document.login_form.mbsId.value) {
                alert("아이디를 입력하세요");
                document.login_form.mbsId.focus();
                return;
            }
            if (!document.login_form.mbsPw.value) {
                alert("비밀번호를 입력하세요");
                document.login_form.mbsPw.focus();
                return;
            }
           
            document.login_form.submit();

        }
    </script>
</body>

</html>