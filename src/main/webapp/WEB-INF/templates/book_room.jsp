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
            <h1 class="text-center">Book room</h1>
        </div>

        <div class="row">
            <form:form modelAttribute="bookRoomRequest" method="post" action="/book/process">
                <form:hidden path="start" />
                <form:hidden path="end" />
                <div class="mb-3">
                    <label for="selectRoom" class="form-label">Choose room</label>
                    <form:select class="form-select" path="roomId" id="selectRoom">
                        <c:forEach items="${availableRooms}" var="room">
                            <option value="${room.id}">${room.roomNumber}</option>
                        </c:forEach>
                    </form:select>
                </div>
                <div class="mb-3">
                    <button class="btn btn-success" type="submit">Submit</button>
                </div>
            </form:form>
        </div>

    </div>
</body>
</html>