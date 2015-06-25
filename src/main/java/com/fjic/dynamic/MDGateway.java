package com.fjic.dynamic;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import quickfix.Application;
import quickfix.DoNotSend;
import quickfix.FieldNotFound;
import quickfix.IncorrectDataFormat;
import quickfix.IncorrectTagValue;
import quickfix.Message;
import quickfix.fix50.MessageCracker;
import quickfix.RejectLogon;
import quickfix.SessionID;
import quickfix.UnsupportedMessageType;

/**
 * MarketData Gateway.
 * 
 * @author fibarra
 */
public final class MDGateway implements Application, InitializingBean {
    /**
     * Delay inicial inmediato a un logon.
     */
    private static final int EXTRA_LOGON_TIME = 1000;
    
    /**
     * Class Logger.
     */
    private final Logger log = LoggerFactory.getLogger(this.getClass().getName());
    
    /**
     * Current active session list.
     */
    private final List<SessionID> activeSessions;
    
    /**
     * Class Constructor.
     */
    public MDGateway() {
        activeSessions = new ArrayList<SessionID>();
    }
    
    @Override
    public void onCreate(final SessionID sid) {
        log.info("Session Created: [" + sid + "]");
    }

    @Override
    public void onLogon(final SessionID sid) {
        activeSessions.add(sid);
    }

    @Override
    public void onLogout(final SessionID sid) {
        activeSessions.remove(sid);
    }

    @Override
    public void toAdmin(final Message msg, final SessionID sid) {
    }

    @Override
    public void fromAdmin(final Message msg, final SessionID sid) 
            throws FieldNotFound, IncorrectDataFormat, IncorrectTagValue, RejectLogon {
    }

    @Override
    public void toApp(final Message msg, final SessionID sid) throws DoNotSend {
    }

    @Override
    public void fromApp(final Message msg, final SessionID sid) 
            throws FieldNotFound, IncorrectDataFormat, IncorrectTagValue, UnsupportedMessageType {
       log.info(msg.toString());
    }

    @Override
    public void afterPropertiesSet() throws Exception {
    }    
}
