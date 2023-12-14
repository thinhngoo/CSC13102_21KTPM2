package layout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;
import java.io.PrintStream;

import javax.swing.border.EmptyBorder;

import connection.*;
import constant.*;
import util.*;

public class GUI extends JFrame {
    private Client client;

    private void setup() {
        client = new Client("localhost", Term.Init.PORT);
        client.run();

        setTitle("Yahuu!");
        setSize(500, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new CardLayout());
        Logger.log("info", "Set up GUI", "GUI.java");
    }

    private void exit() {
        setVisible(true);
    }

    public GUI() {
        setup();

        add(createLoginPanel(), "Login");
        add(createRegisterPanel(), "Register");
        add(createChatPanel(), "Chat");

        ((CardLayout) getContentPane().getLayout()).show(getContentPane(), "Login");
        exit();
    }

    private JTextField loginUsernameField;
    private JPasswordField loginPasswordField;

    private JPanel createLoginPanel() {
        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new BoxLayout(loginPanel, BoxLayout.PAGE_AXIS));

        // Title
        JLabel titleLabel = JFrameCreator.Label("Yahuu!", "Serif", Font.BOLD, 30, Component.CENTER_ALIGNMENT,
                new EmptyBorder(50, 48, 50, 48));
        loginPanel.add(titleLabel);

        // Username
        JPanel usernamePanel = JFrameCreator.Form(new Dimension(400, 52), Component.CENTER_ALIGNMENT, "Username: ");
        loginUsernameField = new JTextField();
        loginUsernameField.setColumns(20);
        loginUsernameField
                .setBorder(new EmptyBorder(Unit.MEDIUM_SIZE, Unit.SMALL_SIZE, Unit.MEDIUM_SIZE, Unit.SMALL_SIZE));
        usernamePanel.add(loginUsernameField);
        loginPanel.add(usernamePanel);

        // Password
        JPanel passwordPanel = JFrameCreator.Form(new Dimension(400, 52), Component.CENTER_ALIGNMENT, "Password: ");
        loginPasswordField = new JPasswordField();
        loginPasswordField.setColumns(20);
        loginPasswordField
                .setBorder(new EmptyBorder(Unit.MEDIUM_SIZE, Unit.SMALL_SIZE, Unit.MEDIUM_SIZE, Unit.SMALL_SIZE));
        passwordPanel.add(loginPasswordField);
        loginPanel.add(passwordPanel);

        // Buttons
        JPanel btnPanel = new JPanel();
        btnPanel.setBorder(new EmptyBorder(Unit.LARGE_SIZE * 2, 0, 0, 0));

        JButton loginButton = JFrameCreator.Button("Login", new Dimension(Unit.LARGE_SIZE * 6, Unit.LARGE_SIZE * 2));
        loginButton.addActionListener(new LoginButtonListener());
        btnPanel.add(loginButton);

        JButton registerButton = JFrameCreator.Button("Register",
                new Dimension(Unit.LARGE_SIZE * 6, Unit.LARGE_SIZE * 2));
        registerButton.addActionListener(new DirectToRegister());
        btnPanel.add(registerButton);
        loginPanel.add(btnPanel);

        return loginPanel;
    }

    private JTextField registerUsernameField;
    private JPasswordField registerPasswordField;
    private JTextField registerEmailField;

    private JPanel createRegisterPanel() {
        JPanel registerPanel = new JPanel();
        registerPanel.setLayout(new BoxLayout(registerPanel, BoxLayout.PAGE_AXIS));

        // Title
        JLabel titleLabel = JFrameCreator.Label("Yahuu!", "Serif", Font.BOLD, 30, Component.CENTER_ALIGNMENT,
                new EmptyBorder(50, 48, 50, 48));
        registerPanel.add(titleLabel);

        // Username
        JPanel usernamePanel = JFrameCreator.Form(new Dimension(400, 52), Component.CENTER_ALIGNMENT, "Username: ");
        registerUsernameField = new JTextField();
        registerUsernameField.setColumns(20);
        registerUsernameField
                .setBorder(new EmptyBorder(Unit.MEDIUM_SIZE, Unit.SMALL_SIZE, Unit.MEDIUM_SIZE, Unit.SMALL_SIZE));
        usernamePanel.add(registerUsernameField);
        registerPanel.add(usernamePanel);

        // Email
        JPanel emailPanel = JFrameCreator.Form(new Dimension(400, 52), Component.CENTER_ALIGNMENT, "Email:    ");
        registerEmailField = new JTextField();
        registerEmailField.setColumns(20);
        registerEmailField
                .setBorder(new EmptyBorder(Unit.MEDIUM_SIZE, Unit.SMALL_SIZE, Unit.MEDIUM_SIZE, Unit.SMALL_SIZE));
        emailPanel.add(registerEmailField);
        registerPanel.add(emailPanel);

        // Password
        JPanel passwordPanel = JFrameCreator.Form(new Dimension(400, 52), Component.CENTER_ALIGNMENT, "Password: ");
        registerPasswordField = new JPasswordField();
        registerPasswordField.setColumns(20);
        registerPasswordField
                .setBorder(new EmptyBorder(Unit.MEDIUM_SIZE, Unit.SMALL_SIZE, Unit.MEDIUM_SIZE, Unit.SMALL_SIZE));
        passwordPanel.add(registerPasswordField);
        registerPanel.add(passwordPanel);

        // Buttons
        JPanel btnPanel = new JPanel();
        btnPanel.setBorder(new EmptyBorder(Unit.LARGE_SIZE * 2, 0, 0, 0));

        JButton registerButton = JFrameCreator.Button("Register",
                new Dimension(Unit.LARGE_SIZE * 6, Unit.LARGE_SIZE * 2));
        registerButton.addActionListener(new RegisterButtonListener());
        btnPanel.add(registerButton);

        JButton loginButton = JFrameCreator.Button("Login", new Dimension(Unit.LARGE_SIZE * 6, Unit.LARGE_SIZE * 2));
        loginButton.addActionListener(new DirectToLogin());
        btnPanel.add(loginButton);
        registerPanel.add(btnPanel);

        return registerPanel;
    }

    private class LoginButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Scanner input;
            PrintStream output;
            try {
                input = new Scanner(client.getInStream());
                output = new PrintStream(client.getOutStream());
            } catch (Exception ex) {
                Logger.log("error", ex.getMessage(), "GUI.java");
                return;
            }
            String mode = Term.User.LOGIN;

            output.println(mode);
            output.println(loginUsernameField.getText());
            output.println(new String(loginPasswordField.getPassword()));

            String tmp = input.nextLine();
            if (tmp.equals(Term.StatusCode.SUCCESS)) {
                Logger.log("info", "Login success", "GUI.java");
                ((CardLayout) getContentPane().getLayout()).show(getContentPane(), "Chat");
            } else {
                Logger.log("info", "Login failed", "GUI.java");
            }
        }
    }

    private class RegisterButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Scanner input;
            PrintStream output;
            try {
                input = new Scanner(client.getInStream());
                output = new PrintStream(client.getOutStream());
            } catch (Exception ex) {
                Logger.log("error", ex.getMessage(), "GUI.java");
                return;
            }
            String mode = Term.User.REGISTER;

            output.println(mode);
            output.println(registerUsernameField.getText());
            output.println(new String(registerPasswordField.getPassword()));
            output.println(new String(registerEmailField.getText()));

            String tmp = input.nextLine();
            if (tmp.equals(Term.StatusCode.SUCCESS)) {
                Logger.log("info", "Register success", "GUI.java");
            } else {
                Logger.log("info", "Register failed", "GUI.java");
            }
        }
    }

    private class DirectToRegister implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            ((CardLayout) getContentPane().getLayout()).show(getContentPane(), "Register");
        }
    }
    private class DirectToLogin implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            ((CardLayout) getContentPane().getLayout()).show(getContentPane(), "Login");
        }
    }
    private JPanel createChatPanel() {
        JPanel chatPanel = new JPanel(new BorderLayout());
        
        JTextArea chatArea = new JTextArea();
        chatArea.setEditable(false);
        chatPanel.add(chatArea, BorderLayout.CENTER);

        JPanel inputPanel = new JPanel(new BorderLayout());
        JTextField inputField = new JTextField();
        inputPanel.add(inputField, BorderLayout.CENTER);
        JButton sendButton = new JButton("Send");
        inputPanel.add(sendButton, BorderLayout.EAST);
        chatPanel.add(inputPanel, BorderLayout.SOUTH);
        
        return chatPanel;
    }

    public static void main(String[] args) {
        new GUI().setVisible(true);
    }
}
