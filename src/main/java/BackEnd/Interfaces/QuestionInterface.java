
package BackEnd.Interfaces;

/**
 *
 * @author darcy
 */
public interface QuestionInterface {
    String getName();
    String getQuestion();
    String getAnswer();
    int score(String ans);
    int getMarks();
}
