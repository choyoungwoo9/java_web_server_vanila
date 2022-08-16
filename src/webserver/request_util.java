package webserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class request_util {
    public Map<String, String> read_header(BufferedReader br) throws IOException {
        System.out.println("req_util");
        Map<String, String> map = new HashMap<String, String>();
        String line;
        System.out.println(br);
        while((line=br.readLine())!=null){
            System.out.println(line);
            if(!line.equals("")){
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
}
