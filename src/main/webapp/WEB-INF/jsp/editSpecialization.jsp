<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Komputer
  Date: 13.01.2019
  Time: 16:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edytuj specjalizacje</title>
</head>
<body>
<jsp:include page="shared/header.jsp"/>
<div class="container">
    <h2>Wybory specjalizacji</h2>
    Wybór specjalizacji jest aktualnie <c:if
        test="${state}">włączone. Zamknięcie specjalizacji spowoduje zatwierdzenie wyboru specjalizacji oraz automatyczne przypisanie specjalizacji tym studentom, którzy nie wybrali żadnej</c:if><c:if
        test="${!state}">wylaczone</c:if>
    <br/>Aby zmienić naciśnij przycisk<br/>
    <div class="col-xs-6 col-sm-6 col-md-6">
        <button type="submit" onclick="location.href='/sp/edit/state'" class="btn btn-raised btn-success">Zmień!
        </button>
    </div>
    <hr>
    <c:if test="${!empty list}">
        <table class="table table-bordered">
            <tr class="bg-success">
                <th>Id studenta</th>
                <th>Imię</th>
                <th>Nazwisko</th>
                <th>PESEL</th>
                <th>Kierunek</th>
                <th>Specjalizacja</th>
            </tr>

            <c:forEach items="${list}" var="student">
                <tr>
                    <td>${student.id}</td>

                    <td>${student.name}</td>

                    <td>${student.surname}</td>

                    <td>${student.pesel}</td>

                    <td>${student.course.name}</td>

                    <td>${empty student.specialization ? 'Brak' : student.specialization.name}</td>
                </tr>
            </c:forEach>
        </table>
    </c:if>

    <c:if test="${empty list}">
        Brak studentów
    </c:if>
</div>
<jsp:include page="shared/footer.jsp"/>
</body>
</html>
