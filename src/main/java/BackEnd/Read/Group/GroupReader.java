package BackEnd.Read.Group;

import BackEnd.Read.Reader;


public abstract class GroupReader extends Reader {
    protected final String TABLE = "STUDYGROUP";
    protected final String ID = "id";
    protected final String NAME = "name";
    protected final String CREATOR = "creator";
    protected final String STUDENTS = "students";
    protected final String POST = "posts";
    protected final String TESTS = "testID";

    protected final int IDCol = 1;
    protected final int NAMECol = 2;
    protected final int CREATORCol = 3;
    protected final int STUDENTSCol = 4;
    protected final int POSTCol = 5;
    protected final int TESTSCol = 6;







}


