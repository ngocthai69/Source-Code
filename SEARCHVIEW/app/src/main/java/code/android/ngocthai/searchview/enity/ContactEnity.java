package code.android.ngocthai.searchview.enity;

/**
 * Created by NgocThai on 04/06/2016.
 */
public class ContactEnity {
    private int id;
    private int avatar;
    private String name, phone, age;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public int getAvatar() {
        return avatar;
    }

    public void setAvatar(int avatar) {
        this.avatar = avatar;
    }

    public ContactEnity(int id, int avatar, String name, String phone, String age) {

        this.id = id;
        this.avatar = avatar;
        this.name = name;
        this.phone = phone;
        this.age = age;
    }
}
