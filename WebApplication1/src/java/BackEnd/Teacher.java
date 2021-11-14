package BackEnd;

import java.util.ArrayList;

public class Teacher extends User{

    private ArrayList<String> groupCreated;
    private ArrayList<String> adPurchased;
    private ArrayList<Test> ownedTest;
    public static final String PLATFORMNAME="PLATFORM";

    public Teacher(String username, String password, String email) {
        super(username, password, email);
        this.adPurchased = new ArrayList<>();
        this.groupCreated= new ArrayList<>();
        this.ownedTest = new ArrayList<>();
    }

    public ArrayList<String> getGroupCreated() {
        return groupCreated;
    }

    public ArrayList<Test> getOwnedTest() {
        return ownedTest;
    }

    public ArrayList<String> getAdPurchased() {
        return adPurchased;
    }
}
