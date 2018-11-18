<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Komputer
  Date: 12.01.2019
  Time: 01:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Wybór specjalizacji</title>
</head>
<body>
<jsp:include page="shared/header.jsp"/>
<div class="container">
    <br/>
    <form:form method="POST" modelAttribute="student" action="/sp/choose?id=${student.id}">

        <table>

            <tr>
                <td>Specjalizacja :</td>
                <td><form:select path="specialization">
                    <form:options items="${list}" itemLabel="name"/>
                </form:select>
                </td>
            </tr>
            <tr>
                <td colspan="3"><input type="submit"/></td>
            </tr>
        </table>
    </form:form>
</div>
<jsp:include page="shared/footer.jsp"/>
</body>
</html>
