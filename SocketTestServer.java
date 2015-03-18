import java.net.Socket;
import java.net.ServerSocket;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.IOException;

public class SocketTestServer {

  public static final int ECHO_PORT = 8080;

  public static void main(String args[]) {
    ServerSocket serverSocket = null;
    Socket socket = null;
    try {
      serverSocket = new ServerSocket(ECHO_PORT);

      //このソケットのバインド先のローカルポートを返す
      System.out.println("--- Start Server ---");
      System.out.println("Listening on " + serverSocket.getLocalPort());
      
      //このソケットが接続されている端点のアドレスを返す
      socket = serverSocket.accept();
      System.out.println("Accept returned! ：" + socket.getRemoteSocketAddress());
      
      //受信用と送信用のインスタンス生成
      BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
      PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

      String line;

      while ( (line = in.readLine()) != null ) {
        System.out.println("受信: " + line);
        out.println(line);
        System.out.println("送信: " + line);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
