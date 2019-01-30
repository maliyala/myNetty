package com.malirui.ep.sonar.netty.real.serialize.impl;

import com.alibaba.fastjson.JSON;
import com.malirui.ep.sonar.netty.real.serialize.Serializer;
import com.malirui.ep.sonar.netty.real.serialize.SerializerAlogrithm;

/**
 * @author maliruimeituan.com
 * @create 2019-01-29:上午10:32
 */
public class JSONSerializer implements Serializer {
    @Override
    public byte getSerializerAlogrithm() {
        return SerializerAlogrithm.JSON;
    }

    @Override
    public byte[] serialize(Object object) {
        return JSON.toJSONBytes(object);
    }

    @Override
    public <T> T deserialize(Class<T> clazz, byte[] bytes) {
        return JSON.parseObject(bytes,clazz);
    }
}
