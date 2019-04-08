<%-- 
    Document   : profile
    Created on : 1/03/2019, 04:38:16 PM
    Author     : andreamarin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            HttpSession mySession = request.getSession();
            
            if(mySession.getAttribute("user") != null){
                out.println("<p>Este es tu perfil,  "+mySession.getAttribute("user")+"</p>");
                out.println("<p>Edad: "+mySession.getAttribute("age")+"</p>");
                out.println("<p>Objeto: "+mySession.getAttribute("obj")+"</p>");
                out.println("<a href='SignOut'>Log out</a>");
            }else{
                response.sendRedirect("index.html");
            }
        %>
    </body>
</html>
