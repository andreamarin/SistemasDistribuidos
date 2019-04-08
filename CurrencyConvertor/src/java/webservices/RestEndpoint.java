/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webservices;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author sdist
 */
@Path("endpoint")
public class RestEndpoint {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of RestEndpoint
     */
    public RestEndpoint() {
    }

    @GET
    @Consumes("text/html")
    public String getConversion(@QueryParam("amount")String amount, @QueryParam("from")String from, @QueryParam("to")String to){
        String conversion = from + "-" + to;
        
        double res = convert(Double.parseDouble(amount), conversion);
        
        return res+"";
        //return "Contactando REST: " + amount + " " + from + " " + to;
    }

    private static double convert(double amount, java.lang.String conversion) {
        wsclient.Convertor_Service service = new wsclient.Convertor_Service();
        wsclient.Convertor port = service.getConvertorPort();
        return port.convert(amount, conversion);
    }
    
    

}
