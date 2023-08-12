<%@page import="kr.or.funding.ptImfor.PtImforVO"%>
<%@page import="java.util.List"%>
<%@page import="kr.or.funding.member.VO.MemberVO" %>
    <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<% List<PtImforVO> detailList = (List<PtImforVO>) request.getAttribute("ptDetail");%>
        <!DOCTYPE html>
        <html lang="ko">

        <head>
        <script type="text/javascript"
	src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<!-- iamport.payment.js -->
<script type="text/javascript"
	src="https://cdn.iamport.kr/js/iamport.payment-1.2.0.js"></script>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>INU Kurly</title>
            <link rel="stylesheet" href="/Funding/view/css/reset.css">
            <link rel="stylesheet" href="/Funding/view/css/common.css">
            <!-- common.css : header, footer 메인 페이지 -->
            <link rel="stylesheet" href="/Funding/viewcss/visual.css">
            <!-- visual.css : header 바로 밑 이미지 슬라이드 파트 -->
            <link rel="stylesheet" href="/Funding/viewcss/contents.css">
            <!-- header와 footer를 제외한 콘텐츠 메인 페이지 -->
            <script src="http://code.jquery.com/jquery-latest.min.js"></script>
            <script defer src="/Funding/view/js/common.js"></script>
            <script defer src="/Funding/view/js/contents.js"></script>
            <script defer src="/Funding/view/js/visual.js"></script>
            <link rel="shortcut icon" href="./img/favicon.png">
            <link rel="icon" href="./img/favicon.png">
			<link rel="stylesheet" href="/Funding/view/css/order.css">
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
                            <% MemberVO memVo=(MemberVO) session.getAttribute("memList");%>

                                <% if(memVo==null){ %>
                                    <li class="link">
                                        <a href="./joinselect.jsp" class="item after join">회원가입</a>
                                    </li>
                                    <li class="link">
                                        <a href="./login.jsp" class="item after login_none">로그인</a>
                                    </li>
                                    <%} else{ %>
                                        <li class="link">
                                            <a href="#" class="item after join">
                                                <%=memVo.getMbsNm()%>님 어서오세요
                                            </a>
                                        </li>
                                        <%if(memVo.getMbsAhy()==2){ %>
                                            <li class="link">
                                                <a href="/Funding/view/notice_seller.jsp"
                                                    class="item after login_none">판매자페이지</a>
                                            </li>
                                            <%} %>
                                                <%if(memVo.getMbsAhy()==1){ %>
                                                    <%if (memVo.getUseAt().equals("Y")){ %>

                                                        <li class="link">
                                                            <a href="/Funding/view/notice_seller.jsp"
                                                                class="item after login_none">마이페이지</a>
                                                        </li>
                                                        <%}else{ %>
                                                            <li class="link">
                                                                <a id="notUseAt" href="#"
                                                                    class="item after login_none">마이페이지</a>
                                                                <script type="text/javascript">
                                                                    $("#notUseAt").click(function () {
                                                                        alert("승인대기중");
                                                                    })
                                                                </script>
                                                            </li>
                                                            <%}} %>
                                                                <%if(memVo.getMbsAhy()==0){ %>
                                                                    <li class="link">
                                                                        <a href="/Funding/view/notice_seller.jsp"
                                                                            class="item after login_none">관리자페이지</a>
                                                                    </li>
                                                                    <%} %>
                                                                        <li class="link">
                                                                            <a href="./logout.jsp" onclick="logout()"
                                                                                class="item after login_none">로그아웃
                                                                            </a>
                                                                        </li>
                                                                        <%} %>
                                                                            <li class="link">

                                                                                <!-- 고객센터 hover 시 sub_menu 등장 -->
                                                                                <ul class="sub_menu">
                                                                                    <li class="list">
                                                                                        <a href="#"
                                                                                            class="list_item">공지사항</a>
                                                                                    </li>
                                                                                    <li class="list">
                                                                                        <a href="#"
                                                                                            class="list_item">자주하는
                                                                                            질문</a>
                                                                                    </li>
                                                                                    <li class="list">
                                                                                        <a href="#"
                                                                                            class="list_item">1:1 문의</a>
                                                                                    </li>
                                                                                    <li class="list">
                                                                                        <a href="#" class="list_item">상품
                                                                                            제안</a>
                                                                                    </li>
                                                                                    <li class="list">
                                                                                        <a href="#"
                                                                                            class="list_item">에코포장
                                                                                            피드백</a>
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
                                <img src="/Funding/view/img/logo.jpg" alt="로고" class="logo_img">
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
                                <a href="/notice2/notice2List.do" class="link">
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
                                    <input type="text" id="keyword" value="건강 기원 새해맞이 보양식 레시피" title="검색어입력"
                                        class="inp_search">
                                    <input type="image"
                                        src="https://res.kurly.com/pc/service/common/1908/ico_search_x2.png"
                                        class="btn_search">
                                    <div class="init">
                                        <button type="button" id="search_init" class="btn_delete">검색어 삭제하기</button>
                                    </div>
                                </form>
                            </div>

                        </ul>
                    </div>
                </div>

                <body>

                    <div id="container" class="container">
                        <div id="container">
                            <div id="main">
                                <div id="content">
                                    <div class="user_form">
                                        <h1 class="h1_tit">주문서</h1>

                                        <h2 class="tit_section fst">
                                            주문상품
                                        </h2>

                                        <div id="itemList" class="page_aticle order_goodslist">
                                            <ul class="list_product">
                                                <li>
                                                    <div class="thumb">
                                                        <img src="<%=request.getContextPath()%>/sunju/<%=detailList.get(0).getStreFileNm() %>"
                                                            alt="상품이미지">
                                                    </div>

                                                    <div class="name">
                                                        <div class="inner_name"><%=detailList.get(0).getPtNm() %></div>
                                                    </div>

                                                    <div class="ea">
                                                        1개
                                                    </div>

                                                    <div class="info_price">
                                                        <span class="num">
                                                            <span class="price">
                                                               <%=detailList.get(0).getPtPrc() %>원
                                                            </span>
                                                            <input class="cost" type="hidden" value="1300">
                                                            <!-- value 에 가격 값 넣기-->

                                                            <!-- <span class="cost"> 할인금액 하는건데 이건 뺴야겠음
                                                                1,300원
                                                            </span> -->
                                                        </span>
                                                    </div>
                                                </li>
                                            </ul>
                                        </div>

                                        
                                            <h2 class="tit_section" id="titFocusOrderer">주문자 정보</h2>

                                            <div class="order_section data_orderer">
                                                <table class="goodsinfo_table">
                                                    <tbody>
                                                        <tr class="fst">
                                                            <th>받는 분</th>
                                                            <td>
                                                               <%=memVo.getMbsNm() %> 
                                                                <input type="hidden" name="orderer_name" value="이도현">
                                                            </td>
                                                        </tr>

                                                        <tr>
                                                            <th>휴대폰</th>
                                                            <td>
                                                                <%=memVo.getMbsPh() %>
                                                                <input type="hidden" name="orderer_phone"
                                                                    value="01011111111">
                                                            </td>
                                                        </tr>

                                                        <tr>
                                                            <th>이메일</th>
                                                            <td>
                                                                <input type="hidden" id="email" name="orderer_email"
                                                                    value="ksmfou98@naver.com" option="regEmail">
                                                                <%=memVo.getMbsMail() %>
                                                                <p class="txt_guide">
                                                                    <span class="txt txt_case1">이메일을 통해 주문처리과정을
                                                                        보내드립니다.</span>
                                                                    <span class="txt txt_case2">정보 변경은 <span
                                                                            class="txt_desc">마이컬리 &gt; 개인정보 수정</span>
                                                                        메뉴에서 가능합니다.</span>
                                                                </p>
                                                            </td>
                                                        </tr>
                                                    </tbody>
                                                </table>
                                            </div>

                                            <h2 class="tit_section" id="divAddressWrapper">
                                                배송정보
                                                <span class="desc">배송 휴무일: 샛별배송(휴무없음), 택배배송(일요일)</span>
                                            </h2>

                                            <div class="order_section order_address" id="dataDelivery">
                                                <h3 href="#" class="section_crux">
                                                    배송지
                                                </h3>
                                                <div class="section_full">
                                                    <span class="address" id="divDesination">
                                                        <span class="addr" id="addrInfo"><%=memVo.getMbsAddr() %></span>
                                                        <span class="tag" id="addrTags">
                                                            
                                                        </span>

                                                    </span>
                                                </div>
                                            </div>



                                            <div class="tax_absolute">
                                                <div class="inner_sticky" id="sticky" style="top:0px;">
                                                    <h2 class="tit_section">결제금액</h2>
                                                    <div id="orderitem_money_info">
                                                        <dl class="amount fst">
                                                            <dt class="tit">주문금액</dt>
                                                            <dd class="price">
                                                                <span id="productsTotalPrice"></span>
                                                               <%=detailList.get(0).getPtPrc() %> 원
                                                            </dd>
                                                        </dl>



                                                        <dl class="amount">
                                                            <dt class="tit">배송비</dt>
                                                            <dd class="price delivery_area">
                                                                <div id="paper_delivery_msg1" style="display: block;">
                                                                    <span class="pm_sign"
                                                                        style="display: inline;">+</span>
                                                                    <span id="paper_delivery" class="">3,000</span>
                                                                    원
                                                                </div>
                                                            </dd>
                                                        </dl>

                                                        <dl class="amount lst">
                                                            <dt class="tit">최종결제금액</dt>
                                                            <dd class="price">
                                                                <span id="paper_settlement"><%=detailList.get(0).getPtPrc()+3000 %></span>
                                                                <input type="hidden" id="product_price_value"
                                                                    name="product_price_value" value="">
                                                                <span class="won">원</span>
                                                            </dd>
                                                        </dl>


                                                    </div>
                                                </div>
                                            </div>

                                            <div class="data_payment">
                                               
                                            </div>

                                            <div class="data_method">
                                                
                                               


                                                
                                            </div>
                                            <div class="tit_section">
                                            <form id="fbabo" action="/Funding/pyhis.do" method="post">
                                           		<input type="hidden" name="mbsNum" value="<%=memVo.getMbsNum()%>">
                                           		<input type="hidden" name="mbsMail" value="<%=memVo.getMbsMail()%>">
                                           		<input type="hidden" name="mbsAddr" value="<%=memVo.getMbsAddr()%>">
                                           		<input type= "hidden" name="ptNm" value="<%=detailList.get(0).getPtNm() %>">
                                            	<input type="hidden" name="ptNum" value="<%=detailList.get(0).getPtNum() %>">
                                            	<input type="hidden" name="mbsNm" value="<%=memVo.getMbsNm() %>">
                                            	<input type="hidden" name="pyAm" value="<%=detailList.get(0).getPtPrc()+3000-1000 %>">
                                            	<input type="hidden" name="commi" value="1000">
                                            	<input type="hidden" name="pyQty" value="1">                                            	                                                                                 
                                              <input type="submit" value="결제하기" class="btn_payment" id="requestPay">
                                            </form>
                                            
                                            
  
		
	
                                            </div>
                                        
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                                          
	<script>
	
		var IMP = window.IMP;
		IMP.init("imp27757035");
		
		$("#fbabo").on('submit',function(){
			event.preventDefault();
			IMP.request_pay({ // param
				pg : "kakaopay.TC0ONETIME",
				pay_method : "card",
				merchant_uid : "a"+Math.floor(Math.random()*1000000),
				name : "노르웨이 회전 의자",
				amount : 64900,
				buyer_email : "gildong@gmail.com",
				buyer_name : "홍길동",
				buyer_tel : "010-4242-4242",
				buyer_addr : "서울특별시 강남구 신사동",
			}, function(rsp) { // callback
				console.log(rsp);
				if (rsp.success) { // 결제 성공 시: 결제 승인 또는 가상계좌 발급에 성공한 경우
					$("#fbabo").submit();
					
				} else {
					alert("결제에 실패하였습니다. 에러 내용: " + rsp.error_msg);
				}
			});
		})
		
		function requestPay() {
			// IMP.request_pay(param, callback) 결제창 호출
			
		}
		
		//리디렉션 설정하기
		// IMP.request_pay(param, callback) 호출
		/*  IMP.request_pay({
		      m_redirect_url: "{리디렉션 될 URL}" // 예: https://www.myservice.com/payments/complete/mobile
		  }, /* callback ); // callback은 실행 안됨*/
	</script>




                </body>

        </html>