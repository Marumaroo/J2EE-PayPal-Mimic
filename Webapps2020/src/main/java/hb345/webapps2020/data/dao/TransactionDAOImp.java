/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hb345.webapps2020.data.dao;

import hb345.webapps2020.data.dto.RequestDTO;
import hb345.webapps2020.data.dto.RequestDTOTransformer;
import hb345.webapps2020.data.dto.TransactionDTOTransformer;
import hb345.webapps2020.data.dto.TransactionDTO;
import hb345.webapps2020.data.entity.SystemRequest;
import hb345.webapps2020.data.entity.SystemTransaction;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import static javax.ejb.TransactionAttributeType.REQUIRED;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@TransactionAttribute(REQUIRED)
@Stateless
public class TransactionDAOImp implements TransactionDAO {
    
    @PersistenceContext
    EntityManager em;
    
    @EJB
    TransactionDTOTransformer transactionTransformer;
    
    @EJB
    RequestDTOTransformer requestTransformer;

    @Override
    public SystemTransaction create(TransactionDTO transactionDTO) {
        SystemTransaction transaction = transactionTransformer.toEntity(transactionDTO);
        
        em.persist(transaction);
        em.flush();
        
        return transaction;
    }

    @Override
    public List<TransactionDTO> getAll() {
        List<SystemTransaction> u = em.createNamedQuery("SystemTransaction.getAllTransactions", SystemTransaction.class).getResultList();
        List<TransactionDTO> transactions = new ArrayList<>();
        u.forEach((SystemTransaction i) -> {
            transactions.add(transactionTransformer.toDTO(i));
        });
        
        return transactions;
    }

    @Override
    public SystemRequest create(RequestDTO requestDTO) {
        SystemRequest request = requestTransformer.toEntity(requestDTO);
        
        em.persist(request);
        em.flush();
        
        return request;
    }

    @Override
    public void delete(RequestDTO requestDTO) {
        SystemRequest request = requestTransformer.toEntity(requestDTO);
        
        if (!em.contains(request)) {
            request = em.merge(request);
        }
        
        em.remove(request);
        em.flush();
    }
}
