public class QuestionSet {
    public String title;
    public int amount;
    public String type; // 1 indicates MCQ and 0 indicates Short Question.
    public String code;

    public QuestionSet() { }
    public QuestionSet(String title, int amount, String type, String code) {
        this.title = title;
        this.amount = amount;
        this.type = type;
        this.code = code;
    }
}
