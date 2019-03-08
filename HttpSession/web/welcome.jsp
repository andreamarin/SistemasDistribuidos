<%-- 
    Document   : welcome
    Created on : 1/03/2019, 04:38:09 PM
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
        <h1>Welcome page</h1>
        
        <%
            if(request.getParameter("pswd") != null){
                
                if(request.getParameter("pswd").equals("123456")){
                    HttpSession mySession = request.getSession();
                    
                    String name = request.getParameter("usr");
                    String edad = request.getParameter("age");
                    mySession.setAttribute("user", name + "!");
                    mySession.setAttribute("age", edad);
                    mySession.setAttribute("obj", new servlets.MyObjectSession(name, Integer.parseInt(edad)));
                    
                    mySession.setMaxInactiveInterval(20);
                    
                    out.println("<p> Bienvenido "+name+"</p>");
                    out.println("<a href='profile.jsp'> Perfil </a>");
                }else{
                    response.sendRedirect("index.html");
                }
                
            }else{
                response.sendRedirect("index.html");
            }
        %>
    </body>
</html>
