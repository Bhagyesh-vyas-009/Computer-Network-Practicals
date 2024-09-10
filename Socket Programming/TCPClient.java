import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class TCPClient {
    public static void main(String[] args) {
        Socket socket = null;
        BufferedReader in = null;
        PrintWriter out = null;

        try{
            // Creating client socket
            socket = new Socket(args[0],Integer.parseInt(args[1]));

            // Setting up input and output streams
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);

            //sending response from server
            String messageFromServer = in.readLine();
            System.out.println("Message fro server: " + messageFromServer);
        } catch(IOException e){
            e.printStackTrace();
        } finally{
            try {
                if (in != null)
                    in.close();
                if (out != null)
                    out.close();
                if (socket != null)
                    socket.close();
            } catch (IOException e) {
               e.printStackTrace();
            }
        }
    }
}
