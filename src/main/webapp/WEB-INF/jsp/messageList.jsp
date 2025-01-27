<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Komputer
  Date: 11.01.2019
  Time: 02:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Skrzynka odbiorcza</title>
</head>
<body>
<jsp:include page="shared/header.jsp">
    <jsp:param name="name" value="messages"/>
</jsp:include>
<div class="container">
    <a href="/msg/send">Wyślij nową wiadomość</a>
    <br/>
    <c:if test="${!empty list}">
        <table class="table table-bordered">
            <tr class="bg-success">
                <th>Nadawca</th>
                <th>Treść</th>
                <th>Data</th>
                <th>Odpowiedz</th>
                <th>Usuń</th>

            </tr>

            <c:forEach items="${list}" var="message">
                <tr>

                    <td>
                            ${message.sender.mail}
                    </td>

                    <td>${message.text}</td>

                    <td>${message.date}</td>

                    <td><a href="/msg/reply/${message.sender.id}">Odpowiedz</a></td>
                    <td><a href="/msg/delete/${message.id}">Usuń</a></td>
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
