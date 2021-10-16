import QuestionTypes.Question;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main extends User{
    public TestManager testManager;
    public UserGroupManager userGroupManager;
    public WordManager wordManager;
    public FileManager fileManager;
    public Main(){
        testManager=new TestManager();
        userGroupManager=new UserGroupManager();
        wordManager=new WordManager(System.getProperty("user.dir")+"\\src\\java\\main\\Files\\Words");
        fileManager=new FileManager();
    }

    public Main(String username, String password, String email){
        super(username, password, email);
    }
    public void main() {
        System.out.println("Are you a teacher or student? Enter 'T' for teacher or 'S' for student");
        Scanner identity = new Scanner(System.in);
        String ide= identity.nextLine();
        System.out.println("Are you a member? Enter 'Y' or 'N'");
        Scanner member = new Scanner(System.in);
        String mem= member.nextLine();
        if (mem.equals("Y")){
            System.out.println("Please enter your username and password.");
        }
        if (mem.equals("N")){
            System.out.println("Please use your email address to sign up");
            Scanner email = new Scanner(System.in);
            String emails= email.nextLine();

            System.out.println("Please set your username.");
            Scanner name = new Scanner(System.in);
            String names= name.nextLine();

            System.out.println("Please set your password.");
            Scanner password = new Scanner(System.in);
            String passwords= password.nextLine();
            //Main user = new Main(names, passwords, emails);
            //user.setEmail(emails);
            //user.setUsername(names);
            //user.setPassword(passwords);


            if (ide.equals("S")){
                userGroupManager.createStudent(names,passwords,emails);
                System.out.println("Please do the following quiz to let us know your level of study.");
                System.out.println("Your score is " + " and your level of study is ");
            }else if (ide.equals("T")){
                userGroupManager.createTeacher(names,passwords,emails);
                System.out.println("Please enter the thing you want to do. Enter 'UT' for uploading " +
                        "tests, 'AS' for seeing assigned students, or 'CT' for seeing the created tests");
                Scanner want = new Scanner(System.in);
                String wants = want.nextLine();
                if (wants.equals("UT")){

                    //assign test id to student
                    System.out.println("Do you want to mark the test automatically? Enter 'Y' or 'N'");
                    Scanner mark = new Scanner(System.in);
                    String marks = mark.nextLine();
                    if (marks.equals("Y")){

                    }
                    if (marks.equals("N")){

                    }
                }
                if (wants.equals("AS")){
                    System.out.println(); //什么什么.getGroupCreated in Teacher.java
                }
                if (wants.equals("CT")){
                    System.out.println(); //什么什么.getOwnedTest in Teacher.java
                }
            }

        }


    }
    public int presentDiagnostic(Student s){
        File file= new File(System.getProperty("user.dir")+"\\src\\java\\main\\Files\\Words");
        System.out.println(file.exists());
        List<Word> w = new ArrayList<>();
        for(int i=0; i<Constant.TOTAL_LEVEL;i++){

            w.addAll(wordManager.generateList(i,(20/Constant.TOTAL_LEVEL)));
        }
        Quiz q =Quiz.diagnostic(w);
        ArrayList<Question> questions  = q.getQuestions();
        String[] answers = new String[q.getQuestions().size()];
        for(int i=0; i<questions.size();i++){
            System.out.println(questions.get(i).getQuestion()+" answer is "+questions.get(i).getAnswer());
            Scanner ans = new Scanner(System.in);
            answers[i]= ans.nextLine();
        }
        Answer a = new Answer(q,s,answers);
        int level = levelByScore(a.Autograde());
        System.out.println("your level is"+level);
        return level;
    }
    public int levelByScore(int[] score){
        int result = 0;
        for (int j : score) {
            System.out.println(j);
            result += j;
        }
        return result;
    }
    public static void main(String[] args){
        new Main().presentDiagnostic(new Student("","",""));
    }

}
