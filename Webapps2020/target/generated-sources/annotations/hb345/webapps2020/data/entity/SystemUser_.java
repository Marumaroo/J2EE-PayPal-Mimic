package hb345.webapps2020.data.entity;

import hb345.webapps2020.data.entity.SystemRequest;
import hb345.webapps2020.data.entity.SystemTransaction;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-05-15T13:56:41")
@StaticMetamodel(SystemUser.class)
public class SystemUser_ { 

    public static volatile SingularAttribute<SystemUser, String> password;
    public static volatile SingularAttribute<SystemUser, Double> balance;
    public static volatile SingularAttribute<SystemUser, String> currency;
    public static volatile ListAttribute<SystemUser, SystemTransaction> received;
    public static volatile SingularAttribute<SystemUser, Long> id;
    public static volatile ListAttribute<SystemUser, SystemRequest> requests;
    public static volatile ListAttribute<SystemUser, SystemTransaction> sent;
    public static volatile SingularAttribute<SystemUser, String> username;

}