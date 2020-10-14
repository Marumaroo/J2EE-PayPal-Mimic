/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hb345.webapps2020.service.cdi;

import hb345.webapps2020.data.dto.UserDTO;
import java.util.List;
import javax.ejb.Local;

@Local
public interface UserService {
    
    public boolean login(UserDTO userDTO);
    public boolean logout();
    public String register(UserDTO userDTO);
    public boolean isRole(String role);
    public UserDTO getUserByName(String username);
    public String getUsername();
    public void updateBalance(UserDTO userDTO);
    public List<UserDTO> getAllUsers();
    public boolean registerAdmin(UserDTO userDTO);
}