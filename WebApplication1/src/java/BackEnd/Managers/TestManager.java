package BackEnd.Managers;


import BackEnd.Entities.Test;
import BackEnd.Entities.Question;
import BackEnd.Entities.QuestionInterface;
import BackEnd.Interfaces.GeneralReadWriter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


//creat test, add question, submit, grade question, grade test
public class TestManager {
    private HashMap<Integer, Test> allTest;
    private GeneralReadWriter testGate;

    /**
     * Construct a Test manager giving some tests
     * @param allTest the list of tests
     */
    public TestManager(HashMap<Integer, Test> allTest){
        this.allTest = allTest;

    }
    public TestManager(GeneralReadWriter testGate){
        this.testGate = testGate;
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
     * Create a test giving its name, author and price
     * @param name the name of the test
     * @param author the author of the test
     * @param price the price of the test
     * @return testID if the test is created, otherwise return -1.
     */

    public int creatTest(String name, int author, int price){
        List<String> info = new ArrayList<>();
        info.add(name);
        info.add(author + "");
        info.add(price + "");
        List<String> result =  testGate.write(1, info);
        if (result != null){
            return Integer.parseInt(result.get(0));
        }
        return -1;
    }

    /**
     * Add a question to a Test
     * @param testID the test we need
     * @param questionID the question we want to add
     * @return True if the question is added. False if the test doesn't exist.
     */
    public boolean addQuestionToTest(int testID, int questionID){
        List<String> result = testGate.readByID(222, 6, testID);

        if (result == null){
            return false;
        }

        if (result.get(0).equals("")){
            List<String> info = new ArrayList<>();
            info.add(testID + "");
            info.add(questionID + "");
            testGate.write(13, info);

            return true;
        }
        else{
            List<String> info = new ArrayList<>();
            info.add(testID + "");
            info.add(questionID + "");
            testGate.write(66, info);
            return true;
        }


    }

    /**
     * Remove question from a test giving its id ang question ID
     * @param testID the ID of the test
     * @param qID the question ID want to remove
     * @return True if succeed or False otherwise
     */
    public boolean removeQuestion(int testID, int qID){
        List<String> result = testGate.readByID(222, 13, testID);
        if (result.get(0).equals("")) {
            return false;
        }
        String[] strings = result.get(0).split(",");
        int[] array = new int[strings.length];
        boolean inTest = false;
        int index = 0;
        for (int i = 0; i < strings.length; i++) {
            array[i] = Integer.parseInt(strings[i]);
            if (array[i] == qID) {
                inTest = true;
                index = i;
            }
        }
        if (!inTest) {
            return false;
        }
        StringBuilder newString = new StringBuilder();
        if (array.length != 1) {
            for (int i = 0; i < array.length; i++) {
                if (i != index) {
                    newString.append(",").append(array[i]);
                }
            }
        }
        List<String> info = new ArrayList<>();
        info.add(testID + "");
        info.add(newString.toString());
        List<String> stringList = testGate.write(13, info);
        return !stringList.get(0).equals("FAILED");
    }



//    /**
//     * Get the grade of this test giving student's answer
//     * @param test the ID of the test
//     * @param answers student's answer
//     * @return Grade of the test
//     */
//    public int grade(int test, List<String> answers){
//        Test t = getTest(test);
//        ArrayList<QuestionInterface> q = t.getQuestions();
//        int result = 0;
//        for(int i=0;i<q.size();i++){
//                result+=q.get(i).score(answers.get(i));
//          }
//        return result;
//
//    }

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

    /**
     * Get the test name giving the testID
     * @param id the testID
     * @return the name of this test
     */
    public String getTestName(int id){
        return(allTest.get(id).getName());
    }

    /**
     * get the questions in this test
     * @param id the id of the test
     * @return the string list of questions
     */
    public String[] getQuestions(int id){
        return this.allTest.get(id).getQuestion();
    }

}
