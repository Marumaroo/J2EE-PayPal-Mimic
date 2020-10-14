/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hb345.webapps2020.rest;

import java.util.HashMap;
import javax.ejb.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Singleton
@Path("/")
public class Conversion {
    
    private final HashMap<String, CurrencyPair> pairs;
    
    public Conversion() {
        pairs = new HashMap<>();
        CurrencyPair GBP_EUR = new CurrencyPair("GBP","EUR", 1.13468);
        CurrencyPair GBP_USD = new CurrencyPair("GBP","USD", 1.24944);
        CurrencyPair EUR_GBP = new CurrencyPair("EUR","GBP", 0.881305);
        CurrencyPair EUR_USD = new CurrencyPair("EUR","USD", 1.10113);
        CurrencyPair USD_GBP = new CurrencyPair("USD","GBP", 0.800362);
        CurrencyPair USD_EUR = new CurrencyPair("USD","EUR", 0.908155);
        pairs.put("GBP-EUR",GBP_EUR);
        pairs.put("GBP-USD",GBP_USD);
        pairs.put("EUR-GBP",EUR_GBP);
        pairs.put("EUR-USD",EUR_USD);
        pairs.put("USD-GBP",USD_GBP);
        pairs.put("USD-EUR",USD_EUR);
    }
    
    @GET
    @Path("/{currency1}/{currency2}/{amount}")
    @Produces({"application/json", "application/xml"})
    public Response getPair(@PathParam("currency1") String currency1, @PathParam("currency2") String currency2, @PathParam("amount") double amount) {
        try {
            CurrencyPair pair = pairs.get(currency1 + "-" + currency2);
            pair.setConverted(pair.rate * amount);
            return Response.ok(pair).build();
        } catch (NullPointerException ex) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
