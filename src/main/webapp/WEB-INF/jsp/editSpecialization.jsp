<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Komputer
  Date: 13.01.2019
  Time: 16:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<jsp:include page="shared/header.jsp"/>
</di class="container">
    Wybór specjalizacji jest aktualnie <c:if test="${state}">włączone</c:if><c:if test="${!state}">wylaczone</c:if>
    <br/>Aby zmienić naciśnij przycisk<br/>
<input type="button" onclick="location.href='/sp/edit/state'" class="btn btn-raised btn-primary">Zmień</input>
</div>
<jsp:include page="shared/footer.jsp"/>
</body>
</html>
