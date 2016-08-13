package ngocthai.android.code.materialdesign.custom;

/**
 * Created by NgocThai on 05/08/2016.
 */
public class ImageGrid {
    private int id;
    private int image;
    private String title;

    public ImageGrid(int id, int image, String title) {
        this.id = id;
        this.image = image;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
