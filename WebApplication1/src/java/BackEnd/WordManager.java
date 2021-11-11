package BackEnd;

import java.util.*;

public class WordManager {
    
    private String fileName;
    private List<HashMap<Word, Integer>> allWords;
    private List<Integer> savedLevels;

    public WordManager(String fileName) {
        this.fileName = fileName;
        this.allWords = new ArrayList<>(10);//10 levels
        this.savedLevels = new ArrayList<>(10);
    }


    public List<Word> generateList(int level, int number) {
        Set<Word> wordListSet;
        //Haven't already have words with the desired level
        if (!this.savedLevels.contains(level)) {
            FileManager fileManager = new FileManager();
            HashMap<Word, Integer> wordListMap = fileManager.readWordFile(this.fileName);
            wordListSet = wordListMap.keySet();
        }
        //Have already read in a list with the desired level
        else {
            Collections.shuffle(allWords);
            wordListSet = allWords.get(0).keySet();
        }
        List<Word> wordList = new ArrayList<>(wordListSet);
        Collections.shuffle(wordList);
        return wordList.subList(0, number);
    }

}