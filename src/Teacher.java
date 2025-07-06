import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class Teacher extends User {

    public Teacher(String name, String email, String password, String department) {
        super(name, email, password, department);
    }

    public void register() {
        try (Connection conn = DBConnection.getconnection()) {
            String query = "INSERT INTO students (name, email, password, department, roll, registration) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement pst = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            pst.setString(1, name);
            pst.setString(2, email);
            pst.setString(3, password);
            pst.setString(4, department);

            int rows = pst.executeUpdate();

            if (rows > 0) {
                JOptionPane.showMessageDialog(null, "Teacher registered successfully!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Registration failed!");
        }

    }
}
