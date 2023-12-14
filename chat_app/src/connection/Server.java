package connection;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.io.*;
import java.net.*;

import middleware.*;
import constant.*;
import util.*;

public class Server {

    private int port;
    private List<UserClient> clients;
    private ServerSocket server;

    public Server(int port) {
        this.port = port;
        this.clients = new ArrayList<UserClient>();
    }

    public void run() throws IOException {
        server = new ServerSocket(port) {
            protected void finalize() throws IOException {
                this.close();
            }
        };
        Logger.log("info", "Port " + port + " is now open", "Server.java");

        while (true) {
            Socket client = null;
            try {
                client = server.accept();
                new Thread(new ClientHandler(this, client)).start();
            } catch (Exception e) {
                client.close();
                Logger.log("error", e.getMessage(), "Server.java");
            }
        }
    }

    public void addUser(UserClient user) {
        this.clients.add(user);
    }

    public void removeUser(UserClient user) {
        this.clients.remove(user);
    }
}

class ClientHandler implements Runnable {

    private Server server;
    private Socket client;
    private UserClient user;

    public ClientHandler(Server server, Socket client) throws IOException {
        this.server = server;
        this.client = client;
    }

    @Override
    public void run() {
        accountSync();
        chatHandle();
        // UserClient user = new UserClient(client, nickname);
        // server.clients.add(user);

        // String message;
        // Scanner sc = new Scanner(this.user.getInStream());
        // Logger.log("info", "User " + this.user.isConnected() + " is connected.",
        // "Server.java");
        // while (sc.hasNextLine()) {
        // message = sc.nextLine();
        // Logger.log("info", "User " + user.toString() + " send message: " + message,
        // "Server.java");
        // }
    }

    private void accountSync() {
        try {
            Scanner input = new Scanner(client.getInputStream());
            PrintStream output = new PrintStream(client.getOutputStream());
            Model.UserDB userDB = new Model.UserDB();
            List<Schema.User> users = userDB.getUsers();

            boolean run = true;
            while (run) {
                String mode = input.nextLine();
                String returnCode = Term.StatusCode.FAILED;
                switch (mode) {
                    case Term.User.LOGIN: {
                        String username = input.nextLine();
                        String password = input.nextLine();
                        if (Authentication.isUserExist(users, username)) {
                            Logger.log("info", "User " + username + " is exist", "Server.java");
                            Schema.User tempUser = new Schema.User(username, password);
                            if (Authentication.isPasswordMatch(users, tempUser)) {
                                Logger.log("info", "User " + username + "'s password is match", "Server.java");
                                user = new UserClient(client, tempUser);
                                server.addUser(user);
                                returnCode = Term.StatusCode.SUCCESS;
                                run = false;
                                Logger.log("info", "User " + username + " is connected", "Server.java");
                            } else {
                                Logger.log("info", "User " + username + "'s password is not match", "Server.java");
                            }
                        } else {
                            Logger.log("info", "User " + username + " is not exist", "Server.java");
                        }
                        break;
                    }

                    case Term.User.REGISTER: {
                        String username = input.nextLine();
                        String password = input.nextLine();
                        String email = input.nextLine();
                        if (Authentication.isUserExist(users, username)) {
                            output.println(Term.StatusCode.FAILED);
                            Logger.log("info", "User " + username + " is exist", "Server.java");
                        } else {
                            Schema.User tempUser = new Schema.User(username, password, email);
                            user = new UserClient(client, tempUser);
                            server.addUser(user);
                            userDB.addUser(tempUser);
                            output.println(Term.StatusCode.SUCCESS);
                            run = false;
                            Logger.log("info", "User " + username + " is registered", "Server.java");
                        }
                        break;
                    }

                    default:
                        break;
                }
                output.println(returnCode);
            }
            input = null;
        } catch (Exception e) {
            Logger.log("error", e.getMessage(), "Server.java");
        }
    }
    private void chatHandle() {
        while(true) {
            
        }
    }
}

class UserClient {

    private static int userTotal = 0;

    public static int getUserTotal() {
        return userTotal;
    }

    private int id;
    private Schema.User user;
    private Socket client;
    private PrintStream outStream;
    private InputStream inStream;

    public UserClient(Socket client, Schema.User user) throws IOException {
        ++userTotal;
        this.id = 0;
        this.user = user;

        this.client = client;
        this.outStream = new PrintStream(client.getOutputStream());
        this.inStream = client.getInputStream();
    }

    public int getId() {
        return this.id;
    }

    public Socket getClient() {
        return this.client;
    }

    public PrintStream getOutStream() {
        return this.outStream;
    }

    public InputStream getInStream() {
        return this.inStream;
    }

    public void closeConnection() {
        try {
            this.client.close();
        } catch (IOException e) {
            Logger.log("error", e.getMessage(), "Server.java");
        }
    }
}
