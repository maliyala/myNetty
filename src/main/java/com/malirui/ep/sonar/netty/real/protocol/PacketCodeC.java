package com.malirui.ep.sonar.netty.real.protocol;

import com.malirui.ep.sonar.netty.real.protocol.request.LoginRequestPacket;
import com.malirui.ep.sonar.netty.real.protocol.request.MessageRequestPacket;
import com.malirui.ep.sonar.netty.real.protocol.response.LoginResponsePacket;
import com.malirui.ep.sonar.netty.real.protocol.response.MessageResponsePacket;
import com.malirui.ep.sonar.netty.real.serialize.Serializer;
import com.malirui.ep.sonar.netty.real.serialize.impl.JSONSerializer;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;

import java.util.HashMap;
import java.util.Map;

import static com.malirui.ep.sonar.netty.real.protocol.command.Command.*;

/**
 * @author maliruimeituan.com
 * @create 2019-01-29:上午10:23
 */
public class PacketCodeC {
    private static final int MAGIC_NUMBER = 0x12345678;
    public static final PacketCodeC INSTANCE = new PacketCodeC();

    private final Map<Byte, Class<? extends Packet>> packetTypeMap;
    private final Map<Byte, Serializer> serializerMap;

    private PacketCodeC(){
        packetTypeMap = new HashMap<>();
        packetTypeMap.put(LOGIN_REQUEST,LoginRequestPacket.class);
        packetTypeMap.put(LOGIN_RESPONSE,LoginResponsePacket.class);
        packetTypeMap.put(MESSAGE_REQUEST,MessageRequestPacket.class);
        packetTypeMap.put(MESSAGE_RESPONSE,MessageResponsePacket.class);
        serializerMap = new HashMap<>();
        Serializer serializer = new JSONSerializer();
        serializerMap.put(serializer.getSerializerAlogrithm(),serializer);
    }

    /**
     * 编码
     * @param byteBuf
     * @param packet
     * @return
     */
    public void encode(ByteBuf byteBuf, Packet packet) {
        // 1. 序列化 java 对象
        byte[] bytes = Serializer.DEFAULT.serialize(packet);

        // 2. 实际编码过程
        byteBuf.writeInt(MAGIC_NUMBER);
        byteBuf.writeByte(packet.getVersion());
        byteBuf.writeByte(Serializer.DEFAULT.getSerializerAlogrithm());
        byteBuf.writeByte(packet.getCommand());
        byteBuf.writeInt(bytes.length);
        byteBuf.writeBytes(bytes);
    }



    /**
     *
     * @param byteBuf
     * @return
     */
    public Packet decode(ByteBuf byteBuf) {
        // 跳过 magic number
        byteBuf.skipBytes(4);

        // 跳过版本号
        byteBuf.skipBytes(1);

        // 序列化算法
        byte serializeAlgorithm = byteBuf.readByte();
        // 指令
        byte command = byteBuf.readByte();

        // 数据包长度
        int length = byteBuf.readInt();

        byte[] bytes = new byte[length];
        byteBuf.readBytes(bytes);

        Class<? extends Packet> requestType = getRequestType(command);
        Serializer serializer = getSerializer(serializeAlgorithm);

        if (requestType != null && serializer != null) {
            return serializer.deserialize(requestType, bytes);
        }

        return null;
    }


    private Serializer getSerializer(byte serializerAlogithm) {
        return serializerMap.get(serializerAlogithm);
    }

    private Class<? extends Packet> getRequestType(byte command) {
        return packetTypeMap.get(command);
    }
}
