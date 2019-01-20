<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Strona główna</title>
</head>
<body>
<jsp:include page="shared/header.jsp"/>
<div class="container">
<h2>Witamy na stronie głównej</h2>
    <c:if test="${!empty list.content}">
        <table class="table table-hover">

            <tr>
                <th>Tekst</th>
                <th>Data</th>
                <th>Autor</th>
            </tr>
            <c:forEach items="${list.content}" var="info">

                <tr>
                    <td><c:choose>
                        <c:when test="${empty info.title}">Brak danych</c:when>
                        <c:otherwise><a href="/sp/${info.id}">${info.title}</a></c:otherwise>
                    </c:choose></td>
                    <td><c:choose>
                        <c:when test="${empty info.author}">Brak danych</c:when>
                        <c:otherwise>${info.author}</c:otherwise>
                    </c:choose></td>
                    <td><fmt:formatDate value="${info.date}" type="date" timeStyle="medium"/></td>

                </tr>
            </c:forEach>
        </table>
        <nav>
            <c:set var="page" value="${list}" scope="request"/>
            <c:set var="mainUrl" value="/" scope="request"/>
            <c:import url="shared/pagination.jsp"/>
        </nav>
    </c:if>

    <c:if test="${empty list.content}">
        Brak wyników
    </c:if>

</div>
<jsp:include page="shared/footer.jsp"/>
</body>
</html>
