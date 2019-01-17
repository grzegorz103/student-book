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
    <jsp:param name="name" value="scholarship"/>
</jsp:include>
<html>
<head>
    <title>Stypendia</title>
</head>
<body>
<div class="container">
    <H1>Stypendia</H1>

    <security:authorize access="hasRole('STUDENT')">
        <c:if test="${!isOpen}">
            <h3 class="text-warning">Możliwość składania podań o stypendium jest zamknięta</h3>
        </c:if>

        <c:choose>
            <c:when test="${!empty scholarshipList.content}">
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
                    <c:forEach items="${scholarshipList.content}" var="courseChange">
                        <tr>
                            <td>
                                <c:choose>
                                    <c:when test="${courseChange.scholarshipType == 'SOCIAL'}">
                                        Socjalne
                                    </c:when>
                                    <c:when test="${courseChange.scholarshipType == 'SCIENTIFIC'}">
                                        Naukowe
                                    </c:when>
                                    <c:otherwise>
                                        Nie dotyczy
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td>
                                <c:choose>
                                    <c:when test="${courseChange.scholarshipType == 'SOCIAL'}">
                                        ${courseChange.peopleNumber}
                                    </c:when>
                                    <c:otherwise>
                                        Nie dotyczy
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td>
                                <c:choose>
                                    <c:when test="${courseChange.scholarshipType == 'SOCIAL'}">
                                        ${courseChange.allMembersIncome}
                                    </c:when>
                                    <c:otherwise>
                                        Nie dotyczy
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td>
                                <c:choose>
                                    <c:when test="${courseChange.scholarshipType == 'SCIENTIFIC'}">
                                        ${courseChange.averageGrade}
                                    </c:when>
                                    <c:otherwise>
                                        Nie dotyczy
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td>
                                <c:choose>
                                    <c:when test="${courseChange.amount != null && courseChange.amount > 0}">
                                        ${courseChange.amount}
                                    </c:when>
                                    <c:otherwise>
                                        Nie ustalono
                                    </c:otherwise>
                                </c:choose>
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
                                        Przyznane
                                    </td>
                                </c:when>
                                <c:when test="${courseChange.status == 'REJECTED'}">
                                    <td class="text-danger">
                                        Odrzucone
                                    </td>
                                </c:when>
                            </c:choose>
                            <td>
                                <c:if test="${courseChange.status == 'REJECTED' && isOpen}">
                                    <c:choose>
                                        <c:when test="${courseChange.scholarshipType == 'SOCIAL' && !hasAwaitingSocial}">
                                            <a href="/scholarships/edit/social/${courseChange.id}"
                                               class="btn btn-raised btn-success">Wyślij ponownie</a>
                                        </c:when>
                                        <c:when test="${courseChange.scholarshipType == 'SCIENTIFIC' && !hasAwaitingScientific}">
                                            <a href="/scholarships/edit/scientific/${courseChange.id}"
                                               class="btn btn-raised btn-success">Wyślij ponownie</a>
                                        </c:when>
                                    </c:choose>
                                </c:if>
                            </td>
                        </tr>
                    </c:forEach>

                </table>
                <c:set var="page" value="${scholarshipList}" scope="request"/>
                <c:set var="mainUrl" value="/scholarships/list" scope="request"/>
                <c:import url="shared/pagination.jsp"/>
            </c:when>
            <c:otherwise>
                <h4>Brak stypendiów</h4>
            </c:otherwise>
        </c:choose>
        <c:if test="${isOpen}">
            <c:if test="${!hasAwaitingSocial}">
                <a class="btn btn-raised btn-success" href="/scholarships/add/social">Nowe
                    podanie o stypendium socjalne</a>
            </c:if>
            <c:if test="${!hasAwaitingScientific}">
                <a class="btn btn-raised btn-success" href="/scholarships/add/scientific">Nowe
                    podanie o stypendium naukowe</a>
            </c:if>
        </c:if><br><br><br>

    </security:authorize>

    <security:authorize access="hasRole('DEAN')">

        <c:choose>
            <c:when test="${isOpen}">
                <a href="/scholarships/close"
                   class="btn btn-raised btn-warning">Zamknij możliwość składania podań o stypendium</a>
            </c:when>
            <c:otherwise>
                <a href="/scholarships/open"
                   class="btn btn-raised btn-success">Otwórz możliwość składania podań o stypendium</a>
            </c:otherwise>
        </c:choose>

        <c:choose>
            <c:when test="${!empty scholarshipList.content}">
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
                    <c:forEach items="${scholarshipList.content}" var="courseChange">
                        <tr>
                            <td>
                                    ${courseChange.id}
                            </td>

                            <td>
                                    ${courseChange.student.name} ${courseChange.student.surname}
                            </td>

                            <td>
                                <c:choose>
                                    <c:when test="${courseChange.scholarshipType == 'SOCIAL'}">
                                        Socjalne
                                    </c:when>
                                    <c:when test="${courseChange.scholarshipType == 'SCIENTIFIC'}">
                                        Naukowe
                                    </c:when>
                                    <c:otherwise>
                                        Nie dotyczy
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td>
                                <c:choose>
                                    <c:when test="${courseChange.scholarshipType == 'SOCIAL'}">
                                        ${courseChange.peopleNumber}
                                    </c:when>
                                    <c:otherwise>
                                        Nie dotyczy
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td>
                                <c:choose>
                                    <c:when test="${courseChange.scholarshipType == 'SOCIAL'}">
                                        ${courseChange.allMembersIncome}
                                    </c:when>
                                    <c:otherwise>
                                        Nie dotyczy
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td>
                                <c:choose>
                                    <c:when test="${courseChange.scholarshipType == 'SCIENTIFIC'}">
                                        ${courseChange.averageGrade}
                                    </c:when>
                                    <c:otherwise>
                                        Nie dotyczy
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td>
                                <c:choose>
                                    <c:when test="${courseChange.amount != null && courseChange.amount > 0}">
                                        ${courseChange.amount}
                                    </c:when>
                                    <c:otherwise>
                                        Nie ustalono
                                    </c:otherwise>
                                </c:choose>
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
                                        Przyznane
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
                                    <a href="/scholarships/list/more/${courseChange.id}"
                                       class="btn btn-raised btn-success">Więcej</a>
                                </c:if>
                            </td>

                        </tr>

                    </c:forEach>

                </table>
                <c:set var="page" value="${scholarshipList}" scope="request"/>
                <c:set var="mainUrl" value="/scholarships/list" scope="request"/>
                <c:import url="shared/pagination.jsp"/>
            </c:when>
            <c:otherwise>
                <h4>Brak stypendiów</h4>
            </c:otherwise>
        </c:choose>
    </security:authorize>

</div>
</body>
</html>
<jsp:include page="shared/footer.jsp"/>

