<%--
  Created by IntelliJ IDEA.
  User: Komputer
  Date: 18.01.2019
  Time: 15:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Informacja</title>
</head>
<body>
<jsp:include page="shared/header.jsp">
    <jsp:param name="name" value="messages"/>
</jsp:include>
<div class="container">
    <br/>
    ${info}
</div>
<jsp:include page="shared/footer.jsp"/>
</body>
</html>
