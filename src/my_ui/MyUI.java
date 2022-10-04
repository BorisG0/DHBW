package my_ui;

import javax.swing.*;
import java.awt.*;

public class MyUI {
    public static void main(String[] args) {
        JFrame frame = new JFrame("First UI");
        frame.setLayout(new FlowLayout());

        frame.setBounds(0, 0, 1000, 800);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        //frame.pack();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        frame.setVisible(true);

        JLabel uiText = new JLabel("Welcome to my UI");
        JButton button = new JButton("Hello");


        frame.add(uiText);
        frame.add(button);


    }
}
