<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <title>Murach's Java Servlets and JSP</title>
    <link rel="stylesheet" href="styles/main.css" type="text/css"/>
</head>

<body>
    <h1>Join our email list</h1>
    <p>To join our email list, enter your name and
        email address below.</p>
    <form action="emailList" method="post">
        <p><i>${message}</i></p>
        <input type="hidden" name="action" value="add">

        <label for="email" class="pad_top">Email:</label>
        <input id="email" type="email" name="email" value="${user.email}" required><br>

        <label for="first_name" class="pad_top">First Name:</label>
        <input id="first_name" type="text" name="firstName" value="${user.firstName}" required><br>

        <label for="last_name" class="pad_top">Last Name:</label>
        <input id="last_name" type="text" name="lastName" value="${user.lastName}" required><br>

        <label>&nbsp;</label>
        <input type="submit" value="Join Now" class="margin_left">
    </form>
</body>
</html>
