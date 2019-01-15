<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet"
          href="https://unpkg.com/bootstrap-material-design@4.1.1/dist/css/bootstrap-material-design.min.css">
    <link rel="stylesheet" type="text/css" href="/statics/css/main.css"/>

    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
            integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
            crossorigin="anonymous"></script>
    <script src="https://unpkg.com/popper.js@1.12.6/dist/umd/popper.js"
            integrity="sha384-fA23ZRQ3G/J53mElWqVJEGJzU0sTs+SvzG8fXVWP+kJQ1lwFAOkcUOysnlKJC33U"
            crossorigin="anonymous"></script>
    <script src="https://unpkg.com/bootstrap-material-design@4.1.1/dist/js/bootstrap-material-design.js"
            integrity="sha384-CauSuKpEqAFajSpkdjv3z9t8E7RlpJ1UP0lKM/+NdtSarroVKu069AlsRPKkFBz9"
            crossorigin="anonymous"></script>
</head>
<body>

<nav class="navbar navbar-expand-md navbar-dark bg-dark">
    <div class="container">
        <a class="navbar-brand" href="/">Strona główna</a>

        <ul class="navbar-nav mr-auto">

            <security:authorize access="isAnonymous()">
                <li ${param['name'] == 'loginForm' ? 'class="nav-item active"' : 'class="nav-item"'}>
                    <a class="nav-link" href="<c:url value="/login"/>">Logowanie</a>
                </li>
            </security:authorize>

            <security:authorize access="isAnonymous()">
                <li ${param['name'] == 'registrationForm' ? 'class="nav-item active"' : 'class="nav-item"'}>
                    <a class="nav-link" href="<c:url value="/register"/>">Rejestracja</a>
                </li>
            </security:authorize>

            <security:authorize access="isAuthenticated()">


                <li ${param['name'] == 'instructorList' ? 'class="nav-item active"' : 'class="nav-item"'}>
                    <a class="nav-link" href="/instructors/list">Wykładowcy</a>
                </li>

                <li ${param['name'] == 'subjectList' ? 'class="nav-item active"' : 'class="nav-item"'}>
                    <a class="nav-link" href="/subjects/list">Przedmioty</a>
                </li>

                <li ${param['name'] == 'workshopList' ? 'class="nav-item active"' : 'class="nav-item"'}>
                    <a class="nav-link" href="/workshops/list">Warsztaty</a>
                </li>

                <li ${param['name'] == 'messages' ? 'class="nav-item active"' : 'class="nav-item"'}>
                    <a class="nav-link" href="/msg">Wiadomości</a>
                </li>

                <li class="nav-item">
                    <div class="dropdown show">
                        <a class="nav-link dropdown-toggle" href="#" role="button"
                           id="dropdownMenuLinkForAdmin"
                           data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            Student
                        </a>

                        <div class="dropdown-menu" aria-labelledby="dropdownMenuLinkForAdmin">
                            <a class="dropdown-item ${param['pageName'] == 'scholarship' ? ' active':''}"
                               href="#">Stypendia</a>
                            <a class="dropdown-item ${param['pageName'] == 'condition' ? ' active':''}"
                               href="#">Warunki</a>
                            <a class="dropdown-item ${param['pageName'] == 'courseChange' ? ' active':''}"
                               href="#">Zmiana kierunku</a>
                        </div>
                    </div>
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
