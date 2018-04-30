package iot.util.data.jpa.oracle;

import iot.util.data.jpa.po.AbstractJpaPO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * File Name             :  MongoFoo
 * Author                :  sylar
 * Create Date           :  2018/4/15
 * Description           :
 * Reviewed By           :
 * Reviewed On           :
 * Version History       :
 * Modified By           :
 * Modified Date         :
 * Comments              :
 * CopyRight             : COPYRIGHT(c) me.iot.com   All Rights Reserved
 * *******************************************************************************************
 */
@Table
@Entity
public class Foo extends AbstractJpaPO {

    @Column
    private String name;

    @Column
    private int count;

    public String getName() {
        return name;
    }

    public Foo setName(String name) {
        this.name = name;
        return this;
    }

    public int getCount() {
        return count;
    }

    public Foo setCount(int count) {
        this.count = count;
        return this;
    }

}
