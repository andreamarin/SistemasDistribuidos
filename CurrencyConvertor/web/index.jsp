<%-- 
    Document   : index
    Created on : 3/04/2019, 04:51:18 PM
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
        <script>
            function getConversion(id){
                //alert("Convirtiendo");
                var ajaxRequest;
                var target = "http://localhost:9090/CurrencyConvertor/webresources/endpoint";
                
                target = target + "?amount=" +document.getElementById("amount").value + "&from=" + document.getElementById("from").value + "&to=" + document.getElementById("to").value;
                
                if(window.XMLHttpRequest){
                    ajaxRequest = new XMLHttpRequest();
                }else{
                    ajaxRequest = new ActiveXObject("Microsoft.XMLHTTP");
                }
                
                ajaxRequest.onreadystatechange = function(){
                    if(ajaxRequest.readyState == 4 && (ajaxRequest.status == 200 || ajaxRequest.status == 204)){
                        var text = ajaxRequest.responseText;
                        //alert("Tengo respuesta: "+text+".");
                        document.getElementById(id).value = text;
                    }
                }
                
                ajaxRequest.open("GET", target, true);
                ajaxRequest.setRequestHeader("Content-Type", "text/html");
                ajaxRequest.send('');
            }
        </script>
        <form>
            <table border="1">
                <thead>
                    <tr>
                        <th>Currency I have</th>
                        <th>Amount</th>
                        <th>Currency I want</th>
                        <th>Amount</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>
                            <select id ="from" name="from" onchange="getConversion('res')">
                                <option value = "MXN">Mexican peso</option>
                                <option value = "US">US Dollar</option>
                                <option value = "YEN">Chinese Yen</option>
                            </select>
                        </td>
                        <td><input id = "amount" type="text" name="amount" onkeyup="getConversion('res')" value="0.0" /></td>
                        <td>
                            <select id = "to" name="to" onchange="getConversion('res')">
                                <option value = "MXN">Mexican peso</option>
                                <option value = "US">US Dollar</option>
                                <option value = "YEN">Chinese Yen</option>
                            </select>
                        </td>
                        <td><input id = "res" type="text" name="res_amount" value="" /></td>
                    </tr>
                </tbody>
            </table>

        </form>
    </body>
</html>
