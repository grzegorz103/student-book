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
    <c:param name="name" value="bankAccount"/>
</c:import>
<head>
    <title>Zarządzanie numerem konta</title>
</head>
<body>
<div class="container text-center">
    <div class="order-md-1">
        <h2 class="mb-3">Zarządzanie numerem konta bankowego</h2>
        <c:if test="${saved != null}">
            <c:choose>
                <c:when test="${saved}">
                    <label class="alert alert-success">Zmieniono numer konta</label>
                </c:when>
                <c:otherwise>
                    <label class="alert alert-danger">Nie można zmienić numeru konta</label>
                </c:otherwise>
            </c:choose>
        </c:if>

        <form:form modelAttribute="student" action="/bankAccountNumber/save">
            <form:input path="id" cssClass="invisible disabled"/>
            <div class="mb-3">
                <div class="input-group">
                    <label for="bankAccountNumber" class="required mr-3 ml-auto">Numer konta bankowego</label><br>
                    <form:errors path="bankAccountNumber" cssClass="alert-danger"/>
                    <form:input path="bankAccountNumber" cssClass="form-control col-md-3 mr-auto"
                                cssErrorClass="form-control is-invalid"
                                placeholder="Numer konta bankowego" required="true" min="1"/>
                </div>
            </div>
            <button type="submit" class="btn btn-raised btn-success">Zmień numer konta</button>
        </form:form>

    </div>
</div>
</body>
<c:import url="shared/footer.jsp"/>
</html>
