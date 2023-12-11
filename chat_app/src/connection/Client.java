package connection;

import java.io.*;
import java.net.*;
import java.util.Scanner;

import util.*;
import constant.*;

public class Client {

    private String host;
    private int port;

    public Client(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public static void main(String[] args) throws UnknownHostException, IOException {
        new Client("localhost", Term.Init.PORT).run();;
    }

    public void run() {
        try {
            Socket client = new Socket(host, port);
            Logger.log("info", "Connected to server", "Client.java");
            new Thread(new ReceivedMessagesHandler(client)).start();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}

class ReceivedMessagesHandler implements Runnable {

    private Socket client;

    public ReceivedMessagesHandler(Socket client) {
        this.client = client;
    }

    public void run() {
        try {
            Scanner input = new Scanner(client.getInputStream());
            PrintStream output = new PrintStream(client.getOutputStream());
            
            Scanner sc = new Scanner(System.in);
            String mode = Term.User.REGISTER;
            boolean run = true;
            while (run) {
                output.println(mode);
                
                switch (mode) {
                    case Term.User.LOGIN: {
                        System.out.print("Enter a username: ");
                        String username = sc.nextLine();
                        output.println(username);
                        System.out.print("Password: ");
                        String password = sc.nextLine();
                        output.println(password);

                        String tmp = input.nextLine();
                        if (tmp.equals(Term.StatusCode.SUCCESS)) {
                            System.out.println("Login success");
                            run = false;
                        } else {
                            System.out.println("Login failed");
                        }
                        break;
                    }
                
                    case Term.User.REGISTER: {
                        System.out.print("Enter a username: ");
                        String username = sc.nextLine();
                        output.println(username);
                        System.out.print("Password: ");
                        String password = sc.nextLine();
                        output.println(password);
                        System.out.print("Email: ");
                        String email = sc.nextLine();
                        output.println(email);

                        String tmp = input.nextLine();
                        if (tmp.equals(Term.StatusCode.SUCCESS)) {
                            System.out.println("Register success");
                            run = false;
                        } else {
                            System.out.println("Register failed");
                        }
                        break;
                    }

                    default:
                        break;
                }
                // System.out.println("Messages: ");
                // while (sc.hasNextLine()) {
                //     output.println(sc.nextLine());
                // }
            }
            sc.close();
            client.close();
        }
        catch (Exception e) {
            Logger.log("error", e.getMessage(), "Client.java");
        }
    }
}