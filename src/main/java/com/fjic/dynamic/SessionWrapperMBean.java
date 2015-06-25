package com.fjic.dynamic;

import java.io.IOException;

/**
 *
 * @author fibarra
 */
public interface SessionWrapperMBean {
    public void nextTargetMsgSeqNum(int num) throws IOException;
    
    public void nextSenderMsgSeqNum(int num) throws IOException;
    
    public void logon();
    
    public void logout();

    public int getNexTargetMsgSeqNum();
    
    public int getNexSenderMsgSeqNum();

    public boolean isLoggedOn();

    public boolean isEnabled();
    
}
