package hb345.webapps2020.data.entity;

import hb345.webapps2020.data.entity.SystemUser;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-05-15T13:56:41")
@StaticMetamodel(SystemRequest.class)
public class SystemRequest_ { 

    public static volatile SingularAttribute<SystemRequest, SystemUser> requester;
    public static volatile SingularAttribute<SystemRequest, SystemUser> requestee;
    public static volatile SingularAttribute<SystemRequest, Date> requestTimestamp;
    public static volatile SingularAttribute<SystemRequest, Double> fromAmount;
    public static volatile SingularAttribute<SystemRequest, Double> toAmount;
    public static volatile SingularAttribute<SystemRequest, Long> id;

}