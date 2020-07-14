<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
    <jsp:include page="sections/head.jsp">
        <jsp:param name="titleName" value="Register"/>
    </jsp:include>
<body>
<div class="container-fluid">

    <div class="row m-2">
        <h1 class="text-center">Registration form</h1>
    </div>

    <div class="row">
        <form:form action="/register" method="post" modelAttribute="userToRegister">

            <div class="mb-3">
                <label for="userName" class="form-label">Name</label>
                <form:input path="name" type="text" class="form-control" id="userName" placeholder="Enter name"/>
                <div class="invalid-feedback d-block">
                    <form:errors path="name" />
                </div>
            </div>

            <div class="mb-3">
                <label for="userEmail" class="form-label">Email address</label>
                <form:input path="email" type="email" class="form-control" id="userEmail" placeholder="name@example.com"/>
                <div class="invalid-feedback d-block">
                    <form:errors path="email" />
                </div>
            </div>

            <div class="mb-3">
                <label for="userPassword" class="form-label">Password</label>
                <form:input path="password" type="password" class="form-control" id="userPassword" placeholder="Your password" />
                <div class="invalid-feedback d-block">
                    <form:errors path="password" />
                </div>
            </div>

            <div class="mb-3">
                <button class="btn btn-success" type="submit">Register</button>
            </div>

        </form:form>
    </div>

</div>
</body>
</html>
