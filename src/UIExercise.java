import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class UIExercise extends JFrame {
    private UIExercise(){
        this.setTitle("Logon");

        loadSouthPanel();
        this.add(loadConnectionPanel(), BorderLayout.WEST);
        this.add(loadFilePanel(), BorderLayout.EAST);

        this.pack();
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    private JPanel loadConnectionPanel(){
        Border borderInner = BorderFactory.createEtchedBorder();
        Border borderOuter = BorderFactory.createTitledBorder(borderInner, "Connection");

        JPanel connectionPanel = new JPanel(new GridLayout(0, 2));
        connectionPanel.setBorder(borderOuter);

        connectionPanel.add(new JLabel("User:"));
        connectionPanel.add(new JTextField());

        connectionPanel.add(new JLabel("Password:"));
        connectionPanel.add(new JPasswordField());

        connectionPanel.add(new JLabel("Type:"));
        connectionPanel.add(new JTextField());

        return connectionPanel;
    }

    private JPanel loadFilePanel(){
        Border borderInner = BorderFactory.createEtchedBorder();
        Border borderOuter = BorderFactory.createTitledBorder(borderInner, "Files");

        JPanel filePanel = new JPanel(new GridLayout(0, 2));
        filePanel.setBorder(borderOuter);

        filePanel.add(new JLabel("Source:"));
        filePanel.add(new JTextField());

        filePanel.add(new JLabel("Destination:"));
        filePanel.add(new JPasswordField());


        return filePanel;
    }

    private void loadSouthPanel(){
        JPanel southPanel = new JPanel(new FlowLayout());
        southPanel.add(new JButton("OK"));
        southPanel.add(new JButton("Cancel"));
        this.add(southPanel, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        UIExercise uiExercise = new UIExercise();
    }
}