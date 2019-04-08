<%-- 
    Document   : index
    Created on : 22/03/2019, 04:49:06 PM
    Author     : sdist
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>
        <title>TODO supply a title</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <script>
            function getCapitales(edo, id){
                var ajaxRequest;
                if(window.XMLHttpRequest){
                    ajaxRequest = new XMLHttpRequest();
                }else{
                    ajaxRequest = new ActiveXObject("MicrosoftXMLHTTP");
                }

                ajaxRequest.onreadystatechange = function(){
                    if(ajaxRequest.readyState == 4 && ajaxRequest.status == 200){
                        document.getElementById(id).innerHTML = ajaxRequest.responseText;
                    }
                }


                ajaxRequest.open("GET", "Capitales?edo="+edo, true);
                ajaxRequest.send();   
            }
        </script>
        <div id ="div1">
            <p>Indica un estado de México para conocer su capital</p>
            <input type="text" name="edo" value="" onkeyup = "getCapitales(this.value, 'sugerencia')"/>
        </div>
        <div id ="sugerencia">
            <p><b>Sugerencia:</b></p>
        </div>
    </body>
    
</html>

