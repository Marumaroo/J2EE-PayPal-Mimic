/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hb345.webapps2020.service.cdi;

import hb345.webapps2020.thriftTimestamp.TimestampService;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Stateful;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

@Stateful
public class TimestampServiceBean {
    
    public static TTransport transport;
    public static TProtocol protocol;
    public static TimestampService.Client client;
    
    
    public TimestampServiceBean() {
        try {
            transport = new TSocket("localhost", 10000);
            transport.open();
            
            protocol = new TBinaryProtocol(transport);
            client = new TimestampService.Client(protocol);
            System.out.println("client init");
        } catch (TTransportException ex) {
            Logger.getLogger(TimestampServiceBean.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("client init failed");
        }
    }
    
    @PreDestroy()
    public void destroy() {
        transport.close();
    }
    
    public String getTimestamp() {
        String timestamp = "";
        try {
            timestamp = client.currentTimestamp();
            System.out.println("success");
        } catch (TException ex) {
            Logger.getLogger(TimestampServiceBean.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("failed");
        }
        return timestamp;
    }
}