package webserver;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Map;

public class request_handler {

    request_util req_util = new request_util();
    public void run(Socket connection){
        try {
            System.out.println("New Client Connection - IP : " + connection.getInetAddress() +
                    " Port : " +connection.getPort());
            InputStream in = connection.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(in, "UTF-8"));
            String line;

            Map<String, String> header_map = req_util.read_header(br);
        }
        catch(Exception e){
            System.out.println("에러 발생");
        }
    }
}
