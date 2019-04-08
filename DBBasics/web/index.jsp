<%-- 
    Document   : index
    Created on : 4/03/2019, 04:35:49 PM
    Author     : Andrea Marín y Luis Felipe Landa
--%>

<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.DriverManager"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Database</h1>
        
        <form action="index.jsp">
            <h2>Staff </h2>
            <%
                Class.forName("org.apache.derby.jdbc.ClientDriver");
                Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/SecondDatabase","root","root");
                Statement query = con.createStatement();
                
                String name, balance, id;
                int res;
                
                if(request.getParameter("add") != null){
                    name = request.getParameter("name");
                    id = request.getParameter("id");
                    balance = request.getParameter("balance");
                    
                    res = query.executeUpdate("INSERT into CUSTOMERS VALUES ("
                            + id + ","
                            + "'" + name + "',"
                            + balance + ")");
                    
                    if (res == 0){
                        out.println("<p style='color:red;'>No se pudo agregar</p>");
                    }
                }
                
                if(request.getParameter("delete") != null){
                    id = request.getParameter("id");
                    
                    res = query.executeUpdate("DELETE FROM CUSTOMERS WHERE ID="+ id + "");
                    
                    if (res == 0){
                        out.println("<p style='color:red;'>No se pudo eliminar</p>");
                    }
                }
                
                if(request.getParameter("update") != null){
                    name = request.getParameter("name");
                    id = request.getParameter("id");
                    balance = request.getParameter("balance");
                    
                    res = query.executeUpdate("UPDATE CUSTOMERS SET"
                            + " NAME='"+name
                            + "', BALANCE="+balance
                            + " WHERE ID="+id);
                    
                    if (res == 0){
                        out.println("<p style='color:red;'>No se pudo actualizar</p>");
                    }
                }
                
                ResultSet rs = query.executeQuery("SELECT * from CUSTOMERS");
                
                while(rs.next()){
                    out.println("<p> Id: "+rs.getInt("ID"));
                    out.println(" Name: "+rs.getString("NAME"));
                    out.println(" Balance: "+rs.getDouble("BALANCE")+ "</p>");
                }
                con.commit();
                con.close();
                %>
        </form>
        
        <form action="index.jsp">
            <h3>Add a record</h3>
            <table border="1">
                <tbody>
                    <tr>
                        <td>Id:</td>
                        <td><input type="text" name="id" value="" /></td>
                        <td>Name:</td>
                        <td><input type="text" name="name" value="" /></td>
                        <td>Balance:</td>
                        <td><input type="text" name="balance" value="" /></td>
                    </tr>
                </tbody>
            </table>
            <input name="add" type="submit" value="Ok" />
        </form>
        
        <form action="index.jsp">
            <h3>Delete a record</h3>
            <table border="1">
                <tbody>
                    <tr>
                        <td>Id:</td>
                        <td><input type="text" name="id" value="" /></td>
                    </tr>
                </tbody>
            </table>
            <input name = "delete" type="submit" value="Ok" />
        </form>
        
        <form action="index.jsp">
            <h3>Update a record</h3>
            <table border="1">
                <tbody>
                    <tr>
                        <td>Id:</td>
                        <td><input type="text" name="id" value="" /></td>
                        <td>Name:</td>
                        <td><input type="text" name="name" value="" /></td>
                        <td>Balance:</td>
                        <td><input type="text" name="balance" value="" /></td>
                    </tr>
                </tbody>
            </table>
            <input name = "update" type="submit" value="Ok" />
        </form>
        
    </body>
</html>
