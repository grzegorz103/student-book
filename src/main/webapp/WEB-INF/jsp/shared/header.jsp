<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://unpkg.com/bootstrap-material-design@4.1.1/dist/css/bootstrap-material-design.min.css">
    <link rel="stylesheet" type="text/css" href="/statics/css/main.css"/>
</head>
<body>

<nav class="navbar navbar-expand-md navbar-dark bg-dark">
    <a class="navbar-brand" href="/">Strona główna</a>

        <ul class="navbar-nav mr-auto">

            <security:authorize access="isAnonymous()">
                <li ${param['name'] == 'loginForm' ? 'class="nav-item active"' : 'class="nav-item"'}>
                    <a class="nav-link" href="<c:url value="/login"/>">Logowanie</a>
                </li>
            </security:authorize>

            <security:authorize access="isAnonymous()">
                <li ${param['name'] == 'registrationForm' ? 'class="nav-item active"' : 'class="nav-item"'}>
                    <a class="nav-link" href="<c:url value="/registrationForm"/>">Rejestracja</a>
                </li>
            </security:authorize>

            <security:authorize access="isAuthenticated()">


                <li ${param['name'] == 'instructorList' ? 'class="nav-item active"' : 'class="nav-item"'}>
                    <a class="nav-link" href="/instructors/list">Lista wykładowców</a>
                </li>

                <li class="nav-item">
                    <a class="nav-link" onclick="document.getElementById('logout').submit()">Witaj
                        <sec:authentication property="principal.username"/>, wyloguj się</a>
                </li>

                <form action="<c:url value="/logout"/>" id="logout" method="post" style="display: none;">
                    <sec:csrfInput/>
                </form>

            </security:authorize>

        </ul>
    </div>
</nav>
<br>
</body>
</html>
