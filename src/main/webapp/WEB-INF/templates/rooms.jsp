<%@ page contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01
Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <jsp:include page="sections/head.jsp">
        <jsp:param name="titleName" value="Rooms"/>
    </jsp:include>
<body>
<div class="container-fluid">

    <jsp:include page="sections/navbar.jsp"/>

    <div class="row m-2">
        <h1 class="text-center">Rooms</h1>
    </div>

    <div class="row">
        <form:form action="/rooms" method="post" modelAttribute="roomToAdd">
            <form:hidden path="hotelId"  />

            <div class="mb-3">
                <label for="roomNumber" class="form-label">Hotel name</label>
                <form:input path="roomNumber" type="text" class="form-control" id="roomNumber" placeholder="Enter room number"/>
                <div class="invalid-feedback d-block">
                    <form:errors path="roomNumber" />
                </div>
            </div>

            <div class="mb-3">
                <button class="btn btn-success" type="submit">Add room</button>
            </div>

        </form:form>
    </div>

    <div class="row">
        <c:choose>
            <c:when test="${empty rooms}">
                <h4>There are no rooms</h4>
            </c:when>
            <c:otherwise>
                <table class="table table-hover table-bordered">
                    <tr>
                        <th scope="col">ID</th>
                        <th scope="col">Room number</th>
                        <th scope="col">Action</th>
                    </tr>
                    <c:forEach items="${rooms}" var="room">
                        <tr>
                            <td>${room.id}</td>
                            <td>${room.roomNumber}</td>
                            <td>
                                <a href="<c:url value="/rooms/delete/${room.id}"/>"
                                   class="btn btn-danger btn-sm">Delete</a>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </c:otherwise>
        </c:choose>
    </div>

</div>
</body>
</html>