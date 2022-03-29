import javax.swing.*;
import java.awt.*;

public class GridLayoutExampleUI extends JFrame {
    private GridLayoutExampleUI(){
        this.setTitle("GridLayout Example UI");

        JPanel uiContent = new JPanel();

        uiContent.setLayout(new GridLayout(2, 4));

        uiContent.add(new JLabel("Zelle1"));
        uiContent.add(new JLabel("Zelle2"));
        uiContent.add(new JLabel("Zelle3"));
        uiContent.add(new JLabel("Zelle4"));
        uiContent.add(new JLabel("Zelle5"));
        uiContent.add(new JLabel("Zelle6"));

        uiContent.add(new JButton("Zelle7"));
        uiContent.add(new JButton("Zelle8"));



        this.add(uiContent);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new GridLayoutExampleUI();
    }
}
