package week_02.homework6;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    public static void main(String[] args) throws IOException {
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors()+1);
        final ServerSocket serverSocket = new ServerSocket(8081,5);

        while (true){
            try{
                final Socket socket = serverSocket.accept();
                System.out.println("接收到一个请求 step 1");
//                processing(socket);
                executorService.execute(()-> processing(socket));
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public static void processing(Socket socket) {
        PrintWriter printWriter = null;
        try {
            Thread.sleep(5000);
            printWriter = new PrintWriter(socket.getOutputStream(),true);
            printWriter.println("HTTP/1.1 200 OK");
            printWriter.println("Content-Type:text/html;charset=utf-8");
            String body = "hello,nio1";
            printWriter.println("Content-Length:" + body.getBytes(StandardCharsets.UTF_8).length);
            printWriter.println();
            printWriter.write(body);
            printWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
