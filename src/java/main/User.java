import java.util.Objects;

public abstract class User{

    private String username;
    private String password;
    private String email;
    private int ID;

    public User(String username, String password, String email){
        this.username = username;
        this.password = password;
        this.email = email;
        this.ID=IDcreater.creat();
    }

    public User(){
        this.username = "guest";
        this.ID=IDcreater.creat();
    }

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

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "User:" + username;
    }
}
