/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hb345.webapps2020.service.cdi;

import hb345.webapps2020.rest.CurrencyPair;
import javax.ejb.Local;

@Local
public interface CurrencyConversion {
    public CurrencyPair getPair(String to, String from, Double amount);
}
