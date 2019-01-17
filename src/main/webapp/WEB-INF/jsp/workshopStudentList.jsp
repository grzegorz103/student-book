<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="jap" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Lista członków</title>
</head>
<body>

<jsp:include page="shared/header.jsp">
    <jsp:param name="name" value="workshopList"/>
</jsp:include>
<div class="container">
<h2>Lista członków warsztatu: ${workshop.name}</h2>
    <c:if test="${!empty workshop.students}">
        <table class="table table-bordered">
            <tr class="bg-success">
                <th>Imię</th>
                <th>Nazwisko</th>
                <th>Kierunek</th>
                <th>Semestr</th>
                <th>Usuń</th>

            </tr>

            <c:forEach items="${workshop.students}" var="student">
                    <tr>

                        <td>
                            <c:choose>
                                <c:when test="${empty student.name}">Brak danych</c:when>
                                <c:otherwise>${student.name}</c:otherwise>
                            </c:choose>
                        </td>

                        <td>
                            <c:choose>
                                <c:when test="${empty student.surname}">Brak danych</c:when>
                                <c:otherwise>${student.surname}</c:otherwise>
                            </c:choose>
                        </td>

                        <td>
                            <c:choose>
                                <c:when test="${empty student.course}">Brak danych</c:when>
                                <c:otherwise>${student.course.name}</c:otherwise>
                            </c:choose>
                        </td>

                        <td>
                            <c:choose>
                                <c:when test="${empty student.semester}">Brak danych</c:when>
                                <c:otherwise>${student.semester}</c:otherwise>
                            </c:choose>
                        </td>

                        <td>
                            <a class="btn btn-raised btn-danger" href="/workshops/${workshop.id}/exit/${student.id}">Usuń</a>
                        </td>

                    </tr>
            </c:forEach>

        </table>


    </c:if>

    <c:if test="${empty workshop.students}">
        Na ten warsztat nikt się jeszcze nie zapisał.
    </c:if>

</div>
<jsp:include page="shared/footer.jsp"/>
</body>
</html>
