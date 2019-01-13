<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Strona główna</title>
</head>
<body>
<jsp:include page="shared/header.jsp"/>
<div class="container">

    <c:if test="${!empty list.content}">
        <table class="table table-hover">

            <tr>
                <th>Text</th>
                <th>Date</th>
                <th>Author</th>
            </tr>
            <c:forEach items="${list.content}" var="info">

                <tr>
                    <td><c:choose>
                        <c:when test="${empty info.title}">Brak danych</c:when>
                        <c:otherwise><a href="/sp/${info.id}">${info.title}</a></c:otherwise>
                    </c:choose></td>
                    <td><c:choose>
                        <c:when test="${empty info.author}">Brak danych</c:when>
                        <c:otherwise>${info.author}</c:otherwise>
                    </c:choose></td>
                    <td><fmt:formatDate value="${info.date}" type="date" timeStyle="medium"/></td>

                </tr>
            </c:forEach>
        </table>

        <c:url var="prevPageUrl" value="/?page=${list.number - 1}&size=${list.size}"/>
        <c:url var="nextPageUrl" value="/?page=${list.number + 1}&size=${list.size}"/>

        <c:url var="firstPageUrl" value="/?page=0&size=${list.size}"/>
        <c:url var="lastPageUrl" value="/?page=${list.totalPages - 1}&size=${list.size}"/>

        <nav aria-label="Page navigation example">
            <ul class="pagination pg-blue">
                <li ${list.first?'class="page-item disabled"':'page-item'}>
                    <a href="${list.first?"#":firstPageUrl}" class="page-link">
                        <span>First</span>
                    </a>
                </li>

                <li ${list.first?'class="page-item disabled"':'page-item'}>
                    <a href="${list.first?'#':prevPageUrl}" class="page-link">
                        <span>&laquo;</span>
                    </a>
                </li>

                <c:forEach var="pageIdx" begin="${0}" end="${list.totalPages-1}">
                    <c:url var="pageUrl" value="/?page=${pageIdx}&size=${list.size}"/>
                    <li ${pageIdx == list.number?'class="page-item active"':'page-item'}>
                        <a href="${pageUrl}" class="page-link">${pageIdx+1}</a>
                    </li>
                </c:forEach>

                <li ${list.last?'class="page-item disabled"':'page-item'}>
                    <a href="${list.last?'#':nextPageUrl}" class="page-link">
                        <span>&raquo;</span>
                    </a>
                </li>

                <li ${list.last?'class="page-item disabled"':'page-item'}>
                    <a href="${list.last?"#":lastPageUrl}" class="page-link">
                        <span>Last</span>
                    </a>
                </li>
            </ul>
        </nav>
        <nav>
            <ul class="pagination pg-blue">
                <c:set var="pageSizes" value="${[10, 20, 30, 50]}"/>
                <c:forEach var="size" items="${pageSizes}">
                    <c:url var="pageUrl" value="/?page=${list.number}&size=${size}"/>
                    <li class="page-item">
                        <a href="${pageUrl}" class="page-link">${size}</a>
                    </li>
                </c:forEach>
            </ul>
        </nav>
    </c:if>

    <c:if test="${empty list.content}">
        Brak wyników
    </c:if>

</div>
<jsp:include page="shared/footer.jsp"/>
</body>
</html>
