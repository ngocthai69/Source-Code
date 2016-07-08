package code.android.ngocthai.searchview.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import code.android.ngocthai.searchview.R;
import code.android.ngocthai.searchview.enity.ContactEnity;

/**
 * Created by NgocThai on 04/06/2016.
 */
public class ContactAdapter extends BaseAdapter {

    private ArrayList<ContactEnity> ls_data;
    private LayoutInflater inflater;

    private ImageView img_avatar;
    private TextView txt_name, txt_age, txt_phone;

    public ContactAdapter(ArrayList<ContactEnity> ls_data, Context context) {
        this.ls_data = ls_data;
        this.inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }

    public ArrayList<ContactEnity> getLs_data() {
        return ls_data;
    }

    public void setLs_data(ArrayList<ContactEnity> ls_data) {
        this.ls_data = ls_data;
    }

    @Override
    public int getCount() {
        return ls_data.size();
    }

    @Override
    public ContactEnity getItem(int position) {
        return ls_data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ContactEnity contact = getItem(position);

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.layout_item_lv, null);
        }

        img_avatar = (ImageView) convertView.findViewById(R.id.img_avatar);
        txt_name = (TextView) convertView.findViewById(R.id.txt_name);
        txt_age = (TextView) convertView.findViewById(R.id.txt_age);
        txt_phone = (TextView) convertView.findViewById(R.id.txt_phone);

        img_avatar.setImageResource(contact.getAvatar());
        txt_name.setText(contact.getName());
        txt_phone.setText(contact.getPhone());
        txt_age.setText(contact.getAge());

        return convertView;
    }
}
