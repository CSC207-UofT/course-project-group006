package BackEnd.Gateways;

import java.util.List;

public class QuestionGateway extends Gateway{
    @Override
    public List<String> readByID(int elementStructure, int type, int targetID) {
        return null;
    }

    @Override
    public List<String> readIntByName(int type, String targetName) {
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
