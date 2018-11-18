<%--
  Created by IntelliJ IDEA.
  User: Komputer
  Date: 13.01.2019
  Time: 21:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Wiadomość</title>
</head>
<body>
<jsp:include page="shared/header.jsp"/>
<div class="container">
    <h2>${sp.title}</h2>
    ${sp.text}
    <br/>
    ${sp.author} ${sp.date}
</div>
<jsp:include page="shared/footer.jsp"/>
</body>
</html>
