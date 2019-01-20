<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="jap" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Lista warsztatów</title>
</head>
<body>

<jsp:include page="shared/header.jsp">
    <jsp:param name="name" value="workshopList"/>
</jsp:include>
<div class="container">

    <c:if test="${!empty list}">
        <table class="table table-bordered">
            <tr class="bg-success">
                <th>Nazwa</th>
                <th>Prowadzący</th>
                <th>Zajęcia</th>
                <th>Ilośc osób</th>
                <security:authorize access="hasAnyRole('DEAN', 'INSTRUCTOR')">
                    <th>Usuń</th>
                </security:authorize>

            </tr>

            <c:forEach items="${list}" var="workshop">
                    <tr>

                        <td>
                            <c:choose>
                                <c:when test="${empty workshop.name}">Brak danych</c:when>
                                <c:otherwise>${workshop.name}</c:otherwise>
                            </c:choose>
                        </td>

                        <td>
                            <c:choose>
                                <c:when test="${empty workshop.instructor}">Brak danych</c:when>
                                <c:otherwise>${workshop.instructor.name} ${workshop.instructor.surname}</c:otherwise>
                            </c:choose>
                        </td>

                        <security:authorize access="hasAnyRole('DEAN', 'INSTRUCTOR')">
                            <td>
                                <a class="btn btn-raised btn-info" href="/workshops/${workshop.id}/units">Lista zajęć</a>
                            </td>
                        </security:authorize>

                        <security:authorize access="hasRole('STUDENT')">
                            <td>

                                <c:choose>
                                    <c:when test="${workshop.students.contains(student)}">
                                        <a class="btn btn-raised btn-info" href="/workshops/${workshop.id}/units/">Lista zajęć</a>
                                        <a class="btn btn-raised btn-danger" href="/workshops/${workshop.id}/exit/${student.id}">Opuść</a>
                                    </c:when>
                                    <c:when test="${workshop.students.size() >= workshop.limit}">
                                        Limit miejsc osiągnięty
                                    </c:when>
                                    <c:otherwise>
                                        <a class="btn btn-raised btn-success" href="/workshops/${workshop.id}/join/${student.id}">Dołącz</a>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                        </security:authorize>

                        <security:authorize access="hasAnyRole('DEAN', 'INSTRUCTOR')">
                            <td>
                                <c:choose>
                                    <c:when test="${empty workshop.students}">0/${workshop.limit}</c:when>
                                    <c:otherwise>
                                        ${workshop.students.size()}/${workshop.limit}
                                        <a class="btn btn-raised btn-warning" href="/workshops/${workshop.id}/students">Lista członków</a>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                        </security:authorize>

                        <security:authorize access="hasRole('STUDENT')">
                            <td>
                                <c:choose>
                                    <c:when test="${empty workshop.students}">0/${workshop.limit}</c:when>
                                    <c:otherwise>${workshop.students.size()}/${workshop.limit}</c:otherwise>
                                </c:choose>
                            </td>
                        </security:authorize>

                        <security:authorize access="hasAnyRole('DEAN', 'INSTRUCTOR')">
                            <td>
                                <a class="btn btn-raised btn-danger" href="/workshops/${workshop.id}/delete">Usuń</a>
                            </td>
                        </security:authorize>

                    </tr>
            </c:forEach>

        </table>


    </c:if>

    <c:if test="${empty list}">
        Brak warsztatów w bazie.
    </c:if>

    <security:authorize access="hasRole('INSTRUCTOR')">
        <br><a class="btn btn-raised btn-success" href="/workshops/add">Dodaj warsztat</a><br><br><br>
    </security:authorize>

</div>
<jsp:include page="shared/footer.jsp"/>
</body>
</html>
