<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Komputer
  Date: 18.11.2018
  Time: 17:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Protokoły</title>
</head>
<body>
<jsp:include page="shared/header.jsp"/>
<div class="container">
    <c:if test="${!empty list}">
        <h2>Protokoły</h2>
        <table class="table table-bordered">
            <tr class="bg-success">
                <th>Id</th>
                <th>I termin</th>
                <th>II termin</th>
                <th>Przedmiot</th>
                <th>Status</th>
                <th>Akcja</th>

            </tr>

            <c:forEach items="${list}" var="prot">
                <tr>

                    <td>${prot.id}
                    </td>

                    <td>${prot.firstTermin}</td>

                    <td>${prot.secondTermin}</td>

                    <td>${prot.subject.name}</td>

                    <td><c:if test="${prot.status == 'PROTOCOLE_CLOSED'}">Zamknięty</c:if>
                        <c:if test="${prot.status == 'PROTOCOLE_OPEN'}">
                            Otwarty
                        </c:if>
                        <c:if test="${prot.status == 'PROTOCOLE_ACCEPTED'}">Zatwierdzony</c:if>
                    </td>

                    <td><a href="/protocol/editState/${prot.id}">Zmiana statusu</a></td>
                </tr>
            </c:forEach>
        </table>
    </c:if>

    <c:if test="${empty list}">
        Brak protokołów
    </c:if>
    <hr>
    <c:if test="${!empty awaiting}">

        <h2>Przedmioty oczekujące na utworzenie protokołów</h2>
        <table class="table table-bordered">
            <tr class="bg-success">
                <th>Id</th>
                <th>Nazwa</th>
                <th>Kierunek</th>
                <th>Semestr</th>
                <th>Prowadzący</th>
            </tr>

            <c:forEach items="${awaiting}" var="subj">
                <tr>

                    <td>${subj.id}
                    </td>

                    <td>${subj.name}</td>

                    <td>${subj.course.name}</td>

                    <td>${subj.semester}</td>

                    <td>${subj.instructor.name}</td>
                </tr>
            </c:forEach>
        </table>
        <a class="btn btn-raised btn-success" href="/protocol/add">Dodaj protokoły</a></td>

    </c:if>

    <c:if test="${empty awaiting}">
        Brak przedmiotów oczekujących na utworzenie protokołów
    </c:if>

    <hr>
    <c:if test="${!empty errors}">

        <h2>Błędy związane z protokołami</h2>
        <table class="table table-bordered">
            <tr class="bg-success">
                <th>Id błędu</th>
                <th>Treść</th>
                <th>Protokół ID</th>
                <th>Data zgłoszenia</th>
            </tr>

            <c:forEach items="${errors}" var="err">
                <tr>

                    <td>${err.id}</td>

                    <td>${err.text}</td>

                    <td>${err.protocol.id}</td>

                    <td>${err.date}</td>

                </tr>
            </c:forEach>
        </table>
    </c:if>

    <c:if test="${empty errors}">
        Brak błędów związanych z protokołami
    </c:if>

</div>
<jsp:include page="shared/footer.jsp"/>
</body>
</html>
