package MultiClientServer;

import java.io.*;
import java.net.*;

public class Server {

    public static void main(String[] args) {

        int port = 5000;

        try {
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Server started...");
            System.out.println("Waiting for clients...");

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Client connected");

                ClientHandler clientThread = new ClientHandler(socket);
                Thread t = new Thread(clientThread);
                t.start();
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

class ClientHandler implements Runnable {

    Socket socket;

    ClientHandler(Socket socket) {
        this.socket = socket;
    }

    public void run() {

        try {
            BufferedReader input = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));

            PrintWriter output = new PrintWriter(
                    socket.getOutputStream(), true);

            String message;

            while ((message = input.readLine()) != null) {

                System.out.println("Client: " + message);

                output.println("Server received: " + message);

                if (message.equalsIgnoreCase("bye")) {
                    break;
                }
            }

            socket.close();
            System.out.println("Client disconnected");

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}