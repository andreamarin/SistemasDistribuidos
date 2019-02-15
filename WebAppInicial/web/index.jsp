<%-- 
    Document   : index
    Created on : 13/02/2019, 04:49:45 PM
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
        <table border="1">
            <thead>
                <tr>
                    <th>Descripción</th>
                    <th>Valor</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td>Apellido paterno:</td>
                    <td><input type="text" name="ap_paterno" value="" /></td>
                </tr>
                <tr>
                    <td>Apellido materno:</td>
                    <td><input type="text" name="ap_materno" value="" /></td>
                </tr>
                <tr>
                    <td>Género</td>
                    <td>
                        <input type="radio" name="sexo" value="Hombre" disabled="disabled" />
                        <input type="radio" name="sexo" value="Mujer" disabled="disabled" />
                    </td>
                </tr>
                <tr>
                    <td>Región del país:</td>
                    <td><select name="reg">
                            <option>Norte</option>
                            <option>Centro</option>
                            <option>Sur</option>
                        </select></td>
                </tr>
                <tr>
                    <td>Comentarios adicionales</td>
                    <td><textarea name="comment" rows="4" cols="20">
                        </textarea></td>
                </tr>
                <tr>
                    <td>Foto:</td>
                    <td></td>
                </tr>
                <tr>
                    <td>Tres países donde me gustaría vivir</td>
                    <td></td>
                </tr>
                <tr>
                    <td>Mi sitio web favorito es:</td>
                    <td></td>
                </tr>
                <tr>
                    <td>Tiene todas las vacunas</td>
                    <td><input type="checkbox" name="vacunas" value="OFF" /></td>
                </tr>
                <tr>
                    <td><input type="submit" value="Limpiar" /></td>
                    <td><input type="submit" value="Enviar" /></td>
                </tr>
            </tbody>
        </table>

    </body>
</html>
