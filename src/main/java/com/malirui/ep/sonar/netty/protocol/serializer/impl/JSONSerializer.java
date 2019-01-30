package com.malirui.ep.sonar.netty.protocol.serializer.impl;

import com.alibaba.fastjson.JSON;
import com.malirui.ep.sonar.netty.protocol.serializer.Serializer;
import com.malirui.ep.sonar.netty.protocol.serializer.SerializerAlgorithm;

/**
 * json序列化实现
 * @author maliruimeituan.com
 * @create 2019-01-28:上午10:30
 */
public class JSONSerializer implements Serializer {

    Serializer DEFAULT = new JSONSerializer();

    public byte getSerializerAlgorithm() {
        return SerializerAlgorithm.JSON;
    }

    public byte[] serialize(Object object) {
        return JSON.toJSONBytes(object);
    }

    public <T> T deserialize(Class<T> clazz, byte[] bytes) {
        return JSON.parseObject(bytes,clazz);
    }
}
