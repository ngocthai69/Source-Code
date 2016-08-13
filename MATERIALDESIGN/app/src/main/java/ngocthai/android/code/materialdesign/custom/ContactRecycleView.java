package ngocthai.android.code.materialdesign.custom;

/**
 * Created by NgocThai on 01/08/2016.
 */
public class ContactRecycleView {

    private int id;
    private String name, header, title;
    private int minute;
    private boolean check;

    public ContactRecycleView() {
    }

    public ContactRecycleView(int id, String name, String header, String title, int minute, boolean ch) {
        this.id = id;
        this.name = name;
        this.header = header;
        this.title = title;
        this.minute = minute;
        this.check = ch;
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

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

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }
}
