<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>AJAX 비동기 게시판 연습중</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            loadList();
        });

        function loadList() {
            //서버와 통신 : 게시판 리스트 가져오기
            $.ajax({
                url: "boardList",
                type: "GET",
                dataType: "json",
                success: function (data) { //여기서 data는 controller에 list이다.
                    var listHtml = "<table class='table table-bordered'>";
                    listHtml += "<tr>";
                    listHtml += "<td>번호</td>";
                    listHtml += "<td>제목</td>";
                    listHtml += "<td>작성자</td>";
                    listHtml += "<td>조회수</td>";
                    listHtml += "</tr>";
                    $.each(data, function (index, obj) { // obj={"idx":5,"title":"게시판"~~ }
                        listHtml += "<tr>";
                        listHtml += "<td>" + obj.idx + "</td>";
                        listHtml += "<td>" + obj.title + "</td>";
                        listHtml += "<td>" + obj.writer + "</td>";
                        listHtml += "<td>" + obj.count + "</td>";
                        listHtml += "</tr>";

                    });
                    listHtml += "</table>";
                    listHtml += "<input type='button' value='추가' id='test' onclick='goAdd()'/>";
                    $("#view").html(listHtml);
                }
            });

        }

        function goAdd() {
            alert("안녕");
            $("#view").css("display", "none");
            $("#write").css("display", "block");
        }

        function goList() {
            $("#view").css("display", "block");
            $("#write").css("display", "none");
        }

        function goInsert(){
        // let title = $("#title").val();
        // let content = $("#content").val();
        // let writer = $("#writer").val();
        //폼 안에 데이터를 직렬화 하기
            let fData = $("#frm").serialize();
            $.ajax({
                url:"/boardInsert",
                type:"post",
               data : fData,
                success: function (data) { //여기서 data는 controller에 list이다.
                    if(data==1){
                    location.href="/";
                    }

                    var listHtml = "<table class='table table-bordered'>";
                    listHtml += "<tr>";
                    listHtml += "<td>번호</td>";
                    listHtml += "<td>제목</td>";
                    listHtml += "<td>작성자</td>";
                    listHtml += "<td>조회수</td>";
                    listHtml += "</tr>";
                    $.each(data, function (index, obj) { // obj={"idx":5,"title":"게시판"~~ }
                        listHtml += "<tr>";
                        listHtml += "<td>" + obj.idx + "</td>";
                        listHtml += "<td>" + obj.title + "</td>";
                        listHtml += "<td>" + obj.writer + "</td>";
                        listHtml += "<td>" + obj.count + "</td>";
                        listHtml += "</tr>";

                    });
                    listHtml += "</table>";
                    listHtml += "<input type='button' value='추가' id='test' onclick='goAdd()'/>";
                    $("#view").html(listHtml);
                    $("#view").css("display", "none");
                    $("#write").css("display", "block");
                }

            })
        }
    </script>
</head>
<body>

<div class="container">
    <h2>AJAX 비동기 게시판 연습중</h2>
    <div class="panel panel-default">
        <div class="panel-heading">BOARD</div>
        <div class="panel-body" id="view">Panel Content</div>
        <div class="panel-body" id="write" style="display: none">
            <form id="frm">
                <table class="table">
                    <tr>
                        <td>제목</td>
                        <td><input type="text" id="title" name="title" class="form-control"/></td>
                    </tr>
                    <tr>
                        <td>내용</td>
                        <td><textarea rows="7" class="form-control" id="content" name="content"></textarea></td>
                    </tr>
                    <tr>
                        <td>작성자</td>
                        <td><input type="text" id="writer" name="writer" class="form-control"/></td>
                    </tr>
                    <tr>

                    </tr>
                </table>
                <td colspan="2" align="center">
                    <button type="button" class="btn btn-success btn-sm" onclick="goInsert()">등록</button>
                    <button type="reset" class="btn btn-warning btn-sm" id="fclear">취소</button>
                    <button type="button" class="btn btn-info btn-sm" onclick="goList()">리스트</button>
                </td>
            </form>
        </div>

        <div class="panel-footer">하하하</div>
    </div>
</div>

</body>
</html>