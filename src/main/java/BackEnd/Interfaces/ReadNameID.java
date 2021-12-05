package BackEnd.Interfaces;

import java.util.List;

public interface ReadNameID extends GeneralReadWriter {

    public List<String> readIntByName(int type, String targetName);
}
