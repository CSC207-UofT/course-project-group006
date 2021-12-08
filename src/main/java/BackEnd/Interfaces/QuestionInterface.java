
package BackEnd.Interfaces;

/**
 *
 * @author darcy
 */

/**
 * The interface question interface
 */
public interface QuestionInterface {
    String getName();
    String getQuestion();
    String getAnswer();
    int score(String ans);
    int getMarks();
}
