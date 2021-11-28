package BackEnd;

public class Word {


    private int level;
    private String spelling;
    private String[] meanings;

    /**
     * Construct a word giving its level and spelling
     * @param level the level of the word
     * @param spelling the spelling of the word
     */

    public Word(int level, String spelling) {
        this.level = level;
        this.spelling = spelling;
        this.meanings = new String[Constant.TOTALLAN];

    }

    /**
     * Add a different meaning of a word
     * @param s the meaning of the word
     * @param type the kind of language of this meaning
     */
    public void addMeaning(String s, int type) {
        this.meanings[type] = s;
    }

    /**
     *
     * @return the level of the word
     */

    public int getLevel() {
        return this.level;
    }

    /**
     *
     * @return the spelling of the word
     */

    public String getSpelling() {
        return this.spelling;
    }

    /**
     *
     * @return the list of meaning of the word
     */

    public String[] getMeaning() {
        return this.meanings;
    }

    /**
     * Get a certain meaning of a word
     * @param n the type of language
     * @return the meaning or not found
     */

    public String getMeaning(int n) {
        try {
            return this.meanings[n];
        } catch (IndexOutOfBoundsException e) {
            return "Not Found";
        }
    }

    /**
     *
     * @return the string form of a word with its meaning and spelling
     */

    public String toString() {
        return "level: " + this.level + ". " + this.spelling;
    }


}
