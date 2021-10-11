import QuestionTypes.Question;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TestManager {
    private HashMap<Integer,Test> allTest;
    public TestManager(List<Test> tests){
        allTest=new HashMap<>();
        for (Test t: tests) {
            allTest.put(t.getId(),t);
        }
    }
    public TestManager(){
        allTest=new HashMap<>();
    }
    public void creatExame(String name, int timeLimit, String author, int price){
        Test test = new Exam(name,timeLimit,author,price);
        allTest.put(test.getId(),test);
    }
    public void creatQuiz(String name, int timeLimit, String author, int price){
        Test test = new Quiz(name,timeLimit,author,price);
        allTest.put(test.getId(),test);
    }
    public void addQueststion(Test test, Question q){
        test.getQuestions().add(q);
    }
    public void removeQueststion(Test test, Question q){
        test.getQuestions().remove(q);
    }
    public int grade(Test t, List<String> answers){
        ArrayList<Question> q = t.getQuestions();
        int result = 0;
        for(int i=0;i<q.size();i++){
                result+=q.get(i).score(answers.get(i));
          }
        return result;

    }


}
