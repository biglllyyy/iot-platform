package iot.dcp.common.bean;

import iot.util.misc.LocalCache;
import org.springframework.stereotype.Component;

import java.net.InetSocketAddress;


/**
 * @author :  sylar
 * @FileName :  TerminalCache
 * @CreateDate :  2017/11/08
 * @Description :  设备端socket地址缓存(key为终端设备id)
 * @ReviewedBy :
 * @ReviewedOn :
 * @VersionHistory :
 * @ModifiedBy :
 * @ModifiedDate :
 * @Comments :
 * @CopyRight : COPYRIGHT(c) me.iot.com All Rights Reserved
 * *******************************************************************************************
 */
@Component
public class TerminalCache extends LocalCache<InetSocketAddress> {

}
