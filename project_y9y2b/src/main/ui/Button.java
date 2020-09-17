package ui;

import javax.swing.*;
import java.awt.*;

public class Button {

    public Button(JButton button, JFrame jframe) {
        button.setAlignmentX(Component.LEFT_ALIGNMENT);
        button.setPreferredSize(new Dimension(938, 30));
        button.setFont(new Font("Arial", Font.PLAIN, 20));
        jframe.add(button);
    }
}
