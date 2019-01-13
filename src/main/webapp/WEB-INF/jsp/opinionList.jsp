<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="jap" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Lista pojazdów</title>
</head>
<body>

<jsp:include page="shared/header.jsp">
    <jsp:param name="name" value="opinionList"/>
</jsp:include>
<div class="container">

    <c:if test="${!empty list}">

        <security:authorize access="hasRole('USER')">

            <table class="table table-bordered">
                <tr class="bg-success">
                    <th>Opinia</th>
                    <th>Przedmiot</th>
                </tr>

                <c:forEach items="${list}" var="opn">
                    <c:forEach items="${opn.instructor.subject}" var="subjects">
                        <c:if test="${subjects.id == opn.subject.id}">
                            <c:if test="${opn.status == 'ACCEPTED'}">
                                <tr>

                                    <td>
                                        <c:choose>
                                            <c:when test="${empty opn.opinion}">Brak danych</c:when>
                                            <c:otherwise>${opn.opinion}</c:otherwise>
                                        </c:choose>
                                    </td>

                                    <td>
                                        <c:choose>
                                            <c:when test="${empty subjects.name}">Brak danych</c:when>
                                            <c:otherwise>${subjects.name}</c:otherwise>
                                        </c:choose>
                                    </td>

                                </tr>
                            </c:if>
                        </c:if>

                    </c:forEach>
                </c:forEach>
            </table>

        </security:authorize>

        <security:authorize access="hasAnyRole('ADMIN', 'INSTRUCTOR')">

            <table class="table table-bordered">
                <tr class="bg-success">
                    <th>Opinia</th>
                    <th>Przedmiot</th>
                    <th>Status</th>
                </tr>

                <c:forEach items="${list}" var="opn">
                    <c:forEach items="${opn.instructor.subject}" var="subjects">
                        <c:if test="${subjects.id == opn.subject.id}">
                            <tr>

                                <td>
                                    <c:choose>
                                        <c:when test="${empty opn.opinion}">Brak danych</c:when>
                                        <c:otherwise>${opn.opinion}</c:otherwise>
                                    </c:choose>
                                </td>

                                <td>
                                    <c:choose>
                                        <c:when test="${empty subjects.name}">Brak danych</c:when>
                                        <c:otherwise>${subjects.name}</c:otherwise>
                                    </c:choose>
                                </td>

                                <td>
                                    <c:choose>
                                        <c:when test="${empty opn.status}">Brak danych</c:when>
                                        <c:when test="${opn.status == 'AWAITING'}">
                                            Oczekująca:<br>
                                            <a class="btn btn-raised btn-success" href="/instructors/${id}/opinions/${opn.id}/status/1">Akceptuj</a>
                                            <a class="btn btn-raised btn-danger" href="/instructors/${id}/opinions/${opn.id}/status/2">Odrzuć</a>
                                        </c:when>
                                        <c:when test="${opn.status == 'ACCEPTED'}">
                                            Zaakceptowana:<br>
                                            <a class="btn btn-raised btn-danger" href="/instructors/${id}/opinions/${opn.id}/status/2">Odrzuć</a>
                                        </c:when>
                                        <c:when test="${opn.status == 'REJECTED'}">
                                            Odrzucona:<br>
                                            <a class="btn btn-raised btn-success" href="/instructors/${id}/opinions/${opn.id}/status/1">Akceptuj</a>
                                        </c:when>
                                        <c:otherwise>Błąd</c:otherwise>
                                    </c:choose>

                                </td>

                            </tr>
                        </c:if>

                    </c:forEach>
                </c:forEach>
            </table>

        </security:authorize>

    </c:if>

    <c:if test="${empty list}">
        Brak opinii na temat tego wykładowcy.
    </c:if>

    <br><a class="btn btn-raised btn-success" href="/instructors/${id}/opinions/add">Dodaj opinię</a><br><br><br>

</div>
<jsp:include page="shared/footer.jsp"/>
</body>
</html>