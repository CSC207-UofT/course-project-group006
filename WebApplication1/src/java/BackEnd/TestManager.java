package BackEnd;

import QuestionTypes.Question;
import QuestionTypes.QuestionInterface;
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
    public int creatExame(String name, int timeLimit, int author, int price){
        Test test = new Exam(name,timeLimit,author,price);
        allTest.put(test.getId(),test);
        return test.Id;
    }
    public int creatQuiz(String name, int timeLimit, int author, int price){
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
        ArrayList<QuestionInterface> q = t.getQuestions();
        int result = 0;
        for(int i=0;i<q.size();i++){
                result+=q.get(i).score(answers.get(i));
          }
        return result;

    }
    private Test getTest(int Id){
        return allTest.get(Id);
    }
    public ArrayList<Integer> getOwnedTest(int t){
        ArrayList<Integer> result = new ArrayList<>();
        for(Test test :allTest.values()){
            if(test.getAuthor()==(t)){
                result.add(test.getId());
            }
        }
        return result;
    }
    public static Quiz diagnostic(List<Word> input){
        return Quiz.diagnostic(input);
    }
    public String getTestName(int id){
        return(allTest.get(id).getName());
    }
    public List<QuestionInterface> getTestInfo(int id){
       List<QuestionInterface> result = allTest.get(id).getQuestions();
       return result;
    }
    public String[] getQuestions(int id){
        return this.allTest.get(id).getQuestion();
    }
    public String[] getAnswers(int id){
        return this.allTest.get(id).getAnswer();
    }
    public int[] getMarks(int id){
        return this.allTest.get(id).getMark();
    }
}
