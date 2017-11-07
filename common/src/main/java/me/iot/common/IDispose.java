package me.iot.common;

import java.io.Serializable;

/**
 * 资源释放接口
 *
 * @author sylar
 */
public interface IDispose extends Serializable {
    void dispose();
}
