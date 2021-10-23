import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

public class BackServer {

    private static final AtomicBoolean flag = new AtomicBoolean(true);

    private static void service(Socket socket){
        try{
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            InputStream inputStream = socket.getInputStream();
            byte[] buffer = new byte[8*1024];
            int len = inputStream.read(buffer);
            outputStream.write(buffer, 0, len);
            byte[] inputBuffer  = outputStream.toByteArray();
            String content = new String(inputBuffer, "UTF-8");
            System.out.println("server 接收到请求: "+content);
            outputStream.close();

            Thread.sleep(20);
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
            printWriter.println("HTTP/1.1 200 OK");
            printWriter.println("Content-Type:text/html;charset=UTF-8");
            String body = "hello, nio3";
            printWriter.println("Content-Length:"+body.getBytes().length);
            printWriter.println();
            printWriter.write(body);
            printWriter.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        ExecutorService excutorService = Executors.newFixedThreadPool(20);
        ServerSocket serverSocket = new ServerSocket(8088);
        while(flag.get()){
            try {
                final Socket socket = serverSocket.accept();
                excutorService.execute(()->
                    service(socket));
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        serverSocket.close();
        excutorService.shutdown();
    }
}
