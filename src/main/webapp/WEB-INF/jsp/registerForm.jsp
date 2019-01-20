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
            <label for="mail">Adres e-mail</label>
            <form:input path="mail" cssClass="form-control" required="true" type="email"/>
            <form:errors path="mail" cssStyle="color:red"/>
        </div>

        <div class="form-group">
            <label for="person.name">Hasło</label>
            <form:password path="password" cssClass="form-control" required="true"/>
            <form:errors path="password" cssStyle="color:red"/>
        </div>
        <div class="form-group">
            <label for="person.name">Imię</label>
            <form:input path="person.name" cssClass="form-control" required="true"/>
            <form:errors path="person.name" cssStyle="color:red"/>
        </div>

        <div class="form-group">
            <label for="person.surname">Nazwisko</label>
            <form:input path="person.surname" cssClass="form-control" required="true"/>
            <form:errors path="person.surname" cssStyle="color:red"/>
        </div>

        <div class="form-group">
            <label for="person.pesel">Pesel</label>
            <form:input path="person.pesel" cssClass="form-control" required="true" pattern="^\d{11}$"
                        title="Pesel składa się z 11 cyfr"/>
            <form:errors path="person.pesel" cssStyle="color:red"/>
        </div>
        <div class="form-group">
            <label><form:radiobutton path="person.sex" value="false"/>Kobieta</label><br>
            <label><form:radiobutton path="person.sex" value="true"/>Mężczyzna</label>
        </div>

        <div class="form-group">
            <label for="person.age">Wiek</label>
            <form:input path="person.age" cssClass="form-control" required="true" type="number" min="16" max="110"/>
            <form:errors path="person.age" cssStyle="color:red"/>
        </div>

        <div class="form-group">
            <label for="person.bankAccountNumber">Numer konta bankowego</label>
            <form:input path="person.bankAccountNumber" cssClass="form-control" required="true" pattern="^\d{26}$"
                        title="Numer składa się z 26 cyfr"/>
            <form:errors path="person.bankAccountNumber" cssStyle="color:red"/>
        </div>
        <div class="form-group">
            <label for="person.bankAccountNumber">Wybierz kierunek</label>
            <form:select path="person.course" cssClass="form-control ml-auto mr-auto" required="true">
                <form:option value="">--- Wybierz kierunek ---</form:option>
                <form:options items="${coursesList}" itemLabel="name" itemValue="id"/>
            </form:select>
            <form:errors path="person.course" cssStyle="color:red"/>
        </div>

        <form:input type="hidden" path="person.specChosen" value="false"/>
        <button type="submit" class="btn btn-raised btn-primary">OK</button>

    </form:form>
</div>
<jsp:include page="shared/footer.jsp"/>
</body>
</html>
