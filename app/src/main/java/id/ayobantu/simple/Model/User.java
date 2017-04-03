package id.ayobantu.simple.Model;

/**
 * Created by fachrul on 4/2/17.
 */

public class User {
    int id;
    String username;
    String password;

    public User() {

    }

    public User(String username, String password) {

        this.username = username;
        this.password = password;
    }

    public User(int id, String username, String password) {

        this.id = id;
        this.username = username;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
