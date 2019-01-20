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
    <jsp:param name="name" value="grades"/>
</jsp:include>
<html>
<head>
    <title>Oceny semestralne</title>
</head>
<body>
<div class="container">
    <H1>Oceny</H1>

    <security:authorize access="hasRole('STUDENT')">
        <c:choose>
            <c:when test="${avg != 0}">
                <h3 class="text-info">Średnia ocen za aktualny semestr: ${avg}</h3>
            </c:when>
            <c:otherwise>
                <h3 class="text-info">W tym semestrze jeszcze nie masz ocen</h3>
            </c:otherwise>
        </c:choose>

        <c:choose>
            <c:when test="${!empty semestralGrades}">
                <table class="table table-bordered">
                    <tr class="bg-success">
                        <th>Semestr</th>
                        <th>Przedmiot</th>
                        <th>Pierwszy termin</th>
                        <th>Drugi termin</th>
                        <th>Ocena końcowa</th>
                    </tr>
                    <c:forEach items="${semestralGrades}" var="grade">
                        <tr>
                            <td>
                                    ${grade.subject.semester}
                            </td>
                            <td>
                                    ${grade.subject.name}
                            </td>
                            <td>
                                <c:choose>
                                    <c:when test="${grade.firstTerminGrade != null}">
                                        ${grade.firstTerminGrade}
                                    </c:when>
                                    <c:otherwise>
                                        Brak
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td>
                                <c:choose>
                                    <c:when test="${grade.secondTerminGrade != null}">
                                        ${grade.secondTerminGrade}
                                    </c:when>
                                    <c:otherwise>
                                        Brak
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td>
                                <c:choose>
                                    <c:when test="${grade.totalGrade != null}">
                                        ${grade.totalGrade}
                                    </c:when>
                                    <c:otherwise>
                                        Brak
                                    </c:otherwise>
                                </c:choose>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </c:when>
            <c:otherwise>
                <h4>Brak ocen</h4>
            </c:otherwise>
        </c:choose>
    </security:authorize>
</div>
</body>
</html>
<jsp:include page="shared/footer.jsp"/>

