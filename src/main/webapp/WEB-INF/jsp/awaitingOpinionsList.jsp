<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="jap" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Lista opinii</title>
</head>
<body>

<jsp:include page="shared/header.jsp">
    <jsp:param name="name" value="opinions"/>
</jsp:include>
<div class="container">

    <c:if test="${!empty list}">

        <security:authorize access="hasRole('DEAN')">

            <table class="table table-bordered">
                <tr class="bg-success">
                    <th>Opinia</th>
                    <th>Autor</th>
                    <th>Wykładowca</th>
                    <th>Przedmiot</th>
                    <th>Status</th>
                </tr>

            <c:forEach items="${list}" var="opn">

                        <tr>

                                <td>
                                    <c:choose>
                                        <c:when test="${empty opn.opinion}">Brak danych</c:when>
                                        <c:otherwise>${opn.opinion}</c:otherwise>
                                    </c:choose>
                                </td>

                                <td>
                                    <c:choose>
                                        <c:when test="${empty opn.student}">Brak danych</c:when>
                                        <c:otherwise>${opn.student.name} ${opn.student.surname}</c:otherwise>
                                    </c:choose>
                                </td>

                                <td>
                                    <c:choose>
                                        <c:when test="${empty opn.instructor}">Brak danych</c:when>
                                        <c:otherwise>${opn.instructor.name} ${opn.student.surname}</c:otherwise>
                                    </c:choose>
                                </td>

                                <td>
                                    <c:choose>
                                        <c:when test="${empty opn.subject.name}">Brak danych</c:when>
                                        <c:otherwise>${opn.subject.name}</c:otherwise>
                                    </c:choose>
                                </td>

                                <td>
                                    <c:choose>
                                        <c:when test="${empty opn.status}">Brak danych</c:when>
                                        <c:otherwise>
                                            Oczekująca:<br>
                                            <a class="btn btn-raised btn-success" href="/instructors/${opn.instructor.id}/opinions/${opn.id}/status/1">Akceptuj</a>
                                            <a class="btn btn-raised btn-danger" href="/instructors/${opn.instructor.id}/opinions/${opn.id}/status/2">Odrzuć</a>
                                        </c:otherwise>
                                    </c:choose>

                                </td>

                        </tr>
                </c:forEach>
            </table>

        </security:authorize>

    </c:if>

    <c:if test="${empty list}">
        Brak oczekujących opinii w bazie.
    </c:if>

</div>
<jsp:include page="shared/footer.jsp"/>
</body>
</html>