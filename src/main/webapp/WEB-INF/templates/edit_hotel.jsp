<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<jsp:include page="sections/head.jsp">
    <jsp:param name="titleName" value="Edit hotel"/>
</jsp:include>
<body>
<div class="container-fluid">

    <jsp:include page="sections/navbar.jsp"/>

    <div class="row m-2">
        <h1 class="text-center">Edit ${hotel.name} hotel</h1>
    </div>

    <div class="row">
        <form:form action="/hotels/edit" method="post" modelAttribute="hotel">
            <form:hidden path="id" />

            <div class="mb-3">
                <label for="hotelName" class="form-label">Hotel name</label>
                <form:input path="name" type="text" class="form-control" id="hotelName" placeholder="Enter hotel name"/>
                <div class="invalid-feedback d-block">
                    <form:errors path="name" />
                </div>
            </div>

            <div class="mb-3">
                <label for="hotelDescription" class="form-label">Hotel description</label>
                <form:textarea path="description" class="form-control" id="hotelDescription" rows="3"></form:textarea>
            </div>

            <div class="mb-3">
                <label for="hotelCountry" class="form-label">Hotel country</label>
                <form:input path="country" class="form-control " list="datalistOptions" id="hotelCountry" placeholder="Enter the country" />
                <datalist id="datalistOptions">
                    <c:forEach items="${countries}" var="country">
                        <option value="${country.name}">
                    </c:forEach>>
                </datalist>
                <div class="invalid-feedback d-block">
                    <form:errors path="country" />
                </div>
            </div>

            <div class="mb-3">
                <button class="btn btn-success" type="submit">Submit changes</button>
            </div>

        </form:form>
    </div>

</div>
</body>
</html>
