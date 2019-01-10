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
    <jsp:param name="name" value="vehicleList"/>
</jsp:include>

<div id="main" class="container">

    <form:form class="form-inline" form:method="post" modelAttribute="vehicleSearch">
        <form:input class="form-control mr-sm-2" type="search" placeholder="Czego szukasz?" aria-label="Search"
                    path="phrase"/>

        <form:input path="priceMin" type="input" placeholder="Cena minimalna" class="form-control mr-sm-2"/>

        <form:input path="priceMax" type="input" placeholder="Cena maksymalna" class="form-control mr-sm-2"/>

        <button class="btn btn-raised btn-primary mr-sm-2" type="submit">Wyszukaj</button>
        <a class="btn btn-raised btn-warning" href="<c:url value="/vehicle/clear"/>">Wyczyść</a>
    </form:form>

    <c:if test="${!empty list}">
        <table class="table table-bordered">
            <tr class="bg-success">
                <th>ID</th>
                <th>Nazwa</th>
                <th>Model</th>
                <th>Data produkcji</th>
                <th>Cena</th>
                <th>Uszkodzenia</th>
                <th>Typ</th>
                <th>Akcesoria</th>
                <security:authorize access="hasRole('ADMIN')">
                    <th></th>
                    <th></th>
                </security:authorize>

            </tr>

            <c:forEach items="${list.content}" var="vehicle">
                <tr>

                    <td>
                        <c:choose>
                            <c:when test="${empty vehicle.id}">Brak danych</c:when>
                            <c:otherwise>
                                <a href="<c:url value="/vehicle/${vehicle.id}"/>">${vehicle.id}</a>
                            </c:otherwise>
                    </c:choose>
                    </td>

                    <td>
                        <c:choose>
                            <c:when test="${empty vehicle.name}">Brak danych</c:when>
                            <c:otherwise>${vehicle.name}</c:otherwise>
                        </c:choose>
                    </td>

                    <td>
                        <c:choose>
                            <c:when test="${empty vehicle.model}">Brak danych</c:when>
                            <c:otherwise>${vehicle.model}</c:otherwise>
                        </c:choose>
                    </td>

                    <td>
                        <fmt:formatDate value="${vehicle.productionDate}" type="date" timeStyle="medium"/>
                    </td>

                    <td>
                        <fmt:formatNumber type="CURRENCY" value="${vehicle.price}" currencySymbol="PLN"/>
                    </td>

                    <td>
                        <c:choose>
                            <c:when test="${empty vehicle.broken}">Brak danych</c:when>
                            <c:otherwise>${vehicle.broken}</c:otherwise>
                        </c:choose>
                    </td>

                    <td>
                        <c:choose>
                            <c:when test="${empty vehicle.type.name}">Brak danych</c:when>
                            <c:otherwise>${vehicle.type.name}</c:otherwise>
                        </c:choose>
                    </td>

                    <td>
                        <ul>

                            <c:choose>
                                <c:when test="${empty vehicle.accessories}">Brak</c:when>
                                <c:otherwise>
                                    <c:forEach items="${vehicle.accessories}" var="accessory">
                                        <li><b>${accessory.name}</b></li>
                                    </c:forEach>
                                </c:otherwise>
                            </c:choose>
                        </ul>
                    </td>

                    <security:authorize access="hasRole('ADMIN')">
                        <td>
                            <a class="btn btn-raised btn-info" href="edit/${vehicle.id}">Edytuj</a>
                        </td>

                        <td>
                            <a class="btn btn-raised btn-danger" href="delete/${vehicle.id}">Usuń</a>
                        </td>
                    </security:authorize>
                </tr>
            </c:forEach>
        </table>

        <a class="btn btn-raised btn-success" href="/vehicle/add.html">Dodaj nowy pojazd</a><br><br><br>

        <c:url var="prevPageUrl" value="/vehicle/list?page=${list.number - 1}&size=${list.size}"/>
        <c:url var="nextPageUrl" value="/vehicle/list?page=${list.number + 1}&size=${list.size}"/>

        <c:url var="firstPageUrl" value="/vehicle/list?page=0&size=${list.size}"/>
        <c:url var="lastPageUrl" value="/vehicle/list?page=${list.totalPages - 1}&size=${list.size}"/>

        <nav>
            <ul class="pagination">

                <li class="page-item" ${list.first?'class="disabled"':''}>
                    <a href="${list.first?"#":firstPageUrl}">
                        <span class="page-link">Pierwsza</span>
                    </a>
                </li>

                <li class="page-item" ${list.first?'class="disabled"':''}>
                    <a href="${list.first?'#':prevPageUrl}">
                        <span class="page-link">&laquo;</span>
                    </a>
                </li>

                <c:forEach var="pageIdx" begin="${0}" end="${list.totalPages-1}">
                    <c:url var="pageUrl" value="/vehicle/list?page=${pageIdx}&size=${list.size}"/>
                    <li class="page-item" ${pageIdx == list.number?'class="active"':''}>
                        <a class="page-link" href="${pageUrl}">${pageIdx+1}</a>
                    </li>
                </c:forEach>

                <li class="page-item" ${list.last?'class="disabled"':''}>
                    <a href="${list.last?'#':nextPageUrl}">
                        <span class="page-link">&raquo;</span>
                    </a>
                </li>

                <li class="page-item" ${list.last?'class="disabled"':''}>
                    <a href="${list.last?"#":lastPageUrl}">
                        <span class="page-link">Ostatnia</span>
                    </a>
                </li>
            </ul>

            <ul class="pagination" style="float: right">
                <c:set var="pageSizes" value="${[5, 10, 15, 20]}"/>
                <c:forEach var="size" items="${pageSizes}">
                    <c:url var="pageUrl" value="/vehicle/list?page=${list.number}&size=${size}"/>

                    <li class="page-item" ${list.size eq size?'class="active"':''}>
                        <a href="${pageUrl}"><span class="page-link">${size}</span></a>
                    </li>

                </c:forEach>
            </ul>

        </nav>

    </c:if>

    <c:if test="${empty list}">
        Brak pojazdów w bazie
    </c:if>


</div>
<jsp:include page="shared/footer.jsp"/>
</body>
</html>
