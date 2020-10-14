/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hb345.webapps2020.view;

import hb345.webapps2020.data.dto.TransactionRequestDTO;
import hb345.webapps2020.service.cdi.TransactionService;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class SendBacking {
    
    private TransactionRequestDTO transaction;
    
    @Inject
    TransactionService transactionService;
    
    public SendBacking() {
        transaction = new TransactionRequestDTO();
    }
    
    @RolesAllowed("users")
    public String sendPayment() {
        FacesContext context = FacesContext.getCurrentInstance();
        System.out.println(transaction.getRecipient());
        System.out.println(transaction.getAmount());
        switch (transactionService.makePayment(transaction)) {
            case "Insufficient-funds":
                context.addMessage("pay-form:amount", new FacesMessage("Insufficient funds"));
                return "failed";
            case "Invalid-recipient":
                context.addMessage("pay-form:destination", new FacesMessage("Invalid recipient"));
                return "failed";
            case "success":
                return "payment-successful";
            default:
                context.addMessage("pay-form", new FacesMessage("Unknown Error"));
                return "failed";
        }
    }
    
    @RolesAllowed("users")
    public TransactionRequestDTO getTransaction() {
        return transaction;
    }
    
    @RolesAllowed("users")
    public void setTransaction(TransactionRequestDTO transaction) {
        this.transaction = transaction;
    }
}
