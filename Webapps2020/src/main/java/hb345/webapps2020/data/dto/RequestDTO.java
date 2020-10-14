/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hb345.webapps2020.data.dto;

import hb345.webapps2020.data.entity.SystemUser;
import java.util.Date;

public class RequestDTO {
    
    private long id;
    private SystemUser requester;
    private SystemUser requestee;
    private Double ToAmount;
    private Double FromAmount;
    private Date timestamp;
    
    public RequestDTO() {
        
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public SystemUser getRequester() {
        return requester;
    }

    public void setRequester(SystemUser requester) {
        this.requester = requester;
    }

    public SystemUser getRequestee() {
        return requestee;
    }

    public void setRequestee(SystemUser requestee) {
        this.requestee = requestee;
    }

    public Double getToAmount() {
        return ToAmount;
    }

    public void setToAmount(Double ToAmount) {
        this.ToAmount = ToAmount;
    }

    public Double getFromAmount() {
        return FromAmount;
    }

    public void setFromAmount(Double FromAmount) {
        this.FromAmount = FromAmount;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }    
}
