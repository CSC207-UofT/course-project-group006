package BackEnd.Interfaces;

import java.util.HashMap;

public interface ReadAll extends GeneralReadWriter{

    HashMap<Integer, String> readAll();
}
