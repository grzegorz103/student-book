<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Pojazd</title>
</head>
<body>

<jsp:include page="shared/header.jsp">
    <jsp:param name="name" value="vehicleDetails"/>
</jsp:include>

<table class="table table-bordered">
    <tr class="bg-success">
        <th>ID</th>
        <th>Nazwa</th>
        <th>Model</th>
        <th>Data produkcji</th>
        <th>Cena</th>
        <th>Uszkodzenia</th>
        <th>Typ</th>
    </tr>
    <tr class="bg-success">
        <td>${empty vehicle.id ? "Brak danych" : vehicle.id}</td>
        <td>${empty vehicle.name ? "Brak danych" : vehicle.name}</td>
        <td>${empty vehicle.model ? "Brak danych" : vehicle.model}</td>
        <td>${empty vehicle.productionDate ? "Brak danych" : vehicle.productionDate}</td>
        <td>${empty vehicle.price ? "Brak danych" : vehicle.price}</td>
        <td>${empty vehicle.broken ? "Brak danych" : vehicle.broken}</td>
        <td>${empty vehicle.type.name ? "Brak danych" : vehicle.type.name}</td>
    </tr>
</table>

<jsp:include page="shared/footer.jsp"/>

</body>
</html>
