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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity(name = "request")
@Table(name = "requests")
public class SystemRequest implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private SystemUser requester;
    
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REMOVE}, fetch = FetchType.LAZY)
    @JoinColumn(name = "REQUESTEE_ID")
    private SystemUser requestee;

    @Column(nullable = false)
    private Double fromAmount;
    
    @Column(nullable = false)
    private Double toAmount;
    
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date requestTimestamp;
    
    public SystemRequest() {
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

    public Date getRequestTimestamp() {
        return requestTimestamp;
    }

    public void setRequestTimestamp(Date requestTimestamp) {
        this.requestTimestamp = requestTimestamp;
    }
}
