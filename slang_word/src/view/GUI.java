package view;

import util.Utils;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.util.ArrayList;

import core.SlangDictionary;


public class GUI extends SlangDictionary implements UI {
    private enum MenuOptions {
        SLANGDB(0), MODIFY(1), RESET_TO_DEFAULT(2), QUIZ(3), EXIT(4);
        private final int value;
        MenuOptions(int value) { this.value = value; }
        public int value() { return value; }
    }

    private JFrame frame;
    private JPanel mainPanel;
    private CardLayout cardLayout;

    public GUI() {
        JFrame.setDefaultLookAndFeelDecorated(true);
        frame = new JFrame("Slang Dictionary");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        mainPanel = new JPanel(cardLayout = new CardLayout());
    }

    public void start() {
        JPanel mainMenu = createMenu();
        mainPanel.add(mainMenu, "mainMenu");

        JPanel SlangDB = createSlangDB();
        mainPanel.add(SlangDB, "SlangDB");

        JPanel Modify = createModify();
        mainPanel.add(Modify, "Modify");

        JPanel Reset = createReset();
        mainPanel.add(Reset, "Reset");

        JPanel Quiz = createQuiz();
        mainPanel.add(Quiz, "Quiz");

        frame.add(mainPanel);
        frame.pack();
        Utils.log("Log: GUI started");
    }

    private JPanel createMenu() {
        ArrayList<String> mainMenuOptions = new ArrayList<>() {{
            add("SlangDB");
            add("Modify");
            add("Reset to default");
            add("Quiz");
            add("Exit");
        }};

        ArrayList<JButton> buttons = new ArrayList<>();
        for (String option : mainMenuOptions) {
            JButton button = new JButton(option);
            button.setMaximumSize(new Dimension(200, 50));
            buttons.add(button);
        }

        buttons.get(MenuOptions.SLANGDB.value()).addActionListener( e -> {
            Utils.log("Log: SlangDB pressed");
            cardLayout.show(mainPanel, "SlangDB");
        });
        buttons.get(MenuOptions.MODIFY.value()).addActionListener(e -> {
            Utils.log("Log: Modify pressed");
            cardLayout.show(mainPanel, "Modify");
        });
        
        buttons.get(MenuOptions.RESET_TO_DEFAULT.value()).addActionListener(e -> {
            Utils.log("Log: Reset to default pressed");
            cardLayout.show(mainPanel, "Reset");
        });
        
        buttons.get(MenuOptions.QUIZ.value()).addActionListener(e -> {
            Utils.log("Log: Quiz pressed");
            cardLayout.show(mainPanel, "Quiz");
        });
        
        buttons.get(MenuOptions.EXIT.value()).addActionListener(e -> {
            Utils.log("Log: Exit pressed");
            System.exit(0);
        });

        
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        panel.setBackground(Color.WHITE);
        for (JButton button : buttons) {
            panel.add(button);
            panel.add(Box.createRigidArea(new Dimension(0, 10)));
        }
        return panel;
    }

    private JPanel createSlangDB() {
        JPanel panel = new JPanel();
        JPanel panel2 = new JPanel();
        panel2.setLayout(new BoxLayout(panel2, BoxLayout.PAGE_AXIS));
        JPanel searchPanel = new JPanel(new FlowLayout());

        // Create a text field for search input
        JTextField searchTextField = new JTextField(15);

        // Create a button for performing the search
        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(e -> {
            searchTextField.getText();
        });

        // Add components to the search panel
        searchPanel.add(searchTextField);
        searchPanel.add(searchButton);

        panel2.add(searchPanel, BorderLayout.PAGE_START);

        JTextArea outputTextArea = new JTextArea(10, 30);
        outputTextArea.setEditable(false); // Make it non-editable
        panel2.add(new JScrollPane(outputTextArea));

        JTextArea history = new JTextArea(10, 30);
        history.setEditable(false); // Make it non-editable
        
        panel.add(panel2);
        panel.add(new JScrollPane(history));
        return panel;
    }

    private JPanel createModify() {
        JPanel panel = new JPanel();
        JPanel p1=new JPanel();  
        JPanel p2=new JPanel();  
        JPanel p3=new JPanel();  
        JTabbedPane tp=new JTabbedPane();  
        tp.setBounds(50,50,20,20);  
        tp.add("main",p1);  
        tp.add("visit",p2);  
        tp.add("help",p3);   
        panel.add(tp); 
        return panel;
    }

    private JPanel createReset() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

        JLabel label = new JLabel("This action CANNOT be undone. This will permanently reset all your data.");
        panel.add(label);
        JLabel label1 = new JLabel("Please type 'yes' to confirm.");
        panel.add(label1);

        JTextField textField = new JTextField();
        panel.add(textField);

        JPanel buttonPanel = new JPanel();
        JButton button1 = new JButton("Confirm");
        button1.addActionListener(e -> {
            String text = textField.getText();
            if (text.equalsIgnoreCase("yes")) {
                resetSlangWords();
                JOptionPane.showMessageDialog(frame, "Hello, Welcome to Javatpoint.");  
            }
        });

        JButton button2 = new JButton("Back");
        button2.addActionListener(e -> {
            cardLayout.show(mainPanel, "mainMenu");
        });
        buttonPanel.add(button1);
        buttonPanel.add(button2);

        panel.add(buttonPanel);
        return panel;
    }

    private JPanel createQuiz() {
        JPanel questionPanel = new JPanel();
        questionPanel.setLayout(new BoxLayout(questionPanel, BoxLayout.PAGE_AXIS));

        // Create the question label
        JLabel questionLabel = new JLabel("What is the capital of France?");
        // questionLabel.setFont(new Font("Arial", Font.BOLD, 14));
        questionPanel.add(questionLabel);

        // Create the answer choices
        JRadioButton[] answerChoices = new JRadioButton[4];
        for (int i = 0; i < answerChoices.length; i++) {
            answerChoices[i] = new JRadioButton("Answer " + (i + 1));
            questionPanel.add(answerChoices[i]);
        }

        // Create the submit button
        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(e -> {
            // @Override
            // public void actionPerformed(ActionEvent e) {
            //     // Check the selected answer
            //     String selectedAnswer = null;
            //     for (JRadioButton answerChoice : answerChoices) {
            //         if (answerChoice.isSelected()) {
            //             selectedAnswer = answerChoice.getText();
            //             break;
            //         }
            //     }

            //     // Display a message based on the selected answer
            //     if (selectedAnswer != null) {
            //         if (selectedAnswer.equals("Answer 3")) {
            //             JOptionPane.showMessageDialog(null, "Correct!");
            //         } else {
            //             JOptionPane.showMessageDialog(null, "Incorrect. The correct answer is Answer 3.");
            //         }
            //     } else {
            //         JOptionPane.showMessageDialog(null, "Please select an answer.");
            //     }
            // }
        });
        questionPanel.add(submitButton, BorderLayout.SOUTH);
        return questionPanel;
    }
}
