<%@page import="kr.or.funding.ptImfor.PtImforVO"%>
<%@page import="java.util.List"%>
<%@page import="kr.or.funding.member.VO.MemberVO" %>
    <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<% List<PtImforVO> detailList = (List<PtImforVO>) request.getAttribute("ptDetail");
	System.out.println(detailList.get(0).getPtNum());%>
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
                            <% MemberVO memVo=(MemberVO) session.getAttribute("memList");%>

                                <% if(memVo==null){ %>
                                    <li class="link">
                                        <a href="/Funding/view/joinselect.jsp" class="item after join">회원가입</a>
                                    </li>
                                    <li class="link">
                                        <a href="/Funding/view/login.jsp" class="item after login_none">로그인</a>
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
                                <a href="/Funding/notice/list.do" class="link">
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
               
                <head>
                    <meta charset="UTF-8">
                    <meta name="viewport" content="width=device-width, initial-scale=1.0">
                    <title>상품등록 탬플릿</title>
                    <link rel="stylesheet" href="/Funding/view/css/reset.css">
                    <link rel="stylesheet" href="/Funding/view/css/common.css">
                    <link rel="stylesheet" href="/Funding/view/css/good_view.css">
                    <script src="http://code.jquery.com/jquery-latest.min.js"></script>
                    <script src="/Funding/view/js/good_view.js"></script>
                    <script src="/Funding/view/js/common.js"></script>

                </head>

                <body>

                    <div id="container" class="container">


                        <div id="main">
                            <div id="content" style="opacity: 1;">
                                <div class="section_view">
                                    <div id="sectionView">
                                        <div class="inner_view">
                                            <div class="thumb"
                                                style="background-image: url(https://res.kurly.com/mobile/img/1808/img_none_x2.png);">
                                                <img src="<%=request.getContextPath()%>/sunju/<%=detailList.get(0).getStreFileNm() %>"
                                                    alt="상품 대표 이미지" class="bg">

                                            </div>
                                            <p class="goods_name">
                                                <span class="btn_share">
                                                    <button id="btnShare">공유하기</button>
                                                </span>
                                                <strong class="name"><%=detailList.get(0).getPtNm() %></strong>
                                            </p>
                                            <p class="goods_price">
                                                <span class="position">
                                                    <span class="dc">
                                                        <span class="dc_price">
                                                            <%=detailList.get(0).getPtPrc() %>
                                                            <input type="hidden" value=0> <!-- 여기 벨류에다가 가격데이터 넣어줘야댐-->

                                                        </span>
                                                        <span class="won">원</span>
                                                    </span>

                                                </span>
                                                <span>
                                                    
                                                </span>
                                            </p>
                                            
                                        </div>
                                    </div>

                                    <div id="cartPut">
                                        <div class="cart_option cart_type2">
                                            
                                                <div class="inner_option">
                                                    <div class="in_option">
                                                        <div class="list_goods">
                                                            <ul class="list list_nopackage">
                                                                <li class="on">
                                                                    <span class="tit_item">구매수량</span>
                                                                    <div class="option">
                                                                        <span class="count">
                                                                           
                                                                            <input type="number" readonly="readonly"
                                                                                value=1 class="inp">
                                                                            
                                                                        </span>

                                                                    </div>
                                                                </li>
                                                            </ul>
                                                        </div>
                                                        
                                                        <div class="goods_info">
                                                        <textarea style="width: 100%; min-height: 150px;font-size:30px;resize:none;border:none; " ><%=detailList.get(0).getItdt() %></textarea>
                                                        
                                                        </div>
                                                    </div>

                                                    <div class="group_btn off">
                                                        <div class="view_function">
                                                            <button type="button" class="btn btn_save">찜하기</button>
                                                        </div>
                                                        <span class="btn_type1">
                                                        
                                                            <button type="button" class="txt_type" onclick="location.href='/Funding/ptimfor/seller.do?ptImforNum=<%=detailList.get(0).getPtNum() %>'">펀딩하기</button>
                                                        </span>
                                                    </div>
                                                    
                                                </div>
                                            
                                        </div>
                                    </div>
                                </div>
                                <div class="layout-wrapper goods-view-area">
                                

                                    <div class="goods-view-infomation detail_wrap_outer" id="goods-view-infomation">
                                      
                                        <div class="goods-view-infomation-content __active" id="goods-description">
                                            <div class="goods_wrap">
                                                <div class="goods_intro">
                                                <%	int listSize = detailList.size();
                                                for(int i = 1; i <= listSize-1;i++){ %>
                                                    <div class="pic">
                                                        <img src="<%=request.getContextPath()%>/sunju/<%=detailList.get(i).getStreFileNm() %>"
                                                            style="width:1010px; height:671px;">
                                                    </div>
                                                    <%} %>
                                                </div>
                                              
                                                
                                                
                                              
                                    </div>
                                </div>
                            </div>

                        </div>

                    </div>





                </body>

                </html>
        </body>

        </html>