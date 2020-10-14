package hb345.webapps2020.data.entity;

import hb345.webapps2020.data.entity.SystemUser;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-05-15T13:56:41")
@StaticMetamodel(SystemTransaction.class)
public class SystemTransaction_ { 

    public static volatile SingularAttribute<SystemTransaction, SystemUser> sender;
    public static volatile SingularAttribute<SystemTransaction, Double> fromAmount;
    public static volatile SingularAttribute<SystemTransaction, SystemUser> recipient;
    public static volatile SingularAttribute<SystemTransaction, Double> toAmount;
    public static volatile SingularAttribute<SystemTransaction, Long> id;
    public static volatile SingularAttribute<SystemTransaction, Date> transactionTimestamp;

}