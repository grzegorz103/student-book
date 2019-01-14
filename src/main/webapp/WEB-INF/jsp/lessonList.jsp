<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="jap" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Lista lekcji</title>
</head>
<body>

<jsp:include page="shared/header.jsp">
    <jsp:param name="name" value="lessonList"/>
</jsp:include>
<div class="container">

    <h2>Lista lekcji z przedmiotu: ${subject.name}</h2>

    <c:if test="${!empty list}">
        <table class="table table-bordered">
            <tr class="bg-success">
                <th>Data</th>
                <th>Lista obecności</th>

            </tr>

            <c:forEach items="${list}" var="lesson">
                    <tr>

                        <td>
                            <c:choose>
                                <c:when test="${empty lesson.lessonDate}">Brak danych</c:when>
                                <c:otherwise>${lesson.lessonDate}</c:otherwise>
                            </c:choose>
                        </td>

                        <td><a class="btn btn-raised btn-info" href="/subjects/${subject.id}/lessons/${lesson.id}">Lista obecności</a></td>

                    </tr>
            </c:forEach>

        </table>


    </c:if>

    <c:if test="${empty list}">
        Brak lekcji w bazie
    </c:if>

    <security:authorize access="hasAnyRole('DEAN', 'INSTRUCTOR')">
        <br><a class="btn btn-raised btn-success" href="/subjects/${subject.id}/lessons/add">Dodaj lekcję</a><br><br><br>
    </security:authorize>

</div>
<jsp:include page="shared/footer.jsp"/>
</body>
</html>
