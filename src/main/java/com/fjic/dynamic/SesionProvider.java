package com.fjic.dynamic;

import java.util.HashMap;
import java.util.Map;
import javax.management.JMException;
import javax.management.ObjectName;
import org.quickfixj.jmx.JmxExporter;
import quickfix.Application;
import quickfix.ConfigError;
import quickfix.DefaultSessionFactory;
import quickfix.LogFactory;
import quickfix.MessageStoreFactory;
import quickfix.SessionID;
import quickfix.mina.SessionConnector;
import quickfix.mina.acceptor.AcceptorSessionProvider;
import quickfix.Session;
import quickfix.SessionFactory;

/**
 *
 * @author fibarra
 */
public class SesionProvider implements AcceptorSessionProvider {
    private final SessionFactory sessionFactory;
    private final Map<SessionID, SessionWrapper> sessionList;
    private JmxExporter jmxExporter;
    
    public SesionProvider(final Application application, MessageStoreFactory storeFactory, LogFactory logFactory) {
        sessionFactory = new DefaultSessionFactory(application, storeFactory, logFactory);
        sessionList = new HashMap<SessionID, SessionWrapper>();
    }
    
    public void setJmxExporter(final JmxExporter jmxExporter) {
        this.jmxExporter = jmxExporter;
    }
    
    @Override
    public Session getSession(SessionID sid, SessionConnector sc) {
        Session s = null;
        
        try {
            if (sid.getTargetCompID().equalsIgnoreCase("ACCO")) {
                SessionWrapper sw = sessionList.get(sid);
                
                if (sw == null) {
                    s = sessionFactory.create(sid, sc.getSettings());
                    sw = new SessionWrapper(s);
                    jmxExporter.registerMBean(sw, 
                        new ObjectName("com.fjic:type=SessionManagement,role=Acceptor,name=" 
                            + sid.getBeginString() + "-" + sid.getTargetCompID() + "_" + sid.getTargetSubID()));
                    sessionList.put(sid, sw);
                } else {
                    s = sw.getSession();
                }
            }
        } catch(ConfigError e) {
            System.err.println(e.getMessage());
        } catch (JMException e) {
            System.err.println(e.getMessage());
        }
        
        return s;
    }

}
