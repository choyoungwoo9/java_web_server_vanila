package webserver;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class request_handler {

    request_util req_util = new request_util();
    public void run(Socket connection){
        System.out.println("request_handler.run");
        System.out.println("New Client Connection - IP : " + connection.getInetAddress() +
                " Port : " +connection.getPort());
        try {
            InputStream in = connection.getInputStream();
            OutputStream out = connection.getOutputStream();

            BufferedReader br = new BufferedReader(new InputStreamReader(in, "UTF-8"));
            Map<String, String> header_map = req_util.read_header(br);

            System.out.println("req 헤더 출력 시작");
            header_map.forEach((key, value) -> {
                System.out.println(key + " : " +value);
            });
            System.out.println("req 헤더 출력 끝");

            if(header_map.containsKey("Content-Length")){
                System.out.println("Content-Length : " + Integer.valueOf(header_map.get("Content-Length")));
                String request_body =
                        request_util.read_data(br, Integer.valueOf(header_map.get("Content-Length")));
                System.out.println(request_body.trim());
            }
            else{
                System.out.println("Content-length : " + 0);
                String request_body =
                        request_util.read_data(br, 0);
                System.out.println("request_body.trim()");
                System.out.println(request_body.trim());
            }

            DataOutputStream dos = new DataOutputStream(out);
            byte[] body = "hello".getBytes();

            response_util.response_200_header(dos, body.length);
            response_util.response_body(dos, body);
        }
        catch(Exception e){
            System.out.println("run 에러 발생");
            e.printStackTrace();
        }
    }
}
