<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>Formularz</title>
</head>
<body>
<jsp:include page="shared/header.jsp">
    <jsp:param name="name" value="lessonForm"/>
</jsp:include>

<div id="main" class="container">

    <form:form method="POST" modelAttribute="workshop">
        <h2>Dodajesz nowy warsztat</h2>

        <div class="form-group">
            <label for="name">Podaj nazwę:</label>
            <form:input path="name" class="date" cssClass="form-control"/>
            <form:errors path="name" cssStyle="color:red"/>
        </div>

        <div class="form-group">
            <label for="limit">Podaj limit miejsc:</label>
            <form:input path="limit" class="date" cssClass="form-control"/>
            <form:errors path="limit" cssStyle="color:red"/>
        </div>

        <button type="submit" class="btn btn-raised btn-primary">OK</button>

    </form:form>
</div>

<jsp:include page="shared/footer.jsp"/>
</body>
</html>