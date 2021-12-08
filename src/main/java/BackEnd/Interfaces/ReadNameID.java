package BackEnd.Interfaces;

import java.util.List;

/**
 * The interface ReadNameID
 */
public interface ReadNameID extends GeneralReadWriter {

    List<String> readIntByName(int type, String targetName);
}
