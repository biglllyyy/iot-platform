package me.iot.util.gps.enums;

/**
 * @FileName             :  GpsCoorType
 * @Author                :  luhao
 * @CreateDate           :  2016/8/25
 * @Description           :
 * @ReviewedBy           :
 * @ReviewedOn           :
 * @VersionHistory       :
 * @ModifiedBy           :
 * @ModifiedDate         :
 * @Comments              :
 * @CopyRight             : COPYRIGHT(c) www.XXXXX.com   All Rights Reserved
 * *******************************************************************************************
 */
public enum GpsCoorType {
    //百度坐标-->高德坐标
    B2A,
    //百度坐标-->国际坐标
    B2W,
    //高德坐标-->百度坐标
    A2B,
    //高德坐标-->国际坐标
    A2W,
    //国际坐标-->百度坐标
    W2B,
    //国际坐标-->高德坐标
    W2A
}
