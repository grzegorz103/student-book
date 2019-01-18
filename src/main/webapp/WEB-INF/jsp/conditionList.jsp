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
    <jsp:param name="name" value="condition"/>
</jsp:include>
<html>
<head>
    <title>Warunki</title>
</head>
<body>
<div class="container">
    <H1>Warunki</H1>

    <security:authorize access="hasRole('STUDENT')">
        <c:if test="${!isOpen}">
            <h3 class="text-warning">Możliwość składania podań o warunek jest zamknięta</h3>
        </c:if>
        <c:if test="${!canStudentGetCondition}">
            <h3 class="text-success">Nie masz z czego złożyć warunku</h3>
        </c:if>

        <c:choose>
            <c:when test="${!empty conditionList.content}">
                <table class="table table-bordered">
                    <tr class="bg-success">
                        <th>Typ warunku</th>
                        <th>Nazwa przedmiotu</th>
                        <th>Imię i nazwisko wykładowcy</th>
                        <th>Data złożenia podania</th>
                        <th>Data rozpatrzenia podania</th>
                        <th>Status podania</th>
                        <th>Opcje</th>
                    </tr>
                    <c:forEach items="${conditionList.content}" var="condition">
                        <tr>
                            <td>
                                <c:choose>
                                    <c:when test="${condition.conditionType == 'SHORT'}">
                                        Krótki
                                    </c:when>
                                    <c:when test="${condition.conditionType == 'LONG'}">
                                        Długi
                                    </c:when>
                                    <c:otherwise>
                                        Nie dotyczy
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td>
                                    ${condition.subject.name}
                            </td>
                            <td>
                                    ${condition.subject.instructor.name} ${condition.subject.instructor.surname}
                            </td>
                            <td>
                                <fmt:formatDate value="${condition.submittingDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
                            </td>
                            <td>
                                <c:choose>
                                    <c:when test="${condition.statusChangeDate != null}">
                                        <fmt:formatDate value="${condition.statusChangeDate}"
                                                        pattern="yyyy-MM-dd HH:mm:ss"/>
                                    </c:when>
                                    <c:otherwise>
                                        Nie ustalono
                                    </c:otherwise>
                                </c:choose>
                            </td>

                            <c:choose>
                                <c:when test="${condition.status == 'AWAITING'}">
                                    <td class="text-info">
                                        Oczekujące
                                    </td>
                                </c:when>
                                <c:when test="${condition.status == 'ACCEPTED'}">
                                    <td class="text-success">
                                        Przyznane
                                    </td>
                                </c:when>
                                <c:when test="${condition.status == 'REJECTED'}">
                                    <td class="text-danger">
                                        Odrzucone
                                    </td>
                                </c:when>
                            </c:choose>
                            <td>
                                <c:if test="${condition.status == 'REJECTED' && isOpen && canStudentGetCondition}">
                                    <a href="/conditions/edit/${condition.id}"
                                       class="btn btn-raised btn-success">Wyślij ponownie</a>
                                </c:if>
                            </td>
                        </tr>
                    </c:forEach>

                </table>
                <c:set var="page" value="${conditionList}" scope="request"/>
                <c:set var="mainUrl" value="/conditions/list" scope="request"/>
                <c:import url="shared/pagination.jsp"/>
            </c:when>
            <c:otherwise>
                <h4>Brak podań o warunek</h4>
            </c:otherwise>
        </c:choose>
        <c:if test="${isOpen}">
            <c:if test="${canStudentGetCondition}">
                <a class="btn btn-raised btn-success" href="/conditions/add">Nowe podanie o warunek</a>
            </c:if>
        </c:if><br><br><br>

    </security:authorize>

    <security:authorize access="hasRole('INSTRUCTOR')">
        <c:if test="${!isOpen}">
            <h3 class="text-info">Możliwość składania podań o warunek jest zamknięta</h3>
        </c:if>
        <c:if test="${!canStudentGetCondition}">
            <h3 class="text-info">Możliwość składania podań o warunek jest otwarta</h3>
        </c:if>

        <c:choose>
            <c:when test="${!empty conditionList.content}">
                <table class="table table-bordered">
                    <tr class="bg-success">
                        <th>id</th>
                        <th>Imię i nazwisko studenta</th>
                        <th>Typ warunku</th>
                        <th>Nazwa przedmiotu</th>
                        <th>Data złożenia podania</th>
                        <th>Data rozpatrzenia podania</th>
                        <th>Status podania</th>
                        <th>Opcje</th>
                    </tr>
                    <c:forEach items="${conditionList.content}" var="condition">
                        <tr>
                            <td>
                                    ${condition.id}
                            </td>

                            <td>
                                    ${condition.student.name} ${condition.student.surname}
                            </td>

                            <td>
                                <c:choose>
                                    <c:when test="${condition.conditionType == 'SHORT'}">
                                        Krótki
                                    </c:when>
                                    <c:when test="${condition.conditionType == 'LONG'}">
                                        Długi
                                    </c:when>
                                    <c:otherwise>
                                        Nie dotyczy
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td>
                                    ${condition.subject.name}
                            </td>
                            <td>
                                <fmt:formatDate value="${condition.submittingDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
                            </td>
                            <td>
                                <c:choose>
                                    <c:when test="${condition.statusChangeDate != null}">
                                        <fmt:formatDate value="${condition.statusChangeDate}"
                                                        pattern="yyyy-MM-dd HH:mm:ss"/>
                                    </c:when>
                                    <c:otherwise>
                                        Nie ustalono
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <c:choose>
                                <c:when test="${condition.status == 'AWAITING'}">
                                    <td class="text-info">
                                        Oczekujące
                                    </td>
                                </c:when>
                                <c:when test="${condition.status == 'ACCEPTED'}">
                                    <td class="text-success">
                                        Przyznane
                                    </td>
                                </c:when>
                                <c:when test="${condition.status == 'REJECTED'}">
                                    <td class="text-danger">
                                        Odrzucone
                                    </td>
                                </c:when>
                            </c:choose>
                            <td>
                                <c:if test="${condition.status == 'AWAITING'}">
                                    <a href="/conditions/list/more/${condition.id}"
                                       class="btn btn-raised btn-success">Więcej</a>
                                </c:if>
                            </td>
                        </tr>
                    </c:forEach>
                </table>

                <c:set var="page" value="${conditionList}" scope="request"/>
                <c:set var="mainUrl" value="/conditions/list" scope="request"/>
                <c:import url="shared/pagination.jsp"/>
            </c:when>
            <c:otherwise>
                <h4>Brak podań o warunek</h4>
            </c:otherwise>
        </c:choose>
    </security:authorize>

    <security:authorize access="hasRole('DEAN')">
        <c:choose>
            <c:when test="${isOpen}">
                <a href="/conditions/close"
                   class="btn btn-raised btn-warning">Zamknij możliwość składania podań o stypendium</a>
            </c:when>
            <c:otherwise>
                <a href="/conditions/open"
                   class="btn btn-raised btn-success">Otwórz możliwość składania podań o stypendium</a>
            </c:otherwise>
        </c:choose>
    </security:authorize>
</div>
</body>
</html>
<jsp:include page="shared/footer.jsp"/>

