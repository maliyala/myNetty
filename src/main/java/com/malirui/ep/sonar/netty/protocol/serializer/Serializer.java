package com.malirui.ep.sonar.netty.protocol.serializer;

import com.malirui.ep.sonar.netty.protocol.serializer.impl.JSONSerializer;

/**
 * 序列化算法
 * @author maliruimeituan.com
 * @create 2019-01-28:上午10:23
 */
public interface Serializer {
    Serializer DEFAULT = new JSONSerializer();
    /**
     * 序列化算法
     * @return
     */
    byte getSerializerAlgorithm();

    /**
     * java 对象转换成二进制
     * @param object
     * @return
     */
    byte[] serialize(Object object);

    /**
     * 二进制转换成java对象
     * @param clazz
     * @param bytes
     * @param <T>
     * @return
     */
    <T> T deserialize(Class<T> clazz,byte[] bytes);
}
