/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hb345.webapps2020.data.dao;

import hb345.webapps2020.data.dto.RequestDTO;
import hb345.webapps2020.data.dto.UserDTO;
import java.util.List;
import javax.ejb.Local;

@Local
public interface UserDAO {
    
    public List<UserDTO> getAll();
    public void insert(UserDTO userDTO);
    public void update(UserDTO userDTO);
    public UserDTO getUserByName(String username);
    public void updateBalance(UserDTO userDTO);
    public boolean removeRequest(RequestDTO request);
}
