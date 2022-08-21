package webserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class request_util {

    public Map<String, String> read_header(BufferedReader br) throws IOException {
        System.out.println("req_util.read_header");
        Map<String, String> map = new HashMap<String, String>();
        String line;
        System.out.println(br.readLine());
        while((line=br.readLine())!=null){
            if(!line.equals("")){
                System.out.println("line");
                System.out.println(line);
                String key = line.split(":")[0];
                String value = line.split(":")[1].trim();
                map.put(key, value);
            }
            else{
                break;
            }
        }
        return map;
    }

    public static String read_data(BufferedReader br, int contentLength) throws IOException{
        System.out.println("req_util.read_data");
        char[] body = new char[contentLength];
        br.read(body, 0, contentLength);
        return String.copyValueOf(body);
    }
}
