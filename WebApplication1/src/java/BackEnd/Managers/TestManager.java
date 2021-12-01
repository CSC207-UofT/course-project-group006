package BackEnd.Managers;


import BackEnd.Entities.Test;
import BackEnd.Entities.Question;
import BackEnd.Entities.QuestionInterface;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TestManager {
    private HashMap<Integer, Test> allTest;

    /**
     * Construct a Test manager giving some tests
     * @param tests the list of tests
     */
    public TestManager(List<Test> tests){
        allTest=new HashMap<>();
        for (Test t: tests) {
            allTest.put(t.getId(),t);
        }
    }
    public TestManager(){
        allTest=new HashMap<>();
    }


//    /**
//     * Create an Exam giving its name, time limit, author and price
//     * @param name the name of the exam
//     * @param timeLimit the time limit of the exam
//     * @param author the author of the exam
//     * @param price the price of the exam
//     * @return the ID of the test in exam
//     */
//
//    public int creatExame(String name, int timeLimit, int author, int price){
//        Test test = new Exam(name,timeLimit,author,price);
//        allTest.put(test.getId(),test);
//        return test.Id;
//    }

//    /**
//     * Create a quiz giving its name, time limit, author and price
//     * @param name the name of the quiz
//     * @param timeLimit the time limit of the quiz
//     * @param author the author of the quiz
//     * @param price the price of the quiz
//     * @return the ID of the quiz
//     */
//
//    public int creatQuiz(String name, int timeLimit, int author, int price){
//        Test test = new Quiz(name,timeLimit,author,price);
//        allTest.put(test.getId(),test);
//        return test.Id;
//    }

    /**
     * Add question to an exam
     * @param test the ID of the test
     * @param question the question want to add
     * @param answer the answer of the question
     * @param mark the mark of the question
     * @return True
     */
    public boolean addQuestion(int test, String question,String answer,int mark){
        return getTest(test).addQuestion(new Question(question,answer,mark));
    }

    /**
     * Remove question from a test
     * @param test the ID of the test
     * @param q the question name want to remove
     * @return True if succeed or False otherwise
     */
    public boolean removeQuestion(int test, String q){
        return getTest(test).deleteQuestion(q);
    }

    /**
     * Remove question from a test
     * @param test the ID of the test
     * @param q the question ID want to remove
     * @return True if succeed or False otherwise
     */
    public boolean removeQuestion(int test, int q){
        return getTest(test).deleteQuestion(q);
    }

    /**
     * Get the grade of this test giving student's answer
     * @param test the ID of the test
     * @param answers student's answer
     * @return Grade of the test
     */
    public int grade(int test, List<String> answers){
        Test t = getTest(test);
        ArrayList<QuestionInterface> q = t.getQuestions();
        int result = 0;
        for(int i=0;i<q.size();i++){
                result+=q.get(i).score(answers.get(i));
          }
        return result;

    }

    /**
     * Get the test from manager
     * @param Id the ID of the test
     * @return the test has given ID
     */
    private Test getTest(int Id){
        return allTest.get(Id);
    }


    /**
     * Get test owned by a teacher
     * @param t the name of the teacher
     * @return the list of test this teacher owned
     */
    
    public ArrayList<Integer> getOwnedTest(int t){
        ArrayList<Integer> result = new ArrayList<>();

        for(Test test :allTest.values()){
            if(test.getAuthor()==(t)){
                result.add(test.getId());
            }
        }
        return result;
    }

//    /**
//     * Create a diagnostic quiz
//     * @param input the question word source
//     * @return the diagnostic quiz that created
//     */
//    public static Quiz diagnostic(List<Word> input){
//        return Quiz.diagnostic(input);
//    }
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
