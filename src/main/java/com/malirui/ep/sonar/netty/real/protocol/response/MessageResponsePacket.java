package com.malirui.ep.sonar.netty.real.protocol.response;

import com.malirui.ep.sonar.netty.real.protocol.Packet;
import lombok.Data;

import static com.malirui.ep.sonar.netty.real.protocol.command.Command.MESSAGE_RESPONSE;

/**
 * @author maliruimeituan.com
 * @create 2019-01-29:下午12:15
 */
@Data
public class MessageResponsePacket extends Packet {

    private String message;
    @Override
    public Byte getCommand() {
        return MESSAGE_RESPONSE;
    }
}
