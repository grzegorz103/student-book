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
    <jsp:param name="name" value="opinionList"/>
</jsp:include>


    <c:if test="${!empty list}">
        <table class="table table-bordered">
            <tr class="bg-success">
                <th>Opinia</th>
                <th>Status</th>
            </tr>

            <c:forEach items="${list}" var="opinion">
                <tr>

                    <td>
                        <c:choose>
                            <c:when test="${empty opinion.opinion}">Brak danych</c:when>
                            <c:otherwise>${opinion.opinion}</c:otherwise>
                        </c:choose>
                    </td>

                    <td>
                        Status opinii dla admina
                    </td>

                </tr>
            </c:forEach>
        </table>

        <a class="btn btn-raised btn-success" href=/instructors/opinions/add.html">Dodaj opinię</a><br><br><br>
    </c:if>

    <c:if test="${empty list}">
        Brak wykładowców w bazie
    </c:if>


</div>
<jsp:include page="shared/footer.jsp"/>
</body>
</html>
