/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hb345.webapps2020.view;

import hb345.webapps2020.data.dto.UserDTO;
import hb345.webapps2020.service.cdi.UserService;
import javax.annotation.security.PermitAll;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
@PermitAll
public class RegistrationBacking {
    
    private UserDTO user;
    private String passwordConfirm;
    
    @Inject
    UserService userService;
    
    public RegistrationBacking() {
        this.user = new UserDTO();
    }
    
    public String register() {
        FacesContext context = FacesContext.getCurrentInstance();
        if (!user.getPassword().equals(passwordConfirm)) {
            context.addMessage("register-form:passwordConfirm", new FacesMessage("Password confirmation incorrect"));
            return "registration";
        } else {
            switch (userService.register(user)) {
                case "user-exists":
                    context.addMessage("register-form:username", new FacesMessage("Username taken"));
                    return "registration";
                case "registered":
                    return "registered";
                default:
                    context.addMessage("register-form:registerError", new FacesMessage("Unknown Error"));
                    return "registration";
            }
        }
    }

    public UserDTO getUser() {
        return user;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }
}
