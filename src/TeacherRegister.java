import javax.swing.*;
import java.awt.*;

public class TeacherRegister extends JFrame {
    public TeacherRegister() {
        setTitle("Teacher Registration");
        setSize(900, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        Color backgroundColor = Color.decode("#e6f0ff");
        Color buttonColor = new Color(179, 198, 255);
        Dimension fieldSize = new Dimension(200, 35);
        Dimension buttonSize = new Dimension(160, 40);
        Font labelFont = new Font("Arial", Font.PLAIN, 16);
        Font buttonFont = new Font("Arial", Font.BOLD, 16);

        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(backgroundColor);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        JLabel title = new JLabel("Teacher Registration");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setFont(new Font("Serif", Font.BOLD, 30));
        title.setForeground(Color.BLACK);

        mainPanel.add(Box.createRigidArea(new Dimension(0, 60)));
        mainPanel.add(title);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 30)));

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(backgroundColor);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Form Fields
        JTextField nameField = new JTextField(); nameField.setPreferredSize(fieldSize);
        JTextField emailField = new JTextField(); emailField.setPreferredSize(fieldSize);
        JTextField deptField = new JTextField(); deptField.setPreferredSize(fieldSize);
        JPasswordField passwordField = new JPasswordField(); passwordField.setPreferredSize(fieldSize);

        String[] labels = {"Name:", "Email:", "Department:", "Password:"};
        Component[] fields = {nameField, emailField, deptField, passwordField};

        for (int i = 0; i < labels.length; i++) {
            JLabel label = new JLabel(labels[i]);
            label.setFont(labelFont);
            gbc.gridx = 0; gbc.gridy = i;
            formPanel.add(label, gbc);
            gbc.gridx = 1;
            formPanel.add(fields[i], gbc);
        }

        // Buttons
        JButton registerBtn = new JButton("Register");
        JButton backBtn = new JButton("Back");

        registerBtn.setPreferredSize(buttonSize);
        backBtn.setPreferredSize(buttonSize);
        registerBtn.setFont(buttonFont);
        backBtn.setFont(buttonFont);
        registerBtn.setBackground(buttonColor);
        backBtn.setBackground(buttonColor);
        registerBtn.setForeground(Color.BLACK);
        backBtn.setForeground(Color.BLACK);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(backgroundColor);
        buttonPanel.add(registerBtn);
        buttonPanel.add(Box.createRigidArea(new Dimension(20, 0)));
        buttonPanel.add(backBtn);

        backBtn.addActionListener(e -> {
            new Register();
            dispose();
        });

        mainPanel.add(formPanel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        mainPanel.add(buttonPanel);

        add(mainPanel);
        setVisible(true);
    }

    public static void main(String[] args) {
        new TeacherRegister();
    }
}
