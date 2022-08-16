package webserver;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Logger;


public class web_server {

    private static final int port = 12345;
    public static void main(String args[]){
        System.out.println("서버 시작");
        System.out.println("포트 번호 : " + port);
        try
        {
            ServerSocket listen_socket = new ServerSocket(port);

            Socket socket;
            while((socket=listen_socket.accept())!=null){
                System.out.println("socket start");
            }
        }
        catch(Exception e)
        {
            System.out.println("에러발생" + e.getMessage());
        }
    }
}
