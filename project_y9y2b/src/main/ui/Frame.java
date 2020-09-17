package ui;

import javax.swing.*;

public class Frame {

    public Frame(JFrame jframe) {
        jframe.setLocationRelativeTo(null);
        jframe.getContentPane().setLayout(new BoxLayout(jframe.getContentPane(), BoxLayout.Y_AXIS));
        jframe.pack();
        jframe.setVisible(true);
    }
}
