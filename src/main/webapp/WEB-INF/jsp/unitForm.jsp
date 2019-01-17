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

    <form:form method="POST" modelAttribute="unit">
        <h2>Dodajesz nowe materiały do warsztatu: ${workshop.name}</h2>

        <div class="form-group">
            <label for="name">Podaj nazwę:</label>
            <form:input path="name" class="date" cssClass="form-control"/>
            <form:errors path="name" cssStyle="color:red"/>
        </div>

        <div class="form-group">
            <label for="description">Podaj opis:</label>
            <form:input path="description" class="date" cssClass="form-control"/>
            <form:errors path="description" cssStyle="color:red"/>
        </div>

        <div class="form-group">
            <label for="file">Podaj link do pliku:</label>
            <form:input path="file" class="date" cssClass="form-control"/>
            <form:errors path="file" cssStyle="color:red"/>
        </div>

        <button type="submit" class="btn btn-raised btn-primary">OK</button>

    </form:form>
</div>

<jsp:include page="shared/footer.jsp"/>
</body>
</html>