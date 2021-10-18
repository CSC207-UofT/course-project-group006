import QuestionTypes.Question;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class testTest {
    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test(timeout = 50)
    public void testquiz() {
        Question question = new Question("苹果", "apple", 1);
        Exam test1 = new Exam("term test 1", 60, "Jenny", 10);
        ArrayList<Question> array = new ArrayList<Question>();
        array.add(question);
        assertEquals(test1.getName(), "term test 1");
        assertEquals(test1.addQuestion(question), true);
        assertEquals(test1.getQuestions(),array);
    }

    @Test(timeout =  50)
    public void testdiagnostic() {
        Question question = new Question("苹果", "apple", 1);
        Question question2 = new Question("香蕉", "banana", 1);
        Word word0 = new Word(1, "apple");
        Word word1 = new Word(1, "banana");
        ArrayList<Word> words= new ArrayList<Word>();
        words.add(word0);
        word0.addMeaning("苹果",Constant.CHINESE);
        word1.addMeaning("香蕉",Constant.CHINESE);
        words.add(word1);
        Quiz q = Quiz.diagnostic(words);
        Answer a = new Answer(q,"",new String[]{"苹果","香蕉"});
        assertEquals(q.getname(), "diagnostic");
        assertEquals(q.gettimelimit(), 6);
        int[] result = a.Autograde();
        assertEquals(result[0]+result[1], 2);
    }

}
