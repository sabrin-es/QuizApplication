public class MCQ extends Question {
    public String op1;
    public String op2;
    public String op3;
    public String op4;
    public String correct;

    public MCQ(){}
    public MCQ (String title, String questionText, int difficulty, String code, String op1, String op2, String op3, String op4, String correct)
    {
        super(title, questionText, difficulty, code);
        this.op1 = op1;
        this.op2 = op2;
        this.op3 = op3;
        this.op4 = op4;
        this.correct = correct;
    }
}