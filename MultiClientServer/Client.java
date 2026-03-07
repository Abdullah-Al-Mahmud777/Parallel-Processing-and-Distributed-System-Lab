package MultiClientServer;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) {

        try {

            Socket socket = new Socket("localhost", 5000);

            BufferedReader input = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));

            PrintWriter output = new PrintWriter(
                    socket.getOutputStream(), true);

            Scanner scanner = new Scanner(System.in);

            String message;

            while (true) {

                System.out.print("Enter message: ");
                message = scanner.nextLine();

                output.println(message);

                String response = input.readLine();
                System.out.println("Server: " + response);

                if (message.equalsIgnoreCase("bye")) {
                    break;
                }
            }

            socket.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}