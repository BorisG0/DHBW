import javax.swing.*;
import java.awt.*;

public class FlowLayoutExampleUI extends JFrame {
    private FlowLayoutExampleUI(){
        this.setTitle("FlowLayout Example UI");

        JPanel uiContent = new JPanel();
        uiContent.setLayout(new FlowLayout(FlowLayout.RIGHT));

        uiContent.add(new JLabel("Hello World!"));
        uiContent.add(new JLabel("Hello There!"));
        uiContent.add(new JLabel("General Kenobi"));
        uiContent.add(new JButton("fight"));
        uiContent.add(new JButton("run"));

        this.add(uiContent);


        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new FlowLayoutExampleUI();
    }
}
