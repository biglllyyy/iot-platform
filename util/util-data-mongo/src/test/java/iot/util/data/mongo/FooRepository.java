package iot.util.data.mongo;

/**
 * File Name             :  FooRepository
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
public interface FooRepository extends BaseMongoRepository<Foo, String> {
    Foo getByName(String name);
}
