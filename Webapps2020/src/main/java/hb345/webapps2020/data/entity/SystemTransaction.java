/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hb345.webapps2020.data.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity(name = "transaction")
@Table(name = "transactions")
@NamedQuery(name = "SystemTransaction.getAllTransactions", query = "SELECT t FROM transaction t")
public class SystemTransaction implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "SENDER_ID")
    private SystemUser sender;
    
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "RECIPIENT_ID")
    private SystemUser recipient;

    @Column(nullable = false)
    private Double fromAmount;
    
    @Column(nullable = false)
    private Double toAmount;
    
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date transactionTimestamp;
    
    public SystemTransaction() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public Date getTransactionTimestamp() {
        return transactionTimestamp;
    }

    public void setTransactionTimestamp(Date transactionTimestamp) {
        this.transactionTimestamp = transactionTimestamp;
    }
}
