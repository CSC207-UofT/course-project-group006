import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class FileManager {


    public FileManager() {}

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
        } catch (IOException e){
            System.out.println("IO Exception");
        }
        return result;
    }


}
