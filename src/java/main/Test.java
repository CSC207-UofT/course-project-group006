import QuestionTypes.Question;

import java.util.ArrayList;

public abstract class Test {
    private String name;
    private int timeLimit;
    private ArrayList<Question> questions;
    private String author;
    private int Id;
    private int price;
    public Test(String name, int timeLimit, String author, int price){
        this.name=name;
        this.timeLimit=timeLimit;
        this.questions=new ArrayList<>();
        this.author=author;
        this.price=price;
        this.Id=IDcreater.creat();
    }

    public int getPrice() {
        return price;
    }

    public int getId() {
        return Id;
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

    public ArrayList<Question> getQuestions(){
        return this.questions;
    }

}


//    Stores all the words in a test
//        Stores the number of the question in a test
//        Stores the time limit for the test
//        Stores whether the test should be supervised by a teacher
//        Stores the score of the test
//        Getter methods for all the above information
//        Author (who made the test)
