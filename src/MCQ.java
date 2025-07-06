import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class MCQ extends Question {
    public String op1;
    public String op2;
    public String op3;
    public String op4;
    public String correct;

    public MCQ() {}

    public MCQ(String title, String questionText, int difficulty, String code,
               String op1, String op2, String op3, String op4, String correct) {
        super(title, questionText, difficulty, code);
        this.op1 = op1;
        this.op2 = op2;
        this.op3 = op3;
        this.op4 = op4;
        this.correct = correct;
    }

    public void addQuestion() {
        String sql = "INSERT INTO mcq_questions (title, questionText, difficulty, code, op1, op2, op3, op4, correct) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getconnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setString(1, title);
            pst.setString(2, questionText);
            pst.setInt(3, difficulty);
            pst.setString(4, code);
            pst.setString(5, op1);
            pst.setString(6, op2);
            pst.setString(7, op3);
            pst.setString(8, op4);
            pst.setString(9, correct);

            int rows = pst.executeUpdate();
            if (rows > 0) {
                JOptionPane.showMessageDialog(null, "MCQ Question added successfully!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Failed to add MCQ question!");
        }
    }
}
