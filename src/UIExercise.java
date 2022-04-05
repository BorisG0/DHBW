import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.text.ParseException;

public class UIExercise extends JFrame {
    private UIExercise() throws ParseException {
        this.setTitle("Logon");

        loadSouthPanel();


        JPanel connectionPanel = loadConnectionPanel();
        JPanel filePanel = loadFilePanel();

        JPanel outerTopPanel = new JPanel(new FlowLayout());

        outerTopPanel.add(connectionPanel, BorderLayout.WEST);
        outerTopPanel.add(filePanel, BorderLayout.EAST);

        this.add(outerTopPanel, BorderLayout.CENTER);

        this.pack();
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);



    }

    private JPanel loadConnectionPanel() throws ParseException {
        Border borderInner = BorderFactory.createEtchedBorder();
        Border borderOuter = BorderFactory.createTitledBorder(borderInner, "Connection");


        JPanel connectionPanel = new JPanel(new GridLayout(0, 2));
        connectionPanel.setBorder(borderOuter);

        JPanel flowPanelInput = new JPanel(new FlowLayout(FlowLayout.LEFT));
        flowPanelInput.add(new JLabel("User:"));
        connectionPanel.add(flowPanelInput);

        flowPanelInput = new JPanel(new FlowLayout(FlowLayout.LEFT));
        flowPanelInput.add(new JTextField(3));
        connectionPanel.add(flowPanelInput);

        flowPanelInput = new JPanel(new FlowLayout(FlowLayout.LEFT));
        flowPanelInput.add(new JLabel("Password:"));
        connectionPanel.add(flowPanelInput);

        flowPanelInput = new JPanel(new FlowLayout(FlowLayout.LEFT));
        flowPanelInput.add(new JPasswordField(4));
        connectionPanel.add(flowPanelInput);

        flowPanelInput = new JPanel(new FlowLayout(FlowLayout.LEFT));
        flowPanelInput.add(new JLabel("Type:"));
        connectionPanel.add(flowPanelInput);

        flowPanelInput = new JPanel(new FlowLayout(FlowLayout.LEFT));
        String[] types = {"FTP", "HTTP"};
        flowPanelInput.add(new JComboBox(types));
        connectionPanel.add(flowPanelInput);

        flowPanelInput = new JPanel(new FlowLayout(FlowLayout.LEFT));
        flowPanelInput.add(new JLabel("Host:"));
        connectionPanel.add(flowPanelInput);

        flowPanelInput = new JPanel(new FlowLayout(FlowLayout.LEFT));
        flowPanelInput.add(new JTextField(4));
        connectionPanel.add(flowPanelInput);

        flowPanelInput = new JPanel(new FlowLayout(FlowLayout.LEFT));
        flowPanelInput.add(new JLabel("Port:"));
        connectionPanel.add(flowPanelInput);

        flowPanelInput = new JPanel(new FlowLayout(FlowLayout.LEFT));
        flowPanelInput.add(new JFormattedTextField(new MaskFormatter("#####")));
        connectionPanel.add(flowPanelInput);

        return connectionPanel;
    }

    private JPanel loadFilePanel(){
        Border borderInner = BorderFactory.createEtchedBorder();
        Border borderOuter = BorderFactory.createTitledBorder(borderInner, "Files");

        JPanel filePanel = new JPanel(new GridLayout(0, 2));
        filePanel.setBorder(borderOuter);


        JPanel flowPanelInput;

        flowPanelInput = new JPanel(new FlowLayout(FlowLayout.LEFT));
        flowPanelInput.add(new JLabel("Source:"));
        filePanel.add(flowPanelInput);

        flowPanelInput = new JPanel(new FlowLayout(FlowLayout.LEFT));
        flowPanelInput.add(new JTextField(6));
        filePanel.add(flowPanelInput);


        flowPanelInput = new JPanel(new FlowLayout(FlowLayout.LEFT));
        flowPanelInput.add(new JLabel("Destination:"));
        filePanel.add(flowPanelInput);

        flowPanelInput = new JPanel(new FlowLayout(FlowLayout.LEFT));
        flowPanelInput.add(new JTextField(6));
        filePanel.add(flowPanelInput);


        return filePanel;
    }

    private void loadSouthPanel(){
        JPanel southPanel = new JPanel(new FlowLayout());
        southPanel.add(new JButton("OK"));
        southPanel.add(new JButton("Cancel"));
        this.add(southPanel, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        try {
            UIExercise uiExercise = new UIExercise();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
