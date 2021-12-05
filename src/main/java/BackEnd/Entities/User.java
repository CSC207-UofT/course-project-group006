package BackEnd.Entities;


import java.util.Objects;

public abstract class User{

    private String username;
    private String password;
    private String email;
    public int ID;

    /**
     * Create a user giving username, password and email
     * @param username the username of this user
     * @param password the password of this user
     * @param email the email of this user
     */

    public User(String username, String password, String email){
        this.username = username;
        this.password = password;
        this.email = email;
    }


    /**
     * Test if two users are the same user
     * @param o the other user
     * @return true if they are same or false otherwise
     */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return ID == user.ID;
    }


    @Override
    public int hashCode() {
        return Objects.hash(ID);
    }

    /**
     * @return the username of a user
     */

    public String getUsername() {
        return username;
    }

    /**
     *
     * @return the password of a user
     */

    public String getPassword() {
        return password;
    }

    /**
     *
     * @return the email of the user
     */

    public String getEmail() {
        return email;
    }

    /**
     * Set the email of a user
     * @param email the email want to set
     */

    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * set the password of a user
     * @param password the password want to set
     */

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * set the username of a user
     * @param username the username want to set
     */

    public void setUsername(String username) {
        this.username = username;
    }

    /**
     *
     * @return Username in string form
     */
    @Override
    public String toString() {
        return "User:" + username;
    }

    /**
     *
     * @return the ID of a user
     */
    public int getID(){
        return ID;
    }
}
