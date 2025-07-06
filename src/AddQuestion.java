import javax.swing.*;
import java.awt.*;

public class AddQuestion extends JFrame {

    private Color backgroundColor = Color.decode("#e6f0ff");
    private Color buttonColor = new Color(179, 198, 255);
    private Font labelFont = new Font("Arial", Font.BOLD, 16);
    private Font fieldFont = new Font("Arial", Font.PLAIN, 14);
    private Dimension fieldSize = new Dimension(400, 30);
    private Dimension buttonSize = new Dimension(160, 45);

    public AddQuestion(String type, int qNo) {
        setTitle("Add " + type);
        setSize(900, 700);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setBackground(backgroundColor);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // Question number
        JLabel qNoLabel = new JLabel("Question No: " + qNo);
        qNoLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        qNoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel title = new JLabel("Add " + type);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setFont(new Font("Serif", Font.BOLD, 36));
        title.setForeground(Color.BLACK);

        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(qNoLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(title);
        panel.add(Box.createRigidArea(new Dimension(0, 30)));

        if (type.equalsIgnoreCase("MCQ")) {
            addMCQFields(panel);
        } else if (type.equalsIgnoreCase("Short Question")) {
            addShortFields(panel);
        }

        add(panel);
        setVisible(true);
    }

    private void addMCQFields(JPanel panel) {
        JTextField questionField = createTextField();
        JTextField[] options = {
                createTextField(), createTextField(), createTextField(), createTextField()
        };

        JRadioButton[] radioButtons = {
                new JRadioButton("1"), new JRadioButton("2"),
                new JRadioButton("3"), new JRadioButton("4")
        };

        ButtonGroup optionGroup = new ButtonGroup();
        for (JRadioButton rb : radioButtons) {
            rb.setBackground(backgroundColor);
            optionGroup.add(rb);
        }

        JTextField difficultyField = createTextField();

        panel.add(createLabeledField("Question:", questionField));
        panel.add(Box.createRigidArea(new Dimension(0, 15)));

        for (int i = 0; i < 4; i++) {
            panel.add(createLabeledField("Option " + (i + 1) + ":", options[i]));
            panel.add(Box.createRigidArea(new Dimension(0, 10)));
        }

        // Correct option selection
        JPanel correctPanel = new JPanel();
        correctPanel.setBackground(backgroundColor);
        correctPanel.setLayout(new BoxLayout(correctPanel, BoxLayout.X_AXIS));
        JLabel correctLabel = new JLabel("Choose correct option:");
        correctLabel.setFont(labelFont);
        correctLabel.setPreferredSize(new Dimension(200, 30));
        correctPanel.add(correctLabel);
        correctPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        for (JRadioButton rb : radioButtons) {
            correctPanel.add(rb);
            correctPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        }
        panel.add(correctPanel);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));

        panel.add(createLabeledField("Difficulty:", difficultyField));
        panel.add(Box.createRigidArea(new Dimension(0, 30)));
        addButtonRow(panel);
    }

    private void addShortFields(JPanel panel) {
        JTextField questionField = createTextField();
        JTextField answerField = createTextField();
        JTextField difficultyField = createTextField();

        panel.add(createLabeledField("Question:", questionField));
        panel.add(Box.createRigidArea(new Dimension(0, 15)));
        panel.add(createLabeledField("Answer:", answerField));
        panel.add(Box.createRigidArea(new Dimension(0, 15)));
        panel.add(createLabeledField("Difficulty:", difficultyField));
        panel.add(Box.createRigidArea(new Dimension(0, 30)));
        addButtonRow(panel);
    }

    private JPanel createLabeledField(String labelText, JTextField field) {
        JPanel line = new JPanel();
        line.setBackground(backgroundColor);
        line.setLayout(new BoxLayout(line, BoxLayout.X_AXIS));
        JLabel label = new JLabel(labelText);
        label.setFont(labelFont);
        label.setPreferredSize(new Dimension(150, 30));
        field.setMaximumSize(fieldSize);
        field.setFont(fieldFont);
        line.add(Box.createRigidArea(new Dimension(30, 0)));
        line.add(label);
        line.add(Box.createRigidArea(new Dimension(10, 0)));
        line.add(field);
        line.add(Box.createRigidArea(new Dimension(30, 0)));
        return line;
    }

    private JTextField createTextField() {
        JTextField tf = new JTextField();
        tf.setMaximumSize(fieldSize);
        tf.setFont(fieldFont);
        return tf;
    }

    private void addButtonRow(JPanel panel) {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(backgroundColor);
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));

        JButton submitBtn = new JButton("Submit");
        JButton backBtn = new JButton("Back");

        JButton[] buttons = {submitBtn, backBtn};
        for (JButton btn : buttons) {
            btn.setFont(new Font("Arial", Font.BOLD, 16));
            btn.setPreferredSize(buttonSize);
            btn.setMaximumSize(buttonSize);
            btn.setBackground(buttonColor);
            btn.setForeground(Color.BLACK);
            btn.setFocusPainted(false);
        }

        submitBtn.addActionListener(e -> JOptionPane.showMessageDialog(this, "Submitted!"));
        backBtn.addActionListener(e -> dispose());

        buttonPanel.add(Box.createHorizontalGlue());
        buttonPanel.add(submitBtn);
        buttonPanel.add(Box.createRigidArea(new Dimension(20, 0)));
        buttonPanel.add(backBtn);
        buttonPanel.add(Box.createHorizontalGlue());

        panel.add(buttonPanel);
    }

    public static void main(String[] args) {
        new AddQuestion("MCQ", 1);
        // new AddQuestion("Short Question", 2);
    }
}
