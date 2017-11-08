package me.iot.util.disruptor;

/**
 * @author :  sylar
 * @FileName :  IMessaging
 * @CreateDate :  2017/11/08
 * @Description :
 * @ReviewedBy :
 * @ReviewedOn :
 * @VersionHistory :
 * @ModifiedBy :
 * @ModifiedDate :
 * @Comments :
 * @CopyRight : COPYRIGHT(c) me.iot.com All Rights Reserved
 * *******************************************************************************************
 */
public interface IMessaging {
    /**
     *
     */
    void stop();

    /**
     * @param value
     */
    void publish(Object value);

    /**
     * @return
     */
    long getRemainBufferSize();
}
