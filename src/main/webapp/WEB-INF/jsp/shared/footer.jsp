<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
<body>
<div id="footer">
    <footer class="blockquote-footer">
        <br>© 2018 Platformy programowania
        <sec:authorize access="isAuthenticated()">
            <sec:authentication property="principal.authorities"/>
        </sec:authorize>
    </footer>
</div>
</body>
</html>