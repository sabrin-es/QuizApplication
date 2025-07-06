public class QuestionSet {
    public String title;
    public int amount;
    public int difficulty;
    public boolean type; // 1 indicates MCQ and 0 indicates Short Question.
    public String code;

    public QuestionSet() { }
    public QuestionSet(String title, int amount, int difficulty, boolean type, String code) {
        this.title = title;
        this.amount = amount;
        this.difficulty = difficulty;
        this.type = type;
        this.code = code;
    }
}
