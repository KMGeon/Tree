<%@page import="kr.or.funding.member.VO.MemberVO"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="kr.or.funding.oneIq.OneiqVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>funding</title>
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
<link rel="stylesheet" href="/Funding/view/css/admin_common.css">
<link rel="stylesheet"
	href="/Funding/view/css/admin_page_order_manage.css">
<script src="/Funding/view/js/admin_common.js"></script>
<script src="/Funding/view/js/admin_order_manage.js"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-iYQeCzEYFbKjA/T2uDLTpkwGzCiq6soy8tYaI1GyVh/UjpbCx/TYkiZhlZB6+fzT" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-u1OknCvxWvY5kfmNBILK2hRnQC3Pr17a+RTT6rIHI7NnikvbZlHgTPOOmMi466C8" crossorigin="anonymous"></script>
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
</style>
</head>

<body>
	<div id="wrap">
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
                        </ul>
				<!-- .sign_menu -->
			</div>
			<div class="header_logo">
				<h1 class="logo">
					<a href="/Funding/ptimfor/ptimforlist.do" class="link_main"> <span class="gnb_logo_container"></span>
						<img src="../img/logo.jpg" alt="로고" class="logo_img"
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
						<h2 class="tit_snb">판매자 메뉴</h2>
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
	<div class="container">
		<div class="row mt-4">

			<div id="category" class="col-2 b">
				<script src="../notice/category.js"></script>
			</div>
			<div id="content" class="col-9 offset-1 b"
				style="border: 1px solid lightgray;">
				<h1>자주묻는 질문</h1>
				<hr>
				<div class="accordion accordion-flush FAQ"
					id="accordionFlushExample">
					<div class="accordion-item">
						<h2 class="accordion-header" id="flush-headingOne">
							<button class="accordion-button collapsed" type="button"
								data-bs-toggle="collapse" data-bs-target="#flush-collapseOne"
								aria-expanded="false" aria-controls="flush-collapseOne">
								Q. 와디즈 스토어가 무엇인가요?</button>
						</h2>
						<div id="flush-collapseOne" class="accordion-collapse collapse"
							aria-labelledby="flush-headingOne"
							data-bs-parent="#accordionFlushExample">
							<div class="accordion-body">
								1. 펀딩으로 성공한 제품을 판매해 보세요.<br>
								와디즈 스토어는 펀딩을 통해 검증된 제품·서비스를 상시 판매할 수 있는 온라인 커머스 플랫폼 이에요. <br>
								 <br>
								  	2. 주문을 받으면 즉시 배송할 수 있어요. <br>
								  	서포터가 상품을 구매하면 미리 준비해둔 상품을 배송합니다. 서포터는 상품을 배송 받아 바로 사용하고, 언제든지 간편하게 재구매 할 수 있어요.
								

							</div>
						</div>
					</div>

					<div class="accordion-item">
						<h2 class="accordion-header" id="flush-headingTwo">
							<button class="accordion-button collapsed" type="button"
								data-bs-toggle="collapse" data-bs-target="#flush-collapseTwo"
								aria-expanded="false" aria-controls="flush-collapseTwo">
								Q. 스토어 프로젝트의 환불과 취소 정책이 궁금해요!</button>
						</h2>
						<div id="flush-collapseTwo" class="accordion-collapse collapse"
							aria-labelledby="flush-headingTwo"
							data-bs-parent="#accordionFlushExample">
							<div class="accordion-body">
								
							1. 서포터는 이럴 때 환불을 신청할 수 있어요. <br>
							서포터는 상품을 받고 7일 동안 단순 변심으로 환불을 신청할 수 있어요.<br>
							
							서포터는 상품을 받고 90일 동안 하자를 이유로 환불(반품)을 신청할 수 있어요.<br>
							
							서포터는 상품을 받고 상품의 하자를 알게 된 날로부터 30일 동안 하자를 이유로 환불(반품)을 신청할 수 있어요.<br>
							
							주문 제작 상품은 ‘상품 준비 중’일 때 서포터가 환불을 신청하지 않도록 사전에 소통해 주세요.<br>
							<br>
							2. 메이커는 이럴 때 환불을 처리할 수 있어요.<br>
							메이커는 부득이한 일이 발생했다면 서포터의 주문을 직접 취소(환불 처리)할 수 있어요.<br>
							
							메이커는 구매 확정 후 90일 동안 서포터와 협의해서 직접 취소(환불 처리)할 수 있어요.<br>
							
							직접 취소를 할 때 결제 금액의 환불이 진행되는데, 취소를 철회할 수 없으니 신중하게 결정해 주세요.<br>
							<br>
							3. 직접 취소는 이렇게 할 수 있어요.<br>
							스토어 메이커 스튜디오에서 [판매 관리] 메뉴에 들어가 직접 취소하려는 주문 번호의 ‘메이커 직접 취소’를 눌러요.<br>

								<code> [판매관리] / [판매 취소] </code>

							</div>
						</div>
					</div>

					<div class="accordion-item">
						<h2 class="accordion-header" id="flush-headingThree">
							<button class="accordion-button collapsed" type="button"
								data-bs-toggle="collapse" data-bs-target="#flush-collapseThree"
								aria-expanded="false" aria-controls="flush-collapseThree">
								Q. 배송은 언제 되나요?</button>
						</h2>
						<div id="flush-collapseThree" class="accordion-collapse collapse"
							aria-labelledby="flush-headingThree"
							data-bs-parent="#accordionFlushExample">
							<div class="accordion-body">
								A. 구매하시려는 상품 배송 일정은 메이커에게 직접 문의해 주셔야 정확한 확인이 가능합니다! <br>
								다만, 프로젝트 상세 페이지의 [배송 안내]에서 지정된 배송 기간을 확인하실 수 있어요.
								<code> </code>

							</div>
						</div>
					</div>
					<div class="accordion-item">
						<h2 class="accordion-header" id="flush-headingFour">
							<button class="accordion-button collapsed" type="button"
								data-bs-toggle="collapse" data-bs-target="#flush-collapseFour"
								aria-expanded="false" aria-controls="flush-collapseFour">
								Q. 스토어 판매를 중단/재개 하고 싶으면 어떻게 하나요?</button>
						</h2>
						<div id="flush-collapseFour" class="accordion-collapse collapse"
							aria-labelledby="flush-headingFour"
							data-bs-parent="#accordionFlushExample">
							<div class="accordion-body">
								A. 스토어 판매를 중단하거나 재개하고 싶다면 [스토어] 판매 중단/재개 요청서를 접수해 주세요.

							</div>
						</div>
					</div>
					<div class="accordion-item">
						<h2 class="accordion-header" id="flush-headingFive">
							<button class="accordion-button collapsed" type="button"
								data-bs-toggle="collapse" data-bs-target="#flush-collapseFive"
								aria-expanded="false" aria-controls="flush-collapseFive">
								Q. 판매를 중단하고 싶어요.</button>
						</h2>
						<div id="flush-collapseFive" class="accordion-collapse collapse"
							aria-labelledby="flush-headingFive"
							data-bs-parent="#accordionFlushExample">
							<div class="accordion-body">
								A. 접수 내역이 확인되면 3 영업일 내 처리를 도와드릴게요. <br>
								판매 종료 시 와디즈 사이트에서 검색 되지 않아요. <br>
								다만, 해당 프로젝트의 링크가 있다면 접속 가능하고 사이트에서는 [판매 종료] 상태로 노출됩니다.
							</div>
						</div>
					</div>
					<div class="accordion-item">
						<h2 class="accordion-header" id="flush-headingSix">
							<button class="accordion-button collapsed" type="button"
								data-bs-toggle="collapse" data-bs-target="#flush-collapseSix"
								aria-expanded="false" aria-controls="flush-collapseSix">
								Q. 결제 취소 후 환불절차는 어떻게 되나요?</button>
						</h2>
						<div id="flush-collapseSix" class="accordion-collapse collapse"
							aria-labelledby="flush-headingSix"
							data-bs-parent="#accordionFlushExample">
							<div class="accordion-body">
								A. 스토어 입점 기준과 수수료는 무엇인가요?
							

							</div>
						</div>
					</div>
					<div class="accordion-item">
						<h2 class="accordion-header" id="flush-headingSeven">
							<button class="accordion-button collapsed" type="button"
								data-bs-toggle="collapse" data-bs-target="#flush-collapseSeven"
								aria-expanded="false" aria-controls="flush-collapseSeven">
								Q. 객실을 미리 배정 받고 싶은데 가능한가요?</button>
						</h2>
						<div id="flush-collapseSeven" class="accordion-collapse collapse"
							aria-labelledby="flush-headingSeven"
							data-bs-parent="#accordionFlushExample">
							<div class="accordion-body">
								A. 스토어 입점 조건 (4가지 조건을 모두 충족해야 해요.) <br>
								1.메이커/리워드 만족도 평가 모두 3.5 이상인 프로젝트
스토어 프로젝트 제출 시점을 기준으로 해요<br>

2.결제 완료 서포터 100명 혹은 펀딩 결제 완료 금액 1,000만 원 이상인 프로젝트<br><br>
결제 완료 서포터는 결제 건수를 기준으로 해요 (펀딩 메이커 스튜디오 > 결제 현황 > 결제 완료 건수에서 확인할 수 있어요)<br>

결제 완료 금액은 펀딩 모집 금액 중 결제 실패 건을 제외한 총 금액을 뜻해요 (펀딩 메이커 스튜디오 > 결제 현황 > 결제 완료 금액에서 확인할 수 있어요)<br>

※ 모든 데이터는 1개의 프로젝트를 기준으로 충족되어야 해요<br>

3. 2018년 10월 1일이 지나고 종료된 프로젝트<br><br>
만족도 평가 점수가 없던 시기(~2018년 9월 30일)에 종료된 프로젝트는 입점 심사가 어려워요.<br>

4. 사업자인 메이커의 프로젝트<br><br>
사업자인 메이커님들만 스토어 입점 후 판매가 가능해요.<br>

만약 개인으로 펀딩 프로젝트를 진행했다면 사업자 등록 후 사업자 등록증을 제출해 주세요.<br>

※ 입점 조건을 만족했더라도 스토어 개설이 제한될 수 있어요<br>

최종 정산이 완료되지 않은 프로젝트<br>

리워드가 제공되지 않은 [ 기부·후원 ] 카테고리의 프로젝트<br>

주류, 숙박업 등 19세 이상만 참여가 가능한 프로젝트<br>
<br>
만족도 기준을 충족하지 않는다면 제출이 어려워요. <br>

								

							</div>
						</div>
					</div>
					<div class="accordion-item">
						<h2 class="accordion-header" id="flush-headingEight">
							<button class="accordion-button collapsed" type="button"
								data-bs-toggle="collapse" data-bs-target="#flush-collapseEight"
								aria-expanded="false" aria-controls="flush-collapseEight">
								Q. 대표자·정산 정보는 어떻게 작성하나요?</button>
						</h2>
						<div id="flush-collapseEight" class="accordion-collapse collapse"
							aria-labelledby="flush-headingEight"
							data-bs-parent="#accordionFlushExample">
							<div class="accordion-body">
								A. 1. 사업자등록증 2. 법인 인감 증명서 (법인사업자) 3. 대표자 휴대폰 본인인증 4. 공동 대표자 5. 계좌 정보
							</div>
						</div>
					</div>
					<div class="accordion-item">
						<h2 class="accordion-header" id="flush-headingNine">
							<button class="accordion-button collapsed" type="button"
								data-bs-toggle="collapse" data-bs-target="#flush-collapseNine"
								aria-expanded="false" aria-controls="flush-collapseNine">
								Q. 예약은 어떻게 해야 하나요?</button>
						</h2>
						<div id="flush-collapseNine" class="accordion-collapse collapse"
							aria-labelledby="flush-headingNine"
							data-bs-parent="#accordionFlushExample">
							<div class="accordion-body">
								A. 원하시는 상품을 검색 후 결제를 하면 예약을 진행하실 수 있습니다. 모두의 여행에서 제공하는 다양한 상품을
								선택해 보세요. 상품 상세 일정표에서 기본 정보, 사용 방법과 같은 내용을 확인한 후, 원하는 상품의 타입을
								선택하세요. 추가적 인원, 연령 등 상품 타입 내 옵션 사항들을 선택하고 총 결제 금액을 확인한 후 ‘예약하기’를
								누르고 결제를 진행해주세요.
								<code> </code>

							</div>
						</div>
					</div>
					<div class="accordion-item">
						<h2 class="accordion-header" id="flush-headingTen">
							<button class="accordion-button collapsed" type="button"
								data-bs-toggle="collapse" data-bs-target="#flush-collapseTen"
								aria-expanded="false" aria-controls="flush-collapseTen">
								Q. 결제는 어떻게 하나요?</button>
						</h2>
						<div id="flush-collapseTen" class="accordion-collapse collapse"
							aria-labelledby="flush-headingTen"
							data-bs-parent="#accordionFlushExample">
							<div class="accordion-body">
								A. 결제는 국내 신용카드(카카오페이)만 이용 가능합니다. (무통장 입금 사용 불가) 외화로 등록된 상품의 경우,
								환율에 따라 해당 상품의 가격이 변동될 수 있어 최초로 확인한 금액과 결제 금액이 상이할 수 있습니다.
								<code> </code>

							</div>
						</div>
					</div>
					<div class="accordion-item">
						<h2 class="accordion-header" id="flush-headingEleven">
							<button class="accordion-button collapsed" type="button"
								data-bs-toggle="collapse" data-bs-target="#flush-collapseEleven"
								aria-expanded="false" aria-controls="flush-collapseEleven">
								Q. 코로나바이러스19 관련 취소/환불 문의</button>
						</h2>
						<div id="flush-collapseEleven" class="accordion-collapse collapse"
							aria-labelledby="flush-headingEleven"
							data-bs-parent="#accordionFlushExample">
							<div class="accordion-body">
								Q. 코로나바이러스19 확진 판정받았습니다. 항공권 취소 시 수수료가 부과되나요? A. 코로나바이러스19 확진
								판정관련하여 서류 검토 후 취소수수료 면제 가능여부 확인 가능합니다.

								<code> </code>

							</div>
						</div>
					</div>
					<div class="accordion-item">
						<h2 class="accordion-header" id="flush-headingTwelve">
							<button class="accordion-button collapsed" type="button"
								data-bs-toggle="collapse" data-bs-target="#flush-collapseTwelve"
								aria-expanded="false" aria-controls="flush-collapseTwelve">
								Q. 개인정보 열람,정정,삭제 어떻게 하나요?</button>
						</h2>
						<div id="flush-collapseTwelve" class="accordion-collapse collapse"
							aria-labelledby="flush-headingTwelve"
							data-bs-parent="#accordionFlushExample">
							<div class="accordion-body">
								A. 고객님의 개인정보 열람 및 정정을 위해서는 [마이페이지> [개인정보]를 클릭하여 열람 또는 정정하실 수
								있습니다.
								<code> </code>

							</div>
						</div>
					</div>
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