/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hb345.webapps2020.data.dto;

import hb345.webapps2020.data.entity.SystemUser;
import javax.ejb.Stateless;

@Stateless
public class UserDTOTransformer {
    
    public UserDTOTransformer() {
        
    }
    
    public SystemUser toEntity(UserDTO userDTO) {
        SystemUser user = new SystemUser();
        user.setId(userDTO.getId());
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setBalance(userDTO.getBalance());
        user.setCurrency(userDTO.getCurrency());
        user.setSent(userDTO.getSent());
        user.setReceived(userDTO.getRecieved());
        user.setRequests(userDTO.getRequests());
        return user;
    }
    
    public UserDTO toDTO(SystemUser userEntity) {
        UserDTO user = new UserDTO();
        user.setId(userEntity.getId());
        user.setUsername(userEntity.getUsername());
        user.setPassword(userEntity.getPassword());
        user.setBalance(userEntity.getBalance());
        user.setCurrency(userEntity.getCurrency());
        user.setSent(userEntity.getSent());
        user.setRecieved(userEntity.getReceived());
        user.setRequests(userEntity.getRequests());
        return user;
    }
}
