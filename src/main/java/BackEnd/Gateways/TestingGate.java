package BackEnd.Gateways;

import java.util.ArrayList;
import java.util.List;

public class TestingGate extends Gateway {

    @Override
    public List<String> readByID(int elementStructure, int type, int targetID) {
        return null;
    }


    @Override
    public List<String> readRow(int targetID) {
        return null;
    }

    @Override
    public List<String> write(int type, List<String> info) {
        return null;
    }
}
