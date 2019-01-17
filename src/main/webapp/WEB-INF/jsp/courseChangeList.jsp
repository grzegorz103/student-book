<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: Seko
  Date: 14.01.2019
  Time: 23:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="shared/header.jsp">
    <jsp:param name="name" value="courseChange"/>
</jsp:include>
<html>
<head>
    <title>Zarządzanie kierunkiem studiów</title>
</head>
<body>
<div class="container">
    <H1>Kierunek studiów</H1>

    <security:authorize access="hasRole('STUDENT')">
        <c:if test="${actualCourse != null}">
            <h3>Aktualny kierunek: ${actualCourse}</h3>
        </c:if>
        <c:if test="${notEnoughtCourses != null}">
            <label class="alert alert-danger">
                W tej chwili nie możesz zmienić kierunku.
            </label>
        </c:if>
        <c:if test="${hasAwaiting != null}">
            <label class="alert alert-danger">
                Zanim złożysz podanie poczekaj aż poprzednie zostanie rozpatrzone.
            </label>
        </c:if>
        <c:choose>
            <c:when test="${!empty courseChangeList.content}">
                <table class="table table-bordered">
                    <tr class="bg-success">
                        <th>Nowy kierunek</th>
                        <th>Data złożenia podania</th>
                        <th>Data rozpatrzenia podania</th>
                        <th>Status podania</th>
                        <th>Opcje</th>
                    </tr>
                    <c:forEach items="${courseChangeList.content}" var="courseChange">
                        <tr>
                            <td>
                                    ${courseChange.newCourse.name}
                            </td>
                            <td>
                                <fmt:formatDate value="${courseChange.submittingDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
                            </td>
                            <td>
                                <c:choose>
                                    <c:when test="${courseChange.statusChangeDate != null}">
                                        <fmt:formatDate value="${courseChange.statusChangeDate}"
                                                        pattern="yyyy-MM-dd HH:mm:ss"/>
                                    </c:when>
                                    <c:otherwise>
                                        Nie ustalono
                                    </c:otherwise>
                                </c:choose>
                            </td>

                            <c:choose>
                                <c:when test="${courseChange.status == 'AWAITING'}">
                                    <td class="text-info">
                                        Oczekujące
                                    </td>
                                </c:when>
                                <c:when test="${courseChange.status == 'ACCEPTED'}">
                                    <td class="text-success">
                                        Zatwierdzone
                                    </td>
                                </c:when>
                                <c:when test="${courseChange.status == 'REJECTED'}">
                                    <td class="text-danger">
                                        Odrzucone
                                    </td>
                                </c:when>
                            </c:choose>
                            <td>
                                <c:if test="${courseChange.status == 'REJECTED'}">
                                    <c:if test="${!hasAwaitingCourseChange}">
                                        <a href="/courseChange/edit/${courseChange.id}"
                                           class="btn btn-raised btn-success">Wyślij ponownie</a>
                                    </c:if>
                                </c:if>
                            </td>
                        </tr>
                    </c:forEach>

                </table>
                <c:set var="page" value="${courseChangeList}" scope="request"/>
                <c:set var="mainUrl" value="/courseChange/list" scope="request"/>
                <c:import url="shared/pagination.jsp"/>
            </c:when>
            <c:otherwise>
                <h4>Brak podań o zmianę kierunku studiów</h4>
            </c:otherwise>
        </c:choose>
        <c:if test="${!hasAwaitingCourseChange}">
            <a class="btn btn-raised btn-success" href="/courseChange/add">Nowe
                podanie o zmianę kierunku</a>
        </c:if><br><br><br>

    </security:authorize>

    <security:authorize access="hasRole('DEAN')">
        <c:choose>
            <c:when test="${!empty courseChangeList.content}">
                <table class="table table-bordered">
                    <tr class="bg-success">
                        <th>id</th>
                        <th>Imię i nazwisko studenta</th>
                        <th>Nowy kierunek</th>
                        <th>Data złożenia podania</th>
                        <th>Data rozpatrzenia podania</th>
                        <th>Status podania</th>
                        <th>Opcje</th>
                    </tr>
                    <c:forEach items="${courseChangeList.content}" var="courseChange">
                        <tr>
                            <td>
                                    ${courseChange.id}
                            </td>
                            <td>
                                    ${courseChange.student.name} ${courseChange.student.surname}
                            </td>
                            <td>
                                    ${courseChange.newCourse.name}
                            </td>
                            <td>
                                <fmt:formatDate value="${courseChange.submittingDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
                            </td>
                            <td>
                                <c:choose>
                                    <c:when test="${courseChange.statusChangeDate != null}">
                                        <fmt:formatDate value="${courseChange.statusChangeDate}"
                                                        pattern="yyyy-MM-dd HH:mm:ss"/>
                                    </c:when>
                                    <c:otherwise>
                                        Nie ustalono
                                    </c:otherwise>
                                </c:choose>
                            </td>

                            <c:choose>
                                <c:when test="${courseChange.status == 'AWAITING'}">
                                    <td class="text-info">
                                        Oczekujące
                                    </td>
                                </c:when>
                                <c:when test="${courseChange.status == 'ACCEPTED'}">
                                    <td class="text-success">
                                        Zatwierdzone
                                    </td>
                                </c:when>
                                <c:when test="${courseChange.status == 'REJECTED'}">
                                    <td class="text-danger">
                                        Odrzucone
                                    </td>
                                </c:when>
                            </c:choose>
                            <td>
                                <c:if test="${courseChange.status == 'AWAITING'}">
                                    <a href="/courseChange/list/more/${courseChange.id}"
                                       class="btn btn-raised btn-success">Więcej</a>
                                </c:if>
                            </td>
                        </tr>
                    </c:forEach>
                </table>

                <c:set var="page" value="${courseChangeList}" scope="request"/>
                <c:set var="mainUrl" value="/courseChange/list" scope="request"/>
                <c:import url="shared/pagination.jsp"/>
            </c:when>
            <c:otherwise>
                <h4>Brak podań o zmianę kierunku studiów</h4>
            </c:otherwise>
        </c:choose>
    </security:authorize>

</div>
</body>
</html>
<jsp:include page="shared/footer.jsp"/>

