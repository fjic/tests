package com.fjic.dynamic;

import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.URL;
import javax.management.JMException;
import org.quickfixj.jmx.JmxExporter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.core.io.UrlResource;
import quickfix.Acceptor;
import quickfix.ConfigError;
import quickfix.mina.acceptor.AbstractSocketAcceptor;
import quickfix.mina.acceptor.AcceptorSessionProvider;

/**
 * Main class.
 * @author fibarra
 */
public final class App {{
    /**
     * FIX Acceptor.
     */
    private Acceptor acceptor;
    
    /**
     * JmxExporter.
     */
    private JmxExporter jmxExporter;
    
    /**
     * sessiosessionProvidernProvider.
     */
    private AcceptorSessionProvider sessionProvider;
    
    /**
     * .
     * @param acceptor .
     */
    public void setAcceptor(final Acceptor acceptor) {
        this.acceptor = acceptor;
    }
    
    /**
     * .
     * @param sessionProvider 
     */
    public void setSessionProvider(final AcceptorSessionProvider sessionProvider) {
        this.sessionProvider = sessionProvider;
    }
    
    public void setJmxExporter(final JmxExporter jmxExporter) {
        this.jmxExporter = jmxExporter;
    }
    /**
     * .
     */
    public App() {
    }
    
    /**
     * 
     * @throws ConfigError .
     * @throws JMException .
     */
    public void run() throws ConfigError, JMException {
        jmxExporter.register(acceptor);
        
        if (sessionProvider != null) {
            
            AbstractSocketAcceptor socketAcceptor = (AbstractSocketAcceptor) acceptor;
            InetSocketAddress sa = new InetSocketAddress(9099);
            socketAcceptor.setSessionProvider(sa, sessionProvider);
        }
        
        acceptor.start();
    }
    
    /**
     * Mail Class.
     * @param args Parameters.
     * @throws java.net.MalformedURLException .
     * @throws quickfix.ConfigError .
     * @throws javax.management.JMException .
     */
    public static void main(final String[] args) throws MalformedURLException, ConfigError, JMException {
        String springFile = System.getProperty("spring.file");
        // BasicConfigurator.configure();
        
        if (springFile != null) {
            ApplicationContext context = new GenericXmlApplicationContext(
                new UrlResource(new URL(springFile)));
            
            App app = context.getBean("app", App.class);
            app.run();
        }
    }
}
