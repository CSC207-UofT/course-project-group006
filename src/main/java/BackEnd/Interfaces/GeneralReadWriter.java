package BackEnd.Interfaces;

import java.util.List;

/**
 * The interface General read writer.
 */
public interface GeneralReadWriter {
    /**
     * Read by id list.
     *
     * @param elementStructure the element structure
     * @param type             the type
     * @param targetID         the target id
     * @return the list of wanted info
     */
    List<String> readByID(int elementStructure, int type, int targetID);


    List<String> readRow(int targetID);

    /**
     * Write list.
     *
     * @param type the type
     * @param info the info
     * @return the list {id}
     */
    List<String> write(int type, List<String> info);

    /**
     * Has duplicate names boolean.
     *
     * @param table the table
     * @param name  the name
     * @return name exists T, dne F
     */
    boolean hasDuplicateNames(String table, String name);


}
