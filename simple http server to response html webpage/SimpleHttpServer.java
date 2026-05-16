import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class SimpleHttpServer {
    private static final int PORT = 8080;

    public static void main(String[] args) {
        System.out.println("Simple HTTP server started on http://localhost:" + PORT);

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) {
                Socket clientSocket = serverSocket.accept();
                handleClient(clientSocket);
            }
        } catch (IOException e) {
            System.err.println("Server error: " + e.getMessage());
        }
    }

    private static void handleClient(Socket clientSocket) {
        try (Socket socket = clientSocket;
             BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
             OutputStream outputStream = socket.getOutputStream()) {

            String requestLine = reader.readLine();
            if (requestLine == null || requestLine.isBlank()) {
                return;
            }

            while (reader.ready()) {
                String headerLine = reader.readLine();
                if (headerLine == null || headerLine.isEmpty()) {
                    break;
                }
            }

            String html = "<!DOCTYPE html>"
                    + "<html lang=\"en\">"
                    + "<head>"
                    + "<meta charset=\"UTF-8\">"
                    + "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">"
                    + "<title>Simple HTTP Server</title>"
                    + "<style>"
                    + "body{font-family:Arial,sans-serif;background:#f4f7fb;margin:0;display:flex;min-height:100vh;align-items:center;justify-content:center;color:#1f2937;}"
                    + ".card{background:#fff;padding:32px 40px;border-radius:16px;box-shadow:0 10px 30px rgba(0,0,0,.12);text-align:center;max-width:600px;}"
                    + "h1{margin-top:0;color:#0f766e;}"
                    + "p{line-height:1.6;font-size:1.05rem;}"
                    + "</style>"
                    + "</head>"
                    + "<body>"
                    + "<div class=\"card\">"
                    + "<h1>It works!</h1>"
                    + "<p>This HTML page is being served by a simple Java HTTP server running on port 8080.</p>"
                    + "<p>ID: 223311246</p>"
                    + "</div>"
                    + "</body>"
                    + "</html>";

            byte[] body = html.getBytes(StandardCharsets.UTF_8);
            String response = "HTTP/1.1 200 OK\r\n"
                    + "Content-Type: text/html; charset=UTF-8\r\n"
                    + "Content-Length: " + body.length + "\r\n"
                    + "Connection: close\r\n"
                    + "\r\n";

            outputStream.write(response.getBytes(StandardCharsets.UTF_8));
            outputStream.write(body);
            outputStream.flush();
        } catch (IOException e) {
            System.err.println("Client error: " + e.getMessage());
        }
    }
}