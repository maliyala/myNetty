package com.malirui.ep.sonar.netty.real.serialize;


import com.malirui.ep.sonar.netty.real.serialize.impl.JSONSerializer;

/**
 * @author maliruimeituan.com
 * @create 2019-01-29:上午10:27
 */
public interface Serializer {

    Serializer DEFAULT = new JSONSerializer();
    /**
     * 序列化算法
     */
    byte getSerializerAlogrithm();
    /**
     * java 对象装换成二进制
     */
    byte[] serialize(Object object);
    /**
     * 二进制转换成java对象
     */
    <T> T deserialize(Class<T> clazz,byte[] bytes);
}
