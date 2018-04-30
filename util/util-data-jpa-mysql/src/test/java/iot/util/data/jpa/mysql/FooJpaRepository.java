package iot.util.data.jpa.mysql;

import iot.util.data.jpa.BaseJpaRepository;

/**
 * File Name             :  MongoFooRepository
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
public interface FooJpaRepository extends BaseJpaRepository<Foo, Long> {

    Foo getByName(String name);
}
