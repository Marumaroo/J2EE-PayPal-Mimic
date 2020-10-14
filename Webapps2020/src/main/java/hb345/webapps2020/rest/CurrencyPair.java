/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hb345.webapps2020.rest;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "pair")
public class CurrencyPair {
    
    String from;
    String to;
    Double rate;
    Double converted;
    
    public CurrencyPair() {
        
    }
    
    public CurrencyPair(String from, String to, Double rate) {
        this.from = from;
        this.to = to;
        this.rate = rate;
    }
    
    @XmlAttribute
    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }
    
    @XmlAttribute
    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }
    
    @XmlAttribute
    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public Double getConverted() {
        return converted;
    }

    public void setConverted(Double converted) {
        this.converted = converted;
    }
    
}
