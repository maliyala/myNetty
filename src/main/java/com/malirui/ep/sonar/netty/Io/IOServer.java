package com.malirui.ep.sonar.netty.Io;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author maliruimeituan.com
 * @create 2019-01-25:下午4:57
 */
public class IOServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8099);
        //(1)接受新连接线程
        new Thread(()->{
            while(true){
                try{
                    //1阻塞方法获取新的连接
                    Socket socket = serverSocket.accept();
                    //每一个新的连接都建立一个新的线程，负责读取数据
                    new Thread(()->{
                        try{
                            int len;
                            byte[] data = new byte[1024];
                            InputStream inputStream = socket.getInputStream();
                            //按照字节流方式获取数据
                            while((len=inputStream.read(data))!=-1){
                                System.out.println(new String(data,0,len));
                            }
                        }catch (IOException e){

                        }
                    }).start();
                }catch (IOException e){

                }
            }
        }).start();
    }

}
