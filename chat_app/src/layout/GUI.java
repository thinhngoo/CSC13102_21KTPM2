package layout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import util.Logger;
import javax.swing.border.EmptyBorder;

import constant.*;

public class GUI extends JFrame {
    private void setup() {
        setTitle("Yahuu!");
        setSize(500, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Logger.log("info", "Set up GUI", "GUI.java");

        setLayout(new CardLayout());
    }

    private void exit() {
        setVisible(true);
        Logger.log("info", "Exit GUI", "GUI.java");
    }

    public GUI() {
        setup();
        
        add(createLoginPanel(), "Login");
        add(createRegisterPanel(), "Register");
        add(createChatPanel(), "Chat");        
        // Show login page by default
        ((CardLayout) getContentPane().getLayout()).show(getContentPane(), "Register");
        exit();
    }
    
    
    private JTextField loginUsernameField;
    private JPasswordField loginPasswordField;
    private JPanel createLoginPanel() {
        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new BoxLayout(loginPanel, BoxLayout.PAGE_AXIS));

        // Title
        JLabel titleLabel = new JLabel("Yahuu!");
        titleLabel.setFont(new Font("Serif", Font.BOLD, 30));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setBorder(new EmptyBorder(50, 48, 50, 48));
        loginPanel.add(titleLabel);

        // Username
        JPanel usernamePanel = new JPanel();
        usernamePanel.setMaximumSize(new Dimension(400, 52));
        usernamePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel usernameLabel = new JLabel("Username: ");
        usernameLabel.setBorder(new EmptyBorder(Unit.MEDIUM_SIZE, Unit.SMALL_SIZE, Unit.MEDIUM_SIZE, Unit.SMALL_SIZE));
        usernamePanel.add(usernameLabel);
        loginUsernameField = new JTextField();
        loginUsernameField.setColumns(20);
        loginUsernameField.setBorder(new EmptyBorder(Unit.MEDIUM_SIZE, Unit.SMALL_SIZE, Unit.MEDIUM_SIZE, Unit.SMALL_SIZE));
        usernamePanel.add(loginUsernameField);
        loginPanel.add(usernamePanel);

        // Password
        JPanel passwordPanel = new JPanel();
        passwordPanel.setMaximumSize(new Dimension(400, 52));
        passwordPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel passwordLabel = new JLabel("Password: ");
        passwordLabel.setBorder(new EmptyBorder(Unit.MEDIUM_SIZE, Unit.SMALL_SIZE, Unit.MEDIUM_SIZE, Unit.SMALL_SIZE));
        passwordPanel.add(passwordLabel);
        loginPasswordField = new JPasswordField();
        loginPasswordField.setColumns(20);
        loginPasswordField.setBorder(new EmptyBorder(Unit.MEDIUM_SIZE, Unit.SMALL_SIZE, Unit.MEDIUM_SIZE, Unit.SMALL_SIZE));
        passwordPanel.add(loginPasswordField);
        loginPanel.add(passwordPanel);
        
        // Buttons
        JPanel btnPanel = new JPanel();
        btnPanel.setBorder(new EmptyBorder(Unit.LARGE_SIZE*2, 0, 0, 0));
        
        JButton loginButton = new JButton("Login");
        loginButton.setPreferredSize(new Dimension(Unit.LARGE_SIZE*6, Unit.LARGE_SIZE*2));
        loginButton.addActionListener(new LoginButtonListener());
        btnPanel.add(loginButton);

        JButton registerButton = new JButton("Register");
        registerButton.setPreferredSize(new Dimension(Unit.LARGE_SIZE*6, Unit.LARGE_SIZE*2));
        registerButton.addActionListener(new RegisterButtonListener());
        btnPanel.add(registerButton);
        loginPanel.add(btnPanel);

        return loginPanel;
    }

    private JTextField registerUsernameField;
    private JPasswordField registerPasswordField;
    private JPasswordField registerEmailField;
    private JPanel createRegisterPanel() {
        JPanel registerPanel = new JPanel();
        registerPanel.setLayout(new BoxLayout(registerPanel, BoxLayout.PAGE_AXIS));

        // Title
        JLabel titleLabel = new JLabel("Yahuu!");
        titleLabel.setFont(new Font("Serif", Font.BOLD, 30));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setBorder(new EmptyBorder(50, 48, 50, 48));
        registerPanel.add(titleLabel);

        // Username
        JPanel usernamePanel = new JPanel();
        usernamePanel.setMaximumSize(new Dimension(400, 52));
        usernamePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel usernameLabel = new JLabel("Username: ");
        usernameLabel.setBorder(new EmptyBorder(Unit.MEDIUM_SIZE, Unit.SMALL_SIZE, Unit.MEDIUM_SIZE, Unit.SMALL_SIZE));
        usernamePanel.add(usernameLabel);
        registerUsernameField = new JTextField();
        registerUsernameField.setColumns(20);
        registerUsernameField.setBorder(new EmptyBorder(Unit.MEDIUM_SIZE, Unit.SMALL_SIZE, Unit.MEDIUM_SIZE, Unit.SMALL_SIZE));
        usernamePanel.add(registerUsernameField);
        registerPanel.add(usernamePanel);

        // Email
        JPanel emailPanel = new JPanel();
        emailPanel.setMaximumSize(new Dimension(400, 52));
        emailPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel emailLabel = new JLabel("Email:          ");
        emailLabel.setBorder(new EmptyBorder(Unit.MEDIUM_SIZE, Unit.SMALL_SIZE, Unit.MEDIUM_SIZE, Unit.SMALL_SIZE));
        emailPanel.add(emailLabel);
        registerEmailField = new JPasswordField();
        registerEmailField.setColumns(20);
        registerEmailField.setBorder(new EmptyBorder(Unit.MEDIUM_SIZE, Unit.SMALL_SIZE, Unit.MEDIUM_SIZE, Unit.SMALL_SIZE));
        emailPanel.add(registerEmailField);
        registerPanel.add(emailPanel);

        // Password
        JPanel passwordPanel = new JPanel();
        passwordPanel.setMaximumSize(new Dimension(400, 52));
        passwordPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel passwordLabel = new JLabel("Password: ");
        passwordLabel.setBorder(new EmptyBorder(Unit.MEDIUM_SIZE, Unit.SMALL_SIZE, Unit.MEDIUM_SIZE, Unit.SMALL_SIZE));
        passwordPanel.add(passwordLabel);
        registerPasswordField = new JPasswordField();
        registerPasswordField.setColumns(20);
        registerPasswordField.setBorder(new EmptyBorder(Unit.MEDIUM_SIZE, Unit.SMALL_SIZE, Unit.MEDIUM_SIZE, Unit.SMALL_SIZE));
        passwordPanel.add(registerPasswordField);
        registerPanel.add(passwordPanel);
        
        // Buttons
        JPanel btnPanel = new JPanel();
        btnPanel.setBorder(new EmptyBorder(Unit.LARGE_SIZE*2, 0, 0, 0));
        
        JButton loginButton = new JButton("Login");
        loginButton.setPreferredSize(new Dimension(Unit.LARGE_SIZE*6, Unit.LARGE_SIZE*2));
        loginButton.addActionListener(new LoginButtonListener());
        btnPanel.add(loginButton);

        JButton registerButton = new JButton("Register");
        registerButton.setPreferredSize(new Dimension(Unit.LARGE_SIZE*6, Unit.LARGE_SIZE*2));
        registerButton.addActionListener(new RegisterButtonListener());
        btnPanel.add(registerButton);
        registerPanel.add(btnPanel);

        return registerPanel;
    }

    private class LoginButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // String username = loginUsernameField.getText();
            // String password = new String(loginPasswordField.getPassword());
            // System.out.println(username);
            // System.out.println(password);
            ((CardLayout) getContentPane().getLayout()).show(getContentPane(), "Register");
        }
    }

    private class RegisterButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // String username = registerUsernameField.getText();
            // String password = new String(registerPasswordField.getPassword());
            // System.out.println(username);
            // System.out.println(password);
            ((CardLayout) getContentPane().getLayout()).show(getContentPane(), "Login");
        }
    }

    public static void main(String[] args) {
        new GUI();
    }
}
