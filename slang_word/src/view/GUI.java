package view;

import util.Utils;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Font;
import java.util.ArrayList;

import core.SlangDictionary;


public class GUI extends SlangDictionary implements UI {
    private JFrame frame;
    private JPanel mainPanel;
    private CardLayout cardLayout;

    static final int WIDTH = 264;
    static final int HEIGHT = 320;
    public GUI() {
        JFrame.setDefaultLookAndFeelDecorated(true);
        frame = new JFrame("Slang Dictionary");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        mainPanel = new JPanel(cardLayout = new CardLayout());
    }

    public void start() {
        mainPanel.add(createMainMenu(), "mainMenu");
        mainPanel.add(createSlangDB(), "SlangDB");
        // mainPanel.add(createModify(), "Modify");
        mainPanel.add(createReset(), "ResetDB");
        mainPanel.add(createPractice(), "Practice");

        frame.add(mainPanel);
        frame.pack();
        Utils.log("Log: GUI started");
    }

    static final int BUTTON_WIDTH = 120;
    static final int BUTTON_HEIGHT = 30;
    static final int PADDING_LR = 40;
    static final int PADDING_TB = 15;
    private JPanel createMainMenu() {
        ArrayList<String> mainMenuOptions = new ArrayList<>() {{
            add("SlangDB");
            add("Modify");
            add("ResetDB");
            add("Practice");
            add("Exit");
        }};

        return createMenu(mainMenuOptions, "Slang Dictionary");
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

        JLabel warning = new JLabel("<html>This action CANNOT be undone. This will permanently reset all your data.</html>");
        warning.setAlignmentX(Component.CENTER_ALIGNMENT);
        warning.setBorder(new EmptyBorder(10, 5, 20, 5));
        panel.add(warning);
        JLabel confirm = new JLabel("Please type 'yes' to confirm.");
        confirm.setAlignmentX(Component.CENTER_ALIGNMENT);
        confirm.setBorder(new EmptyBorder(0, 0, 5, 0));
        panel.add(confirm);

        int txtBoxWidth = WIDTH - 24;
        int txtBoxHeight = 28;
        JTextField confirmText = new JTextField();
        confirmText.setSize(new Dimension(txtBoxWidth, txtBoxHeight));
        confirmText.setMinimumSize(new Dimension(txtBoxWidth, txtBoxHeight));
        confirmText.setMaximumSize(new Dimension(txtBoxWidth, txtBoxHeight));
        confirmText.setPreferredSize(new Dimension(txtBoxWidth, txtBoxHeight));
        panel.add(confirmText);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBorder(new EmptyBorder(12, 0, 0, 0));
        JButton confirmBtn = new JButton("Confirm");
        confirmBtn.setPreferredSize(new Dimension(80, 32));
        confirmBtn.addActionListener(e -> {
            String inputText = confirmText.getText();
            if (inputText.equalsIgnoreCase("yes")) {
                resetSlangWords();
                confirmText.setText("");
                JOptionPane.showMessageDialog(frame, "Reset Completed!"); 
                cardLayout.show(mainPanel, "mainMenu");
            } else {
                JOptionPane.showMessageDialog(frame, "Wrong input. Please type 'yes' to confirm.", "Alert", JOptionPane.WARNING_MESSAGE); 
            }
        });

        JButton backBtn = new JButton("Back");
        backBtn.setPreferredSize(new Dimension(80, 32));
        backBtn.addActionListener(e -> {
            confirmText.setText("");
            cardLayout.show(mainPanel, "mainMenu");
        });
        buttonPanel.add(confirmBtn);
        buttonPanel.add(backBtn);

        panel.add(buttonPanel);
        return panel;
    }

    private JPanel createPractice() {
        ArrayList<String> options = new ArrayList<>() {{
            add("Random Slang");
            add("Word Quiz");
            add("Definition Quiz");
            add("Back");
        }};

        mainPanel.add(createRandomSlang(), "Random Slang");
        mainPanel.add(createQuiz("word"), "Word Quiz");
        mainPanel.add(createQuiz("def"), "Definition Quiz");
        
        return createMenu(options, "Practice");
    }

    private JPanel createRandomSlang() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

        JLabel slangLabel = new JLabel("On this day slang word: " + randomSlangWord());
        slangLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        slangLabel.setBorder(new EmptyBorder(24, 5, 0, 5));
        panel.add(slangLabel);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBorder(new EmptyBorder(12, 0, 0, 0));
        JButton renewBtn = new JButton("Renew");
        renewBtn.setPreferredSize(new Dimension(80, 32));
        renewBtn.addActionListener(e -> {
            slangLabel.setText("On this day slang word: " + randomSlangWord());
        });

        JButton backBtn = new JButton("Back");
        backBtn.setPreferredSize(new Dimension(80, 32));
        backBtn.addActionListener(e -> {
            cardLayout.show(mainPanel, "Practice");
        });
        buttonPanel.add(renewBtn);
        buttonPanel.add(backBtn);
        panel.add(buttonPanel);

        return panel;
    }

    private String question = "";
    private String answer = "";
    private JPanel createQuiz(String type) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        
        JLabel questionLabel = new JLabel();
        questionLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        questionLabel.setBorder(new EmptyBorder(24, 16, 0, 5));
        questionLabel.setFont(new Font("Arial", Font.BOLD, 14));
        panel.add(questionLabel);

        if (type.equals("word")) {
            questionLabel.setText("<html>What is the word " + question + " short for?<html>");
        } else {
            questionLabel.setText("<html>What is the short for " + question + " ?<html>");
        }
        ArrayList<Object> answers = new ArrayList<>(getSlangWordQuiz(answer));

        ButtonGroup btnGroup = new ButtonGroup();
        JRadioButton[] answerChoices = new JRadioButton[4];
        for (int i = 0; i < answerChoices.length; i++) {
            answerChoices[i] = new JRadioButton("Temp");
            answerChoices[i].setAlignmentX(Component.LEFT_ALIGNMENT);
            answerChoices[i].setBorder(new EmptyBorder(8, 16, 0, 0));
            panel.add(answerChoices[i]);
            btnGroup.add(answerChoices[i]);
        }
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBorder(new EmptyBorder(12, 0, 0, 0));
        buttonPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        JButton submitBtn = new JButton("Submit");
        submitBtn.setPreferredSize(new Dimension(80, 32));
        submitBtn.addActionListener(e -> {
            String selectedAnswer = null;
            for (JRadioButton answerChoice : answerChoices) {
                if (answerChoice.isSelected()) {
                    selectedAnswer = answerChoice.getText();
                    break;
                }
            }
    
            if (selectedAnswer != null) {
                if (selectedAnswer.equals(answer)) {
                    JOptionPane.showMessageDialog(null, "Correct!");
                    cardLayout.show(mainPanel, "Practice");
                } else {
                    JOptionPane.showMessageDialog(null, "Incorrect. The correct answer is " + answer + ".");
                    cardLayout.show(mainPanel, "Practice");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Please select an answer.");
            }
        });

        JButton backBtn = new JButton("Back");
        backBtn.setPreferredSize(new Dimension(80, 32));
        backBtn.addActionListener(e -> {
            cardLayout.show(mainPanel, "Practice");
        });
        buttonPanel.add(submitBtn);
        buttonPanel.add(backBtn);
        panel.add(buttonPanel);
        return panel;
    }

    private JPanel createMenu(ArrayList<String> menuOptions, String title) {
        ArrayList<JButton> buttons = new ArrayList<>();
        for (String option : menuOptions) {
            JButton button = new JButton(option);
            button.setMaximumSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
            button.setAlignmentX(Component.CENTER_ALIGNMENT);
            buttons.add(button);
        }

        for (int i = 0; i < buttons.size() - 1; i++) {
            String option = menuOptions.get(i);
            buttons.get(i).addActionListener( e -> {
                Utils.log("Log: " + option + " pressed");
                cardLayout.show(mainPanel, option);
            });
        }
        
        
        buttons.get(buttons.size() - 1).addActionListener(e -> {
            if (menuOptions.get(menuOptions.size() - 1).equals("Exit")) {
                Utils.log("Log: Exit pressed");
                System.exit(0);
            } else {
                Utils.log("Log: Back pressed");
                cardLayout.show(mainPanel, "mainMenu");
            }
        });

        
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        panel.setBorder(new EmptyBorder(PADDING_TB, PADDING_LR, PADDING_TB, PADDING_LR));
        JLabel label = new JLabel(title);
        label.setBorder(new EmptyBorder(10, 0, 20, 0));
        label.setFont(new Font("Arial", Font.PLAIN, 20));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(label);
        for (JButton button : buttons) {
            panel.add(button);
            panel.add(Box.createRigidArea(new Dimension(0, 10)));
        }
        return panel;
    }
}
