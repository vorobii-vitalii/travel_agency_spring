<%@ page contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01
Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<jsp:include page="sections/head.jsp">
    <jsp:param name="titleName" value="Hotels"/>
</jsp:include>
<body>
<div class="container-fluid">

    <jsp:include page="sections/navbar.jsp"/>

    <div class="row m-2">
        <h1 class="text-center">Hotel Management</h1>
    </div>

    <div class="row mt-4 mb-4">
        <h3>
            <a href="<c:url value="/hotels/new" />" class="btn btn-success btn-lg" >Add Hotel</a>
        </h3>
    </div>

    <div class="row">

        <table class="table table-hover table-bordered">
            <tr>
                <th scope="col">ID</th>
                <th scope="col">Name</th>
                <th scope="col">Description</th>
                <th scope="col">Country</th>
                <th scope="col">Action</th>
            </tr>
            <c:forEach items="${hotels}" var="hotel">
                <tr>
                    <td>${hotel.id}</td>
                    <td>${hotel.name}</td>
                    <td>${hotel.description}</td>
                    <td>${hotel.country}</td>
                    <td>
                        <a href="<c:url value="/rooms/${hotel.id}"/>"
                           class="btn btn-outline-secondary btn-sm">Rooms</a>
                        <a href="<c:url value="/hotels/edit/${hotel.id}"/>"
                           class="btn btn-info btn-sm">Edit</a>
                        <a href="<c:url value="/hotels/delete/${hotel.id}"/>"
                           class="btn btn-danger btn-sm">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>

</div>
</body>
</html>