package BackEnd;

import java.util.List;

public interface ReadAll extends GeneralReadWriter{

    List<String> readAllByID(int type, int targetID);
}
