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

            <c:forEach items="${list}" var="opn">
                <tr>

                    <td>
                        <c:choose>
                            <c:when test="${empty opn.opinion}">Brak danych</c:when>
                            <c:otherwise>${opn.opinion}</c:otherwise>
                        </c:choose>
                    </td>

                    <td>
                        Status opinii dla admina
                    </td>

                </tr>


            </c:forEach>
        </table>

        </c:if>

    <c:if test="${empty list}">
        Brak opinii na temat tego wykładowcy.
    </c:if>

<br><a class="btn btn-raised btn-success" href="/instructors/opinions/${id}/add">Dodaj opinię</a><br><br><br>

</div>
<jsp:include page="shared/footer.jsp"/>
</body>
</html>
