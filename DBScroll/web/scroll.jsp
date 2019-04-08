<%-- 
    Document   : scroll
    Created on : 6/03/2019, 04:19:41 PM
    Author     : andreamarin y luis felipe landa
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="scroll.jsp">
            <%
                HttpSession mySession = request.getSession();
                Object[][] data = (Object[][])mySession.getAttribute("data");
                int index = (Integer) mySession.getAttribute("index");
                String disabled_p = "";
                String disabled_n = "";
                
                if(data != null){
                    if(request.getParameter("prev") != null)
                        index--;
                    
                    if(request.getParameter("next") != null)
                        index++;
                    
                    mySession.setAttribute("index", index);
                    
                    out.println("<h1>Scrolling database records</h1>");
                    out.println("<p> ID:"+data[index][0].toString()
                            +" Name: "+data[index][1].toString()
                            +"</p>");
                    out.println("<hr>");
                    
                    if(index == 0){
                       disabled_p = "disabled='disabled'";
                    }
                    
                    if(index+1 == data.length){
                        disabled_n = "disabled='disabled'";
                    }
                    
                    out.print("<input name = 'prev' type='submit' "+disabled_p+" value='<' />");
                    out.print("<input name = 'next' type='submit' "+disabled_n+" value='>' />");
                }
                %>
        </form>
    </body>
</html>
