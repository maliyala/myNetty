package com.malirui.ep.sonar.netty.protocol.command;

import lombok.Data;

import static com.malirui.ep.sonar.netty.protocol.command.Command.LOGIN_REQUEST;


@Data
public class LoginRequestPacket extends Packet {
    private Integer userId;

    private String username;

    private String password;

    @Override
    public Byte getCommand() {
        return LOGIN_REQUEST;
    }
}
