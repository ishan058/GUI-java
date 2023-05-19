import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(1234);
            Socket socket = serverSocket.accept();
            System.out.println("A client is addeed");

            //Input from user
            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));

            //taking the input from socket
            BufferedReader socketInput = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            //to send the message i need a pen
            OutputStream outputStream = socket.getOutputStream();
            PrintWriter printWriter = new PrintWriter(outputStream);

            String sendingMessage, receivingMessage;
            while(true) {
                sendingMessage = userInput.readLine();
                printWriter.println(sendingMessage);
                printWriter.flush();
                receivingMessage = socketInput.readLine();
                System.out.println("Message from client: "+ receivingMessage);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}