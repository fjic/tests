package com.fjic.dynamic;

import java.io.IOException;
import quickfix.Session;
import quickfix.SessionID;

/**
 *
 * @author fibarra
 */
public class SessionWrapper implements SessionWrapperMBean {
    private final Session session;
    
    public SessionWrapper(final Session session) {
        this.session = session;
    }
    
    public Session getSession() {
        return this.session;
    }
    
    public SessionID getSessionID() {
        return session.getSessionID(); 
    }
    
    @Override
    public void nextTargetMsgSeqNum(int num) throws IOException {
        session.setNextTargetMsgSeqNum(num);
    }
    
    @Override
    public void nextSenderMsgSeqNum(int num) throws IOException {
        session.setNextSenderMsgSeqNum(num);
    }

    @Override
    public boolean isLoggedOn() {
        return session.isLoggedOn();
    }

    @Override
    public synchronized boolean isEnabled() {
        return session.isEnabled();
    }

    @Override
    public void logon() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void logout() {
        session.generateLogout();
    }

    @Override
    public int getNexTargetMsgSeqNum() {
        return session.getExpectedTargetNum();
    }

    @Override
    public int getNexSenderMsgSeqNum() {
        return session.getExpectedSenderNum();
    }
        
}
