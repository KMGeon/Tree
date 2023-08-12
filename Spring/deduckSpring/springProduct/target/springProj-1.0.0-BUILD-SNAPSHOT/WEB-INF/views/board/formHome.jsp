<?xml version="1.0" encoding="UTF-8" ?>
<jsp:directive.page contentType="text/html; charset=UTF-8"
                    pageEncoding="UTF-8" session="false"/>
<head>
    <title>Insert title here</title>
</head>
<body>

<form action="/board/register2">
    <INPUT type="text" name="name" value="${name}"/> <INPUT type="submit"
                                                            value="register"/>
</form>


<form action="/board/register2" method="post">
    <INPUT type="text" name="name" value="${name}"/> <INPUT type="submit"
                                                            value="register"/>
</form>

<A href="/board/get?register">Register</A> <A href="/board/get?modify">Modify</A>

<form action="/board/post" method="post">
    <INPUT type="text" name="name" value="${name}"/>
    <INPUT type="submit" value="register"/>
</form>

<form action="/board/post" method="get">
    <INPUT type="text" name="modify" value="${name}"/>
    <INPUT type="submit" value="register"/>
</form>


</body>
</html>