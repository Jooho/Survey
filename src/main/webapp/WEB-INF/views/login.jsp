<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="true" %>
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>Welcome to Survey Login page</title>
    <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.css"/>">
    <link rel="stylesheet" href="<c:url value="/resources/css/survey.css"/>">

</head>
<body>

<div class="container">
    <form class="form-signin" role="form" action="login" method="POST">
        <h2 class="form-signin-heading">Welcome to <span style="color: red">Survey</span></h2>
        <br>
        <label for="inputUserName" class="sr-only">User Name</label>
        <input type="userName" id="inputUserName" name="userName" class="form-control" placeholder="User Name" required autofocus>
        <label for="inputPassword" class="sr-only">Password</label>
        <input type="passwd" id="inputPassword" name="passwd" class="form-control" placeholder="Password" required>

        <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
        <div class="text-center">${msg}</div>

    </form>


</div> <!-- /container -->
<div class="mastfoot">
    <div class="inner">
        <p>Survey game for <a href="http://redhat.com">Red Hat</a>, by <a href="http://github.com/jooho">Jooho</a>.</p>
        <img src="/resources/images/logo-red-hat-black.png" />
    </div>
</div>

</body>
</html>
