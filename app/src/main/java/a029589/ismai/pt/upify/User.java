package a029589.ismai.pt.upify;

/**
 * Created by Spanker Desktop on 19/05/2017.
 */

public class User {


    /**
     * id : 5
     * email : auth2@auth.com
     * access_token : temptoken
     * experience : 600
     * name : Test User
     * avatar_color : #FAFAFA
     * avatar : tempavatar
     * level : 20
     * description : Software Engineer
     * purple_medals : 12
     * orange_medals : 36
     * green_medals : 82
     */

    private int id;
    private String email;
    private String access_token;
    private Double experience;
    private String name;
    private String avatar_color;
    private String avatar;
    private Double level;
    private String description;
    private int purple_medals;
    private int orange_medals;
    private int green_medals;

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

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public Double getExperience() {
        return experience;
    }

    public void setExperience(Double experience) {
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

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Double getLevel() {
        return level;
    }

    public void setLevel(Double level) {
        this.level = level;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPurple_medals() {
        return purple_medals;
    }

    public void setPurple_medals(int purple_medals) {
        this.purple_medals = purple_medals;
    }

    public int getOrange_medals() {
        return orange_medals;
    }

    public void setOrange_medals(int orange_medals) {
        this.orange_medals = orange_medals;
    }

    public int getGreen_medals() {
        return green_medals;
    }

    public void setGreen_medals(int green_medals) {
        this.green_medals = green_medals;
    }
}
