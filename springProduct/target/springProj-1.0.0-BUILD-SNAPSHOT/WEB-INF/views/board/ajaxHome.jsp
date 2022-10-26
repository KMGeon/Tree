<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<html>
<head>
    <script src="/resources/js/jquery.min.js"></script>
    <title>Title</title>
    <script type="text/javascript">
        //document가 로딩이 완료 되면 실행
        //아작 났어요 a,u,c,d,t,c

        $(function () {
            $("button").on("click", function () {
                let boardNumber = "7";
                let boardObject = {
                    "boardNo": "7",
                    "title": "수리남",
                    "content": "스릴러",
                    "writer": "김무건"
                };
                console.log("boardObject: " + JSON.stringify(boardObject))
                console.log(boardObject.title)
                //비동기 통신
                //ajax(ucdt(su))
                $.ajax({
                    url: "/board/" + boardNumber,
                    hearder: {
                        "X-HTTP-Method-Override": "PUT"
                    },
                    contentType: "application/json;charset=utf-8",
                    data: JSON.stringify(boardObject),
                    type: "put",
                    success: function (result) {
                        console.log("result" + result);
                    }
                });
            });//끝

            $("input[name='btnAccept']").on("click", function () {
                let boardNo = $("boardNO").val();//7

                console.log("boardNo: " + boardNo);

                $.get("/board/" + boardNo, function (data) {
                    //data : json  , get 방식으로 /board/7 uri를 요청하면
                    //json 데이터로 비동기 응답이 되겠구나
                    console.log("data" + JSON.stringify(data));
                });
            });
            $("input[name='btnJson']").on("click", function () {
                let boardNo = $("#boardNO").val();

                console.log("boaardNo: " + boardNo);
                let data = {
                    "boardNo": boardNo
                };

                //아작났어요 pc 다 탔어
                $.ajax({
                    url: "/board/getBook",
                    contentType: "application/json;charset:utf-8",
                    data: JSON.stringify(data),
                    type: "post",
                    success: function (result) {
                        console.log(JSON.stringify(result))
                    }
                });
            });
        });

        /*
        json은 content타입이 필요하지만 ajax는 pathvaliable로 보내서
        필요없다.
         */
    </script>
</head>
<body>
<button type="button">식사는 잡쉈어?</button>
<h2>7.Accept 매핑</h2>
<p><input type="text" name="boardNo" id="boardNO" value="7"/></p>
<p><input type="button" name="btnAccept" value="실행"></p>
<p><input type="button" name="btnJson" value="ajax실행_중요"></p>
</body>
</html>
