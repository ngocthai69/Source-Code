package ngocthai.android.code.contentprovider.custom;

import java.io.Serializable;

/**
 * Created by NgocThai on 08/08/2016.
 */
public class Contact implements Serializable{
    private String id;
    private int avatar;
    private String name;
    private String number;

    public Contact(String id, int avatar, String name, String number) {
        this.id = id;
        this.avatar = avatar;
        this.name = name;
        this.number = number;
    }

    public Contact(String id, String name, String number) {
        this.id = id;
        this.name = name;
        this.number = number;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
