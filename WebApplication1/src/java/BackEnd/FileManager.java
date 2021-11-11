package BackEnd;

import QuestionTypes.Question;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FileManager {

    public FileManager() {
    }

    public List<User> readUsers(String fileName) {
        List<User> result = new ArrayList<>();

        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line = bufferedReader.readLine();
            while (line != null) {
                String[] splited = line.split("-");
                User tempU;
                if (splited[3].equals(Constant.STUDENT)) {
                    tempU = new Student(splited[0], splited[1], splited[2]);
                } else {
                    tempU = new Teacher(splited[0], splited[1], splited[2]);
                }
                result.add(tempU);
                line = bufferedReader.readLine();
            }
            bufferedReader.close();
            fileReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found");
        } catch (IOException e) {
            System.out.println("IO Exception");
        }
        return result;
    }

    public Test readTest(String fileName) {
        ArrayList<Question> questions = new ArrayList<>();
        Test t = null;
        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String name = bufferedReader.readLine();
            int time = Integer.parseInt(bufferedReader.readLine());
            String author = bufferedReader.readLine();
            int price = Integer.parseInt(bufferedReader.readLine());
            String line = bufferedReader.readLine();

            while (line != null) {
                String[] splited = line.split("-");
                Question tempQ = new Question(splited[0], splited[1], Integer.parseInt(splited[2]));//check error
                questions.add(tempQ);
                line = bufferedReader.readLine();
            }
            bufferedReader.close();
            fileReader.close();
            t = new Exam(name, time, author, price, questions);
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found");
        } catch (IOException e) {
            System.out.println("IO Exception");
        }

        return t;

    }


    public HashMap<Word, Integer> readWordFile(String fileName) {
        HashMap<Word, Integer> result = new HashMap<>();
        try {
            FileReader fileReader = new FileReader("src/java/main/Files/Words");
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line = bufferedReader.readLine();
            int type = Integer.parseInt(line.split(" ")[1]); //check error
            line = bufferedReader.readLine();
            int level = Integer.parseInt(line.split(" ")[1]); //check error
            line = bufferedReader.readLine();

            while (line != null) {
                String[] splited = line.split("\t");
                Word tempWord = new Word(level, splited[0]);
                tempWord.addMeaning(splited[1], type);
                result.put(tempWord, level);

                line = bufferedReader.readLine();
            }
            bufferedReader.close();
            fileReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found");
        } catch (IOException e) {
            System.out.println("IO Exception");
        }
        return result;
    }


    public List<Group> readGroups(UserManager userManager, String fileName) {
        List<Group> result = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader("src/java/main/Files/Group");
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line = bufferedReader.readLine();
            while (line != null) {
                String[] splited = line.split("-");
                int l = splited.length;
                String name = splited[0];
                int teacher = Integer.parseInt(splited[1]);
                int id = Integer.parseInt(splited[l - 2]);
                int[] students = new int[Group.MAXNUMBER];
                Student[] students1 = new Student[Group.MAXNUMBER];
                for (int i = 2; i < l - 2; i++) {
                    students[i - 2] = Integer.parseInt(splited[i]);
                }
                for (int i = 0; i < students.length; i++) {
                    if (userManager.getUser(students[i]) instanceof Student) {
                        students1[i] = (Student) userManager.getUser(students[i]);
                    }
                }
                String test = splited[l - 1].substring(1, splited[l - 1].length() - 1);
                String[] testArray = test.split(",");
                HashMap<Test, List<Answer>> tests = new HashMap<>();
                for (String s : testArray) {
                    Test test1 = readTest(s);
                    tests.put(test1, new ArrayList<Answer>());
                }

                Group tempG = new Group(teacher, name, students, id, tests);
                result.add(tempG);
                line = bufferedReader.readLine();
            }
            bufferedReader.close();
            fileReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found");
        } catch (IOException e) {
            System.out.println("IO Exception");
        }
        return result;
    }

}
