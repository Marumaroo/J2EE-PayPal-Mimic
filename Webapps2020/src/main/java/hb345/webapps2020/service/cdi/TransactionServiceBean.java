/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hb345.webapps2020.service.cdi;

import hb345.webapps2020.data.dao.TransactionDAO;
import hb345.webapps2020.data.dto.TransactionDTOTransformer;
import hb345.webapps2020.data.dao.UserDAO;
import hb345.webapps2020.data.dto.RequestDTO;
import hb345.webapps2020.data.dto.RequestDTOTransformer;
import hb345.webapps2020.data.dto.UserDTOTransformer;
import hb345.webapps2020.data.dto.TransactionDTO;
import hb345.webapps2020.data.dto.TransactionRequestDTO;
import hb345.webapps2020.data.dto.UserDTO;
import hb345.webapps2020.data.entity.SystemRequest;
import hb345.webapps2020.data.entity.SystemTransaction;
import hb345.webapps2020.data.entity.SystemUser;
import hb345.webapps2020.rest.CurrencyPair;
import hb345.webapps2020.thriftTimestamp.TimestampService;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import static javax.ejb.TransactionAttributeType.REQUIRED;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

@Stateless
public class TransactionServiceBean implements TransactionService {

    @EJB
    UserService userService;
    
    @EJB
    TransactionDAO transactionDAO;
    
    @EJB
    UserDAO userDAO;
    
    @EJB
    UserDTOTransformer userTransformer;
    
    @EJB
    TransactionDTOTransformer transactionTransformer;
    
    @EJB
    RequestDTOTransformer requestTransformer;
    
    @EJB
    CurrencyConversion currencyConversion;
    
    @EJB
    TimestampServiceBean timestampService;

    @Override
    @TransactionAttribute(REQUIRED)
    @RolesAllowed("users")
    public String makePayment(TransactionRequestDTO transaction) {
        UserDTO senderDTO = userDAO.getUserByName(userService.getUsername());
        UserDTO recipientDTO = userService.getUserByName(transaction.getRecipient());
        System.out.println("begin timestamp");
        System.out.println(timestampService.getTimestamp());
        System.out.println("end timestamp");
        if (userService.getUsername().equals(transaction.getRecipient()) || recipientDTO == null) {
            return "Invalid-recipient";
        } else if ((senderDTO.getBalance() - transaction.getAmount()) >= 0) {
            TransactionDTO transactionDTO = new TransactionDTO();
            transactionDTO.setFromAmount(transaction.getAmount());
            //transactionDTO.setTimestamp(Date.valueOf(timestampClient.getTimestamp()));
            
            
            transactionDTO.setTimestamp(new Timestamp(System.currentTimeMillis()));

            if (!senderDTO.getCurrency().equals(recipientDTO.getCurrency())) {
                CurrencyPair pair = currencyConversion.getPair(recipientDTO.getCurrency(), senderDTO.getCurrency(), transaction.getAmount());
                BigDecimal balance = new BigDecimal(pair.getConverted().toString()).setScale(2, BigDecimal.ROUND_HALF_UP);
                transactionDTO.setToAmount(balance.doubleValue());
            } else {
                transactionDTO.setToAmount(transaction.getAmount());
            }
            
            Double senderBalance = senderDTO.getBalance() - transactionDTO.getFromAmount();
            Double recipientBalance = recipientDTO.getBalance() + transactionDTO.getToAmount();
            
            BigDecimal senderBalanceTrunc = new BigDecimal(senderBalance.toString()).setScale(2, BigDecimal.ROUND_HALF_UP);
            BigDecimal recipientBalanceTrunc = new BigDecimal(recipientBalance.toString()).setScale(2, BigDecimal.ROUND_HALF_UP);
            
            senderDTO.setBalance(senderBalanceTrunc.doubleValue());
            recipientDTO.setBalance(recipientBalanceTrunc.doubleValue());

            SystemUser sender = userTransformer.toEntity(senderDTO);
            SystemUser recipient = userTransformer.toEntity(recipientDTO);

            SystemTransaction trans = transactionDAO.create(transactionDTO);
            
            recipient.addTransaction(trans, "recipient");
            recipientDTO.setSent(recipient.getSent());
            userDAO.update(recipientDTO);
            
            sender.addTransaction(trans, "sender");
            senderDTO.setSent(sender.getSent());
            userDAO.update(senderDTO);

            return "success";
        } else {
            return "Insufficient-funds";
        }
    }

    @Override
    @TransactionAttribute(REQUIRED)
    @RolesAllowed("users")
    public String requestPayment(TransactionRequestDTO transaction) {
        UserDTO requesterDTO = userDAO.getUserByName(userService.getUsername());
        if (!requesterDTO.getUsername().equals(transaction.getRecipient())) {
            UserDTO requesteeDTO = userDAO.getUserByName(transaction.getRecipient());

            if (requesteeDTO == null) {
                return "user-not-found";
            }
            SystemUser requester = userTransformer.toEntity(requesterDTO);
            RequestDTO requestDTO = new RequestDTO();
            requestDTO.setFromAmount(transaction.getAmount());
            requestDTO.setRequester(requester);
            requestDTO.setTimestamp(new Timestamp(System.currentTimeMillis()));
            
            
            if (!requesterDTO.getCurrency().equals(requesteeDTO.getCurrency())) {
                CurrencyPair pair = currencyConversion.getPair(requesteeDTO.getCurrency(), requesterDTO.getCurrency(), transaction.getAmount());
                BigDecimal balance = new BigDecimal(pair.getConverted().toString()).setScale(2, BigDecimal.ROUND_HALF_UP);
                requestDTO.setToAmount(balance.doubleValue());
            } else {
                requestDTO.setToAmount(transaction.getAmount());
            }
            
            SystemUser requestee = userTransformer.toEntity(requesteeDTO);
            
            SystemRequest request = transactionDAO.create(requestDTO);
            
            requestee.addRequest(request);
            requesteeDTO.setRequests(requestee.getRequests());
            userDAO.update(requesteeDTO);
            
            return "success";
        } else {
            return "same-user";
        }
    }

    @Override
    @RolesAllowed("users")
    public void accceptRequest(RequestDTO request) {
        TransactionRequestDTO transaction = new TransactionRequestDTO();
        transaction.setAmount(request.getToAmount());
        transaction.setRecipient(request.getRequester().getUsername());
        this.makePayment(transaction);
        userDAO.removeRequest(request);
    }

    @Override
    @RolesAllowed("users")
    public boolean declineRequest(RequestDTO request) {
        return userDAO.removeRequest(request);
        //transactionDAO.delete(request);
    }

    @Override
    @RolesAllowed("admins")
    public List<TransactionDTO> getAll() {
        return transactionDAO.getAll();
    }
    
    private String getTimestamp() {
        try {
            TTransport transport;
            
            transport = new TSocket("localhost", 15000);
            transport.open();
            
            TProtocol protocol = new TBinaryProtocol(transport);
            TimestampService.Client client = new TimestampService.Client(protocol);
            String timestamp = client.currentTimestamp();
            transport.close();
            return timestamp;
        } catch (TTransportException ex) {
            Logger.getLogger(TransactionServiceBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TException ex) {
            Logger.getLogger(TransactionServiceBean.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }
}
