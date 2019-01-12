<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>Formularz</title>
</head>
<body>
<jsp:include page="shared/header.jsp">
    <jsp:param name="name" value="opinionForm"/>
</jsp:include>

<div id="main" class="container">

    <form:form method="POST" modelAttribute="opn">
        <h2>Twoja opinia na temat ${instructor.name} ${instructor.surname}:</h2>

        <div class="form-group">
            <label for="subject"></label>
            <form:select path="subject" cssClass="form-control">
                <form:options items="${instructor.subject}" itemLabel="name" itemValue="id"/>
            </form:select>
        </div>

        <div class="form-group">
            <label for="opinion"></label>
            <form:textarea path="opinion" cssClass="form-control"/>
            <form:errors path="opinion" cssStyle="color:red"/>
        </div>


        <button type="submit" class="btn btn-raised btn-primary">OK</button>

    </form:form>
</div>

<jsp:include page="shared/footer.jsp"/>
</body>
</html>