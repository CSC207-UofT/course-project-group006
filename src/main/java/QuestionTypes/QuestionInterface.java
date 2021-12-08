/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package QuestionTypes;

/**
 *
 * @author darcy
 */

/**
 * The interface QuestionInterface
 */
public interface QuestionInterface {
    String getQuestion();
    String getAnswer();
    int score(String ans);
    int getMarks();
}
