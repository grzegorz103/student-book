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

    <h2>Data: ${lesson.lessonDate}</h2>
    <h2>Przedmiot: ${lesson.subject.name}</h2>

    <c:if test="${!empty list}">
        <table class="table table-bordered">
            <tr class="bg-success">
                <th>Student</th>
                <th>Obecność</th>
                <th>Zmiana</th>

            </tr>

            <c:forEach items="${list}" var="attendance">
                    <tr>

                        <td>
                            <c:choose>
                                <c:when test="${empty attendance.student}">Brak danych</c:when>
                                <c:otherwise>${attendance.student.name} ${attendance.student.surname}</c:otherwise>
                            </c:choose>
                        </td>

                        <td>
                            <c:choose>
                                <c:when test="${empty attendance.presence}">Brak danych</c:when>
                                <c:when test="${attendance.presence}">Obecny</c:when>
                                <c:otherwise>Nieobecny</c:otherwise>
                            </c:choose>
                        </td>

                        <td>
                            <c:choose>
                                <c:when test="${empty attendance.presence}">Brak danych</c:when>
                                <c:when test="${attendance.presence}">
                                    <a class="btn btn-raised btn-danger" href="/subjects/${lesson.subject.id}/lessons/${lesson.id}/attendances/${attendance.id}/status/1">Wstaw nieobecność</a>
                                </c:when>
                                <c:otherwise>
                                    <a class="btn btn-raised btn-success" href="/subjects/${lesson.subject.id}/lessons/${lesson.id}/attendances/${attendance.id}/status/2">Wstaw obecność</a>
                                </c:otherwise>
                            </c:choose>

                        </td>

                    </tr>
            </c:forEach>
        </table>


    </c:if>

    <c:if test="${empty list}">
        Brak listy obecności dla tej lekcji.
        <br><a class="btn btn-raised btn-success" href="/subjects/${lesson.subject.id}/lessons/${lesson.id}/attendances/add">Dodaj listę obecności</a><br><br><br>
    </c:if>


</div>
<jsp:include page="shared/footer.jsp"/>
</body>
</html>
