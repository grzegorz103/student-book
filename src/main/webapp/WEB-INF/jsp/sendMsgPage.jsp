<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<jsp:include page="shared/header.jsp">
    <jsp:param name="name" value="loginForm"/>
</jsp:include>

<form:form modelAttribute="message" action="/msg/send">

    <div class="form-group">
        <form:input path="receiver.mail" cssClass="form-control" placeholder="Odbiorca"
                    autofocus="true" cssErrorClass="form-control is-invalid"/>
    </div>

    <div class="form-group">
        <form:input path="text" cssClass="form-control"
                       placeholder="Treść" cssErrorClass="form-control is-invalid"/>
    </div>


    <div class="row">
        <div class="col-xs-6 col-sm-6 col-md-6">
            <input type="submit" class="btn btn-lg btn-primary btn-block" value="Zarejestruj"/>
        </div>
        <div class="col-xs-6 col-sm-6 col-md-6">
        </div>
    </div>
</form:form>

<jsp:include page="shared/footer.jsp"/>
</body>
</html>
