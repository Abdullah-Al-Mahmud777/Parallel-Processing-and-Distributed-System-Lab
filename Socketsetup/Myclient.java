package Socketsetup;

import java.io.*;
import java.net.*;

public class Myclient {
    public static void main(String[] args) {
        // Create a server socket listening on port 6666
        try (ServerSocket ss = new ServerSocket(6666)) {
            System.out.println("Server is waiting for client on port 6666...");

            // Accept the incoming client connection
            try (Socket s = ss.accept();
                 DataInputStream dis = new DataInputStream(s.getInputStream())) {
                
                System.out.println("Status: Client Connected!");

                // Read the UTF message sent by the client
                String str = dis.readUTF();
                System.out.println("Message received: " + str);
            }
        } catch (IOException e) {
            System.out.println("Server Error: " + e.getMessage());
        }
    }
}