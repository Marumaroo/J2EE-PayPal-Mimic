/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hb345.webapps2020.service.cdi;

import hb345.webapps2020.data.dao.UserDAO;
import hb345.webapps2020.data.dto.UserDTO;
import hb345.webapps2020.rest.CurrencyPair;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

@Stateless
@PermitAll
public class UserServiceBean implements UserService {

    @EJB
    UserDAO userDAO;
    
    @EJB
    CurrencyConversion currencyConversion;
    
    @Override
    public UserDTO getUserByName(String username) {
        return userDAO.getUserByName(username);
    }
    
    @Override
    public String getUsername() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        return request.getSession().getAttribute("username").toString();
    }
    
    @Override
    public boolean login(UserDTO userDTO) {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        try {
            request.login(userDTO.getUsername().toLowerCase(), userDTO.getPassword());
            request.getSession().setAttribute("username", userDTO.getUsername());
            return true;
        } catch (ServletException ex) {
            return false;
        }
    }

    @Override
    public boolean logout() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        try {
            request.logout();
            return true;
        } catch (ServletException ex) {
            return false;
        }
    }

    @Override
    public String register(UserDTO userDTO) {
        System.out.println(getUserByName(userDTO.getUsername()) != null);
        if (this.getUserByName(userDTO.getUsername().toLowerCase()) != null) {
            return "user-exists";
        } else {
            try {
                userDTO.setUsername(userDTO.getUsername().toLowerCase());
                MessageDigest md = MessageDigest.getInstance("SHA-256");
                md.update(userDTO.getPassword().getBytes("UTF-8"));
                byte[] digest = md.digest();
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < digest.length; i++) {
                    sb.append(Integer.toString((digest[i] & 0xff) + 0x100, 16).substring(1));
                }

                userDTO.setPassword(sb.toString());


                if (userDTO.getCurrency().equals("GBP")) {
                    userDTO.setBalance(1000.00);
                } else {
                    CurrencyPair pair = currencyConversion.getPair(userDTO.getCurrency(), "GBP", 1000.00);
                    BigDecimal bal = new BigDecimal(pair.getConverted().toString()).setScale(2, BigDecimal.ROUND_HALF_UP);
                    userDTO.setBalance(bal.doubleValue());
                }

                //userDTO.setBalance( !userDTO.getCurrency().equals("GBP") ? currencyConversion.getPair(userDTO.getCurrency(), "GBP", 1000.0).getConverted() : 1000.0 );

                userDTO.setRole("users");
                userDAO.insert(userDTO);

                return "registered";
            } catch (UnsupportedEncodingException | NoSuchAlgorithmException ex) {
                Logger.getLogger(UserServiceBean.class.getName()).log(Level.SEVERE, null, ex);
                return "failed";
            }
        }
    }
            
    @Override
    public boolean isRole(String role) {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        return request.isUserInRole(role);
    }

    @Override
    public void updateBalance(UserDTO userDTO) {
        userDAO.updateBalance(userDTO);
    }

    @Override
    @RolesAllowed("admins")
    public List<UserDTO> getAllUsers() {
        return userDAO.getAll();
    }

    @Override
    @RolesAllowed("admins")
    public boolean registerAdmin(UserDTO userDTO) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(userDTO.getPassword().getBytes("UTF-8"));
            byte[] digest = md.digest();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < digest.length; i++) {
                sb.append(Integer.toString((digest[i] & 0xff) + 0x100, 16).substring(1));
            }
            
            userDTO.setPassword(sb.toString());

            userDTO.setRole("admins");
            userDAO.insert(userDTO);
            
            return true;
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException ex) {
            Logger.getLogger(UserServiceBean.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
}
