/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hb345.webapps2020.service.cdi;

import hb345.webapps2020.data.dto.RequestDTO;
import hb345.webapps2020.data.dto.TransactionDTO;
import hb345.webapps2020.data.dto.TransactionRequestDTO;
import java.util.List;
import javax.ejb.Local;

@Local
public interface TransactionService {
    
    public String makePayment(TransactionRequestDTO transaction);
    public String requestPayment(TransactionRequestDTO transaction);
    public List<TransactionDTO> getAll();
    public void accceptRequest(RequestDTO request);
    public boolean declineRequest(RequestDTO request);
}
