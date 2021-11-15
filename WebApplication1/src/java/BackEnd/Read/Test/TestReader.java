package Read.Test;

import Read.Reader;

public abstract class TestReader extends Reader {

    protected final String TABLE = "TEST";
    protected final String ID = "id";
    protected final String NAME = "name";
    protected final String AUTHOR = "author";
    protected final String DUE = "date";
    protected final String PRICE = "price";
    protected final String QUESTIONS = "questions";


    protected final int IDCol = 1;
    protected final int NAMECol = 2;
    protected final int AUTHORCol = 3;
    protected final int DUECol = 4;
    protected final int PRICECol = 5;
    protected final int QUESTIONSCol = 6;
}
