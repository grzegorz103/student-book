<%--
  Created by IntelliJ IDEA.
  User: Komputer
  Date: 13.01.2019
  Time: 15:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<jsp:include page="shared/header.jsp"/>
<div class="container">
    <h2><a href="/msg/list">Odebrane wiadomości</a></h2>
    <h2><a href="/msg/send">Wyślij nową wiadomość</a></h2>
    <h2><a href="/msg/sent">Wysłane wiadomości</a></h2>
</div>
<jsp:include page="shared/footer.jsp"/>
</body>
</html>
