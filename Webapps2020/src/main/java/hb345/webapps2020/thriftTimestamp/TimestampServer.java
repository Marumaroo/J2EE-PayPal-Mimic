/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hb345.webapps2020.thriftTimestamp;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.server.TThreadPoolServer.Args;
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;
import org.apache.thrift.transport.TTransportException;

@Startup
@Singleton
public class TimestampServer {
    
    public static TimestampHandler handler;
    public static TimestampService.Processor processor;
    public static TServerTransport serverTransport;
    public static TServer server;
    
    public TimestampServer() {

    }
    
    @PostConstruct
    public void init() {
        handler = new TimestampHandler();
        processor = new TimestampService.Processor(handler);
        Runnable simple;
        simple = () -> {
            simpleServer(processor);
        };
        new Thread(simple).start();
    }
    
    @PreDestroy
    public void cleanup() {
        server.stop();
    }
    
    public static void simpleServer(TimestampService.Processor processor) {
        try {
            serverTransport = new TServerSocket(10000);
            Args args = new TThreadPoolServer.Args(serverTransport).processor(processor);
            server = new TThreadPoolServer(args);
            server.serve();
        } catch (Exception ex) {
            System.err.println(ex);
        }
    }
}
