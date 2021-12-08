package BackEnd.Managers;


import BackEnd.Entities.Test;
import BackEnd.Entities.Question;
import BackEnd.Interfaces.QuestionInterface;
import BackEnd.Interfaces.GeneralReadWriter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


//creat test, add question, submit, grade question, grade test

/**
 * The manager TestManager
 */
public class TestManager {
    private HashMap<Integer, Test> allTest;
    private GeneralReadWriter testGate;
    private QuestionManager questionManager;


    /**
     * The constructor of TestManager
     * @param testGate the test gate
     * @param questionManager the question manager
     */
    public TestManager(GeneralReadWriter testGate, QuestionManager questionManager){
        this.testGate = testGate;
        this.questionManager=questionManager;
    }

    /**
     * The constructor of TestManager
     * @param testGate the test gate
     */
    public TestManager(GeneralReadWriter testGate){
        this.testGate = testGate;
    }

    /**
     * Create test
     * @param name the name
     * @param author the author
     * @param price the price
     * @return The Integer representing creation of test
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
     * Add question to an exam

     * @return True
     */
    public boolean addQuestionToTest(int testID, int questionID){
        List<String> result = testGate.readByID(222, 6, testID);

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
     * Get test
     * @param testID the test ID
     * @return the Test
     */
    public Test getTest(int testID){
        List<String> result = testGate.readRow(testID);
        String[] questionsID = result.get(5).split(",");
        System.out.println(Arrays.toString(result.get(5).split(",")));
        ArrayList<Question> questions = new ArrayList<>();
        if (questionsID.length != 0){
            for (String q: questionsID){
                try {
                    questions.add(questionManager.getQuestion(Integer.parseInt(q)));

                }catch (NumberFormatException e){
                    //
                }
            }
            Test myTest = new Test(result.get(1), Integer.parseInt(result.get(2)), Integer.parseInt(result.get(4)),
                    questions);
            return myTest;
        }
        else{
            Test myTest = new Test(result.get(1), Integer.parseInt(result.get(2)), Integer.parseInt(result.get(4)));
        }

        return null;
    }

    /**
     * Remove question from a test
     * @param testID the ID of the test
     * @param qID the question ID want to remove
     * @return True if succeed or False otherwise
     */
    public boolean removeQuestion(int testID, int qID){
        List<String> result = testGate.readByID(222, 6, testID);
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

    /**
     * Get test name
     * @param testID the test ID
     * @return The name of the test
     */
    public String getTestName(int testID){
        List<String> result = testGate.readRow(testID);
        return result.get(1);
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

    /**
     * Get test info
     * @param id the test ID
     * @return The questions in the test
     */
    public List<QuestionInterface> getTestInfo(int id){
       List<QuestionInterface> result = getTest(id).getQuestions();
       return result;
    }

    /**
     * Get questions
     * @param id the test ID
     * @return The String list of questions
     */
    public String[] getQuestions(int id){
        return getTest(id).getQuestion();
    }

    /**
     * Get answers
     * @param id the test ID
     * @return The String list of answers
     */
    public String[] getAnswers(int id){
        return getTest(id).getAnswer();
    }

    /**
     * Get marks
     * @param id the test ID
     * @return The int list of marks
     */
    public int[] getMarks(int id){
        return getTest(id).getMark();
    }

    /**
     * Get question ID
     * @param id the test ID
     * @return The int list of question ID
     */
    public int[] getQuestionId(int id){
        System.out.println(id);
        System.out.println(Arrays.toString(getTest(id).getQuestionId()));
        return getTest(id).getQuestionId();
    }

    /**
     * Add question to test
     * @param testId The test ID
     * @param q the question
     * @param a the answer to the question
     * @param m the mark of the question
     */
    public void addQuestion(int testId,String q,String a, int m){
        addQuestionToTest(testId,questionManager.addQuestion("?",q,a,m));
    }
}
