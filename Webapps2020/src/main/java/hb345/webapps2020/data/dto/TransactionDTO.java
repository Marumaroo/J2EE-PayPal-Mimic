/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hb345.webapps2020.data.dto;

import hb345.webapps2020.data.entity.SystemUser;
import java.util.Date;

public class TransactionDTO {
    
    private SystemUser sender;
    private SystemUser recipient;
    private Double fromAmount;
    private Double toAmount;
    private Date timestamp;

    public TransactionDTO() {
    }

    public SystemUser getSender() {
        return sender;
    }

    public void setSender(SystemUser sender) {
        this.sender = sender;
    }

    public SystemUser getRecipient() {
        return recipient;
    }

    public void setRecipient(SystemUser recipient) {
        this.recipient = recipient;
    }

    public Double getFromAmount() {
        return fromAmount;
    }

    public void setFromAmount(Double fromAmount) {
        this.fromAmount = fromAmount;
    }

    public Double getToAmount() {
        return toAmount;
    }

    public void setToAmount(Double toAmount) {
        this.toAmount = toAmount;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}
