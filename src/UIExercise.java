import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.*;
import java.text.ParseException;

public class UIExercise extends JFrame {
    private UIExercise() throws ParseException {

        this.setTitle("Logon");


        MouseListener mouseListender = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("mouse clicked");
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        };


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
        this.addMouseListener(mouseListender);



    }

    private JPanel loadConnectionPanel() throws ParseException {
        Border borderInner = BorderFactory.createEtchedBorder();
        Border borderOuter = BorderFactory.createTitledBorder(borderInner, "Connection");

        JFormattedTextField portField = new JFormattedTextField(new MaskFormatter("#####"));


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
        JComboBox typesBox = new JComboBox(types);
        typesBox.addItemListener(e -> {
//            System.out.println("Ausgelöst");
//            System.out.println("ItemEvent(Item) für JComboBox: " + e.getItem());
//            System.out.println("ItemEvent(ParameterString) für JComboBox: " + e.paramString());
//            System.out.println("ItemEvent(StateChange) für JComboBox: " + e.getStateChange());

            if(e.getStateChange() == 1){
                int portValue = 0;
                if(e.getItem().equals("FTP")){
                    portValue = 21;
                }else if (e.getItem().equals("HTTP")){
                    portValue = 80;
                }
                portField.setValue(portValue);
            }

        });
        flowPanelInput.add(typesBox);
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
        flowPanelInput.add(portField);
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
        final String cancelCommand = "cancel", okCommand = "ok";

        ActionListener myButtonListener = (ActionEvent e) ->{
            System.out.println(e.paramString());
            if(e.getActionCommand().equals(okCommand)){
                System.out.println("alles OK");
            }else if(e.getActionCommand().equals(cancelCommand)){
                System.out.println("es wird gecancelt");
            }
            System.exit(0);
        };


        JPanel southPanel = new JPanel(new FlowLayout());

        JButton okButton = new JButton("OK");
        okButton.addActionListener(myButtonListener);
        okButton.setActionCommand(okCommand);
        southPanel.add(okButton);


        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(myButtonListener);
        cancelButton.setActionCommand(cancelCommand);
        southPanel.add(cancelButton);

        this.add(southPanel, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        GraphicsDevice defaultGraphicsDevice = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();

        System.out.println("Screen Dimensions: " + defaultGraphicsDevice.getDefaultConfiguration().getBounds());


        try {
            UIExercise uiExercise = new UIExercise();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
