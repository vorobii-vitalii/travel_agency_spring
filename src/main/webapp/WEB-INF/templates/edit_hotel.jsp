<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <link href="<c:url value="/resources/bootstrap.min.css"/>" rel="stylesheet" type="text/css"/>
    <title>Add new hotel</title>
</head>
<body>
<div class="container-fluid">

    <div class="row m-2">
        <h1 class="text-center">Edit ${hotel.name} hotel</h1>
    </div>

    <div class="row">
        <form:form action="/hotels/save" method="post" modelAttribute="hotel">
            <form:hidden path="id" />

            <div class="mb-3">
                <label for="hotelName" class="form-label">Hotel name</label>
                <form:input path="name" type="text" class="form-control" id="hotelName" placeholder="Enter hotel name"/>
            </div>

            <div class="mb-3">
                <label for="hotelDescription" class="form-label">Hotel description</label>
                <form:textarea path="description" class="form-control" id="hotelDescription" rows="3"></form:textarea>
            </div>

            <div class="mb-3">
                <label for="hotelCountry" class="form-label">Hotel country</label>
                <form:input path="country" class="form-control" list="datalistOptions" id="hotelCountry" placeholder="Enter the country" />
                <datalist id="datalistOptions">
                    <option value="Ukraine">
                    <option value="USA">
                    <option value="Italy">
                    <option value="Canada">
                    <option value="Spain">
                </datalist>
            </div>

            <div class="mb-3">
                <button class="btn btn-success" type="submit">Submit changes</button>
            </div>

        </form:form>
    </div>

</div>
</body>
</html>
