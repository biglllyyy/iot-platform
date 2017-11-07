package me.iot.util.gps.enums;

/**
 * File Name             :  GpsCoorType
 * Author                :  luhao
 * Create Date           :  2016/8/25
 * Description           :
 * Reviewed By           :
 * Reviewed On           :
 * Version History       :
 * Modified By           :
 * Modified Date         :
 * Comments              :
 * CopyRight             : COPYRIGHT(c) www.XXXXX.com   All Rights Reserved
 * *******************************************************************************************
 */
public enum GpsCoorType {

    B2A, //百度坐标-->高德坐标
    B2W, //百度坐标-->国际坐标
    A2B, //高德坐标-->百度坐标
    A2W, //高德坐标-->国际坐标
    W2B, //国际坐标-->百度坐标
    W2A  //国际坐标-->高德坐标
}
