package com.malirui.ep.sonar.netty.real.protocol.request;

import com.malirui.ep.sonar.netty.real.protocol.Packet;
import lombok.Data;

import static com.malirui.ep.sonar.netty.real.protocol.command.Command.LOGIN_REQUEST;

/**
 * @author maliruimeituan.com
 * @create 2019-01-29:上午10:11
 */
@Data
public class LoginRequestPacket extends Packet {
    private String userId;

    private String username;

    private String password;
    @Override
    public Byte getCommand() {
        return LOGIN_REQUEST;
    }
}
