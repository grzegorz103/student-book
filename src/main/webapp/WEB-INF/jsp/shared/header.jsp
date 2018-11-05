<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700|Material+Icons">
    <link rel="stylesheet"
          href="https://unpkg.com/bootstrap-material-design@4.1.1/dist/css/bootstrap-material-design.min.css">
    <link rel="stylesheet" type="text/css" href="/resources/css/main.css"/>

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
    <a class="navbar-brand" href="/">Strona główna</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#myNavbar" aria-controls="myNavbar"
            aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"/>
    </button>
    <div class="collapse navbar-collapse" id="myNavbar">
        <ul class="navbar-nav mr-md-auto">

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

                <security:authorize access="hasRole('STUDENT')">
                    <li class="nav-item">
                        <div class="dropdown show">
                            <a class="nav-link dropdown-toggle" href="#" role="button"
                               id="dropdownMenuLinkForStudent"
                               data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                Student
                            </a>

                            <div class="dropdown-menu" aria-labelledby="dropdownMenuLinkForStudent">
                                <a class="dropdown-item ${param['name'] == 'scholarship' ? ' active':''}"
                                   href="/scholarships/list">Stypendia</a>
                                <a class="dropdown-item ${param['name'] == 'bankAccount' ? ' active':''}"
                                   href="/bankAccountNumber/show">Konto w banku</a>
                                <a class="dropdown-item ${param['name'] == 'condition' ? ' active':''}"
                                   href="/conditions/list">Warunki</a>
                                <a class="dropdown-item ${param['name'] == 'courseChange' ? ' active':''}"
                                   href="/courseChange/list">Kierunek studiów</a>
                            </div>
                        </div>
                    </li>
                </security:authorize>
                <security:authorize access="hasRole('INSTRUCTOR')">
                    <li class="nav-item">
                        <div class="dropdown show">
                            <a class="nav-link dropdown-toggle" href="#" role="button"
                               id="dropdownMenuLinkForInstructor"
                               data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                Wykładowca
                            </a>

                            <div class="dropdown-menu" aria-labelledby="dropdownMenuLinkForInstructor">
                                <a class="dropdown-item ${param['name'] == 'condition' ? ' active':''}"
                                   href="/conditions/list">Warunki</a>
                            </div>
                        </div>
                    </li>
                </security:authorize>
                <security:authorize access="hasRole('DEAN')">
                    <li class="nav-item">
                        <div class="dropdown show">
                            <a class="nav-link dropdown-toggle" href="#" role="button"
                               id="dropdownMenuLinkForDean"
                               data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                Dziekan
                            </a>

                            <div class="dropdown-menu" aria-labelledby="dropdownMenuLinkForDean">
                                <a class="dropdown-item ${param['name'] == 'scholarship' ? ' active':''}"
                                   href="/scholarships/list">Stypendia</a>
                                <a class="dropdown-item ${param['name'] == 'condition' ? ' active':''}"
                                   href="/conditions/list">Warunki</a>
                                <a class="dropdown-item ${param['name'] == 'courseChange' ? ' active':''}"
                                   href="/courseChange/list">Zmiana kierunku</a>
                            </div>
                        </div>
                    </li>
                </security:authorize>
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
