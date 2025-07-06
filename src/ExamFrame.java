import javax.swing.*;
import java.util.ArrayList;

public class ExamFrame extends JFrame {

    public void LoadQuestions(String type, String code, int difficulty) {
        if(type == "MCQ"){
            MCQ m = new MCQ();
            ArrayList<MCQ> mcqs = new ArrayList<>();
            mcqs = m.load(code);
        }
        else if(type == "Short Question"){
            Short s = new Short();
            ArrayList<Short> shorts = new ArrayList<>();
            shorts = s.load(code);

        }
    }
}
