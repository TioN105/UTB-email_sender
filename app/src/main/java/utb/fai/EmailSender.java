package utb.fai;

import java.net.*;
import java.io.*;

public class EmailSender {

    private Socket socket;
    private BufferedReader reader;
    private PrintWriter writer;

    public EmailSender(String host, int port) throws UnknownHostException, IOException {
        socket = new Socket(host, port);
        
        reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        writer = new PrintWriter(socket.getOutputStream(), true);
        
        reader.readLine();
    }

    public void send(String from, String to, String subject, String text) throws IOException {

        writer.println("HELO localhost");
        reader.readLine();

        writer.println("MAIL FROM:<" + from + ">");
        reader.readLine();

        writer.println("RCPT TO:<" + to + ">");
        reader.readLine();

        writer.println("DATA");
        reader.readLine();

        writer.println("Subject: " + subject);
        writer.println();
        writer.println(text);
        
        writer.println(".");
        reader.readLine();
    }

    public void close() {
        try {
            if (writer != null) {
                writer.println("QUIT");
            }
            if (socket != null && !socket.isClosed()) {
                socket.close();
            }
        } catch (IOException e) {
        
        }
    }

}