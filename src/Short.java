import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Short extends Question {
    public String answer;
    public String correct;

    public Short() {}

    public Short(String title, String questionText, int difficulty, String code,
                 String answer, String correct) {
        super(title, questionText, difficulty, code);
        this.answer = answer;
        this.correct = correct;
    }

    public void addQuestion() {
        String sql = "INSERT INTO short_questions (title, questionText, difficulty, code, answer, correct) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getconnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setString(1, title);
            pst.setString(2, questionText);
            pst.setInt(3, difficulty);
            pst.setString(4, code);
            pst.setString(5, answer);
            pst.setString(6, correct);

            int rows = pst.executeUpdate();
            if (rows > 0) {
                JOptionPane.showMessageDialog(null, "Short Question added successfully!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Failed to add Short question!");
        }
    }
}
