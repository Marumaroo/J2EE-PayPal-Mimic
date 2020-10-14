/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hb345.webapps2020.service.cdi;

import hb345.webapps2020.rest.CurrencyPair;
import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;

@Stateless
@PermitAll
public class CurrencyConversionBean implements CurrencyConversion {

    private final String baseURI = "http://localhost:8080/Webapps2020/conversion";
    private final Client client;
    
    public CurrencyConversionBean() {
        this.client = ClientBuilder.newClient();
    }
    
    @Override
    public CurrencyPair getPair(String to, String from, Double amount) {
        CurrencyPair pair = null;
        
        try {
            pair = client.target(baseURI).path("/" + from + "/" + to + "/" + amount).request(MediaType.APPLICATION_JSON).get(CurrencyPair.class);
        } catch (WebApplicationException e) {
            
        }
        
        return pair;
    }
    
}
