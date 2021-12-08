package BackEnd.Interfaces;

import java.util.HashMap;

/**
 * The interface ReadAll
 */
public interface ReadAll extends GeneralReadWriter{

    HashMap<Integer, String> readAll();
}
