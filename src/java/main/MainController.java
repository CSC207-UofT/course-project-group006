import QuestionTypes.Question;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainController{// extends User{
    public TestManager testManager;
    public UserGroupManager userGroupManager;
    public WordManager wordManager;
    public FileManager fileManager;
    public UserManager userManager;
    public MainController(){
        testManager=new TestManager();
        userGroupManager=new UserGroupManager();
        wordManager=new WordManager(System.getProperty("user.dir")+"\\src\\java\\main\\Files\\Words");
        fileManager=new FileManager();
        userManager=new UserManager(new ArrayList<>());
    }

    //public MainController(String username, String password, String email){
        //super(username, password, email);
    //}
    public void run() {
        String currentUser;
        System.out.println("Are you a teacher or student? Enter 'T' for teacher or 'S' for student");
        Scanner identity = new Scanner(System.in);
        String ide= identity.nextLine();
        System.out.println("Are you a member? Enter 'Y' or 'N'");
        Scanner member = new Scanner(System.in);
        String mem= member.nextLine();
        if (mem.equals("Y")){
            while(true) {
                System.out.println("Please enter your username.");
                String userName = new Scanner((System.in)).nextLine();
                System.out.println("Please enter your password.");
                String password = new Scanner((System.in)).nextLine();
                if (userManager.loginWithUsername(userName, password)) {
                    currentUser = userName;
                    break;
                } else{
                    System.out.println("no such user exist, try again");
                }
            }
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
                currentUser=userManager.createStudent(names,passwords,emails);
                System.out.println("Please do the following quiz to let us know your level of study.");
                int mark=presentDiagnostic(currentUser);
                System.out.println("Your score is " +mark+ " and your level of study is "+mark/20);
            }else if (ide.equals("T")){
                currentUser=userManager.createTeacher(names,passwords,emails);
                System.out.println("Please enter the thing you want to do. Enter 'CT' for creating " +
                        "tests, 'CG' for crate group, or 'ST' for seeing the created tests"+"'SG' for seeing created group");
                Scanner want = new Scanner(System.in);
                String wants = want.nextLine();
                if (wants.equals("CT")){

                    //assign test id to student
                    System.out.println("What it the name for the test?");
                    String n = new Scanner(System.in).nextLine();
                    System.out.println("What it the time limit for the test?");
                    int t = Integer.parseInt(new Scanner(System.in).nextLine());
                    System.out.println("What it the name for the test?");
                    int p = Integer.parseInt(new Scanner(System.in).nextLine());
                    int currentTest = testManager.creatExame(n,t,currentUser,p);
                    boolean entering_question=true;
                    while (entering_question){
                        System.out.println("what is the question?");
                        String q = new Scanner(System.in).nextLine();
                        System.out.println("what is the answer?");
                        String a = new Scanner(System.in).nextLine();
                        System.out.println("How mach mark it worth?");
                        int m = Integer.parseInt(new Scanner(System.in).nextLine());
                        testManager.addQuestion(currentTest,q,a,m);
                        System.out.println("Do you like to add more question?, enter y for yes, other keys for no");
                        entering_question=new Scanner(System.in).nextLine().equals("y");
                    }
                }
                if (wants.equals("CG")){
                    System.out.println("what is the name?"); //什么什么.getGroupCreated in Teacher.java
                    String n = new Scanner(System.in).nextLine();
                    userGroupManager.creatGroup(currentUser,n);
                }
                if (wants.equals("SG")){
                    List<Integer> groups = userGroupManager.createdBy(currentUser);
                    for(int i:groups){
                        System.out.println(userGroupManager.getNameOfGroup(i));
                    }
                }
                if (wants.equals("ST")){
                    List<String> l=testManager.getOwnedTest(currentUser);
                    for(String s:l){
                        System.out.println(s); //什么什么.getOwnedTest in Teacher.java
                    }
                }
            }

        }


    }
    public int presentDiagnostic(String s){
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
            System.out.println(questions.get(i).getQuestion());
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


}

