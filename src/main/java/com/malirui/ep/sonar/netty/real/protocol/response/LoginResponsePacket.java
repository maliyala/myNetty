package com.malirui.ep.sonar.netty.real.protocol.response;

import com.malirui.ep.sonar.netty.real.protocol.Packet;
import lombok.Data;

import static com.malirui.ep.sonar.netty.real.protocol.command.Command.LOGIN_RESPONSE;

/**
 * @author maliruimeituan.com
 * @create 2019-01-29:上午10:19
 */
@Data
public class LoginResponsePacket extends Packet {
    private String userid;
    private String userName;
    private boolean success;
    private String reason;
    @Override
    public Byte getCommand() {
        return LOGIN_RESPONSE;
    }
}
