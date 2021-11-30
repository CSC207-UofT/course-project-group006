package BackEnd.Interfaces;

import java.util.List;

public interface GeneralReadWriter {
    List<String> readByID(int elementStructure, int type, int targetID);

    //List<String> readStringByName(int amountType, String target);

    List<String> readIntByName(int type, String targetName);

    List<String> readRow(int targetID);

    List<String> write(int type, List<String> info);



}
