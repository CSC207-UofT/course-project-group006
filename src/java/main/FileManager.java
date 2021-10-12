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

    public List<User> readUsers(String fileName){
        List<User> result = new ArrayList<>();

        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line = bufferedReader.readLine();
            while (line != null) {
                String[] splited = line.split("-");
                User tempU;
                if(splited[3].equals(Constant.STUDENT)){
                    tempU = new Student(splited[0],splited[1], splited[2]);
                }
                else {
                    tempU = new Teacher(splited[0],splited[1], splited[2]);
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

    public List<Question> readQuestions(String fileName) {
        List<Question> result = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line = bufferedReader.readLine();
            while (line != null) {
                String[] splited = line.split("-");
                Question tempQ = new Question(splited[0], splited[1], Integer.parseInt(splited[2]));//check error
                result.add(tempQ);
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


    public HashMap<Word, Integer> readWordFile(String fileName) {
        HashMap<Word, Integer> result = new HashMap<>();
        try {
            FileReader fileReader = new FileReader(fileName);
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

}
