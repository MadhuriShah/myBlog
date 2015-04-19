<%-- 
    Document   : login
    Created on : 19-Apr-2015, 2:22:24 AM
    Author     : c0647610
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
         <link href="https://bootswatch.com/flatly/bootstrap.min.css" rel="stylesheet" />
        
        <title>JSP Page</title>
    </head>
    <body>
        <center>
         <form action="LoginServlet" method="post">
             <h1>Welcome to my Blog</h1>
             <h3> Login </h3>
             
             <div class="form-group">
             <div class="container">
            <p>Username: <input type="Text"  name="username" /></p>
            <p>Password: <input type="password"  name="password" /> </p>
            <p><input type="Submit" class="btn btn-success" name="submit" value="submit"/></p>            
             </div>
             </div>
        </form>
        </center>    </body>
</html>
