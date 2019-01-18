<%--
  Created by IntelliJ IDEA.
  User: Seko
  Date: 17.11.2018
  Time: 13:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<c:import url="shared/header.jsp">
    <c:param name="name" value="condition"/>
</c:import>
<head>
    <title>Szczegóły podania o warunek</title>
</head>
<body>
<div class="container text-center">
    <div class="order-md-1">
        <h2 class="mb-3">Szczegóły podania o warunek</h2>

        <form:form modelAttribute="condition" action="/conditions/list/reject">
            <form:input path="id" cssClass="invisible disabled"/>
            <h3>Dane studenta</h3>
            <div class="mb-3">
                <label>Identyfikator: ${condition.student.id}</label><br>
            </div>
            <div class="mb-3">
                <label>Imię i
                    nazwisko: ${condition.student.name} ${condition.student.surname}</label><br>
            </div>
            <div class="mb-3">
                <label>Pesel: ${condition.student.pesel}</label><br>
            </div>
            <div class="mb-3">
                <label>Semestr: ${condition.student.semester}</label><br>
            </div>
            <div class="mb-3">
                <label>Kierunek: ${condition.student.course.name}</label><br>
            </div>
            <div class="mb-3">
                <label>Uzasadnienie podania:</label><br>
                <p>${condition.conditionJustification}</p>
            </div>
            <hr>
            <div class="mb-3">
                <label>
                    Data złożenia podania: <fmt:formatDate value="${condition.submittingDate}"
                                                           pattern="yyyy-MM-dd HH:mm:ss"/>
                </label><br>
            </div>
            <div class="mb-3">
                <label for="conditionRejectionJustification" class="required mr-3 ml-auto">
                    Powód odrzucenia podania
                </label>
                <div class="input-group">
                    <form:errors path="conditionRejectionJustification" cssClass="alert-danger"/>
                    <form:textarea path="conditionRejectionJustification"
                                   cssClass="form-control col-md-4 ml-auto mr-auto"
                                   cssErrorClass="form-control is-invalid"
                                   placeholder="Powód" required="true" rows="5"/>
                </div>
            </div>
            <a href="/conditions/list/accept/${condition.id}"
               class="btn btn-raised btn-warning">Przyznaj warunek</a>
            <button type="submit" class="btn btn-raised btn-success">Odrzuć warunek</button>

        </form:form>
    </div>
</div>
</body>
<c:import url="shared/footer.jsp"/>
</html>
