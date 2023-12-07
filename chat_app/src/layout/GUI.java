package layout;

import javax.swing.JFrame;

import util.Logger;

public class GUI extends JFrame {
    private void setup() {
        setTitle("Chat");
        setSize(500, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Logger.log("info", "Set up GUI", System.getProperty("user.dir"));
    }

    private void exit() {
        setVisible(true);
        Logger.log("info", "Exit GUI", System.getProperty("user.dir"));
    }
}
