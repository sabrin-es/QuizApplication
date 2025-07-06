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
                StudentPanel studentPanel = new StudentPanel();
                studentPanel.setVisible(true);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Registration failed!");
            MainFrame mainFrame = new MainFrame();
            mainFrame.setVisible(true);
        }
    }

    public boolean login(String email, String password) {
        try (Connection conn = DBConnection.getconnection()) {
            String query = "SELECT * FROM students WHERE email = ? AND password = ?";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, email);
            pst.setString(2, password);

            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                this.name = rs.getString("name");
                this.email = rs.getString("email");
                this.password = rs.getString("password");
                this.department = rs.getString("department");
                this.roll = rs.getInt("roll");
                this.registration = rs.getInt("registration");
                return true;
            } else {
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
