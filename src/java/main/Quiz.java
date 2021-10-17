import QuestionTypes.Question;

import java.util.ArrayList;
import java.util.List;

public class Quiz extends Test{
    public Quiz(String name, int timeLimit, Teacher author, int price){
        super(name,timeLimit, author, price);
    }
    private void autoGenerateTranslationQuestion(List<Word> input){
        for(Word w:input){
            StringBuilder ans = new StringBuilder();
            for(String s: w.getMeaning()){
                if(s != null) {
                    ans.append(s).append(",");
                }
            }
            this.questions.add(new Question(w.getSpelling(), ans.toString(), w.getLevel()));
        }
    }
    public static Quiz diagnostic(List<Word> input){
        Quiz q =  new Quiz("diagnostic", input.size()*3,Teacher.PLATFORM,0);
        q.autoGenerateTranslationQuestion(input);
        return q;
    }
}
