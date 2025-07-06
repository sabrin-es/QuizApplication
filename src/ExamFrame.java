import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Collections;

public class ExamFrame extends JFrame {

    // For MCQ
    private ArrayList<MCQ> mcqQuestions;
    private JRadioButton[] optionButtons;
    private ButtonGroup optionsGroup;

    // For Short Question
    private ArrayList<Short> shortQuestions;
    private JTextArea answerArea;

    // Common
    private int currentIndex = 0;
    private JLabel questionLabel;
    private JButton nextButton, backButton;

    private String currentType;

    public ExamFrame() {
        setTitle("Exam");
        setSize(700, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        questionLabel = new JLabel();
        questionLabel.setFont(new Font("Arial", Font.BOLD, 16));
        questionLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(questionLabel, BorderLayout.NORTH);

        nextButton = new JButton("Next");
        backButton = new JButton("Back");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(backButton);
        buttonPanel.add(nextButton);
        add(buttonPanel, BorderLayout.SOUTH);

        backButton.addActionListener(e -> {
            this.dispose();
            new StudentPanel().setVisible(true); // Replace with your actual StudentPanel
        });

        nextButton.addActionListener(e -> {
            currentIndex++;
            if (currentType.equals("MCQ")) {
                if (currentIndex < mcqQuestions.size()) {
                    displayMCQ(currentIndex);
                } else {
                    JOptionPane.showMessageDialog(this, "MCQ Exam Finished!");
                    this.dispose();
                    new StudentPanel().setVisible(true);
                }
            } else if (currentType.equals("Short Question")) {
                if (currentIndex < shortQuestions.size()) {
                    displayShort(currentIndex);
                } else {
                    JOptionPane.showMessageDialog(this, "Short Question Exam Finished!");
                    this.dispose();
                    new StudentPanel().setVisible(true);
                }
            }
        });
    }

    public void LoadQuestions(String type, String code, int difficulty) {
        currentType = type;
        currentIndex = 0;

        if (type.equals("MCQ")) {
            MCQ m = new MCQ();
            ArrayList<MCQ> allMcqs = m.load(code);
            Collections.shuffle(allMcqs);

            mcqQuestions = new ArrayList<>();
            int currentDifficulty = 0;
            for (MCQ q : allMcqs) {
                if (currentDifficulty + q.difficulty <= difficulty) {
                    mcqQuestions.add(q);
                    currentDifficulty += q.difficulty;
                }
            }

            if (mcqQuestions.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No MCQ available for the given difficulty.");
                this.dispose();
                return;
            }

            setupMCQUI();
            displayMCQ(0);
        }

        else if (type.equals("Short Question")) {
            Short s = new Short();
            ArrayList<Short> allShorts = s.load(code);
            Collections.shuffle(allShorts);

            shortQuestions = new ArrayList<>();
            int currentDifficulty = 0;
            for (Short q : allShorts) {
                if (currentDifficulty + q.difficulty <= difficulty) {
                    shortQuestions.add(q);
                    currentDifficulty += q.difficulty;
                }
            }

            if (shortQuestions.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No Short Questions available for the given difficulty.");
                this.dispose();
                return;
            }

            setupShortUI();
            displayShort(0);
        }
    }

    private void setupMCQUI() {
        // Remove center panel if already added
        getContentPane().removeAll();

        add(questionLabel, BorderLayout.NORTH);

        JPanel optionsPanel = new JPanel(new GridLayout(4, 1, 5, 5));
        optionsPanel.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));
        optionButtons = new JRadioButton[4];
        optionsGroup = new ButtonGroup();
        for (int i = 0; i < 4; i++) {
            optionButtons[i] = new JRadioButton();
            optionsGroup.add(optionButtons[i]);
            optionsPanel.add(optionButtons[i]);
        }

        add(optionsPanel, BorderLayout.CENTER);
        add(createButtonPanel(), BorderLayout.SOUTH);
        revalidate();
        repaint();
    }

    private void setupShortUI() {
        getContentPane().removeAll();

        add(questionLabel, BorderLayout.NORTH);

        answerArea = new JTextArea(6, 40);
        answerArea.setLineWrap(true);
        answerArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(answerArea);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));

        add(scrollPane, BorderLayout.CENTER);
        add(createButtonPanel(), BorderLayout.SOUTH);
        revalidate();
        repaint();
    }

    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(backButton);
        buttonPanel.add(nextButton);
        return buttonPanel;
    }

    private void displayMCQ(int index) {
        MCQ q = mcqQuestions.get(index);
        questionLabel.setText("<html>Q" + (index + 1) + ": " + q.questionText + "</html>");

        optionButtons[0].setText(q.getOp1());
        optionButtons[1].setText(q.getOp2());
        optionButtons[2].setText(q.getOp3());
        optionButtons[3].setText(q.getOp4());
        optionsGroup.clearSelection();

        nextButton.setText((index == mcqQuestions.size() - 1) ? "Submit" : "Next");
    }

    private void displayShort(int index) {
        Short q = shortQuestions.get(index);
        questionLabel.setText("<html>Q" + (index + 1) + ": " + q.questionText + "</html>");
        answerArea.setText("");

        nextButton.setText((index == shortQuestions.size() - 1) ? "Submit" : "Next");
    }
}
