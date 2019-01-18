<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Komputer
  Date: 15.01.2019
  Time: 16:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Oceny semestralne</title>
</head>
<body>
<jsp:include page="shared/header.jsp"/>
<div class="container">
    <c:if test="${!empty list}">
        <table class="table table-bordered">
            <tr class="bg-success">
                <th>Przedmiot</th>
                <th>Student</th>
                <th>Pierwszy termin egzaminu</th>
                <th>Drugi termin egzaminu</th>
                <th>Ocena końcowa</th>
                <th>Zapisz</th>
            </tr>

            <c:forEach items="${list}" var="grade">
                <tr>
                    <form:form action="/protocol/grades?grade=${grade.id}&id=${param.id}&subj=${param.subj}"
                               method="post" modelAttribute="grade">

                        <td>
                                ${grade.subject.name}
                        </td>
                        <td>
                                ${grade.student.name} ${grade.student.surname}
                        </td>

                        <td><form:input path="firstGrade" value="${grade.firstTerminGrade}"/>

                        </td>

                        <td><form:input path="secondGrade" value="${grade.secondTerminGrade}"/>
                        </td>

                        <td><form:input path="totalGrade" value="${grade.totalGrade}"/>
                        </td>
                        <td>
                            <button type="submit" class="btn btn-raised btn-success">Zapisz</button></td>
                    </form:form>
                </tr>
            </c:forEach>
        </table>
    </c:if>

    <c:if test="${empty list}">
        Brak
    </c:if>
</div>

<jsp:include page="shared/footer.jsp"/>
</body>
</html>
