<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Komputer
  Date: 13.01.2019
  Time: 15:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Wiadomości</title>
</head>
<body>

<jsp:include page="shared/header.jsp"/>
<div class="container">
    <a href="/msg/send">Wyślij nową wiadomość</a>
    <c:if test="${!empty list}">
        <table class="table table-bordered">
            <tr class="bg-success">
                <th>Odbiorca</th>
                <th>Treść</th>
                <th>Data</th>
                <th>Status</th>
                <th></th>
            </tr>

            <c:forEach items="${list}" var="message">
                <tr>
                    <td>${message.receiver.mail}</td>
                    <td>${message.text}</td>
                    <td>${message.date}</td>
                    <td>
                        <c:if test="${message.status.name() == 'MESSAGE_READ'}">Przeczytana</c:if>
                        <c:if test="${message.status.name() == 'MESSAGE_UNREAD'}">Dostarczona</c:if>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </c:if>

    <c:if test="${empty list}">
        Brak wiadomości
    </c:if>
</div>
<jsp:include page="shared/footer.jsp"/>
</body>
</html>
