package iot.quickstart.dustbin.data.entity;

import iot.util.data.jpa.po.AbstractJpaPO;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;


/**
 * @author :  sylar
 * @FileName :  BaseEntity
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
@MappedSuperclass
public abstract class BaseEntity extends AbstractJpaPO {

    @Column(name = "create_user_id")
    private String createUserId;

    @Column(name = "update_user_id")
    private String updateUserId;

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }

    public String getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(String updateUserId) {
        this.updateUserId = updateUserId;
    }
}
