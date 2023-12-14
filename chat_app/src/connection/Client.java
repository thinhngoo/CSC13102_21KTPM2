package connection;

import java.io.*;
import java.net.*;

import util.*;
import constant.*;

public class Client {

    private String host;
    private Socket client;
    private int port;
    private boolean run = true;

    public Client(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void finalize() throws Throwable {
        try {
            client.close();
            Logger.log("info", "Client is closed", "Client.java");
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) throws UnknownHostException, IOException {
        new Client("localhost", Term.Init.PORT).run();
    }

    public void run() {
        try {
            client = new Socket(host, port);
            Logger.log("info", "Connected to server", "Client.java");
            new Thread(new ReceivedMessagesHandler(client)).start();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public Socket getClient() {
        return client;
    }

    public PrintStream getOutStream() throws IOException {
        return new PrintStream(client.getOutputStream());
    }

    public InputStream getInStream() throws IOException {
        return client.getInputStream();
    }

    public boolean isConnected() {
        return run;
    }
}

class ReceivedMessagesHandler implements Runnable {

    private Socket client;

    public ReceivedMessagesHandler(Socket client) {
        this.client = client;
    }

    public void run() {
        
    }
}