<%-- 
    Document   : logout
    Created on : 19-Apr-2015, 3:01:53 AM
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
     <%   session.invalidate();
        response.sendRedirect("show");
        %>
    </body>
</html>
