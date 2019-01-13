<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="jap" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Lista pojazdów</title>
</head>
<body>

<jsp:include page="shared/header.jsp">
    <jsp:param name="name" value="instructorList"/>
</jsp:include>
<div class="container">

    <c:if test="${!empty list}">
        <table class="table table-bordered">
            <tr class="bg-success">
                <th>Przedmiot</th>
                <th>Lekcje</th>

            </tr>

            <c:forEach items="${list}" var="subject">
                    <tr>

                        <td>
                            <c:choose>
                                <c:when test="${empty subject.name}">Brak danych</c:when>
                                <c:otherwise>${subject.name}</c:otherwise>
                            </c:choose>
                        </td>

                        <td><a class="btn btn-raised btn-info" href="/subjects/${subject.id}/lessons/list">Lekcje</a></td>

                    </tr>
            </c:forEach>
        </table>


    </c:if>

    <c:if test="${empty list}">
        Brak przedmiotów w bazie.
    </c:if>


</div>
<jsp:include page="shared/footer.jsp"/>
</body>
</html>
