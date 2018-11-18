<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Wyślij wiadomość</title>
</head>
<body>
<jsp:include page="shared/header.jsp"/>
<div class="container">
    <form:form modelAttribute="message" action="/msg/send">

        <div class="form-group">
            <form:input path="receiver.mail" cssClass="form-control" placeholder="Mail odbiorcy"
                        autofocus="true" cssErrorClass="form-control is-invalid"/>
        </div>

        <div class="form-group">
            <form:textarea rows="7" path="text" cssClass="form-control"
                        placeholder="Treść" cssErrorClass="form-control is-invalid"/>
        </div>


        <div class="row">
            <div class="col-xs-6 col-sm-6 col-md-6">
                <button type="submit" class="btn btn-raised btn-success">Wyślij wiadomość!</button>
            </div>
            <div class="col-xs-6 col-sm-6 col-md-6">
            </div>
        </div>
    </form:form>
</div>
<jsp:include page="shared/footer.jsp"/>
</body>
</html>
