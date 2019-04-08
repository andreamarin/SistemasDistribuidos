<%-- 
    Document   : index
    Created on : 22/02/2019, 05:21:59 PM
    Author     : Andrea Marín y Luis Felipe Landa
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SugeCine</title>
    </head>
    <body>
        <h1>¡Bienvenido a SugeCine!</h1>
        <p> Te vamos a recomendar una película </p>
        <form action="GetSuggestion">
            <table border="1">
                <tbody>
                    <tr>
                        <td><p> Zona: </p></td>
                        <td><select name="zona">
                                <option>Norte</option>
                                <option>Centro</option>
                                <option>Sur</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td><p>Tipo de película</p></td>
                        <td><select name="tipo">
                                <option>Comedia</option>
                                <option>Accion</option>
                                <option>Drama</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td><input type="reset" value="Limpiar" /></td>
                        <td><input type="submit" value="Disfrutar recomendacion" /></td>
                    </tr>
                </tbody>
            </table>
        </form>
    </body>
</html>
