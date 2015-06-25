package com.fjic.dynamic;

import quickfix.Message;

/**
 * Interface para implementar en la clase que envie mensajes.
 * @author fibarra
 */
public interface FIXPublisher {
    /**
     * Metodo para enviar un mensaje FIX a los subscriptores.
     * @param message Mensaje FIX a enviar.
     */
    void sendMessageToSubscribers(final Message message);
}
