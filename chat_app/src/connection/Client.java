package connection;

import java.io.*;
import java.net.*;

import util.Logger;

public class Client {

    private String serverName;
    private String clientName;
    private int port;

    private Thread read;
    BufferedReader input;
    PrintWriter output;
    Socket server;

    public Client(String serverName, String clientName, int port) {
        this.serverName = serverName;
        this.clientName = clientName;
        this.port = port;
    }

    public static void main(String[] args) throws UnknownHostException, IOException {
        Client client = new Client("localhost", "Con ca", 5000);
        client.connect();
    }

    public void connect() {
        try {
            server = new Socket(serverName, port);
            System.out.println("Connected to server " + serverName + " on port " + port);
            input = new BufferedReader(new InputStreamReader(server.getInputStream()));
            output = new PrintWriter(server.getOutputStream(), true);
            Logger.log("info", "Connected to server " + serverName + " on port " + port, System.getProperty("user.dir"));

            output.println(clientName);
            // thread = new Thread(new ReceivedMessagesHandler(client.getInputStream()));
            server.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
