import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class AddQuestion extends JFrame {
    private final Color backgroundColor = Color.decode("#e6f0ff");
    private final Color buttonColor = new Color(179, 198, 255);
    private final Font labelFont = new Font("Arial", Font.BOLD, 16);
    private final Font fieldFont = new Font("Arial", Font.PLAIN, 14);
    private final Dimension fieldSize = new Dimension(400, 30);
    private final Dimension buttonSize = new Dimension(160, 45);

    private final String type;
    private final int totalQuestions;
    private int currentIndex = 0;

    private final ArrayList<QuestionData> questions = new ArrayList<>();

    // UI components (used across questions)
    private JTextField questionField, difficultyField, shortAnswerField;
    private JTextField[] options;
    private JRadioButton[] optionButtons;
    private ButtonGroup optionGroup;

    public AddQuestion(String type, int totalQuestions) {
        this.type = type;
        this.totalQuestions = totalQuestions;

        setTitle("Add " + type);
        setSize(900, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        buildUI();
        setVisible(true);
    }

    private void buildUI() {
        JPanel panel = new JPanel();
        panel.setBackground(backgroundColor);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel numberLabel = new JLabel("Question No: " + (currentIndex + 1));
        numberLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        numberLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel titleLabel = new JLabel("Add " + type);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 36));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(numberLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(titleLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 30)));

        if (type.equalsIgnoreCase("MCQ")) {
            buildMCQFields(panel);
        } else {
            buildShortFields(panel);
        }

        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        addNavigationButtons(panel, numberLabel);
        add(panel);
    }

    private void buildMCQFields(JPanel panel) {
        questionField = createTextField();
        options = new JTextField[4];
        optionButtons = new JRadioButton[4];
        optionGroup = new ButtonGroup();

        panel.add(createLabeledField("Question:", questionField));
        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        for (int i = 0; i < 4; i++) {
            options[i] = createTextField();
            optionButtons[i] = new JRadioButton("Correct");
            optionButtons[i].setBackground(backgroundColor);
            optionGroup.add(optionButtons[i]);

            JPanel row = new JPanel();
            row.setBackground(backgroundColor);
            row.setLayout(new BoxLayout(row, BoxLayout.X_AXIS));

            JLabel label = new JLabel("Option " + (i + 1) + ": ");
            label.setFont(labelFont);
            label.setPreferredSize(new Dimension(100, 30));

            row.add(Box.createRigidArea(new Dimension(30, 0)));
            row.add(label);
            row.add(Box.createRigidArea(new Dimension(10, 0)));
            row.add(options[i]);
            row.add(Box.createRigidArea(new Dimension(10, 0)));
            row.add(optionButtons[i]);
            row.add(Box.createHorizontalGlue());

            panel.add(row);
            panel.add(Box.createRigidArea(new Dimension(0, 10)));
        }

        difficultyField = createTextField();
        panel.add(createLabeledField("Difficulty:", difficultyField));
    }

    private void buildShortFields(JPanel panel) {
        questionField = createTextField();
        shortAnswerField = createTextField();
        difficultyField = createTextField();

        panel.add(createLabeledField("Question:", questionField));
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(createLabeledField("Answer:", shortAnswerField));
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(createLabeledField("Difficulty:", difficultyField));
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

    private void addNavigationButtons(JPanel panel, JLabel numberLabel) {
        JPanel btnPanel = new JPanel();
        btnPanel.setBackground(backgroundColor);
        btnPanel.setLayout(new BoxLayout(btnPanel, BoxLayout.X_AXIS));

        JButton backBtn = new JButton("Back");
        JButton nextOrSubmitBtn = new JButton(currentIndex == totalQuestions - 1 ? "Submit" : "Next");

        for (JButton btn : new JButton[]{backBtn, nextOrSubmitBtn}) {
            btn.setFont(labelFont);
            btn.setBackground(buttonColor);
            btn.setFocusPainted(false);
            btn.setPreferredSize(buttonSize);
            btn.setMaximumSize(buttonSize);
        }

        backBtn.addActionListener(e -> {
            if (currentIndex > 0) {
                saveCurrentQuestion();
                currentIndex--;
                dispose();
                new AddQuestion(type, totalQuestions).loadQuestion(currentIndex, questions);
            } else {
                dispose();
                new TeacherPanel();
            }
        });

        nextOrSubmitBtn.addActionListener(e -> {
            if (!saveCurrentQuestion()) return;

            if (currentIndex < totalQuestions - 1) {
                currentIndex++;
                dispose();
                new AddQuestion(type, totalQuestions).loadQuestion(currentIndex, questions);
            } else {
                // Save to DB here if needed
                JOptionPane.showMessageDialog(this, "All questions submitted!");
                dispose();
                new TeacherPanel();
            }
        });

        btnPanel.add(Box.createHorizontalGlue());
        btnPanel.add(backBtn);
        btnPanel.add(Box.createRigidArea(new Dimension(20, 0)));
        btnPanel.add(nextOrSubmitBtn);
        btnPanel.add(Box.createHorizontalGlue());

        panel.add(btnPanel);
    }

    private boolean saveCurrentQuestion() {
        String question = questionField.getText().trim();
        String difficulty = difficultyField.getText().trim();
        int difficultyVal;

        try {
            difficultyVal = Integer.parseInt(difficulty);
            if (difficultyVal <= 0) {
                JOptionPane.showMessageDialog(this, "Difficulty must be a positive number.");
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Difficulty must be a valid number.");
            return false;
        }

        if (type.equalsIgnoreCase("MCQ")) {
            String[] ops = new String[4];
            for (int i = 0; i < 4; i++) ops[i] = options[i].getText().trim();
            int correctOption = -1;
            for (int i = 0; i < 4; i++) {
                if (optionButtons[i].isSelected()) {
                    correctOption = i + 1;
                    break;
                }
            }

            if (question.isEmpty() || ops[0].isEmpty() || ops[1].isEmpty() || ops[2].isEmpty() || ops[3].isEmpty() || correctOption == -1) {
                JOptionPane.showMessageDialog(this, "Please fill all fields and select correct option.");
                return false;
            }

            QuestionData data = new QuestionData("MCQ", question, ops, correctOption, null, difficulty);
            if (currentIndex < questions.size()) questions.set(currentIndex, data);
            else questions.add(data);

        } else {
            String answer = shortAnswerField.getText().trim();
            if (question.isEmpty() || answer.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill all fields.");
                return false;
            }

            QuestionData data = new QuestionData("Short", question, null, -1, answer, difficulty);
            if (currentIndex < questions.size()) questions.set(currentIndex, data);
            else questions.add(data);
        }

        return true;
    }

    private void loadQuestion(int index, ArrayList<QuestionData> dataList) {
        this.questions.addAll(dataList);
        this.currentIndex = index;
    }

    // You would define this class or replace it with your actual model
    static class QuestionData {
        String type, question, answer, difficulty;
        String[] options;
        int correctIndex;

        public QuestionData(String type, String question, String[] options, int correctIndex, String answer, String difficulty) {
            this.type = type;
            this.question = question;
            this.options = options;
            this.correctIndex = correctIndex;
            this.answer = answer;
            this.difficulty = difficulty;
        }
    }

    public static void main(String[] args) {
        new AddQuestion("MCQ", 3);
    }
}
