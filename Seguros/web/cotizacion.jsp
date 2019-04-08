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
            
            out.println("<p>En función a los datos proporcionados:</p>");
            
            if(request.getParameter("gen") != null)
                out.println("<p>Género: "+request.getParameter("gen")+"</p>");
            
            if(request.getParameter("edad") != null)
                out.println("<p>Edad: "+request.getParameter("edad")+"</p>");
            
            if(request.getParameter("edo") != null)
                out.println("<p>Estado "+request.getParameter("edo")+"</p>");
            
            String marca = "";
            if(request.getParameter("marca") != null){
                marca = request.getParameter("marca");
                out.println("<p>Marca "+marca+"</p>");
            }
            
            if(request.getParameter("modelo") != null)
                out.println("<p>Modelo "+request.getParameter("modelo")+"</p>");
            
            if(request.getParameter("placas") != null)
                out.println("<p>Placas "+request.getParameter("placas")+"</p>");
            
            out.println("<b> La cotización de su seguro es: </b>");
            String precio = "";
            if(marca.equals("Chevrolet"))
                precio = "$15,000";
            else if(marca.equals("Toyota"))
                precio = "$20,000";
            else
                precio = "$50,000";
            out.println("<h1>"+precio+" pesos</h1>");
                
        %>
    </body>
</html>
