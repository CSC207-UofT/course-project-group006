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
    public int creatExame(String name, int timeLimit, String author, int price){
        Test test = new Exam(name,timeLimit,author,price);
        allTest.put(test.getId(),test);
        return test.Id;
    }
    public int creatQuiz(String name, int timeLimit, String author, int price){
        Test test = new Quiz(name,timeLimit,author,price);
        allTest.put(test.getId(),test);
        return test.Id;
    }
    public boolean addQuestion(int test, String question,String answer,int mark){
        return getTest(test).addQuestion(new Question(question,answer,mark));
    }
    public boolean removeQuestion(int test, String q){
        return getTest(test).deleteQuestion(q);
    }
    public boolean removeQuestion(int test, int q){
        return getTest(test).deleteQuestion(q);
    }
    public int grade(int test, List<String> answers){
        Test t = getTest(test);
        ArrayList<Question> q = t.getQuestions();
        int result = 0;
        for(int i=0;i<q.size();i++){
                result+=q.get(i).score(answers.get(i));
          }
        return result;

    }
    private Test getTest(int Id){
        return allTest.get(Id);
    }
    public ArrayList<String> getOwnedTest(String t){
        ArrayList<String> result = new ArrayList<>();
        for(Test test :allTest.values()){
            if(test.getAuthor().equals(t)){
                result.add(test.getName());
            }
        }
        return result;
    }

}
