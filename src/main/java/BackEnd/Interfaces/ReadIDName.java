package BackEnd.Interfaces;

import java.util.List;

/**
 * The interface ReadIDName
 */
public interface ReadIDName extends GeneralReadWriter {

        List<String> readByIDName(int elementStructure, int studentID, int type, int targetID);

}