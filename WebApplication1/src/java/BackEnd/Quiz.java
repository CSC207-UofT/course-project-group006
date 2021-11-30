package BackEnd;

import BackEnd.Entities.Test;
import BackEnd.Entities.QuestionTypes.Question;

import java.util.List;

public class Quiz extends Test {

    /**
     * Construct a quiz giving its name, time limit, author and
     * price
     *
     * @param name the name of the quiz
     * @param timeLimit the time limit of the quiz
     * @param author the author of the quiz
     * @param price the price this quiz will be sold
     */
    
    public Quiz(String name, int timeLimit, int author, int price){
        super(name,timeLimit, author, price);
    }

    /**
     * Generate a random word translation question for users giving a list of word
     *
     * @param input the list of word that the question will contain
     */
    private void autoGenerateTranslationQuestion(List<Word> input){
        for(Word w:input){
            StringBuilder ans = new StringBuilder();
            for(String s: w.getMeaning()){
                if(s != null) {
                    ans.append(s).append(",");
                }
            }
            this.questions.add(new Question(w.getSpelling(), ans.toString(), w.getLevel()));
        }
    }

    /**
     * Generate a diagnostic quiz for new users
     *
     * @param input the list of word that this build this diagnostic quiz
     * @return the quiz created
     */
    public static Quiz diagnostic(List<Word> input){
        Quiz q =  new Quiz("diagnostic", input.size()*3,-1,0);
        q.autoGenerateTranslationQuestion(input);
        return q;
    }

    /**
     * Get the name of the quiz
     * @return the name of the quiz
     */

    public String getname(){
        return this.name;
    }

    /**
     * Get the time limit of the quiz
     * @return time limit of the quiz
     */

    public int gettimelimit(){
        return this.timeLimit;
    }
}
