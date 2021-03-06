import java.net.Socket;
import java.net.ServerSocket;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.IOException;

public class SocketTestClient {
  public static final int ECHO_PORT = 8080;
  public static void main(String args[]) {
    Socket socket = null;
    try {
      socket = new Socket(args[0], ECHO_PORT);
      System.out.println("接続しました" + socket.getRemoteSocketAddress());

      //入出力のインスタンス生成
      BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
      PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
      BufferedReader keyIn = new BufferedReader(new InputStreamReader(System.in));
      String input;

      while ( (input = keyIn.readLine()).length() > 0 ) {
        out.println(input);
        String line = in.readLine();
        if (line != null) {
          System.out.println(line);
        } else {
          break;
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
