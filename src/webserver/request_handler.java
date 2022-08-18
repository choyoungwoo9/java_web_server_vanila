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
            System.out.println("Content-length : " + Integer.valueOf(header_map.get("Content-Length")));
            String request_body = request_util.read_data(br, Integer.valueOf(header_map.get("Content-Length")));
            System.out.println(request_body.trim());

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
