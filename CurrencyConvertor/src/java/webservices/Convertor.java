/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webservices;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author sdist
 */
@WebService(serviceName = "Convertor")
public class Convertor {

    /**
     * Web service operation
     */
    @WebMethod(operationName = "convert")
    public double convert(@WebParam(name = "amount") double amount, @WebParam(name = "conversion") String conversion) {
        double res = 0.0;
        
        switch(conversion){
            case "US-MXN":
                res = 20*amount;
                break;
            
            case "MX-US":
                res = amount/20;
                break;
                
            case "YEN-US":
                res = 200*amount;
                break;
                
            case "US-YEN":
                res = amount/200;
                break;
                
            case "MXN-YEN":
                res = 3*amount;
                break;
                
            case "YEN-MXN":
                res = amount/3;
                break;
        }
        
        return res;
    }

    /**
     * This is a sample web service operation
     */
    
}
