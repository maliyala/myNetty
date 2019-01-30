package com.malirui.ep.sonar.netty.client;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;

/**
 * @author maliruimeituan.com
 * @create 2019-01-26:上午11:17
 */
public class ByteBufTest {
    public static void main(String[] args) {
        ByteBuf buf = ByteBufAllocator.DEFAULT.buffer(9,100);
        //print("allocate ByteBuf(9, 100)", buf);
        // write 方法改变写指针，写完之后写指针未到 capacity 的时候，buffer 仍然可写
        buf.writeBytes(new byte[]{1, 2, 3, 4});
        //print("writeBytes(1,2,3,4)", buf);
        // write 方法改变写指针，写完之后写指针未到 capacity 的时候，buffer 仍然可写, 写完 int 类型之后，写指针增加4
        buf.writeInt(12);
        print("writeInt(12)", buf);
        // write 方法改变写指针, 写完之后写指针等于 capacity 的时候，buffer 不可写
        buf.writeBytes(new byte[]{5});
        print("writeBytes(5)", buf);
        // write 方法改变写指针，写的时候发现 buffer 不可写则开始扩容，扩容之后 capacity 随即改变
        buf.writeBytes(new byte[]{6});
        print("writeBytes(6)", buf);
        // get 方法不改变读写指针
        System.out.println("getByte(3) return: " + buf.getByte(3));
        System.out.println("getShort(3) return: " + buf.getShort(3));
        System.out.println("getInt(3) return: " + buf.getInt(3));
        print("getByte()", buf);
        // set 方法不改变读写指针
        buf.setByte(buf.readableBytes() + 1, 0);
        print("setByte()", buf);
        // read 方法改变读指针
        byte[] dst = new byte[buf.readableBytes()];
        buf.readBytes(dst);
        print("readBytes(" + dst.length + ")", buf);


    }
    private static void print(String action, ByteBuf buf){
        System.out.println("before ===========" + action + "============");
        System.out.println("capacity():"+buf.capacity());
        System.out.println("maxCapacity():"+buf.maxCapacity());
        System.out.println("readerIndex():"+buf.readerIndex());
        System.out.println("readableBytes():"+buf.readableBytes());
        System.out.println("isReadable():"+buf.isReadable());
        System.out.println("writerIndex():"+buf.writerIndex());
        System.out.println("writableBytes():"+buf.writableBytes());
        System.out.println("isWritable():"+buf.isWritable());
        System.out.println("maxWritableBytes():"+buf.maxWritableBytes());
        System.out.println("after  ===========" + action + "============");
    }
}
