<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
<body>
<div id="footer">
    <div class="container">
    <footer class="blockquote-footer">
        <br>© 2019 Inżynieria oprogramowania
        <sec:authorize access="isAuthenticated()">
            <sec:authentication property="principal.authorities"/>
        </sec:authorize>
    </footer>
    </div>
</div>
</body>
</html>