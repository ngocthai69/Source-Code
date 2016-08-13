package ngocthai.android.code.contentprovider.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import ngocthai.android.code.contentprovider.R;
import ngocthai.android.code.contentprovider.custom.Contact;

/**
 * Created by NgocThai on 09/08/2016.
 */
public class ContactAdapter extends BaseAdapter {

    private ArrayList<Contact> listContact;
    private LayoutInflater inflater;

    //---Constructor default---
    public ContactAdapter(ArrayList<Contact> listContact, Context context) {
        this.listContact = listContact;
        this.inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }

    //---get list of contact---
    public ArrayList<Contact> getListContact() {
        return listContact;
    }

    //---set list of contact---
    public void setListContact(ArrayList<Contact> listContact) {
        this.listContact = listContact;
    }

    //---return size of list contact---
    @Override
    public int getCount() {
        return listContact.size();
    }

    //---return current position of list contact---
    @Override
    public Contact getItem(int position) {
        return listContact.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    //---return one by one view---
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;

        //---check convertview is null or not
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_contact, null);
            viewHolder = new ViewHolder();
            //---find view by id---
            viewHolder.avatar = (ImageView) convertView.findViewById(R.id.imgAvatar);
            viewHolder.txtName = (TextView) convertView.findViewById(R.id.txtName);
            viewHolder.txtPhone = (TextView) convertView.findViewById(R.id.txtPhone);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Contact current_contact = getItem(position);
        //---set text---
        viewHolder.txtName.setText(current_contact.getName());
        viewHolder.txtPhone.setText(current_contact.getNumber());

        return convertView;
    }

    //---class view holder---
    class ViewHolder {
        ImageView avatar;
        TextView txtName, txtPhone;
    }
}
