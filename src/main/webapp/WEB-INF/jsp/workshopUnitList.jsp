<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="jap" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Lista zajęć</title>
</head>
<body>

<jsp:include page="shared/header.jsp">
    <jsp:param name="name" value="workshopList"/>
</jsp:include>
<div class="container">
<h2>Lista meteriałów z warsztatu: ${workshop.name}</h2>
    <c:if test="${!empty workshop.units}">
        <table class="table table-bordered">
            <tr class="bg-success">
                <th>Nazwa</th>
                <th>Opis</th>
                <th>Materiały</th>
                <th>Usuń</th>

            </tr>

            <c:forEach items="${workshop.units}" var="unit">
                    <tr>

                        <td>
                            <c:choose>
                                <c:when test="${empty unit.name}">Brak danych</c:when>
                                <c:otherwise>${unit.name}</c:otherwise>
                            </c:choose>
                        </td>

                        <td>
                            <c:choose>
                                <c:when test="${empty unit.description}">Brak danych</c:when>
                                <c:otherwise>${unit.description}</c:otherwise>
                            </c:choose>
                        </td>

                        <td>
                            <c:choose>
                                <c:when test="${empty unit.file}">Brak danych</c:when>
                                <c:otherwise><a class="btn btn-raised btn-warning" href="${unit.file}">Pobierz</a></c:otherwise>
                            </c:choose>
                        </td>

                        <td>
                            <a class="btn btn-raised btn-danger" href="/workshops/${workshop.id}/units/${unit.id}/delete">Usuń</a>
                        </td>

                    </tr>
            </c:forEach>

        </table>


    </c:if>

    <c:if test="${empty workshop.units}">
        Do tego warsztatu nie dodano jeszcze żadnych materiałów.
    </c:if>

    <security:authorize access="hasRole('INSTRUCTOR')">
        <br><a class="btn btn-raised btn-success" href="/workshops/${workshop.id}/units/add">Dodaj materiały</a><br><br><br>
    </security:authorize>

</div>
<jsp:include page="shared/footer.jsp"/>
</body>
</html>
