/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hb345.webapps2020.data.dto;

import hb345.webapps2020.data.entity.SystemRequest;
import javax.ejb.Stateless;

@Stateless
public class RequestDTOTransformer {
    
    public RequestDTOTransformer() {
        
    }
    
    public SystemRequest toEntity(RequestDTO requestDTO) {
        SystemRequest request = new SystemRequest();
        request.setId(requestDTO.getId());
        request.setRequester(requestDTO.getRequester());
        request.setRequestee(requestDTO.getRequestee());
        request.setToAmount(requestDTO.getToAmount());
        request.setFromAmount(requestDTO.getFromAmount());
        request.setRequestTimestamp(requestDTO.getTimestamp());
        return request;
    }
    
    public RequestDTO toDTO(SystemRequest requestEntity) {
        RequestDTO request = new RequestDTO();
        request.setId(requestEntity.getId());
        request.setRequester(requestEntity.getRequester());
        request.setRequestee(requestEntity.getRequestee());
        request.setToAmount(requestEntity.getToAmount());
        request.setFromAmount(requestEntity.getFromAmount());
        request.setTimestamp(requestEntity.getRequestTimestamp());
        
        return request;
    }
}
