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
public class LoginBacking {
    
    private UserDTO user;
    
    @Inject
    UserService userService;
    
    public LoginBacking() {
        this.user = new UserDTO();
    }
    
    public String login() {
        if (userService.login(user)) {
            return userService.isRole("admins") ? "admin-dashboard" : "user-dashboard";
        } else {
            FacesContext.getCurrentInstance().addMessage("login-form", new FacesMessage("Username or Password Incorrect"));
            return "index";
        }
    }
    
    public String logout() {
        if (userService.logout()) {
            return "loggedout";
        } else {
            return userService.isRole("admins") ? "admin-dashboard" : "user-dashboard";
        }
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }
}
