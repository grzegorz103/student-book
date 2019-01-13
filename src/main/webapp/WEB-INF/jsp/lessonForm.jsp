<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>Formularz</title>
</head>
<body>
<jsp:include page="shared/header.jsp">
    <jsp:param name="name" value="opinionForm"/>
</jsp:include>

<div id="main" class="container">

    <form:form method="POST" modelAttribute="les">
        <h2>Dodajesz leckję do przedmiotu: ${subject.name}</h2>

        <div class="form-group">
            <label for="lessonDate">Podaj datę zajęć:</label>
            <form:input path="lessonDate" class="date" cssClass="form-control"/>
            <form:errors path="lessonDate" cssStyle="color:red"/>
        </div>

        <button type="submit" class="btn btn-raised btn-primary">OK</button>

    </form:form>
</div>

<jsp:include page="shared/footer.jsp"/>
</body>
</html>