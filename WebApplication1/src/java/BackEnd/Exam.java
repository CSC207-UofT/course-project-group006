package BackEnd;

import QuestionTypes.Question;

import java.util.ArrayList;

public class Exam extends Test{
    public Exam(String name, int timeLimit, String author, int price){
        super(name,timeLimit,author,price);
    }
    public Exam(String name, int timeLimit, String author, int price, ArrayList<Question> questions){
        super(name,timeLimit,author,price, questions);
    }
}