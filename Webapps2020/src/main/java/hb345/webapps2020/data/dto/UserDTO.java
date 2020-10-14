/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hb345.webapps2020.data.dto;

import hb345.webapps2020.data.entity.SystemRequest;
import hb345.webapps2020.data.entity.SystemTransaction;
import java.util.List;

public class UserDTO {
    
    private long id;
    private String username;
    private String password;
    private String currency;
    private String role;
    private Double balance;
    private List<SystemTransaction> sent;
    private List<SystemTransaction> recieved;
    private List<SystemRequest> requests;

    public UserDTO() {
    }

    public UserDTO(String username, String password) {
        this.username = username;
        this.password = password;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<SystemTransaction> getRecieved() {
        return recieved;
    }

    public void setRecieved(List<SystemTransaction> recieved) {
        this.recieved = recieved;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<SystemTransaction> getSent() {
        return sent;
    }

    public void setSent(List<SystemTransaction> sent) {
        this.sent = sent;
    }

    public List<SystemRequest> getRequests() {
        return requests;
    }

    public void setRequests(List<SystemRequest> requests) {
        this.requests = requests;
    }
    
}
