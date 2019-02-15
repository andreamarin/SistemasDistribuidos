<%-- 
    Document   : cotizacion
    Created on : 15/02/2019, 05:53:12 PM
    Author     : sdist
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Sistema de cotización de seguros de auto</h1>
        <%
            if(request.getParameter("nombre") != null)
                out.println("<b>Estimado Sr(a): "+request.getParameter("nombre")+"</b>");
            
            out.println("<p>En función a los datos proporcionados:</p><br>");
            
            if(request.getParameter("gen") != null)
                out.println("<p>Género: "+request.getParameter("gen")+"</p>");
            
            if(request.getParameter("edad") != null)
                out.println("<p>Edad: "+request.getParameter("edad")+"</p>");
            
            if(request.getParameter("edo") != null)
                out.println("<p>Estado "+request.getParameter("edo")+"</p>");
                    
        %>
    </body>
</html>
