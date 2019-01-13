<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Komputer
  Date: 13.01.2019
  Time: 18:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Rejestracja</title>
</head>
<body>
<jsp:include page="shared/header.jsp"/>
<div class="container">
    <form:form method="POST" modelAttribute="user">
        <h2>Rejestracja</h2>

        <div class="form-group">
            <label for="mail">Mail</label>
            <form:input path="mail" cssClass="form-control"/>
            <form:errors path="mail" cssStyle="color:red"/>
        </div>

        <div class="form-group">
            <label for="person.name">Hasło</label>
            <form:input path="password" cssClass="form-control"/>
            <form:errors path="password" cssStyle="color:red"/>
        </div>
        <div class="form-group">
            <label for="person.name">Imię</label>
            <form:input path="person.name" cssClass="form-control"/>
            <form:errors path="person.name" cssStyle="color:red"/>
        </div>

        <div class="form-group">
            <label for="person.surname">Nazwisko</label>
            <form:input path="person.surname" cssClass="form-control"/>
            <form:errors path="person.surname" cssStyle="color:red"/>
        </div>

        <div class="form-group">
            <label for="person.pesel">Pesel</label>
            <form:input path="person.pesel" cssClass="form-control"/>
            <form:errors path="person.pesel" cssStyle="color:red"/>
        </div>
        <div class="form-group">
            <form:radiobutton path="person.sex" value="true"/>Mężczyzna
        </div>
        <div class="form-group">
            <form:radiobutton path="person.sex" value="false"/>Kobieta
        </div>

        <div class="form-group">
            <label for="person.age">Wiek</label>
            <form:input path="person.age" cssClass="form-control"/>
            <form:errors path="person.age" cssStyle="color:red"/>
        </div>

        <form:input type="hidden" path="person.specChosen" value="false"/>
        <button type="submit" class="btn btn-raised btn-primary">OK</button>

    </form:form>
</div>
<jsp:include page="shared/footer.jsp"/>
</body>
</html>
