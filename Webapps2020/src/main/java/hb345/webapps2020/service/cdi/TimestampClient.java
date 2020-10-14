/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hb345.webapps2020.service.cdi;

import hb345.webapps2020.thriftTimestamp.TimestampService;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;


public class TimestampClient {
    
    public static String getCurrentTimestamp() {
        String timestamp = null;
        try {
            TTransport transport;
            
            transport = new TSocket("localhost", 15000);
            transport.open();
            
            TProtocol protocol = new TBinaryProtocol(transport);
            TimestampService.Client client = new TimestampService.Client(protocol);
            System.out.println("getting timestamp");
            timestamp = client.currentTimestamp();
            transport.close();
        } catch (TTransportException ex) {
            Logger.getLogger(TimestampClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TException ex) {
            Logger.getLogger(TimestampClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        return timestamp;
    }
}