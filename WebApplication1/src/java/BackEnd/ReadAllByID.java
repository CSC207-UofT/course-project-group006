package BackEnd;

import java.util.List;

public interface ReadAllByID extends GeneralReadWriter{

    List<String> readAllByID(int targetID);
}
