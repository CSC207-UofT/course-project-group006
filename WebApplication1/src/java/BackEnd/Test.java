package BackEnd;

import QuestionTypes.Question;
import QuestionTypes.QuestionInterface;
import java.util.ArrayList;

public abstract class Test {
    protected String name;
    protected int timeLimit;
    protected ArrayList<Question> questions;
    protected int author;
    protected int Id;
    protected int price;
    public Test(String name, int timeLimit, int author, int price){
        this.name=name;
        this.timeLimit=timeLimit;
        this.questions=new ArrayList<>();
        this.author=author;
        this.price=price;
        this.Id=IDcreater.creat();
    }
    public Test(String name, int timeLimit, int author, int price, ArrayList<Question> questions){
        this.name=name;
        this.timeLimit=timeLimit;
        this.questions=questions;
        this.author=author;
        this.price=price;
        this.Id=IDcreater.creat();
    }

    public int getAuthor(){
        return author;
    }
    public int getPrice() {
        return price;
    }

    public int getId() {
        return Id;
    }
    public String getName(){
        return this.name;
    }
    //public void addQuestion(Question q){
      //  questions.add(q);
    //}
    //public void removeQuestion(Question q){
      //  questions.remove(q);
    //}
    //public int score(ArrayList<String> answer){
      //  int result = 0;
        //for(int i=0;i<questions.size();i++){
    //        result+=questions.get(i).score(answer.get(i));
      //  }
        //return result;
    //}
    public boolean addQuestion(Question q){
        this.questions.add(q);
        return true;
    }
    public boolean deleteQuestion(String q){
        for(Question que:questions){
            if(que.getQuestion().equals(q)){
                questions.remove(que);
                return true;
            }
        }
        return false;
    }
    public boolean deleteQuestion(int i){
        return questions.remove(i)!=null;
    }
    public ArrayList<QuestionInterface> getQuestions(){
        ArrayList<QuestionInterface> result = new ArrayList<QuestionInterface>();
        for(int i=0;i<questions.size();i++){
            result.add(questions.get(i));
        }
        return result;
    }
    public int[] Autograde(String[] answer){
        int[] result = new int[questions.size()];
        for(int i=0;i<questions.size();i++){
            result[i]=questions.get(i).score(answer[i]);
        }
        return result;

    }
    public String[] getQuestion(){
        String[] result = new String[questions.size()];
        for(int i=0;i<questions.size();i++){
            result[i]=questions.get(i).getQuestion();
        }
        return result;
    }
    public String[] getAnswer(){
        String[] result = new String[questions.size()];
        for(int i=0;i<questions.size();i++){
            result[i]=questions.get(i).getAnswer();
        }
        return result;
    }
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
