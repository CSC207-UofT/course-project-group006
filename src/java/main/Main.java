import java.util.Scanner;

public class Main extends User{

    public Main(String username, String password, String email){
        super(username, password, email);
    }

    public static void main(String[] args) {
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
            Main user = new Main(names, passwords, emails);
            user.setEmail(emails);
            user.setUsername(names);
            user.setPassword(passwords);

            if (ide.equals("S")){
                System.out.println("Please do the following quiz to let us know your level of study.");
                System.out.println("Your score is " + " and your level of study is ");
            }

        }

        if (ide.equals("T")){
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

