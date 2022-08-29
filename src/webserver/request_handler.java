package webserver;

import java.io.*;
import java.net.Socket;
import java.net.URL;
import java.nio.file.Files;
import java.util.Map;

public class request_handler {

    request_util req_util = new request_util();

    public void run(Socket connection) {
        System.out.println("START //// request_handler.run");
        System.out.println("New Client Connection - IP : " + connection.getInetAddress() +
                " Port : " + connection.getPort());
        try (InputStream in = connection.getInputStream();
             OutputStream out = connection.getOutputStream()) {

            BufferedReader br = new BufferedReader(new InputStreamReader(in, "UTF-8"));

            String first_line = br.readLine();
            String arr_first_line[] = first_line.split(" ");

            if (arr_first_line.length == 3) {
                String http_method = arr_first_line[0];
                String http_url = arr_first_line[1].equals("/") ? "/index.html" : arr_first_line[1];
                String http_protocol = arr_first_line[2];

                Map<String, String> header_map = req_util.read_header(br);

                System.out.println("req 헤더 출력 시작");
                header_map.forEach((key, value) -> {
                    System.out.println(key + " : " + value);
                });
                System.out.println("req 헤더 출력 끝");

                if (header_map.containsKey("Content-Length") && !http_method.equals("GET")) {
                    System.out.println("Content-Length : " + Integer.valueOf(header_map.get("Content-Length")));
                    String request_body =
                            request_util.read_data(br, Integer.valueOf(header_map.get("Content-Length")));
                    System.out.println(request_body.trim());
                } else if (!http_method.equals("GET")) {
                    System.out.println("Content-length : " + 0);
                    String request_body =
                            request_util.read_data(br, 0);
                    System.out.println("request_body.trim()");
                    System.out.println(request_body.trim());
                }

                DataOutputStream dos = new DataOutputStream(out);
                File file = new File(System.getProperty("user.dir") + "\\src\\webserver\\webapp\\" + http_url);
                String file_path_404 = System.getProperty("user.dir") + "\\src\\webserver\\webapp\\404page.html";
                System.out.println(file_path_404);
                byte[] body;
                if (file.exists()) {
                    body = Files.readAllBytes(file.toPath());
                } else {
                    body = Files.readAllBytes(new File(file_path_404).toPath());
                }

                response_util.response_200_header(dos, body.length);
                response_util.response_body(dos, body);
            } else {
                throw new Exception("INVALID FORMAT");
            }

        } catch (IOException e) {
            System.out.println("error: " + e);
        } catch (Exception e) {
            System.out.println("error: " + e);
        } finally {
            try {
                connection.close();
            } catch (IOException e) {
                System.out.println("error: " + e);
            }
        }

        System.out.println("END //// request_handler.run");
    }
}
