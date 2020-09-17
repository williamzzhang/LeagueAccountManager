package ui;

import javax.swing.*;
import java.awt.*;

public class Label {

    public Label(JLabel jlabel) {
        jlabel.setPreferredSize(new Dimension(1000, 100));
        jlabel.setFont(new Font("Arial", Font.PLAIN, 40));
    }
}
