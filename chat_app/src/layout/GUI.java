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
        Logger.log("info", "Set up GUI", System.getProperty("user.dir"));

        setLayout(new CardLayout());
    }

    private void exit() {
        setVisible(true);
        Logger.log("info", "Exit GUI", System.getProperty("user.dir"));
    }

    private JTextField registerUsernameField;
    private JPasswordField registerPasswordField;
    public GUI() {
        setup();
        
        add(createLoginPanel(), "Login");
        // add(registerPanel, "Register");
        
        // Show login page by default
        ((CardLayout) getContentPane().getLayout()).show(getContentPane(), "Login");
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
        
        // Login button
        JPanel loginButtonPanel = new JPanel();
        loginButtonPanel.setMaximumSize(new Dimension(400, 52));
        loginButtonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(new LoginButtonListener());
        loginButtonPanel.add(loginButton);
        loginPanel.add(loginButtonPanel);
        
        // Register button
        JPanel registerButtonPanel = new JPanel();
        registerButtonPanel.setMaximumSize(new Dimension(400, 52));
        registerButtonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        JButton registerButton = new JButton("Register");
        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ((CardLayout) getContentPane().getLayout()).show(getContentPane(), "Register");
            }
        });

        registerButtonPanel.add(registerButton);
        loginPanel.add(registerButtonPanel);

        // Forgot password
        JPanel forgotPasswordPanel = new JPanel();
        forgotPasswordPanel.setMaximumSize(new Dimension(400, 52));
        forgotPasswordPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        JButton forgotPasswordButton = new JButton("Forgot password?");
        loginPanel.add(forgotPasswordButton);

        return loginPanel;
    }

    private class LoginButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String username = loginUsernameField.getText();
            String password = new String(loginPasswordField.getPassword());
            System.out.println(username);
            System.out.println(password);
            // Handle login
        }
    }

    private class RegisterButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String username = registerUsernameField.getText();
            String password = new String(registerPasswordField.getPassword());
            System.out.println(username);
            System.out.println(password);
            // Handle registration
        }
    }

    public static void main(String[] args) {
        new GUI();
    }
}
