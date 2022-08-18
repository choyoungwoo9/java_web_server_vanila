package webserver;

import java.io.DataOutputStream;
import java.io.IOException;

public class response_util {
    public static void response_200_header(DataOutputStream dos, int lengthOfBodyContent){
        System.out.println("response_util.response_200_header");
        try{
            dos.writeBytes("HTTP/1.1 200 OK \r\n");
            dos.writeBytes("Content-Type: text/html;charset=utf-8\r\n");
            dos.writeBytes("Content-Length: " + lengthOfBodyContent + "\r\n");
            dos.writeBytes("\r\n");
        }
        catch(IOException e){
            System.out.println("에러발생");
            System.out.println(e.getMessage());
        }
    }
    public static void response_body(DataOutputStream dos, byte[] body){
        System.out.println("response_util.response_body");
        try{
            dos.write(body, 0, body.length);
            dos.flush();
        }
        catch (IOException e){
            System.out.println("에러발생");
            System.out.println(e.getMessage());
        }
    }
}
