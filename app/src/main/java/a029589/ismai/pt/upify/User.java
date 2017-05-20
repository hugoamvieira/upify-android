package a029589.ismai.pt.upify;

/**
 * Created by Spanker Desktop on 19/05/2017.
 */

public class User {

    /**
     * id : 5
     * email : auth2@auth.com
     * experience : 0
     * name : Test User
     * avatar_color : #FAFAFA
     */

    private int id;
    private String email;
    private int experience;
    private String name;
    private String avatar_color;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar_color() {
        return avatar_color;
    }

    public void setAvatar_color(String avatar_color) {
        this.avatar_color = avatar_color;
    }
}
