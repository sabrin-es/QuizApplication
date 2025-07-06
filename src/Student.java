import javax.swing.*;
import java.sql.*;

public class Student extends User {
    protected int roll;
    protected int registration;

    public Student(String name, String email, String password, String department, int roll, int registration) {
        super(name, email, password, department);
        this.roll = roll;
        this.registration = registration;
    }

    public void register() {
        try (Connection conn = DBConnection.getconnection()) {
            String query = "INSERT INTO students (name, email, password, department, roll, registration) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement pst = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            pst.setString(1, name);
            pst.setString(2, email);
            pst.setString(3, password);
            pst.setString(4, department);
            pst.setInt(5, roll);
            pst.setInt(6, registration);

            int rows = pst.executeUpdate();

            if (rows > 0) {
                JOptionPane.showMessageDialog(null, "Student registered successfully!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Registration failed!");
        }

    }
}
