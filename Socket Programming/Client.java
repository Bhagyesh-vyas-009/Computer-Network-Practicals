import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) {
        Socket socket = null;
        BufferedReader in = null;
        PrintWriter out = null;
        BufferedReader consoleInput = null;
        try {
            
            socket = new Socket(args[0], Integer.parseInt(args[1]));
            System.out.println("Connected to the server.\n Type \"exit \" to end chat");

            // Create input and output streams for communication
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);

            consoleInput = new BufferedReader(new InputStreamReader(System.in));
            String serverMessage, clientMessage;

            while (true) {
                // Send a message to the server
                System.out.print("Client: ");
                clientMessage = consoleInput.readLine();
                if (clientMessage.equalsIgnoreCase("exit")) {
                    System.out.println("Closing the connection...");
                    break;
                }
                out.println(clientMessage);

                // Receive a message from the server
                serverMessage = in.readLine();
                System.out.println("Server: " + serverMessage);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally{
            try {
                socket.close();
                in.close();
                out.close();
                consoleInput.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}