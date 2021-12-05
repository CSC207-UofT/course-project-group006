package BackEnd.Entities;

import BackEnd.Entities.Question;
import BackEnd.Entities.QuestionInterface;
import java.util.ArrayList;

public class Test {
    protected String name;
    protected ArrayList<Question> questions;
    protected int author;
    protected int Id;
    protected int price;


    /**
     * Construct a test giving its name, time limit,
     * author and price
     * @param name name of the test
     * @param author author of the test
     * @param price price of the test
     */
    
    public Test(String name, int author, int price){
        this.name=name;

        this.questions=new ArrayList<>();
        this.author=author;
        this.price=price;
    }


    /**
     * Construct a test giving its name, time limit,
     * author, price and list of questions
     * @param name name of the test
     * @param author author of the test
     * @param price price of the test
     * @param questions list of questions in this test
     */
    
    public Test(String name, int author, int price, ArrayList<Question> questions){
        this.name=name;
        this.questions=questions;
        this.author=author;
        this.price=price;
    }




    /**
     *
     * @return the author of this test
     */

    
    public int getAuthor(){
        return author;
    }

    /**
     *
     * @return the price of this test
     */
    public int getPrice() {
        return price;
    }

    /**
     *
     * @return the ID of the test
     */
    public int getId() {
        return Id;
    }

    /**
     *
     * @return the name of the test
     */
    public String getName(){
        return this.name;
    }

    /**
     * Add question to this test
     * @param q the question need to add
     * @return True
     */
    public boolean addQuestion(Question q){
        this.questions.add(q);
        return true;
    }

    /**
     * Delete a question from this test giving its name
     * @param q the question name want to delete
     * @return True if succeed or False otherwise
     */
    public boolean deleteQuestion(String q){
        for(Question que:questions){
            if(que.getQuestion().equals(q)){
                questions.remove(que);
                return true;
            }
        }
        return false;
    }

    /**
     * Delete a question from a test giving its ID
     * @param i the question ID
     * @return True if succeed
     */
    public boolean deleteQuestion(int i){
        return questions.remove(i)!=null;
    }

    /**
     * Getter method of the questions
     * @return the list of question in this test
     */
    
    public ArrayList<QuestionInterface> getQuestions(){
        ArrayList<QuestionInterface> result = new ArrayList<QuestionInterface>();
        for (Question question : questions) {
            result.add(question);
        }
        return result;
    }

    /**
     * Method allows teacher to autograge tests
     * @param answer Answer to the question
     * @return Result mark for autograded tests
     */
    public int[] Autograde(String[] answer){
        int[] result = new int[questions.size()];
        for(int i=0;i<questions.size();i++){
            result[i]=questions.get(i).score(answer[i]);
        }
        return result;
    }

    /**
     * Getter method of the question
     * @return Question in the test
     */
    public String[] getQuestion(){
        String[] result = new String[questions.size()];
        for(int i=0;i<questions.size();i++){
            result[i]=questions.get(i).getQuestion();
        }
        return result;
    }

    /**
     * Getter method of the Answer
     * @return Answer to the question
     */
    public String[] getAnswer(){
        String[] result = new String[questions.size()];
        for(int i=0;i<questions.size();i++){
            result[i]=questions.get(i).getAnswer();
        }
        return result;
    }

    /**
     * Getter method of the Mark
     * @return Mark of the question
     */
    public int[] getMark(){
        int[] result = new int[questions.size()];
        for(int i=0;i<questions.size();i++){
            result[i]=questions.get(i).getMarks();
        }
        return result;
    }
}



//    Stores all the words in a test
//        Stores the number of the question in a test
//        Stores the time limit for the test
//        Stores whether the test should be supervised by a teacher
//        Stores the score of the test
//        Getter methods for all the above information
//        Author (who made the test)
