/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hb345.webapps2020.data.dto;

import hb345.webapps2020.data.entity.SystemTransaction;
import javax.ejb.Stateless;

@Stateless
public class TransactionDTOTransformer {
    
    public TransactionDTOTransformer() {
        
    }
    
    public SystemTransaction toEntity(TransactionDTO transactionDTO) {
        SystemTransaction transaction = new SystemTransaction();
        transaction.setSender(transactionDTO.getSender());
        transaction.setRecipient(transactionDTO.getRecipient());
        transaction.setFromAmount(transactionDTO.getFromAmount());
        transaction.setToAmount(transactionDTO.getToAmount());
        transaction.setTransactionTimestamp(transactionDTO.getTimestamp());
        return transaction;
    }
    
    public TransactionDTO toDTO(SystemTransaction transactionEntity) {
        TransactionDTO transaction = new TransactionDTO();
        transaction.setSender(transactionEntity.getSender());
        transaction.setRecipient(transactionEntity.getRecipient());
        transaction.setFromAmount(transactionEntity.getFromAmount());
        transaction.setToAmount(transactionEntity.getToAmount());
        transaction.setTimestamp(transactionEntity.getTransactionTimestamp());
        return transaction;
    }
}
