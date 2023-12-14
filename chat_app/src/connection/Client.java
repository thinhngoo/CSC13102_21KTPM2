package connection;

import java.io.*;
import java.net.*;
import java.util.Scanner;
import javax.swing.JFrame;

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

    public static void main(String[] args) throws UnknownHostException, IOException {
        new Client("localhost", Term.Init.PORT).run();
    }

    public void run() {
        try {
            client = new Socket(host, port);
            System.out.println(Thread.currentThread().getName());
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

    public void close() throws IOException {
        run = false;
        client.close();
    }
}

class ReceivedMessagesHandler extends JFrame implements Runnable {

    private Socket client;

    public ReceivedMessagesHandler(Socket client) {
        JFrame frame = new JFrame("Chat");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setVisible(true);

        this.client = client;
    }

    public void run() {
        try {
            Scanner input = new Scanner(client.getInputStream());
            PrintStream output = new PrintStream(client.getOutputStream());
            
            Scanner sc = new Scanner(System.in);
            String mode = Term.User.LOGIN;
            
            // boolean run = true;
            // while (run) {
            //     output.println(mode);
                
            //     switch (mode) {
            //         case Term.User.LOGIN: {
            //             System.out.print("Enter a username: ");
            //             String username = sc.nextLine();
            //             output.println(username);
            //             System.out.print("Password: ");
            //             String password = sc.nextLine();
            //             output.println(password);

            //             String tmp = input.nextLine();
            //             if (tmp.equals(Term.StatusCode.SUCCESS)) {
            //                 System.out.println("Login success");
            //                 run = false;
            //             } else {
            //                 System.out.println("Login failed");
            //             }
            //             break;
            //         }
                
            //         case Term.User.REGISTER: {
            //             System.out.print("Enter a username: ");
            //             String username = sc.nextLine();
            //             output.println(username);
            //             System.out.print("Password: ");
            //             String password = sc.nextLine();
            //             output.println(password);
            //             System.out.print("Email: ");
            //             String email = sc.nextLine();
            //             output.println(email);

            //             String tmp = input.nextLine();
            //             if (tmp.equals(Term.StatusCode.SUCCESS)) {
            //                 System.out.println("Register success");
            //                 run = false;
            //             } else {
            //                 System.out.println("Register failed");
            //             }
            //             break;
            //         }

            //         default:
            //             break;
            //     }
            //     System.out.println("Messages: ");
            //     while (sc.hasNextLine()) {
            //         output.println(sc.nextLine());
            //     }
            // }
            System.out.println(Thread.currentThread().getName());
            sc.close();
            Logger.log("info", "Disconnected from server", "Client.java");
        }
        catch (Exception e) {
            Logger.log("error", e.getMessage(), "Client.java");
        }
    }
}