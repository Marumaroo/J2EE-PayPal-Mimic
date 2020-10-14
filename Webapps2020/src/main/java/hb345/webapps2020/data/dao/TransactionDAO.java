/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hb345.webapps2020.data.dao;

import hb345.webapps2020.data.dto.RequestDTO;
import hb345.webapps2020.data.dto.TransactionDTO;
import hb345.webapps2020.data.entity.SystemRequest;
import hb345.webapps2020.data.entity.SystemTransaction;
import java.util.List;
import javax.ejb.Local;

@Local
public interface TransactionDAO {
    
    public SystemTransaction create(TransactionDTO transactionDTO);
    public List<TransactionDTO> getAll();
    public SystemRequest create(RequestDTO requestDTO);
    public void delete(RequestDTO requestDTO);
}
