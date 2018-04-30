/*
 * Copyright (c) 2012-2014 The original author or authors
 * ------------------------------------------------------
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Apache License v2.0 which accompanies this distribution.
 *
 * The Eclipse Public License is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * The Apache License v2.0 is available at
 * http://www.opensource.org/licenses/apache2.0.php
 *
 * You may elect to redistribute this code under either of these licenses.
 */
package iot.dcp.mqtt.protocol.message;

/**
 * Base class for alla the messages that carries only MessageID. (PUBACK, PUBREC,
 * PUBREL, PUBCOMP, UNSUBACK)
 * <p>
 * The flags dup, QOS and Retained doesn't take care.
 *
 * @author andrea
 */
public class MessageIDMessage extends AbstractMessage {
    /**
     * could be null if Qos is == 0
     */
    private Integer mMessageID;

    public Integer getMessageID() {
        return mMessageID;
    }

    public void setMessageID(Integer messageID) {
        this.mMessageID = messageID;
    }

}
