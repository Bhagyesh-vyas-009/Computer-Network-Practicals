import java.io.*;
import java.net.*;

public class Server {

    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        Socket clientSocket = null;
        BufferedReader in = null;
        PrintWriter out = null;
        BufferedReader consoleInput = null;
        try {
            serverSocket = new ServerSocket(Integer.parseInt(args[0]));
            System.out.println("Server started. Waiting for a client...");
            clientSocket = serverSocket.accept();
            System.out.println("Client connected.\n Type \"exit\" to end chat");

            // Create input and output streams for communication
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out = new PrintWriter(clientSocket.getOutputStream(), true);

            consoleInput = new BufferedReader(new InputStreamReader(System.in));
            String serverMessage, clientMessage;

            while (true) {
                // Receive a message from the client
                clientMessage = in.readLine();
                System.out.println("Client: " + clientMessage);

                // Send a message to the client
                System.out.print("Server: ");
                serverMessage = consoleInput.readLine();
                if (serverMessage.equalsIgnoreCase("exit")) {
                    System.out.println("Closing the connection...");
                    break;
                }
                out.println(serverMessage);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                serverSocket.close();
                clientSocket.close();
                in.close();
                out.close();
                consoleInput.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
} 
