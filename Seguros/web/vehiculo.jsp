<%-- 
    Document   : vehiculo
    Created on : 15/02/2019, 05:31:33 PM
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
        <form name="auto" action="cotizacion.jsp">
            <%
                if(request.getParameter("nombre")!=null){
                    String nombre = request.getParameter("nombre");
                    out.println("<p> Estimado(a) Sr(a). "+nombre+"</p>"
                            + "<input  type = 'hidden' name = 'nombre' value = '"+nombre+"' / >");
                }
                
                String genero = "Desconocido";
                if(request.getParameter("gen")!=null){
                    genero = request.getParameter("gen");
                }
                out.println("<p>Género: "+genero+"</p> <input  type = 'hidden' name = 'gen' value = '"+genero+"' / >");
                
                String edad = "Desconocida";
                if(request.getParameter("edad")!=null){
                    edad = request.getParameter("edad");
                }
                out.println("<p>Edad: "+edad+"</p>");
                out.println("<input  type = 'hidden' name = 'edad' value = '"+edad+"' / >");
                
                if(request.getParameter("edo")!=null){
                    String edo = request.getParameter("edo");
                    out.println("<p>Estado: "+edo+"</p><input  type = 'hidden' name = 'edo' value = '"+edo+"' / >");
                }
               
            %>
            
            <p>Datos del auto: </p>
            <table border="1">
                <thead>
                    <tr>
                        <th>Campo</th>
                        <th>Valor</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>Marca:</td>
                        <td><select name="marca">
                                <option>Toyota</option>
                                <option>Chevrolet</option>
                                <option>Mercedes Benz</option>
                            </select></td>
                    </tr>
                    <tr>
                        <td>Modelo</td>
                        <td><input type="text" name="modelo" value="" /></td>
                    </tr>
                    <tr>
                        <td>Placas</td>
                        <td><input type="text" name="placas" value="" /></td>
                    </tr>
                    <tr>
                        <td><input type="reset" value="Limpiar" /></td>
                        <td><input type="submit" value="Enviar" /></td>
                    </tr>
                </tbody>
            </table>

        </form>
    </body>
</html>
