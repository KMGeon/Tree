<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<jsp:include page="root.jsp"/>
<head>
    <script>
        $(document).ready(function(){
            var eventSource; // EventSource 변수를 전역 변수로 선언

            // SSE 연결 함수
            function connectSSE() {
                eventSource = new EventSource('/subscribe');

                eventSource.addEventListener("open", function (event) {
                    console.log("connection open");
                });

                eventSource.addEventListener("board", function (event) {
                    console.log(event.data);
                    handleReloadBoard();
                });

                // SSE 에러 핸들러
                eventSource.onerror = function(error) {
                    console.log('SSE Error: ' + error);
                    console.log('SSE Error: ' + error.toString());
                    console.log('SSE Error: ' + event.target.readyState);

                    if (event.target.readyState == EventSource.CLOSED) {
                        console.log("event close");
                    }

                    // 에러가 발생하면 연결을 닫고 다시 연결 시도
                    eventSource.close();
                    reconnectSSE();
                };
            }

            // SSE 연결 재시도 함수
            function reconnectSSE() {
                console.log("Attempting to reconnect...");
                setTimeout(function() {
                    connectSSE(); // 다시 연결 시도
                }, 3000); // 3초 후에 재시도
            }

            // 초기 SSE 연결 시도
            connectSSE();
        });



        function handleReloadBoard() {
            // 이전 데이터를 비워줌
            $('table tbody').empty();

            $.ajax({
                url: '/api/board',
                type: 'GET',
                success: function(data) {
                    $.each(data, function(index, item) {
                        var row = '<tr>' +
                            '<td><a href="/content/' + item.id + '">' + item.id + '</a></td>'+
                            '<td>' + item.title + '</td>' +
                            '<td>' + item.content + '</td>' +
                            '<td>' + item.writer + '</td>' +
                            '<td>' + item.count + '</td>' +
                            '</tr>';
                        $('table tbody').append(row);
                    });
                },
                error: function(error) {
                    console.log('Error: ' + error);
                }
            });
        }

    </script>
</head>
<body>
<table class="table table-bordered">
    <thead>
    <tr>
        <th>번호</th>
        <th>제목</th>
        <th>내용</th>
        <th>작가</th>
        <th>조회수</th>
    </tr>
    </thead>
    <tbody>
    </tbody>
</table>

<a href="/insert" class="btn btn-primary">글쓰기</a>

<div>
    <div class="panel-footer"></div>
</div>
</body>
</html>
