<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Seko
  Date: 14.01.2019
  Time: 23:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="shared/header.jsp">
    <jsp:param name="name" value="opinionList"/>
</jsp:include>
<html>
<head>
    <title>Stypendia</title>
</head>
<body>
<div class="container">

    <c:if test="${!empty scholarshipList.content}">

        <security:authorize access="hasRole('STUDENT')">
            <H1>Stypendia</H1>
            <table class="table table-bordered">
                <tr class="bg-success">
                    <th>Typ stypendium</th>
                    <th>Ilość osób w rodzinie</th>
                    <th>Łączny przychód wszystkich członków rodziny</th>
                    <th>Średnia ocen</th>
                    <th>Przyznana kwota</th>
                    <th>Data złożenia wniosku</th>
                    <th>Data rozpatrzenia wniosku</th>
                    <th>Status wniosku</th>
                    <th>Opcje</th>
                </tr>
                <c:forEach items="${scholarshipList.content}" var="scholarship">


                    <tr>
                        <td>
                            <c:choose>
                                <c:when test="${scholarship.scholarshipType == 'SOCIAL'}">
                                    ${scholarship.scholarshipType}
                                </c:when>
                                <c:otherwise>
                                    Nie dotyczy
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td>
                            <c:choose>
                                <c:when test="${scholarship.scholarshipType == 'SOCIAL'}">
                                    ${scholarship.peopleNumber}
                                </c:when>
                                <c:otherwise>
                                    Nie dotyczy
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td>
                            <c:choose>
                                <c:when test="${scholarship.scholarshipType == 'SOCIAL'}">
                                    ${scholarship.allMembersIncome}
                                </c:when>
                                <c:otherwise>
                                    Nie dotyczy
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td>
                            <c:choose>
                                <c:when test="${scholarship.scholarshipType == 'SCIENTIFIC'}">
                                    ${scholarship.averageGrade}
                                </c:when>
                                <c:otherwise>
                                    Nie dotyczy
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td>
                            <c:choose>
                                <c:when test="${scholarship.amount != null && scholarship.amount > 0}">
                                    ${scholarship.amount}
                                </c:when>
                                <c:otherwise>
                                    Nie ustalono
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td>
                                ${scholarship.submittingDate}
                        </td>
                        <td>
                            <c:choose>
                                <c:when test="${scholarship.statusChangeDate != null}">
                                    ${scholarship.statusChangeDate}
                                </c:when>
                                <c:otherwise>
                                    Nie ustalono
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td>
                                ${scholarship.status}
                        </td>
                        <td>
                            <c:if test="${scholarship.status == 'REJECTED'}">
                                <a href="#" class="btn btn-raised btn-success">Wyślij ponownie</a>
                            </c:if>
                        </td>

                    </tr>

                </c:forEach>

            </table>

            <a class="btn btn-raised btn-success" href="/instructors/${id}/opinions/add">Złóż nowe
                podanie</a><br><br><br>

        </security:authorize>

        <security:authorize access="hasRole('DEAN')">

            <H1>Stypendia</H1>
            <table class="table table-bordered">
                <tr class="bg-success">
                    <th>id</th>
                    <th>Imię i nazwisko studenta</th>
                    <th>Typ stypendium</th>
                    <th>Ilość osób w rodzinie</th>
                    <th>Łączny przychód wszystkich członków rodziny</th>
                    <th>Średnia ocen</th>
                    <th>Przyznana kwota</th>
                    <th>Data złożenia wniosku</th>
                    <th>Data rozpatrzenia wniosku</th>
                    <th>Status wniosku</th>
                    <th>Opcje</th>
                </tr>
                <c:forEach items="${scholarshipList.content}" var="scholarship">
                    <tr>
                        <td>
                                ${scholarship.id}
                        </td>

                        <td>
                                ${scholarship.student.name} ${scholarship.student.surname}
                        </td>

                        <td>
                            <c:choose>
                                <c:when test="${scholarship.scholarshipType == 'SOCIAL'}">
                                    ${scholarship.scholarshipType}
                                </c:when>
                                <c:otherwise>
                                    Nie dotyczy
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td>
                            <c:choose>
                                <c:when test="${scholarship.scholarshipType == 'SOCIAL'}">
                                    ${scholarship.peopleNumber}
                                </c:when>
                                <c:otherwise>
                                    Nie dotyczy
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td>
                            <c:choose>
                                <c:when test="${scholarship.scholarshipType == 'SOCIAL'}">
                                    ${scholarship.allMembersIncome}
                                </c:when>
                                <c:otherwise>
                                    Nie dotyczy
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td>
                            <c:choose>
                                <c:when test="${scholarship.scholarshipType == 'SCIENTIFIC'}">
                                    ${scholarship.averageGrade}
                                </c:when>
                                <c:otherwise>
                                    Nie dotyczy
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td>
                            <c:choose>
                                <c:when test="${scholarship.amount != null && scholarship.amount > 0}">
                                    ${scholarship.amount}
                                </c:when>
                                <c:otherwise>
                                    Nie ustalono
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td>
                                ${scholarship.submittingDate}
                        </td>
                        <td>
                            <c:choose>
                                <c:when test="${scholarship.statusChangeDate != null}">
                                    ${scholarship.statusChangeDate}
                                </c:when>
                                <c:otherwise>
                                    Nie ustalono
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td>
                                ${scholarship.status}
                        </td>
                        <td>
                            <c:if test="${scholarship.status == 'AWAITING'}">
                                <a href="#" class="btn btn-raised btn-success">Zatwierdź</a>
                                <a href="#" class="btn btn-raised btn-success">Odrzuć</a>
                            </c:if>
                        </td>

                    </tr>

                </c:forEach>

            </table>

        </security:authorize>

    </c:if>

    <c:if test="${empty list}">
        Brak stypendiów
    </c:if>


</div>
</body>
</html>
<jsp:include page="shared/footer.jsp"/>
