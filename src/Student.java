public class Student extends User {
    protected int roll;
    protected int registration;
    public Student(String name, String email, String password, String department, int roll, int registration) {
        super(name, email, password, department);
        this.roll = roll;
        this.registration = registration;
    }
}
