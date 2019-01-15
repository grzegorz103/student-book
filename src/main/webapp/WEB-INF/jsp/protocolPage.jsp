<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Komputer
  Date: 15.01.2019
  Time: 14:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<jsp:include page="shared/header.jsp"/>
<div class="container">
    <c:if test="${!empty list}">
        <table class="table table-bordered">
            <tr class="bg-success">
                <th>Przedmiot</th>
                <th>Pierwszy termin egzaminu</th>
                <th>Drugi termin egzaminu</th>
                <th>Status</th>
                <th>Oceny</th>
                <th>Zapisz</th>
            </tr>

            <c:forEach items="${list}" var="protocol">
                <tr>
                <form:form action="/protocol/edit?id=${protocol.id}" modelAttribute="protForm">
                    <td>${protocol.subject.name}</td>

                    <td><c:if test="${protocol.status == 'PROTOCOLE_CLOSED'}">---</c:if>
                        <c:if test="${protocol.status == 'PROTOCOLE_OPEN'}">
                            <form:input path="firstTermin" value="${protocol.firstTermin}"/>
                        </c:if>
                    </td>

                    <td><c:if test="${protocol.status == 'PROTOCOLE_CLOSED'}">---</c:if>
                        <c:if test="${protocol.status == 'PROTOCOLE_OPEN'}">
                            <form:input path="secondTermin" value="${protocol.secondTermin}"/>
                        </c:if>
                    </td>
                    <td></td>
                    <td><c:if test="${protocol.status == 'PROTOCOLE_OPEN'}">
                        <button type="submit" class="btn btn-raised btn-primary">Do ocen</button>
                    </c:if></td>
                    <td>
                        <c:if test="${protocol.status == 'PROTOCOLE_OPEN'}">
                            <button type="submit" class="btn btn-raised btn-primary">Zapisz</button>
                        </c:if>
                    </td>
                    </tr>
                </form:form>
            </c:forEach>
        </table>
    </c:if>

    <c:if test="${empty list}">
        Brak
    </c:if>
</div>
<jsp:include page="shared/footer.jsp"/>
</body>
</html>
