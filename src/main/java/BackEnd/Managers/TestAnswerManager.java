package BackEnd.Managers;

import BackEnd.Gateways.QuestionGateway;
import BackEnd.Gateways.TestGateway;
import BackEnd.Interfaces.GeneralReadWriter;


import java.util.ArrayList;
import java.util.List;

public class TestAnswerManager {
    private final GeneralReadWriter TestAnswerGate;

    public TestAnswerManager(GeneralReadWriter readWriter) {
        this.TestAnswerGate = readWriter;
    }

}

