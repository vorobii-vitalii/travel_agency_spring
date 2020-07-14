<%@ page contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01
Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<jsp:include page="sections/head.jsp">
    <jsp:param name="titleName" value="User details"/>
</jsp:include>
<body>
<div class="container-fluid">

    <div class="row m-2">
        <h1 class="text-center">User details of ${user.name}</h1>
    </div>

    <div class="row">

        <ul class="list-group">
            <li class="list-group-item">Name: ${user.name}</li>
            <li class="list-group-item">Email: ${user.email}</li>
            <li class="list-group-item">Has management status: &nbsp;
                <c:choose>
                    <c:when test="${isManager == true}">
                        Yes
                    </c:when>
                    <c:otherwise>
                        No
                    </c:otherwise>
                </c:choose>
            </li>
        </ul>

        <form action="" method="post">
            <div class="mb-3">
                <button class="btn btn-success" type="submit">Toggle management status</button>
            </div>
        </form>

    </div>

</div>
</body>
</html>