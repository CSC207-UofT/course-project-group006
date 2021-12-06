package BackEnd.Interfaces;

import java.util.List;

public interface ReadNameID extends GeneralReadWriter {

    List<String> readIntByName(int type, String targetName);
}
