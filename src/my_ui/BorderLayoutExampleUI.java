package my_ui;

import javax.swing.*;
import java.awt.*;

public class BorderLayoutExampleUI extends JFrame {
    private BorderLayoutExampleUI(){
        this.setTitle("BorderLayout Example UI");

        JPanel uiContent = new JPanel();

        JPanel centerContentPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        centerContentPanel.add(new JButton("Center1"));
        centerContentPanel.add(new JButton("Center2"));
        centerContentPanel.add(new JButton("Center3"));
        this.add(centerContentPanel, BorderLayout.CENTER);
        this.add(new JButton("North"), BorderLayout.NORTH);
        this.add(new JButton("South"), BorderLayout.SOUTH);
        this.add(new JButton("East"), BorderLayout.EAST);
        this.add(new JButton("West"),BorderLayout.WEST);




        //this.add(uiContent);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new BorderLayoutExampleUI();
    }
}
