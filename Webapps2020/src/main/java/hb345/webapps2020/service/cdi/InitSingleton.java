/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hb345.webapps2020.service.cdi;

import hb345.webapps2020.data.dao.UserDAO;
import hb345.webapps2020.data.dto.UserDTO;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;

@Startup
@Singleton
public class InitSingleton {
    
    @EJB
    UserService userService;
    
    @EJB
    UserDAO userDAO;

    public InitSingleton() {
    }
    
    @PostConstruct
    public void Init() {
        if (userService.getUserByName("admin1") == null) {
            try {
                UserDTO admin = new UserDTO("admin1", "admin1");
                MessageDigest md = MessageDigest.getInstance("SHA-256");
                md.update(admin.getPassword().getBytes("UTF-8"));
                byte[] digest = md.digest();
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < digest.length; i++) {
                    sb.append(Integer.toString((digest[i] & 0xff) + 0x100, 16).substring(1));
                }

                admin.setPassword(sb.toString());

                admin.setRole("admins");
                userDAO.insert(admin);

    //        if (userService.getUserByName("admin1") == null) {
    //            UserDTO admin = new UserDTO("admin1", "admin1");
    //            userService.registerAdmin(admin);
    //        }
            } catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
                Logger.getLogger(InitSingleton.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
