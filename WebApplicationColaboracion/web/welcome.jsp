<%-- 
    Document   : welcome
    Created on : 22/02/2019, 04:56:59 PM
    Author     : sdist
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Welcome</title>
    </head>
    <body>
        <%
            String name = request.getParameter("usr");
            
            if(name != null){
                out.println("<h4>Bienvenido "+name+"</h4>");
            }
        %>
    </body>
</html>
