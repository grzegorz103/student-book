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
        <h2 class="mb-3">Składanie wniosku o stypendium <c:choose><c:when
                test="${scholarship.scholarshipType == 'SOCIAL'}">socjalne</c:when><c:otherwise>naukowe</c:otherwise></c:choose></h2>

        <form:form modelAttribute="scholarship" action="/scholarships/save">
            <form:input path="id" cssClass="invisible disabled"/>
            <form:input path="scholarshipType" cssClass="invisible disabled"/>
            <c:choose>
                <c:when test="${scholarship.scholarshipType.name() == 'SOCIAL'}">
                    <div class="mb-3">
                        <div class="input-group">
                            <label for="peopleNumber" class="required mr-3 ml-auto">Ilość osób w rodzinie (z
                                wnioskodawcą)</label><br>
                            <form:errors path="peopleNumber" cssClass="alert-danger"/>
                            <form:input path="peopleNumber" cssClass="form-control col-md-1 mr-auto"
                                        cssErrorClass="form-control is-invalid"
                                        placeholder="Minimum 1" required="true" min="1"/>
                        </div>
                    </div>
                    <div class="mb-3">
                        <div class="input-group">
                            <label for="allMembersIncome" class="required mr-3 ml-auto">Dochód wszystkich członków
                                rodziny (suma kwot z Urzędu Skarbowego)</label><br>
                            <form:errors path="allMembersIncome" cssClass="alert-danger"/>
                            <form:input path="allMembersIncome" cssClass="form-control col-md-1 mr-auto"
                                        cssErrorClass="form-control is-invalid"
                                        placeholder="0.00 zł" required="true" min="0"/>
                        </div>
                    </div>
                </c:when>
                <c:otherwise>
                    <div class="mb-3">
                        <div class="input-group">
                            <label for="allMembersIncome" class="required mr-3 ml-auto">Podaj swoją średnią ocen za
                                poprzedni semestr</label><br>
                            <form:errors path="averageGrade" cssClass="alert-danger"/>
                            <form:input path="averageGrade" cssClass="form-control col-md-1 mr-auto"
                                        cssErrorClass="form-control is-invalid"
                                        placeholder="Średnia ocen" required="true" min="2.0" max="5.0" step="0.01"
                                        type="number"/>
                        </div>
                    </div>
                </c:otherwise>
            </c:choose>

            <button type="submit" class="btn btn-raised btn-success">Wyślij wniosek o stypendium</button>
        </form:form>

    </div>
</div>
</body>
<c:import url="shared/footer.jsp"/>
</html>
