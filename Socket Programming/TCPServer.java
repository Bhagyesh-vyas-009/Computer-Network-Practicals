import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {
    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        Socket clientSocket = null;
        BufferedReader in = null;
        PrintWriter out = null;

        try {
            // Creating server socket
            serverSocket = new ServerSocket(Integer.parseInt(args[0]));
            System.out.println("Server is running on port " + args[0]);

            // Accepting client connection
            clientSocket = serverSocket.accept();
            System.out.println("Client connected");

            // Getting input and output streams
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out = new PrintWriter(clientSocket.getOutputStream(), true);

            String messageFromClient = in.readLine();
            System.out.println("Received message: " + messageFromClient);

            // Sending message to client
            out.println("Server received your message: " + messageFromClient);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null)
                    in.close();
                if (out != null)
                    out.close();
                if (clientSocket != null)
                    clientSocket.close();
                if (serverSocket != null)
                    serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}