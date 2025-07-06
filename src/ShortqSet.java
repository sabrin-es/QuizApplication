public class ShortqSet extends MCQSet{

    public String question;
    public String answer;
    public String correct;
    public ShortqSet(String title, int amount, int difficulty, boolean type, String code) {
        super(title, amount, difficulty, type, code);
    }

    public void setQuestion(String question, String answer, String correct)
    {
        this.question = question;
        this.answer = answer;
        this.correct = correct;
    }
}
