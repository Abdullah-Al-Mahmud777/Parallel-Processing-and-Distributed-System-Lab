package Socketsetup;

import java.io.*;
import java.net.*;

public class MyServer {
    public static void main(String[] args) {
        
        try (ServerSocket ss = new ServerSocket(6666)) {
            System.out.println("Server is waiting for client on port 6666...");

         
            try (Socket s = ss.accept();
                 DataInputStream dis = new DataInputStream(s.getInputStream())) {
                
                System.out.println("Client Connected!");

                
                String str = dis.readUTF();
                System.out.println("Message from Client: " + str);
            }
        } catch (IOException e) {
            System.out.println("Server Error: " + e.getMessage());
        }
    }
}