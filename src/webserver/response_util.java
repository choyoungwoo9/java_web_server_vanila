package webserver;

import java.io.DataOutputStream;
import java.io.IOException;

public class response_util {
    public static void response_200_header(String respContextType, DataOutputStream dos, int lengthOfBodyContent){
        System.out.println("START //// response_util.response_200_header");
        try{
            dos.writeBytes("HTTP/1.1 200 OK \r\n");
            dos.writeBytes("Content-Type: text/html;charset=utf-8\r\n");
            //dos.writeBytes("Content-Type: text/html,text/css,text/javascript;charset=utf-8\r\n");
            //dos.writeBytes("Content-Type: "+ respContextType + ";charset=utf-8\r\n");
            System.out.println("Content-Type: "+ respContextType + ";charset=utf-8\r\n");
            dos.writeBytes("Content-Length: " + lengthOfBodyContent + "\r\n");
            dos.writeBytes("\r\n");
        }
        catch(IOException e){
            System.out.println("에러발생");
            System.out.println(e.getMessage());
        }
        System.out.println("END //// response_util.response_200_header");
    }
    public static void response_body(DataOutputStream dos, byte[] body){
        System.out.println("START //// response_util.response_body");
        try{
            dos.write(body, 0, body.length);
            dos.flush();
        }
        catch (IOException e){
            System.out.println("에러발생");
            System.out.println(e.getMessage());
        }
        System.out.println("END //// response_util.response_body");
    }
}
