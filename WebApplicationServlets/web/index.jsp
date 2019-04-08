<%-- 
    Document   : index
    Created on : 18/02/2019, 05:02:25 PM
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
        <form action="ServletOpinion">
            <h1>Encuesta Importante sobre cosas: </h1>
            <p> Nombre: </p><input type="text" name="nom" value="" />
            <p> Apellidos: </p><input type="text" name="ap" value="" />
            <p> ¿Te gustan los servlets? </p>
            <input type="radio" name="op" value="si" />Sí
            <input type="radio" name="op" value="No" />No
            <p> Comentarios adicionales: </p>
            <textarea name="com" rows="4" cols="20">
            </textarea>
            <br>
            <input type="submit" value="Enviar" /><input type="reset" value="Limpiar" />
        </form>
    </body>
</html>
