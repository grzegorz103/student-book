<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Konto utworzone</title>
</head>
<body>
<c:import url="shared/header.jsp">
    <c:param name="name" value="vehicle"/>
</c:import>
<div id="main" class="container">
    ${inf}
</div>
<jsp:include page="shared/footer.jsp"/>
</body>
</html>
