package ngocthai.android.listviewadvance;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by NgocThai on 23/05/2016.
 */
public class MyAdapter extends BaseAdapter {

    private ArrayList<Enity> ls_data;
    private LayoutInflater inflater;

    private ImageView img_avatar;
    private TextView txt_name, txt_age, txt_phone;

    public MyAdapter(ArrayList<Enity> ls_data, Context context) {
        this.ls_data = ls_data;
        this.inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }

    public ArrayList<Enity> getLs_data() {
        return ls_data;
    }

    public void setLs_data(ArrayList<Enity> ls_data) {
        this.ls_data = ls_data;
    }

    /*
    return size of list
     */
    @Override
    public int getCount() {
        return ls_data.size();
    }

    /*
    return position of list
     */
    @Override
    public Enity getItem(int position) {
        return ls_data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    /*
    create view one by one
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Enity current = getItem(position);

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.layout_item_listview, null);
        }

        // find view by id
        img_avatar = (ImageView) convertView.findViewById(R.id.img_avatar);
        txt_age = (TextView) convertView.findViewById(R.id.txt_age);
        txt_name = (TextView) convertView.findViewById(R.id.txt_name);
        txt_phone = (TextView) convertView.findViewById(R.id.txt_phone);

        //set values
        img_avatar.setImageResource(current.getAvatar());
        txt_phone.setText(current.getPhone());
        txt_name.setText(current.getName());
        txt_age.setText(current.getAge());

        return convertView;
    }
}
