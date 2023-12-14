package util;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;

import constant.Unit;

public class JFrameCreator {
    public static JLabel Label(String text, String font, int fontWeight, int fontSize, float alignment,
            Border border) {
        JLabel label = new JLabel(text);
        label.setFont(new Font(font, fontWeight, fontSize));
        label.setAlignmentX(alignment);
        label.setBorder(border);
        return label;
    }

    public static JButton Button(String text, Dimension dimension) {
        JButton button = new JButton(text);
        button.setPreferredSize(dimension);
        return button;
    }

    public static JPanel Form(Dimension dimension, float alignment, String text) {
        JPanel panel = new JPanel();
        panel.setMaximumSize(dimension);
        panel.setAlignmentX(alignment);
        JLabel usernameLabel = new JLabel(text);
        usernameLabel.setBorder(new EmptyBorder(Unit.MEDIUM_SIZE, Unit.SMALL_SIZE, Unit.MEDIUM_SIZE, Unit.SMALL_SIZE));
        panel.add(usernameLabel);

        return panel;
    }
}