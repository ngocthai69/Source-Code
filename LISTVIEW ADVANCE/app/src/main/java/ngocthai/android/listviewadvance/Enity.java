package ngocthai.android.listviewadvance;

/**
 * Created by NgocThai on 23/05/2016.
 */
public class Enity {
    private int id;
    private int avatar;
    private String name, age, phone;

    public Enity(int id, int avatar, String name, String age, String phone) {
        this.id = id;
        this.avatar = avatar;
        this.name = name;
        this.age = age;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAvatar() {
        return avatar;
    }

    public void setAvatar(int avatar) {
        this.avatar = avatar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
