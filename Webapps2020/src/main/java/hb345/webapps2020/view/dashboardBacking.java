/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hb345.webapps2020.view;

import hb345.webapps2020.data.dto.RequestDTOTransformer;
import hb345.webapps2020.data.dto.TransactionDTO;
import hb345.webapps2020.data.dto.UserDTO;
import hb345.webapps2020.data.entity.SystemRequest;
import hb345.webapps2020.data.entity.SystemTransaction;
import hb345.webapps2020.service.cdi.TransactionService;
import hb345.webapps2020.service.cdi.UserService;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.annotation.PostConstruct;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;


@Named
@RequestScoped
public class dashboardBacking {
    
    private List<SystemTransaction> transactions;
    private UserDTO user;
    private List<UserDTO> users;
    private List<TransactionDTO> allTransactions;
    private SystemRequest selectedRequest;
    private boolean masterAdmin;
    
    private UserDTO adminDTO;
    
    
    @Inject
    UserService userService;
    
    @Inject
    TransactionService transactionService;
    
    @Inject
    RequestDTOTransformer requestTransformer;
    
    public dashboardBacking() {
        //user = userService.getUserByName(session.getCallerPrincipal().getName());
    }
    
    @PostConstruct
    public void init() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        
        String username = userService.getUsername();
        user = userService.getUserByName(username);
        
        if (request.isUserInRole("admins")) {
            adminDTO = new UserDTO();
            this.refreshDataAdmin();
        } else {
            selectedRequest = new SystemRequest();
            this.refreshData();
        }
    }
    
    @RolesAllowed("admins")
    public String registerAdmin() {
        if (userService.registerAdmin(adminDTO)) {
            adminDTO = new UserDTO();
        }
        return "";
    }
    
    @RolesAllowed("users")
    public String acceptRequest() {
        //System.out.println(request.getId());
        transactionService.accceptRequest(requestTransformer.toDTO(selectedRequest));
        this.refreshData();
        return "";
    }
    
    @RolesAllowed("users")
    public String declineRequest() {
        //System.out.println(request.getRequester());
        transactionService.declineRequest(requestTransformer.toDTO(selectedRequest));
        this.refreshData();
        return "";
    }
    
    @RolesAllowed("users")
    public void refreshData() {
        String username = userService.getUsername();
        user = userService.getUserByName(username);
        transactions = Stream.concat(user.getRecieved().stream(), user.getSent().stream()).collect(Collectors.toList());
    }
    
    @RolesAllowed("admins")
    public void refreshDataAdmin() {
        users = userService.getAllUsers();
        masterAdmin = user.getUsername().equals("admin1");
        allTransactions = transactionService.getAll();
    }
    
    @PermitAll
    public UserDTO getUser() {
        return user;
    }
    
    @PermitAll
    public void setUser(UserDTO user) {
        this.user = user;
    }
    
    @RolesAllowed("admins")
    public List<UserDTO> getUsers() {
        return users;
    }
    
    @RolesAllowed("admins")
    public void setUsers(List<UserDTO> users) {
        this.users = users;
    }
    
    @RolesAllowed("users")
    public List<SystemTransaction> getTransactions() {
        return transactions;
    }
    
    @RolesAllowed("users")
    public void setTransactions(List<SystemTransaction> transactions) {
        this.transactions = transactions;
    }
    
    @RolesAllowed("users")
    public SystemRequest getSelectedRequest() {
        return selectedRequest;
    }
    
    @RolesAllowed("users")
    public void setSelectedRequest(SystemRequest selectedRequest) {
        this.selectedRequest = selectedRequest;
    }
    
    @RolesAllowed("admins")
    public List<TransactionDTO> getAllTransactions() {
        return allTransactions;
    }
    
    @RolesAllowed("admins")
    public void setAllTransactions(List<TransactionDTO> allTransactions) {
        this.allTransactions = allTransactions;
    }

    @RolesAllowed("admins")
    public boolean isMasterAdmin() {
        return masterAdmin;
    }
    
    @RolesAllowed("admins")
    public UserDTO getAdminDTO() {
        return adminDTO;
    }

    @RolesAllowed("admins")
    public void setAdminDTO(UserDTO adminDTO) {
        this.adminDTO = adminDTO;
    }
    
    
}
