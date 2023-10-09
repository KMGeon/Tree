<%@page import="kr.or.funding.ptImfor.PtImforVO"%>
<%@page import="java.util.List"%>
<%@page import="kr.or.funding.member.VO.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%List<PtImforVO> myList = (List<PtImforVO>)request.getAttribute("atchList"); %>
<!DOCTYPE html>
<html lang="ko">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>INU Kurly</title>
<link rel="stylesheet" href="/Funding/view/css/reset.css">
<link rel="stylesheet" href="/Funding/view/css/common.css">
<!-- common.css : header, footer 메인 페이지 -->
<link rel="stylesheet" href="/Funding/view/css/visual.css">
<!-- visual.css : header 바로 밑 이미지 슬라이드 파트 -->
<link rel="stylesheet" href="/Funding/view/css/contents.css">
<!-- header와 footer를 제외한 콘텐츠 메인 페이지 -->
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script defer src="/Funding/view/js/common.js"></script>
<script defer src="/Funding/view/js/contents.js"></script>
<script defer src="/Funding/view/js/visual.js"></script>
<link rel="shortcut icon" href="./img/favicon.png">
<link rel="icon" href="./img/favicon.png">

</head>

<body>
	<!-- 규칙:
    축약형(link, emph, gnb 등)을 먼저 사용)
    alt로 적절한 대체 텍스트 제공, 너무 긴 경우 공통클래스 blind로 제공-->
	<div id="wrap">

		<!-- id=top_event -->

		<div id="header">
			<div class="user_menu">


				<ul class="sign_menu">
					<!--login class 추가-->
					<% MemberVO memVo = (MemberVO) session.getAttribute("memList");%>

					<% if(memVo == null){ %>
					<li class="link"><a href="/Funding/view/joinselect.jsp"
						class="item after join">회원가입</a></li>
					<li class="link"><a href="/Funding/view/login.jsp"
						class="item after login_none">로그인</a></li>
					<%} else{ %>
					<li class="link"><a href="#" class="item after join"><%=memVo.getMbsNm()%>님
							어서오세요</a></li>
					<%if(memVo.getMbsAhy() == 2 ){ %>
					<%if(memVo.getUseAt().equals("Y")){ %>
					<li class="link"><a href="/Funding/view/sellerMypage.jsp"
						class="item after login_none">판매자페이지</a></li>
						<%}else{ %>
						<li class="link"><a id="sellerMypage" href="#"
						class="item after login_none">판매자페이지</a></li>
						
						
						<%} %>
					<%} %>
					
					

					<%if(memVo.getMbsAhy() == 1){ %>
					<li class="link"><a href="/Funding/view/notice_Customer.jsp"
						class="item after login_none">마이페이지</a></li>
					<%} %>
					<%if(memVo.getMbsAhy() == 0){ %>
					<li class="link"><a href="/Funding/view/notice_seller.jsp"
						class="item after login_none">관리자페이지</a></li>
					<%} %>
					<li class="link"><a href="/Funding/view/logout.jsp"
						onclick="logout()" class="item after login_none">로그아웃 </a></li>
					<%} %>
					<li class="link">
						<!-- 고객센터 hover 시 sub_menu 등장 -->
						<ul class="sub_menu">
							<li class="list"><a href="#" class="list_item">공지사항</a></li>
							<li class="list"><a href="#" class="list_item">자주하는 질문</a></li>
							<li class="list"><a href="#" class="list_item">1:1 문의</a></li>
							<li class="list"><a href="#" class="list_item">상품 제안</a></li>
							<li class="list"><a href="#" class="list_item">에코포장 피드백</a>
							</li>
						</ul>
					</li>
				</ul>
				<!-- .sign_menu -->
			</div>
			<div class="header_logo">
				<h1 class="logo">
					<a href="/Funding/ptimfor/ptimforlist.do" class="link_main"> <span
						class="gnb_logo_container"></span> <img
						src="/Funding/view/img/logo.jpg" alt="로고" class="logo_img">
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
					<li class="list"><a href="/Funding/notice2/notice2List.do"
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
		<!-- id ="header" -->

		<div id="visual">
			<h2 class="blind">마켓컬리 메인</h2>
			<div class="swipe">
				<ul class="promotions">
					<li data-index="1" class="slide_list"><img object-fit: cover
						class="thumb"
						src="<%=request.getContextPath()%>/sunju/<%=myList.get(6).getStreFileNm()%>">
						<!--이미지 오류. bg color로 대체--></li>
					<li data-index="1" class="slide_list"><img object-fit: cover
						class="thumb"
						src="<%=request.getContextPath()%>/sunju/<%=myList.get(14).getStreFileNm()%>">
						<!--이미지 오류. bg color로 대체--></li>
					<li data-index="1" class="slide_list"><img object-fit: cover
						class="thumb"
						src="<%=request.getContextPath()%>/sunju/<%=myList.get(7).getStreFileNm()%>">
						<!--이미지 오류. bg color로 대체--></li>
					<li data-index="1" class="slide_list"><img object-fit: cover
						class="thumb"
						src="<%=request.getContextPath()%>/sunju/<%=myList.get(1).getStreFileNm()%>">
						<!--이미지 오류. bg color로 대체--></li>
					<li data-index="1" class="slide_list"><img object-fit: cover
						class="thumb"
						src="<%=request.getContextPath()%>/sunju/<%=myList.get(5).getStreFileNm()%>">
						<!--이미지 오류. bg color로 대체--></li>
					<li data-index="1" class="slide_list"><img object-fit: cover
						class="thumb"
						src="<%=request.getContextPath()%>/sunju/<%=myList.get(17).getStreFileNm()%>">
						<!--이미지 오류. bg color로 대체--></li>
				</ul>

				<div class="controls_direction" style="display: none;">
					<!-- bg url 처리 -->
					<a href="prev">이전으로</a> <a href="next">다음으로</a>
				</div>
			</div>

		</div>
		<!-- id="visual" -->
		<div id="main">

			<div id="contents" class="page_aticle">


				<div class="top_btn">
					<button id="go-top" alt="맨 위로가기"></button>
				</div>

				<!-- 우선은 bg color 있는 것과 없는 2가지 types만 추가  -->
				<!-- class = "main_type1은 bg 없음" -->

				<!-- class="main_type1" 끝 -->


				<!-- class ="main_type2" 끝 -->

				<!-- .main_type1 반복 -->
				<div class="main_type1">
					<!-- type1:  -->
					<div class="product_list">
						<!-- 상품 추천 -->
						<div class="tit_goods">
							<h3 class="tit">
								<span class="name">
									<hr>
								</span>
								<!-- 아이콘 없는 버전, h3에 링크 없음 -->
							</h3>
						</div>

						<%                                                                                                 
                                                	int myListSize = myList.size();
                                                	for(int i =0;i < (myListSize-1);i++){
                                                		if(myList.get(i).getFileSn() != 1){
                                                		myList.remove(i);
                                                		}
                                                	}
                                                	int myListRemoveList = myList.size();
                                                	System.out.println(myListRemoveList);
                                                	int startList = 0;
                                                	int endList = 3;
                                                	%>
						<!-- list_goods는 마켓컬리와 똑같이 html 파일에서 스타일함 -->
						<% for(int i =0 ; i < myListRemoveList/4 ;i++){ %>
						<div class="list_goods">
							<div class="bx_wrapper"
								style="max-width: 1054px; margin: 0 auto;">

								<div class="vx-viewport"
									style="width: 100%; height: 506px; position: relative; overflow: hidden;">
									<!-- overflow: hidden 임시로 삭제 -->
									<ul data-title="이 상품 어때요?" data-section="today_recommendation"
										class="list_goods_ul"
										style="width: 2215%; position: relative; transition-duration: 0.5s; transform: translate3d(0, 0, 0);">
										<!--width: 2215%는 아직 뭔지 모르겠음  -->
										<!-- data-section과 data-name은 임시값 -->
										<%for(int j =startList; j<=endList ; j++){%>
										<%if(myList.get(j).getFileSn() == 1){ %>
										<!-- list data  -->
										<li data-index="<%=j%>" data-name="today_recommendation"
											class="list_item"
											style="float: left; position: relative; width: 249px; margin-right: 18px;">

											<a class="thumb_goods"
											href="ptImforDetail.do?ptImforNum=<%=myList.get(j).getPtNum()%>">
												<img
												src="<%=request.getContextPath()%>/sunju/<%=myList.get(j).getStreFileNm() %>"
												alt="상품이미지" class="thumb"
												style="background-image: url('https://img-cf.kurly.com/shop/data/goods/1466488979157l0.jpg');">
												<!-- 상품 이미지 임시값임. src는 데이터로 받아오기 -->
										</a>
											<div class="info_goods"
												onclick="location.href ='ptImforDetail.do?ptImforNum=<%=myList.get(j).getPtNum()%>'">
												<span class="name"> <a class="txt"> <%=myList.get(j).getPtNm() %>
												</a>
												</span> <span class="price"><%=myList.get(j).getPtPrc() %>원</span>

											</div>

										</li>
										<%
                                                
                                        }}
                                                %>
									</ul>
								</div>
								<!-- bx-viewport 끝 -->
							</div>
						</div>
						<% 
                       	startList +=4;
                       endList +=4;
                       } %>

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
										000-00-0000 <a href="#" class="link">사업자정보확인</a><br>
										통신판매업 : 제 2018-서울강남-00000 호 <span class="bar">I</span>
										개인정보보호책임자 : 000 <br> 주소 : 서울시 도산대로 16길 20, 이래빌딩 B1 ~ 4F <span
											class="bar">I</span> 대표이사 : 000 <br> 입점문의 : <a href="#"
											class="link">입점문의하기</a> 제휴문의 : <a href="#" class="link">ghkdvnfld345@naver.com</a><br>
										채용문의 : <a href="#" class="link">ghkdvnfld345@naver.com</a><br>
										팩스 : 000 - 0000 - 0000 <span class="bar">I</span> 이메일 : <a
											href="#" class="link">ghkdvnfld345@naver.com</a><br> <br>
										<strong class="copy">© KURLY CORP. ALL RIGHTS
											RESERVED</strong>
										<ul class="sns">
											<li><a href="#" class="link_sns insta" target="_blank">
													<img
													src="https://res.kurly.com/pc/ico/1810/ico_instagram.png"
													alt="마켓컬리 인스타그램 바로가기">
											</a> <!-- bg url 넣기 --></li>
											<li><a href="#" class="link_sns fb" target="_blank">
													<img src="https://res.kurly.com/pc/ico/1810/ico_fb.png"
													alt="마켓컬리 페이스북 바로가기">
											</a></li>
											<li><a href="#" class="link_sns naver_blog"
												target="_blank"> <img
													src="https://res.kurly.com/pc/ico/1810/ico_blog.png"
													alt="마켓컬리 네이버블로그 바로가기">
											</a></li>
											<li><a href="#" class="link_sns naver_post"
												target="_blank"> <img
													src="https://res.kurly.com/pc/ico/1810/ico_naverpost.png"
													alt="마켓컬리 포스트 바로가기">
											</a></li>
											<li><a href="#" class="link_sns yt" target="_blank">
													<img
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
						<!-- id="footer" -->


					</div>
					<script type="text/javascript">
					$("#sellerMypage").click(function(){
						alert("승인후 접근가능");
					})
					
					</script>
					
</body>

</html>