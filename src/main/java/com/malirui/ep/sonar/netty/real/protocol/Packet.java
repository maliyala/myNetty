package com.malirui.ep.sonar.netty.real.protocol;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * @author maliruimeituan.com
 * @create 2019-01-29:上午10:11
 */
@Data
public abstract class Packet {
    /**
     * 协议版本号
     */
    @JSONField(deserialize = false,serialize = false)
    private Byte version =1;

    @JSONField(serialize = false)
    public abstract Byte getCommand();
}

