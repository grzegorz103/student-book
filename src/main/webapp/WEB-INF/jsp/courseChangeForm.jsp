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
    <title>Formularz składania podania o zmianę kierunku</title>
</head>
<body>
<div class="container text-center">
    <div class="order-md-1">
        <h2 class="mb-3">Składanie podania o zmianę kierunku</h2>

        <c:if test="${courseChange.courseRejectionJustification != null && !courseChange.courseRejectionJustification.blank}">
            <div class="alert alert-warning">
                <label class="required mr-3 ml-auto mr-auto">
                    Powód odrzucenia podania:
                </label>
                <p>${courseChange.courseRejectionJustification}</p>
            </div>
        </c:if>

        <form:form modelAttribute="courseChange" action="/courseChange/save">
            <form:input path="id" cssClass="invisible disabled"/>
            <div class="mb-3">
                <label for="newCourse" class="required mr-3 ml-auto">
                    Wybór nowego kierunku
                </label>
                <div class="input-group">
                    <form:errors path="newCourse" cssClass="alert-danger"/>
                    <form:select path="newCourse" cssClass="form-control col-md-3 ml-auto mr-auto" required="true">
                        <form:option value="">--- Wybierz nowy kierunek ---</form:option>
                        <form:options items="${availableCourses}" itemLabel="name" itemValue="id"/>
                    </form:select>
                </div>
            </div>
            <div class="mb-3">
                <label for="courseChangeJustification" class="required mr-3 ml-auto">
                    Uzasadnij prośbę zmiany kierunku
                </label>
                <div class="input-group">
                    <form:errors path="courseChangeJustification" cssClass="alert-danger"/>
                    <form:textarea path="courseChangeJustification" cssClass="form-control col-md-5 ml-auto mr-auto"
                                   cssErrorClass="form-control is-invalid"
                                   placeholder="Uzasadnienie" required="true" rows="5"/>
                </div>
            </div>
            <button type="submit" class="btn btn-raised btn-success">Wyślij podanie o zmianę kierunku</button>
        </form:form>

    </div>
</div>
</body>
<c:import url="shared/footer.jsp"/>
</html>
