<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
	<title>Formularz</title>
</head>
<body>
<jsp:include page="shared/header.jsp">
	<jsp:param name="name" value="vehicleForm"/>
</jsp:include>

<div id="main" class="container">

	<form:form method="POST" modelAttribute="vehicle">

		<div class="form-group">
			<label for="name">Nazwa</label>
			<form:input path="name" cssClass="form-control"/>
			<form:errors path="name" cssStyle="color:red"/>
		</div>

		<div class="form-group">
			<label for="model">Model</label>
			<form:input path="model" cssClass="form-control"/>
			<form:errors path="model" cssStyle="color:red"/>
		</div>

		<div class="form-group">
			<label for="productionDate">Data produkcji</label>
			<form:input path="productionDate" cssClass="form-control"/>
			<form:errors path="productionDate" cssStyle="color:red"/>
		</div>

		<div class="form-group">
			<label for="price">Cena</label>
			<form:input path="price" cssClass="form-control"/>
			<form:errors path="price" cssStyle="color:red"/>
		</div>

		<div class="form-group">
			<label for="broken">Uszkodzenia</label>
			<form:select path="broken" cssClass="form-control">
				<form:option value="true"/>
				<form:option value="false"/>
			</form:select>
		</div>

		<div class="form-group">
			<label for="type.id">Typ</label>
			<form:select path="type.id" cssClass="form-control">
				<form:option value="-1">--wybierz typ pojazdu--</form:option>
				<form:options items="${types}" itemLabel="name" itemValue="id"/>
			</form:select>
		</div>

		<div class="mb-3">
			<label>Wyposażenie pojazdu</label>
			<ul class="list-group">
				<form:checkboxes path="accessories" items="${accessories}" element="div class='material-switch pull-right'"
								 itemLabel="name" itemValue="id"/>
				<form:errors path="accessories" cssClass="alert-danger"/><br>
			</ul>
		</div>

		<button type="submit" class="btn btn-raised btn-primary">OK</button>

	</form:form>
</div>

<jsp:include page="shared/footer.jsp"/>
</body>
</html>