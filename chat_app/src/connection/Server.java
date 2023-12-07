package connection;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.io.*;
import java.net.*;

import middleware.*;
import constant.*;
import util.Logger;

public class Server {

    private int port;
    private List<User> clients;
    private ServerSocket server;

    public Server(int port) {
        this.port = port;
        this.clients = new ArrayList<User>();
    }

    public void run() throws IOException {
        server = new ServerSocket(port) {
            protected void finalize() throws IOException {
                this.close();
            }
        };
        Logger.log("info", "Port " + port + " is now open.", System.getProperty("user.dir"));

        while (true) {
            Socket client = server.accept();
            Scanner sc = new Scanner(client.getInputStream());

            String nickname = sc.nextLine();
            String password = sc.nextLine();
            Logger.log("info", "New Client \"" + nickname + "\" - "+ client.getInetAddress().getHostAddress() + " connected.", System.getProperty("user.dir"));
        
            Schema.User connectUser = new Schema.User(nickname, password);
            Model.UserDB userDB = new Model.UserDB();
            List<Schema.User> users = userDB.getUsers();
            if (Authentication.isUserExist(users, connectUser.getUsername())) {
                Logger.log("info", "User \"" + nickname + "\" is exist.", System.getProperty("user.dir"));
            } else {
                Logger.log("info", "User \"" + nickname + "\" is not exist.", System.getProperty("user.dir"));
            }

            User user = new User(client, nickname);
            this.clients.add(user);

            // new Thread(new UserHandler(this, user)).start();
            sc.close();
        }
    }

    public void removeUser(User user) {
        this.clients.remove(user);
    }
}

class UserHandler implements Runnable {
    
    private Server server;
    private User user;

    public UserHandler(Server server, User user) {
        this.server = server;
        this.user = user;
    }

    public void run() {
        String message;
        server.removeUser(this.user);
    }
}

/**
 * User
 */
class User {

    private static int userTotal = 0;
    private int id;
    private String nickname;
    private String color;
    private Socket client;
    private PrintStream outStream;
    private InputStream inStream;

    public User(Socket client, String nickname) throws IOException {
        ++userTotal;
        this.id = 0;
        this.nickname = nickname;
        this.color = "black";

        this.client = client;
        this.outStream = new PrintStream(client.getOutputStream());
        this.inStream = client.getInputStream();
    }
}
