package BackEnd.Entities;

import BackEnd.Entities.Question;

import java.util.ArrayList;

public class Exam extends Test{
    public Exam(String name, int timeLimit, int author, int price){
        super(name,timeLimit,author,price);
    }
    public Exam(String name, int timeLimit, int author, int price, ArrayList<Question> questions){
        super(name,timeLimit,author,price, questions);
    }
    public Exam(String name, int timeLimit, int author, int price,ArrayList<String> questionNames, ArrayList<String> questions,
    ArrayList<String> answer,ArrayList<Integer> questionMarks){
        super(name,timeLimit,author,price, null);
        ArrayList<Question> a = new ArrayList<>();
        for (int i=0;i<questionNames.size();i++){
            a.add(new Question(questionNames.get(i),questions.get(i),answer.get(i),questionMarks.get(i)));
        }
        this.questions=a;
    }
}
