<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Domyślny widok błędu w Spring Boot</h1>
<div>

    Wiadomość: <span>${message}</span></br>
    Wyjątek: <span>${exception}</span></br>
    <!--
        StackTrace: <span>${stackTrace}</span>
        -->

</div>
</body>
</html>
