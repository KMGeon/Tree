<%--core 태그 임포트--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--formatting 태그 임포트--%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--jsp 한글 깨짐 방지--%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

<jsp:include page="root.jsp"/>

<script>
    $(document).ready(function () {
        $('#registerBtn').click(function () {
            insertBoard();
        });
    });

    function insertBoard() {
        let title = $('input[name="title"]').val();
        let content = $('textarea[name="content"]').val();
        let writer = $('input[name="writer"]').val();

        let requestData = {
            title: title,
            content: content,
            writer: writer
        };

        $.ajax({
            url: '/api/board',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(requestData),
            success: function(response) {
                console.log('Success:', response);
                if (response === 1) {
                    window.location.href = "/";
                } else {
                    alert("요청이 실패했습니다.");
                }
            },
            error: function(xhr, status, error) {
                console.error('error status:', xhr.status);
                console.error('error message:', xhr.responseText);
            }
        });
    }

</script>

<body>
<table class="table">
    <tr>
        <td>제목</td>
        <td>
            <label>
                <input type="text" name="title" class="form-control"/>
            </label>
        </td>
    </tr>

    <tr>
        <td>내용</td>
        <td>
            <label>
                <textarea rows="7" class="form-control" name="content"></textarea>
            </label>
        </td>
    </tr>

    <tr>
        <td>작성자</td>
        <td>
            <label>
                <input type="text" name="writer" class="form-control"/>
            </label>
        </td>
    </tr>

    <tr>
        <td colspan="2" align="center">
            <button id="registerBtn" class="btn btn-success btn-sm">등록</button>
            <button type="reset" class="btn btn-danger btn-sm">취소</button>
        </td>
    </tr>
</table>
</body>
</html>
