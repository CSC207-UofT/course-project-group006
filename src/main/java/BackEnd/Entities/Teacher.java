package BackEnd.Entities;

import java.util.ArrayList;

public class Teacher extends User{

    private ArrayList<Integer> groupCreated;
    private ArrayList<Integer> adPurchased;
    private ArrayList<Integer> ownedTest;
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
    public Teacher(String username, String password, String email,ArrayList<Integer> groupCreated, ArrayList<Integer> ownedTest){
        super(username, password, email);
        this.adPurchased = new ArrayList<>();
        this.groupCreated= groupCreated;
        this.ownedTest = ownedTest;
    }
    /**
     * Get the group this teacher has created
     */

    public ArrayList<Integer> getGroupCreated() {
        return groupCreated;
    }

    /**
     *Get the Test this teacher owned
     */

    public ArrayList<Integer> getOwnedTest() {
        return ownedTest;
    }

    /**
     *Get the ad this teacher owned
     */

    public ArrayList<Integer> getAdPurchased() {
        return adPurchased;
    }
}
