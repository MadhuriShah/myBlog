<%-- 
    Document   : profile
    Created on : 18-Apr-2015, 2:49:53 PM
    Author     : c0647610
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Profile</h1>
        <Label>First Name: <input type="text" name="fname"></Label><br>
        <Label>Last Name: <input type="text" name="fname"></Label><br>
        <Label>Description: <textarea name="desc" rows="5" cols="50"> </textarea ></Label><br>
        <Label>Email: <input type="text" name="email"></Label><br>
        <Label>Phone: <input type="text" name="phone"></Label><br>
        <Label>Image: <input type="file" name="imageUrl"></Label><br>
        <input type="submit" value="submit">
        
    </body>
</html>
