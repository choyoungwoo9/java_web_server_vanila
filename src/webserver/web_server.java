package webserver;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Logger;


public class web_server {

    private static final int port = 12345;

    public static void main(String args[]){
        System.out.println("START //// main");
        System.out.println("서버 시작");
        System.out.println("포트 번호 : " + port);

        int log_count = 1;

        try
        {
            ServerSocket listen_socket = new ServerSocket(port);

            Socket socket;
            while((socket=listen_socket.accept())!=null){
                System.out.println("socket start");
                System.out.println("-"+log_count + "-" + "번째 접근입니다.");
                log_count++;
                request_handler req_handler = new request_handler();
                req_handler.run(socket);
            }
        }
        catch(Exception e)
        {
            System.out.println("에러발생" + e.getMessage());
        }
        System.out.println("END //// main");
    }
}
