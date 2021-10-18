public class Word {


    private int level;
    private String spelling;
    private String[] meanings;

    public Word(int level, String spelling) {
        this.level = level;
        this.spelling = spelling;
        this.meanings = new String[Constant.TOTALLAN];

    }

    public void addMeaning(String s, int type) {
        this.meanings[type] = s;
    }

    public int getLevel() {
        return this.level;
    }

    public String getSpelling() {
        return this.spelling;
    }

    public String[] getMeaning() {
        return this.meanings;
    }

    public String getMeaning(int n) {
        try {
            return this.meanings[n];
        } catch (IndexOutOfBoundsException e) {
            return "Not Found";
        }
    }

    public String toString() {
        return "level: " + this.level + ". " + this.spelling;
    }


}
