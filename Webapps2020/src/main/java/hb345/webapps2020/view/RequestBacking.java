/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hb345.webapps2020.view;

import hb345.webapps2020.data.dto.RequestDTOTransformer;
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
public class RequestBacking {
    
    private TransactionRequestDTO transaction;
    
    @Inject
    TransactionService transactionService;
    
    @Inject
    RequestDTOTransformer requestTransformer;
    
    public RequestBacking() {
        transaction = new TransactionRequestDTO();
    }
    
    @RolesAllowed("users")
    public String requestPayment() {
        FacesContext context = FacesContext.getCurrentInstance();
        switch (transactionService.requestPayment(transaction)) {
            case "user-not-found":
                context.addMessage("request-form:from", new FacesMessage("Requested party not found"));
                return "failed";
            case "same-user":
                context.addMessage("request-form:from", new FacesMessage("Cannot request from self"));
                return "failed";
            case "success":
                return "request-successful";
            default:
                context.addMessage("request-form", new FacesMessage("Unknown Error"));
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