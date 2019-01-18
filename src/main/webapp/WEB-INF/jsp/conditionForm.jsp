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
    <title>Formularz składania podania o warunek</title>
</head>
<body>
<div class="container text-center">
    <div class="order-md-1">
        <h2 class="mb-3">Składanie podania o warunek</h2>

        <c:if test="${condition.conditionRejectionJustification != null && !condition.conditionRejectionJustification.blank}">
            <div class="alert alert-warning">
                <label class="required mr-3 ml-auto mr-auto">
                    Powód odrzucenia podania:
                </label>
                <p>${condition.conditionRejectionJustification}</p>
            </div>
        </c:if>

        <form:form modelAttribute="condition" action="/conditions/save">
            <form:input path="id" cssClass="invisible disabled"/>
            <div class="mb-3">
                <label for="conditionType" class="required mr-3 ml-auto">
                    Wybór typu warunku
                </label>
                <div class="input-group">
                    <form:errors path="conditionType" cssClass="alert-danger"/>
                    <form:select path="conditionType" cssClass="form-control col-md-3 ml-auto mr-auto" required="true">
                        <form:option value="">--- Wybierz typ ---</form:option>
                        <form:option value="SHORT">Krótki</form:option>
                        <form:option value="LONG">Długi</form:option>
                    </form:select>
                </div>
            </div>
            <div class="mb-3">
                <label for="subject" class="required mr-3 ml-auto">
                    Wybór przedmiotu
                </label>
                <div class="input-group">
                    <form:errors path="subject" cssClass="alert-danger"/>
                    <form:select path="subject" cssClass="form-control col-md-3 ml-auto mr-auto" required="true">
                        <form:option value="">--- Wybierz przedmiot ---</form:option>
                        <form:options items="${availableSubjects}" itemLabel="name" itemValue="id"/>
                    </form:select>
                </div>
            </div>
            <div class="mb-3">
                <label for="conditionJustification" class="required mr-3 ml-auto">
                    Uzasadnij prośbę o warunek
                </label>
                <div class="input-group">
                    <form:errors path="conditionJustification" cssClass="alert-danger"/>
                    <form:textarea path="conditionJustification" cssClass="form-control col-md-5 ml-auto mr-auto"
                                   cssErrorClass="form-control is-invalid"
                                   placeholder="Uzasadnienie" required="true" rows="5"/>
                </div>
            </div>
            <button type="submit" class="btn btn-raised btn-success">Wyślij podanie o warunek</button>
        </form:form>

    </div>
</div>
</body>
<c:import url="shared/footer.jsp"/>
</html>
