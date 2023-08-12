<%@page import="kr.or.funding.member.VO.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

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
    <script defer src="js/common.js"></script>
    <script defer src="js/contents.js"></script>
    <script defer src="js/visual.js"></script>
    <link rel="shortcut icon" href="./img/favicon.png">
    <link rel="icon" href="/Funding/view/img/favicon.png">
    <link rel="stylesheet" href="/Funding/view/css/admin_common.css">
    <link rel="stylesheet" href="/Funding/view/css/admin_page_order_manage.css">
    <script src="/Funding/view/js/admin_common.js"></script>
    <script src="/Funding/view/js/admin_order_manage.js"></script>
     <style>
    .ptnm {
            font-size: 20px;
            line-height: 28px;
            padding-top: 3%;
            padding-left: 3%;
        }
        #ptnmtitle {
            font-size: 19px;
            border: none;
            background-color: rgb(240, 240, 240);
        }
        #hhh {
            width: 50px;
            font-size: 20px;
            float: right;
            background-color: rgb(158, 153, 153);
            border: none;
        }
        hr {
            border: 1px solid black;
            width: 500px;
            /* display: inline-block; */
            margin-top: 5%;
            margin-bottom: 5%;
        }
        .ptfile {
            font-size: 19px;
            padding-left: 3%;
        }
        #id_file {
            font-size: 15px;
            padding-left: 5%;
        }
        .pyqty {
            display: inline-block;
            font-size: 19px;
            padding-left: 3%;
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
                        <a href="/Funding/view//notice/notice_seller.jsp" class="link">
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

        <!-- 공지사항 넣기 -->
        <div id="main">
            <div id="content">
                <div class="page_aticle aticle_type2" style="boarder: 1px solid black">
                    <div id="snb" class="snb_my">
                        <h2 class="tit_snb">판매자 메뉴</h2>
                        <div class="inner_snb">
                            <ul class="list_menu">
                                <li>
                                    <a href="notice_seller.html">공지사항</a>
                                </li>
                                <li>
                                    <a href="faq.html">자주묻는질문</a>
                                </li>
                                <li>
                                    <a href="one_iq.html">1:1문의</a>
                                </li>
                               

                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
         <div style="border:1px solid black; position: absolute; height:700px; width:750px; top:200px;left:35%">
                 <div id="member_page">
                <div id="reg_fd">상품등록</div>
                <form action="/Funding/ptimfor/insertptimfor.do" method="POST" enctype="multipart/form-data">
                    <div class="ptnm">
                        회원 번호 :<input type="text" id="ptnmtitle" name="mbsNum" autofocus placeholder="회원 번호 3을 입력하시오" value="<%=memVo.getMbsNum()%>"readOnly><br><br>
                        상품 이름 :<input type="text" id="ptnmtitle" name="ptNm" autofocus placeholder="상품 이름을 작성하시오" required><br><br>
                       
                        수     량 :<input type="text" id="ptnmtitle" name="ptCgy" autofocus placeholder="수량을 작성하시오" required><br><br>
                        가     격 :<input type="text" id="ptnmtitle" name="ptPrc" autofocus placeholder="가격을 작성하시오" required><br><br>
                        시작일 :<input type="date" id="ptnmtitle" name="stDt" autofocus placeholder="상품 이름을 작성하시오" required><br><br>
                        종료일 :<input type="date" id="ptnmtitle" name="edDt" autofocus placeholder="상품 이름을 작성하시오" required>
                    </div>
                <br>
                <div class="ptfile">상품 사진
                    <input type="file" id="id_file" name="atchFileId" multiple accept=".jpg,.png,.jpeg,.gif">
                </div>
                <div>
                <br><br>
                    <h4 class="pyqty">상품 카테고리</h4>
                    <select name="pyQty">
                        <option value="1">테크·가전</option>
                        <option value="2">패션·잡화</option>
                        <option value="3">뷰티</option>
                        <option value="4">푸드</option>
                        <option value="5">홈·리빙</option>
                        <option value="6">캐릭터·굿즈</option>
                        <option value="7">반려동물</option>
                    </select>
                </div>
            </div>
            <br><br>
            <div id="middle">
                <div id="info" style="font-size:19px ;padding-left:3%  ">상품 소개글</div>
    
            </div>
                <div id="foot" style="padding-left:3%">
                        <textarea name="itdt" id="text_content" placeholder="소개글을 작성하시오"cols="100" rows="13" style="resize: none;  " required ></textarea>
                        <input type="submit" id="hhh" value="등록" >
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
                                <img src="https://res.kurly.com/pc/ico/1810/ico_instagram.png" alt="마켓컬리 인스타그램 바로가기">
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
</body>

</html>