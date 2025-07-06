public class Short extends Question{

    public String answer;
    public String correct;

    public Short() {}

    public Short(String title, String questionText, int difficulty, String code, String answer, String correct) {
        super(title, questionText, difficulty, code);
        this.answer = answer;
        this.correct = correct;
    }
}
