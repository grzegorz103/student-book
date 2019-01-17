<%--
  Created by IntelliJ IDEA.
  User: Seko
  Date: 17.11.2018
  Time: 13:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<html>
<c:import url="shared/header.jsp">
    <c:param name="name" value="scholarships"/>
</c:import>
<head>
    <title>Formularz akceptacji stypendium</title>
</head>
<body>
<div class="container text-center">
    <div class="order-md-1">
        <h2 class="mb-3">Akceptacja stypendium</h2>

        <form:form modelAttribute="scholarship" action="/scholarships/list/accept">
            <form:input path="id" cssClass="invisible disabled"/>
            <h3>Dane studenta</h3>
            <div class="mb-3">
                <label>Identyfikator: ${scholarship.student.id}</label><br>
            </div>
            <div class="mb-3">
                <label>Imię i
                    nazwisko: ${scholarship.student.name} ${scholarship.student.surname}</label><br>
            </div>
            <div class="mb-3">
                <label>Pesel: ${scholarship.student.pesel}</label><br>
            </div>
            <div class="mb-3">
                <label>Semestr: ${scholarship.student.semester}</label><br>
            </div>
            <div class="mb-3">
                <label>Numer konta bankowego: ${scholarship.student.bankAccountNumber}</label><br>
            </div>
            <hr>
            <c:choose>
                <c:when test="${scholarship.scholarshipType.name() == 'SOCIAL'}">
                    <h3>Dane stypendium socjalnego</h3>
                    <div class="mb-3">
                        <label>Ilość osób w rodzinie (z wnioskodawcą): ${scholarship.peopleNumber}</label><br>
                    </div>
                    <div class="mb-3">
                        <label>Dochód wszystkich członków rodziny: ${scholarship.allMembersIncome}</label><br>
                    </div>
                    <div class="mb-3">
                        <label>Dochód na jednego członka
                            rodziny: ${scholarship.allMembersIncome / scholarship.peopleNumber}
                        </label><br>
                    </div>
                </c:when>
                <c:otherwise>
                    <h3>Dane stypendium naukowego</h3>
                    <div class="mb-3">
                        <label>Średnia ocen podana przez studenta: ${scholarship.averageGrade}</label><br>
                        <label>Średnia ocen wyliczona przez system: ${avgGrade}</label><br>
                    </div>
                </c:otherwise>
            </c:choose>
            <hr>
            <div class="mb-3">
                <label>Data złożenia wniosku: ${scholarship.submittingDate}</label><br>
            </div>
            <div class="mb-3">
                <div class="input-group">
                    <label for="amount" class="required mr-3 ml-auto">Przyznana kwota</label><br>
                    <form:errors path="amount" cssClass="alert-danger"/>
                    <form:input path="amount" cssClass="form-control col-md-1 mr-auto"
                                cssErrorClass="form-control is-invalid"
                                placeholder="0.00 zł" required="true" min="1" value=""/>
                </div>
            </div>
            <button type="submit" class="btn btn-raised btn-success">Przyznaj stypendium</button>
            <a href="/scholarships/list/reject/${scholarship.id}"
               class="btn btn-raised btn-warning">Odrzuć stypendium</a>
        </form:form>

    </div>
</div>
</body>
<c:import url="shared/footer.jsp"/>
</html>
