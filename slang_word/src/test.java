
// Import statements.  
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class test extends JFrame {

    // Initializing the value of
    // currCard to 1 .
    private int currCard = 1;

    // Declaring of objects
    // of the CardLayout class.
    private CardLayout cObjl;

    // constructor of the class
    public test() {

        // Method to set the Title of the JFrame
        setTitle("Card Layout Methods");
        // Method to set the visibility of the JFrame
        setSize(310, 160);
        // Creating an Object of the "Jpanel" class
        JPanel cPanel = new JPanel();
        cObjl = new CardLayout();
        cPanel.setLayout(cObjl);

        // Initializing the object
        // "jPanel1" of the JPanel class.
        JPanel jPanel1 = new JPanel();
        JPanel jPanel2 = new JPanel();
        JPanel jPanel3 = new JPanel();
        JPanel jPanel4 = new JPanel();

        // Initializing the object
        // "jl1" of the JLabel class.
        JLabel jLabel1 = new JLabel("C1");
        JLabel jLabel2 = new JLabel("C2");
        JLabel jLabel3 = new JLabel("C3");
        JLabel jLabel4 = new JLabel("C4");

        // Adding JLabel "jLabel1" to the JPanel "jPanel1".
        jPanel1.add(jLabel1);
        jPanel2.add(jLabel2);
        jPanel3.add(jLabel3);
        jPanel4.add(jLabel4);

        // Add the "jPanel1" on cPanel
        cPanel.add(jPanel1, "1");
        cPanel.add(jPanel2, "2");
        cPanel.add(jPanel3, "3");
        cPanel.add(jPanel4, "4");

        // Creating an Object of the "JPanel" class
        JPanel btnPanel = new JPanel();
        JButton firstButton = new JButton("First");
        JButton nextButton = new JButton("->");
        JButton previousButton = new JButton("<-");
        JButton lastButton = new JButton("Last");

        // Adding the JButton "firstbutton" on the JPanel.
        btnPanel.add(firstButton);
        btnPanel.add(nextButton);
        btnPanel.add(previousButton);
        btnPanel.add(lastButton);

        // adding firstButton in the ActionListener
        firstButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {

                // using the first cObjl CardLayout
                cObjl.first(cPanel);

                // value of currCard is 1
                currCard = 1;
            }
        });

        // add lastButton in ActionListener
        lastButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {

                // using the last cObjl CardLayout
                cObjl.last(cPanel);

                // value of currCard is 4
                currCard = 4;
            }
        });

        // add nextButton in ActionListener
        nextButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {

                if (currCard < 4) {

                    // increase the value of currCard by 1
                    currCard = currCard + 1;

                    // show the value of currCard
                    cObjl.show(cPanel, "" + (currCard));
                }
            }
        });

        // add previousButton in ActionListener
        previousButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {

                if (currCard > 1) {

                    // decrease the value
                    // of currCard by 1
                    currCard = currCard - 1;

                    // show the value of currCard
                    cObjl.show(cPanel, "" + (currCard));
                }
            }
        });

        // using to get the content pane
        getContentPane().add(cPanel, BorderLayout.NORTH);

        // using to get the content pane
        getContentPane().add(btnPanel, BorderLayout.SOUTH);
    }

    // main method
    public static void main(String argvs[]) {

        // Creating an object of the test class.
        test cll = new test();

        // method to set the default operation of the JFrame.
        cll.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // aethod to set the visibility of the JFrame.
        cll.setVisible(true);
    }
}