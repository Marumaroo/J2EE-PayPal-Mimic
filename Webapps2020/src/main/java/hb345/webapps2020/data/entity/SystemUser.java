/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hb345.webapps2020.data.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@Entity
@NamedQueries({
    @NamedQuery(name = "SystemUser.getAllUsers", query = "SELECT u FROM SystemUser u"),
    @NamedQuery(name = "SystemUser.findByUsername", query = "SELECT u FROM SystemUser u WHERE u.username = :name")
})
public class SystemUser implements Serializable {
    
    @Id
    @Column(updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    
    @Column(nullable = false, unique = true)
    private String username;
    
    @Column(nullable = false)
    private String password;
    
    @Column(nullable = true)
    private String currency;
    
    @Column(nullable = true)
    private Double balance;
  
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "sender")
    private List<SystemTransaction> sent = new ArrayList<>();
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "recipient")
    private List<SystemTransaction> received = new ArrayList<>();
    
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "requestee", orphanRemoval = true)
    private List<SystemRequest> requests = new ArrayList<>();

    public SystemUser() {
    }

    public SystemUser(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public SystemUser(String username, String password, String currency, Double balance) {
        this.username = username;
        this.password = password;
        this.currency = currency;
        this.balance = balance;
    }
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    
    
//    public List<SystemTransaction> getMadeTransactions() {
//        return madeTransactions;
//    }
//
//    public void setMadeTransactions(List<SystemTransaction> madeTransactions) {
//        this.madeTransactions = madeTransactions;
//    }
//
//    public List<SystemTransaction> getRequestedTransactions() {
//        return requestedTransactions;
//    }
//
//    public void setRequestedTransactions(List<SystemTransaction> requestedTransactions) {
//        this.requestedTransactions = requestedTransactions;
//    }

//    public Collection getTransactions() {
//        return madeTransactions;
//    }
//
//    public void setTransactions(Collection transactions) {
//        this.madeTransactions = transactions;
//    }
//
//    public Collection getRequestedTransactions() {
//        return requestedTransactions;
//    }
//
//    public void setRequestedTransactions(Collection requestedTransactions) {
//        this.requestedTransactions = requestedTransactions;
//    }
//    

    public List<SystemTransaction> getSent() {
        return sent;
    }

    public void setSent(List<SystemTransaction> sent) {
        this.sent = sent;
    }
    
    public void addTransaction(SystemTransaction transaction, String role) {
        if (transaction != null) {
            if (role.equals("sender")) {
                transaction.setSender(this);
                this.sent.add(transaction);
            } else if (role.equals("recipient")) {
                transaction.setRecipient(this);
                this.received.add(transaction);
            }
        }
    }

    public List<SystemRequest> getRequests() {
        return requests;
    }

    public void setRequests(List<SystemRequest> requests) {
        this.requests = requests;
    }

    public void addRequest(SystemRequest request) {
        if (request != null) {
            request.setRequestee(this);
//            if (role.equals("requester")) {
//                transaction.setRecipient(this);
//            } else if (role.equals("requestee")) {
//                transaction.setSender(this);
//            }
            this.requests.add(request);
        }
    }
    
    public void removeRequest(SystemRequest request) {
        if (request != null) {
            this.requests.remove(request);
        }
    }

    public List<SystemTransaction> getReceived() {
        return received;
    }

    public void setReceived(List<SystemTransaction> received) {
        this.received = received;
    }
}
