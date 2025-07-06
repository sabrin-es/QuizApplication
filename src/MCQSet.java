public class MCQSet extends QuestionSet{

    public String question, op1, op2, op3, op4, correct;
    public MCQSet() {}
    public MCQSet(String title, int amount, int difficulty, boolean type, String code) {
        super(title, amount, difficulty, type, code);
    }

    public void setQuestion(String question, String op1, String op2, String op3, String op4, String correct)
    {
        this.question = question;
        this.op1 = op1;
        this.op2 = op2;
        this.op3 = op3;
        this.op4 = op4;
        this.correct = correct;
    }
}
