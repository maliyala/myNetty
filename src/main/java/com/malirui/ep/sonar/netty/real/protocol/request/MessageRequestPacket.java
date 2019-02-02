package com.malirui.ep.sonar.netty.real.protocol.request;

import com.malirui.ep.sonar.netty.real.protocol.Packet;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.malirui.ep.sonar.netty.real.protocol.command.Command.MESSAGE_REQUEST;

/**
 * @author maliruimeituan.com
 * @create 2019-01-29:上午11:50
 */
@Data
@NoArgsConstructor
public class MessageRequestPacket extends Packet {
    private String toUserId;
    private String message;

    public MessageRequestPacket(String toUserId, String message) {
        this.toUserId = toUserId;
        this.message = message;
    }

    @Override
    public Byte getCommand() {
        return MESSAGE_REQUEST;
    }
}
