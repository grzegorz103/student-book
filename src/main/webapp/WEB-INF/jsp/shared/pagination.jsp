<%--
  Created by IntelliJ IDEA.
  User: Seko
  Date: 04.12.2018
  Time: 14:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:url var="firstPageUrl" value="${mainUrl}?page=0&size=${page.size}"/>
<c:url var="prevPageUrl" value="${mainUrl}?page=${page.number - 1}&size=${page.size}"/>
<c:url var="nextPageUrl" value="${mainUrl}?page=${page.number + 1}&size=${page.size}"/>
<c:url var="lastPageUrl" value="${mainUrl}?page=${page.totalPages - 1}&size=${page.size}"/>

<nav class="navbar navbar-expand">
    <div class="collapse navbar-collapse">
        <ul class="navbar-nav mr-md-auto">
            <li class="nav-item">
                <a class="nav-link ${page.first?"disabled":""}" href="${page.first?'#':firstPageUrl}">
                    <span>Pierwsza</span>
                </a>
            </li>

            <li class="nav-item">
                <a class="nav-link ${page.first?"disabled":""}" href="${page.first?'#':prevPageUrl}">
                    <span>&laquo;</span>
                </a>
            </li>

            <c:forEach var="pageIdx" begin="${0}" end="${page.totalPages-1}">
                <c:url var="pageUrl" value="${mainUrl}?page=${pageIdx}&size=${page.size}"/>
                <li class="nav-item">
                    <a class="nav-link ${pageIdx == page.number?"disabled":""}"
                       href="${pageIdx == page.number ? "#" : pageUrl}">
                        <span>${pageIdx+1}</span>
                    </a>
                </li>
            </c:forEach>

            <li class="nav-item">
                <a class="nav-link ${page.last?"disabled":""}" href="${page.last?'#':nextPageUrl}">
                    <span>&raquo;</span>
                </a>
            </li>

            <li class="nav-item">
                <a class="nav-link ${page.last?"disabled":""}" href="${page.last?'#':lastPageUrl}">
                    <span>Ostatnia</span>
                </a>
            </li>
        </ul>

        <ul class="navbar-nav ml-md-auto">
            <c:set var="pageSizes" value="${[1, 2, 3, 4, 20]}"/>
            <c:forEach var="size" items="${pageSizes}">
                <c:url var="pageUrl" value="${mainUrl}?page=0&size=${size}"/>
                <li class="nav-item text-md-right">
                    <a class="nav-link ${page.size eq size? "disabled":""}" href="${pageUrl}">
                        <span>${size}</span>
                    </a>
                </li>
            </c:forEach>
        </ul>
    </div>
</nav>
<BR>
