package BackEnd;

import QuestionTypes.Question;
import QuestionTypes.QuestionInterface;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class MainController {// extends User{
    public TestManager testManager;
    public UserGroupManager userGroupManager;
    public WordManager wordManager;
    public FileManager fileManager;
    public UserManager userManager;

    public MainController() {
        testManager = new TestManager();
        userManager = initiateUserManager();
        userGroupManager = initiateGroups(userManager);
        wordManager = new WordManager(System.getProperty("user.dir") + "\\src\\java\\main\\Files\\Words");
        fileManager = new FileManager();

    }

    //public MainController(String username, String password, String email){
    //super(username, password, email);
    //}
    public void run() {
        int currentUser = -1;
        System.out.println("Are you a teacher or student? Enter 'T' for teacher or 'S' for student");
        Scanner identity = new Scanner(System.in);
        String ide = identity.nextLine();
        System.out.println("Are you a member? Enter 'Y' or 'N'");
        Scanner member = new Scanner(System.in);
        String mem = member.nextLine();
        if (mem.equals("Y")) {
            while (true) {
                System.out.println("Please enter your username.");
                String userName = new Scanner((System.in)).nextLine();
                System.out.println("Please enter your password.");
                String password = new Scanner((System.in)).nextLine();
                if (userManager.loginWithUsername(userName, password)!=-1) {
                    //currentUser = userName;
                    break;
                } else {
                    System.out.println("no such user exist, try again");
                }
            }
            if(ide.equals("S")) {
                //presentGroup(currentUser);
            }else {
                teacherPart(currentUser);
            }
        }
        if (mem.equals("N")) {
            System.out.println("Please use your email address to sign up");
            Scanner email = new Scanner(System.in);
            String emails = email.nextLine();

            System.out.println("Please set your username.");
            Scanner name = new Scanner(System.in);
            String names = name.nextLine();

            System.out.println("Please set your password.");
            Scanner password = new Scanner(System.in);
            String passwords = password.nextLine();
            //Main user = new Main(names, passwords, emails);
            //user.setEmail(emails);
            //user.setUsername(names);
            //user.setPassword(passwords);


            if (ide.equals("S")) {
                //currentUser = userManager.createStudent(names, passwords, emails);

                System.out.println("Do you want to do a quiz to know your level of study? enter y for yes, other key other wise");
                String want = new Scanner(System.in).nextLine();
                if (want.equals("y")) {
                    int mark = presentDiagnostic(userManager.getUser(currentUser).getUsername());
                    System.out.println("Your score is " + mark + " and your level of study is " + mark / 20);
                }
                //presentGroup(currentUser);
              

            } else if (ide.equals("T")) {
                //currentUser = userManager.createTeacher(names, passwords, emails);
                teacherPart(currentUser);
            }
            


        }


    }


    public int presentDiagnostic(String s) {
        File file = new File("src/java/main/Files/Words");

        System.out.println(file.exists());
        List<Word> w = new ArrayList<>();
        for (int i = 0; i < Constant.TOTAL_LEVEL; i++) {

            w.addAll(wordManager.generateList(i, (20 / Constant.TOTAL_LEVEL)));
        }
        Quiz q = Quiz.diagnostic(w);
        ArrayList<QuestionInterface> questions = q.getQuestions();
        String[] answers = new String[q.getQuestions().size()];
        for (int i = 0; i < questions.size(); i++) {
            System.out.println(questions.get(i).getQuestion());
            Scanner ans = new Scanner(System.in);
            answers[i] = ans.nextLine();
        }
        Answer a = new Answer(q.Id, s, answers);
        int level = levelByScore(q.Autograde(answers));
        System.out.println("your level is" + level);
        return level;
    }

    public int levelByScore(int[] score) {
        int result = 0;
        for (int j : score) {
            System.out.println(j);
            result += j;
        }
        return result;
    }

    public UserGroupManager initiateGroups(UserManager userManager) {
        FileManager fm = new FileManager();
        List<Group> groups = new ArrayList<>(fm.readGroups(this.userManager, "src/java/main/Files/Group"));
        HashMap<Integer, Group> result = new HashMap<>();
        for (Group g : groups) {
            result.put(g.GetID(), g);
        }
        return new UserGroupManager(result);
    }

    public UserManager initiateUserManager() {
        FileManager fm = new FileManager();
        ArrayList<User> users = new ArrayList<>(fm.readUsers("src/java/main/Files/Users"));
        return new UserManager(users);

    }
    
    public void presentGroup(int currentUser){
        System.out.println("you have joined following group.");
        HashMap<Integer, String> groupJoined = userGroupManager.getJoinedGroup(currentUser);
        for (int i : groupJoined.keySet()) {
            System.out.println(groupJoined.get(i) + " with ID " + i);
        }
        System.out.println("if you like to join another group, enter 'j', if you like to quit a group, enter 'q'");
        String ans = new Scanner(System.in).nextLine();
        if (ans.equals("j")) {
            HashMap<Integer, String> groups = userGroupManager.getAllGroup();
            for (int i : groups.keySet()) {
                System.out.println(groups.get(i) + " with ID " + i);
            }
            System.out.println("Enter the ID for the group you want to join");
            int id = Integer.parseInt(new Scanner(System.in).nextLine());
            userGroupManager.addStudentToGroup(currentUser, id);
        } else if (ans.equals("q")) {
            System.out.println("Enter the ID for the group you want to quit");
            int id = Integer.parseInt(new Scanner(System.in).nextLine());
            userGroupManager.removeStudentFromGroup(currentUser, id);
        }
        
    }

    public void teacherPart(int currentUser) {
        //String currentUser = userManager.createTeacher("names", "passwords", "emails");
        boolean running = true;
        while (running) {
            System.out.println("Please enter the thing you want to do. Enter 'CT' for creating " +
                    "tests, 'CG' for crate group, or 'ST' for seeing the created tests" + "'SG' for seeing created group");
            Scanner want = new Scanner(System.in);
            String wants = want.nextLine();
            if (wants.equals("CT")) {
                //assign test id to student
                System.out.println("What it the name for the test?");
                String n = new Scanner(System.in).nextLine();
                System.out.println("What it the time limit for the test?");
                int t = Integer.parseInt(new Scanner(System.in).nextLine());
                System.out.println("What it the price for the test?");
                int p = Integer.parseInt(new Scanner(System.in).nextLine());
                int currentTest = testManager.creatExame(n, t, currentUser, p);
                boolean entering_question = true;
                while (entering_question) {
                    System.out.println("what is the question?");
                    String q = new Scanner(System.in).nextLine();
                    System.out.println("what is the answer?");
                    String a = new Scanner(System.in).nextLine();
                    System.out.println("How mach mark it worth?");
                    int m = Integer.parseInt(new Scanner(System.in).nextLine());
                    testManager.addQuestion(currentTest, q, a, m);
                    System.out.println("Do you like to add more question?, enter y for yes, other keys for no");
                    entering_question = new Scanner(System.in).nextLine().equals("y");
                }
            }
            if (wants.equals("CG")) {
                System.out.println("what is the name?"); //什么什么.getGroupCreated in Teacher.java
                String n = new Scanner(System.in).nextLine();
                userGroupManager.creatGroup(currentUser, n);
            }
            if (wants.equals("SG")) {
                List<Integer> groups = userGroupManager.createdBy(currentUser);
                for (int i : groups) {
                    System.out.println(userGroupManager.getNameOfGroup(i));
                }
            }
            if (wants.equals("ST")) {
                List<Integer> l = testManager.getOwnedTest(currentUser);
                for (Integer s : l) {
                    System.out.println(s); //什么什么.getOwnedTest in Teacher.java
                }
            }
            System.out.println("want exit? enter y");
            running = !new Scanner(System.in).nextLine().equals("y");
        }
    }

}

