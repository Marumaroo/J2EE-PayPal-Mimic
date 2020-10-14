/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hb345.webapps2020.data.dao;

import hb345.webapps2020.data.dto.RequestDTO;
import hb345.webapps2020.data.dto.RequestDTOTransformer;
import hb345.webapps2020.data.dto.UserDTOTransformer;
import hb345.webapps2020.data.dto.UserDTO;
import hb345.webapps2020.data.entity.SystemUser;
import hb345.webapps2020.data.entity.SystemUserGroup;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class UserDAOImp implements UserDAO {
    
    @PersistenceContext(unitName = "WebappsPU")
    EntityManager em;
    
    @EJB
    UserDTOTransformer userTransformer;
    
    @EJB
    RequestDTOTransformer requestTransformer;

    /**
     *
     * @param userDTO 
     * @return boolean true if successful user registration else false
     */
    @Override
    public void insert(UserDTO userDTO) {
        SystemUser user = userTransformer.toEntity(userDTO);
        SystemUserGroup userGroup = new SystemUserGroup(userDTO.getUsername(), userDTO.getRole());

        em.persist(user);
        em.persist(userGroup);
        em.flush();
    }

    @Override
    public void update(UserDTO userDTO) {
        SystemUser user = em.find(SystemUser.class, userDTO.getId());
        user.setBalance(userDTO.getBalance());
        user.setSent(userDTO.getSent());
        em.merge(user);
        em.flush();
    }

    @Override
    public UserDTO getUserByName(String username) {
        List<SystemUser> user = em.createNamedQuery("SystemUser.findByUsername",SystemUser.class).setParameter("name", username).getResultList();
        em.flush();
        return user.isEmpty() ? null : userTransformer.toDTO(user.get(0));
    }

    @Override
    public void updateBalance(UserDTO userDTO) {
        SystemUser user = em.find(SystemUser.class, userDTO.getId());
        user.setBalance(userDTO.getBalance());
        em.merge(user);
        //em.persist(user);
        em.flush();
    }

    @Override
    public List<UserDTO> getAll() {
        List<SystemUser> u = em.createNamedQuery("SystemUser.getAllUsers", SystemUser.class).getResultList();
        List<UserDTO> users = new ArrayList<>();
        u.forEach((i) -> {
            users.add(userTransformer.toDTO(i));
        });
        
        return users;
    }

    @Override
    public boolean removeRequest(RequestDTO request) {
        SystemUser user = em.find(SystemUser.class, request.getRequestee().getId());
        
        user.getRequests().remove(requestTransformer.toEntity(request));
        em.merge(user);
        em.flush();
        return true;
    }
}
