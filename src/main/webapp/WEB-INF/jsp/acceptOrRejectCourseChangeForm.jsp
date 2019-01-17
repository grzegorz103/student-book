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
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<c:import url="shared/header.jsp">
    <c:param name="name" value="courseChange"/>
</c:import>
<head>
    <title>Szczegóły podania o zmianę kierunku</title>
</head>
<body>
<div class="container text-center">
    <div class="order-md-1">
        <h2 class="mb-3">Szczegóły podania o zmianę kierunku</h2>

        <form:form modelAttribute="courseChange" action="/courseChange/list/reject">
            <form:input path="id" cssClass="invisible disabled"/>
            <h3>Dane studenta</h3>
            <div class="mb-3">
                <label>Identyfikator: ${courseChange.student.id}</label><br>
            </div>
            <div class="mb-3">
                <label>Imię i
                    nazwisko: ${courseChange.student.name} ${courseChange.student.surname}</label><br>
            </div>
            <div class="mb-3">
                <label>Pesel: ${courseChange.student.pesel}</label><br>
            </div>
            <div class="mb-3">
                <label>Semestr: ${courseChange.student.semester}</label><br>
            </div>
            <div class="mb-3">
                <label>Aktualny kierunek: ${courseChange.student.course.name}</label><br>
            </div>
            <div class="mb-3">
                <label>Nowy kierunek: ${courseChange.newCourse.name}</label><br>
            </div>
            <div class="mb-3">
                <label>Uzasadnienie podania:</label><br>
                <p>${courseChange.courseChangeJustification}</p>
            </div>
            <hr>
            <div class="mb-3">
                <label>
                    Data złożenia podania: <fmt:formatDate value="${courseChange.submittingDate}"
                                                           pattern="yyyy-MM-dd HH:mm:ss"/>
                </label><br>
            </div>
            <c:if test="${courseChange.courseRejectionJustification != null && !courseChange.courseRejectionJustification.blank}">
                <div class="alert alert-warning">
                    <label class="required mr-3 ml-auto mr-auto">
                        Poprzedni powód odrzucenia podania:
                    </label>
                    <p>${courseChange.courseRejectionJustification}</p>
                </div>
            </c:if>
            <div class="mb-3">
                <label for="courseRejectionJustification" class="required mr-3 ml-auto">
                    Powód odrzucenia podania
                </label>
                <div class="input-group">
                    <form:errors path="courseRejectionJustification" cssClass="alert-danger"/>
                    <form:textarea path="courseRejectionJustification" cssClass="form-control col-md-4 ml-auto mr-auto"
                                   cssErrorClass="form-control is-invalid"
                                   placeholder="Powód" required="true" rows="5"/>
                </div>
            </div>
            <a href="/courseChange/list/accept/${courseChange.id}"
               class="btn btn-raised btn-warning">Zatwierdź zmianę kierunku</a>
            <button type="submit" class="btn btn-raised btn-success">Odrzuć zmianę kierunku</button>

        </form:form>
    </div>
</div>
</body>
<c:import url="shared/footer.jsp"/>
</html>
