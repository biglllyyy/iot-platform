package me.iot.util.gps.bmap.convertor;

import me.iot.util.gps.AbstractGpsCorrecter;
import me.iot.util.gps.common.BasicPosition;

/**
 * File Name             :  BMapGpsCorrecter
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
public class BMapGpsCorrecter extends AbstractGpsCorrecter {
    private String bmapGeoconvUrl;

    /**
     * wgs84转百度坐标系
     *
     * @param basicPosition
     */
    public static <T extends BasicPosition> void wgs2bmap(T basicPosition) {
//        RestTemplate restTemplate = new RestTemplate();
    }

    public static double[] wgs2bmap(double wgLon, double wgLat) {
        double[] result = new double[2];

        return result;
    }

}
