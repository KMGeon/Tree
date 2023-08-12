// 게시판 테이블명 전역으로
var v_tblName = "gesiTB";

var request = {}; // name-space용 빈객체
// 사용자 요청(request)을 담은 객체
// var v_url = location.href; // 문자열이기 때문에 복사(참조x)
// alert(v_url);
// var v_queryString = location.href.split("?")[1];
// alert(v_queryString.split("&")[1].split("=")[1]); // 글쓴이 확인가능

// 개발자는 반복을 줄인다. 값이 하나있는것
request.getParameter = function (p_name) { // name을 넘기면 value를 리턴
    if (location.href.indexOf("?") == -1) return null;
    var v_queryString = location.href.split("?")[1]; // split은 배열로 넘겨준다.
    var v_nvSSang = v_queryString.split("&");
    for (var i = 0; i < v_nvSSang.length; i++) {
        var nv = v_nvSSang[i].split("=");
        if (nv[0] == p_name) {
            return decodeURIComponent(nv[1]).replace("+"," ");
        }
    }
    return null; // 못 찾았다면 null, 꼭 null일 필요는 없음
}

// 같은 name으로 값이 여러개 넘어오는 것 처리할 함수
request.getParameterValues = function (p_name) { // name을 넘기면 value를 리턴
    if (location.href.indexOf("?") == -1) return null;
    var v_queryString = location.href.split("?")[1];
    var v_values = []; // 값을 담을 배열
    var v_nvSSang = v_queryString.split("&");
    for (var i = 0; i < v_nvSSang.length; i++) {
        var nv = v_nvSSang[i].split("=");
        if (nv[0] == p_name) {    // 찾았다면
            v_values.push(nv[1]); // 배열에 담기
        }
    }
    if (!v_values.length) return null;
    return v_values; // 못 찾았다면 null, 꼭 null일 필요는 없음
}

/*  인코딩 함수          디코딩 함수
escape              unescape               아주 예전것
encodeURI           decodeURI              예전것
encodeURIComponent  decodeURIComponent     최근것
짝맞춰 사용하는 것이 중요(일부 글자 인코딩이 서로 다름)
*/
// alert(decodeURIComponent(getParameter("nm_title")));
// alert(decodeURIComponent(getParameter("nm_writer")));
// alert(decodeURIComponent(getParameter("nm_content")));
// 스페이스바는 + 로 표시됨
// alert(getParameter("nm_title"));
// alert(getParameter("nm_writer"));
// alert(getParameter("nm_content"));
// alert(getParameterValues("nm_cat"));
