<%-- 
    Document   : index
    Created on : 29/03/2019, 05:48:49 PM
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
        <h1>Hello World!</h1>
        
    <%-- start web service invocation --%><hr/>
    <%
    try {
	webservices.MyWebService_Service service = new webservices.MyWebService_Service();
	webservices.MyWebService port = service.getMyWebServicePort();
	 // TODO initialize WS operation arguments here
	int x = 0;
	int y = 0;
	// TODO process result here
	int result = port.add(x, y);
	out.println("Result = "+result);
    } catch (Exception ex) {
	// TODO handle custom exceptions here
    }
    %>
    <%-- end web service invocation --%><hr/>
    </body>
</html>
