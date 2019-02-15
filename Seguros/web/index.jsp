<%-- 
    Document   : index
    Created on : 15/02/2019, 05:26:39 PM
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
        <form name="datos" action="vehiculo.jsp">
            <h1>Sistema de cotización de seguros</h1>
            <p>Datos personales </p>
            <table border="1">
                <thead>
                    <tr>
                        <th>Campo</th>
                        <th>Valor</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>Nombre</td>
                        <td><input type="text" name="nombre" value="" /></td>
                    </tr>
                    <tr>
                        <td>Apellidos:</td>
                        <td><input type="text" name="apellido" value="" /></td>
                    </tr>
                    <tr>
                        <td>Género:</td>
                        <td>
                            <input type="radio" name="gen" value="masculino" />Hombre
                            <input type="radio" name="gen" value="femenino" />Mujer
                        </td>
                    </tr>
                    <tr>
                        <td>Edad:</td>
                        <td><input type="text" name="edad" value="" /></td>
                    </tr>
                    <tr>
                        <td>Estado</td>
                        <td><select name="edo">
                                <option>CDMX</option>
                                <option>Puebla</option>
                                <option>Nuevo León</option>
                            </select></td>
                    </tr>
                    <tr>
                        <td><input type="reset" value="Limpiar" /></td>
                        <td><input type="submit" value="Enviar" name="env" /></td>
                    </tr>
                </tbody>
            </table>

        </form>
        
    </body>
</html>
