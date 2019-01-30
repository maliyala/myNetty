package com.malirui.ep.sonar.netty.Io;

import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.util.Date;

/**
 * @author maliruimeituan.com
 * @create 2019-01-25:下午5:08
 */
public class IOClient {
    public static void main(String[] args) {
        new Thread(()->{
            try{
                Socket socket = new Socket("127.0.0.1",8099);
                //接受新连接线程
                while(true){
                    try{
                        socket.getOutputStream().write((new Date()+":hello world!").getBytes());
                        Thread.sleep(2000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }catch (IOException e){
            }
        }).start();
    }
}
