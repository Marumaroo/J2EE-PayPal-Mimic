/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hb345.webapps2020.thriftTimestamp;

import java.sql.Timestamp;
import org.apache.thrift.TException;

public class TimestampHandler implements TimestampService.Iface {

    @Override
    public String currentTimestamp() throws TException {
        String timestamp = new Timestamp(System.currentTimeMillis()).toString();
        System.out.println(timestamp);
        return timestamp;
    }
}
