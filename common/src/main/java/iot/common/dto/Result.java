package iot.common.dto;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;

/**
 * @author :  sylar
 * @FileName :
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
public class Result<T> implements Serializable {

    public final static int SUCCESS = 0;
    public final static int FAILD = 1;

    public static boolean isSuccess(Result result) {
        return result.getRc() == SUCCESS;
    }

    public static <T> Result<T> newFaild() {
        return newResult(FAILD, null, null);
    }

    public static <T> Result<T> newFaild(String err) {
        return newResult(FAILD, null, err);
    }

    public static <T> Result<T> newFaild(int rc, String err) {
        return newResult(rc, null, err);
    }

    public static <T> Result<T> newSuccess() {
        return newResult(SUCCESS, null, null);
    }

    public static <T> Result<T> newSuccess(T t) {
        return newResult(SUCCESS, t, null);
    }

    private static <T> Result<T> newResult(int rc, T t, String err) {
        return new Result<T>(rc, t, err);
    }

    /**
     * 返回码
     */
    private int rc;

    /**
     * 正确时的返回结果
     */
    private T ret;

    /**
     * 失败时的异常信息
     */
    private String err;


    private Result() {
    }

    private Result(int rc, String err) {
        this(rc, null, err);
    }

    private Result(int rc, T ret) {
        this(rc, ret, null);
    }

    private Result(int rc, T ret, String err) {
        setRc(rc);
        setRet(ret);
        setErr(err);
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

    public int getRc() {
        return rc;
    }

    public void setRc(int rc) {
        this.rc = rc;
    }

    public String getErr() {
        return this.err;
    }

    public void setErr(String err) {
        this.err = err;
    }

    public T getRet() {
        return ret;
    }

    public void setRet(T ret) {
        this.ret = ret;
    }


}