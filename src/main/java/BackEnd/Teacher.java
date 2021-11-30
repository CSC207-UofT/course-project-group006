package BackEnd;

import java.util.ArrayList;

public class Teacher extends User{

    private ArrayList<String> groupCreated;
    private ArrayList<String> adPurchased;
    private ArrayList<Test> ownedTest;
    public static final String PLATFORMNAME="PLATFORM";

    /**
     * Construct a teacher giving username, password and email
     *
     *  @param username the username of this teacher account
     *  @param password the password of this teacher account
     *  @param email the email of this teacher account
     */
    public Teacher(String username, String password, String email) {
        super(username, password, email);
        this.adPurchased = new ArrayList<>();
        this.groupCreated= new ArrayList<>();
        this.ownedTest = new ArrayList<>();
    }

    /**
     * Get the group this teacher has created
     */

    public ArrayList<String> getGroupCreated() {
        return groupCreated;
    }

    /**
     *Get the Test this teacher owned
     */

    public ArrayList<Test> getOwnedTest() {
        return ownedTest;
    }

    /**
     *Get the ad this teacher owned
     */

    public ArrayList<String> getAdPurchased() {
        return adPurchased;
    }
}
