package me.iot.util.mq;

/**
 * @author :  sylar
 * @FileName :  IFactory
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
public interface IFactory {

    /**
     * @param provider
     * @return
     */
    IProducer createProducer(Provider provider);

    /**
     * @param provider
     * @return
     */
    IConsumer createConsumer(Provider provider);
}
