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

    /**
     * Method to create exam with questions
     * @param name Exam name
     * @param timeLimit Time required to do the exam
     * @param author The id of author of the exam
     * @param price The price of the exam
     * @param questionNames The name of questions
     * @param questions The question
     * @param answer The answer to the question
     * @param questionMarks The mark of the question
     */
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
