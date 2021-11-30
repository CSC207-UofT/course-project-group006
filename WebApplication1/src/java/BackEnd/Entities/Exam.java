package BackEnd.Entities;

import BackEnd.Entities.QuestionTypes.Question;

import java.util.ArrayList;

public class Exam extends Test{
    public Exam(String name, int timeLimit, int author, int price){
        super(name,timeLimit,author,price);
    }
    public Exam(String name, int timeLimit, int author, int price, ArrayList<Question> questions){
        super(name,timeLimit,author,price, questions);
    }
}
