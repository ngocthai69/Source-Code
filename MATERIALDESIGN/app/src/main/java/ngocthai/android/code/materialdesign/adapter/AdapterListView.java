package ngocthai.android.code.materialdesign.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import ngocthai.android.code.materialdesign.R;
import ngocthai.android.code.materialdesign.custom.ContactListView;

/**
 * Created by NgocThai on 01/08/2016.
 */
public class AdapterListView extends BaseAdapter {

    private ArrayList<ContactListView> listData;
    private LayoutInflater inflater;
    public int i;

    public AdapterListView(ArrayList<ContactListView> listData, Context context) {
        this.listData = listData;
        this.inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }

    public ArrayList<ContactListView> getListData() {
        return listData;
    }

    public void setListData(ArrayList<ContactListView> listData) {
        this.listData = listData;
    }

    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public ContactListView getItem(int position) {
        return listData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;
        ViewHolder holder = null;

        if (view == null) {
            view = inflater.inflate(R.layout.layout_list_view, parent, false);

            holder = new ViewHolder();
            holder.mName = (TextView) view.findViewById(R.id.list_layout).findViewById(R.id.txtName);
            holder.mHeader = (TextView) view.findViewById(R.id.list_layout).findViewById(R.id.txtHeader);
            holder.mTitle = (TextView) view.findViewById(R.id.list_layout).findViewById(R.id.txtTitle);
            holder.mMinute = (TextView) view.findViewById(R.id.list_layout).findViewById(R.id.txtMinute);
            holder.imgStar = (ImageView) view.findViewById(R.id.list_layout).findViewById(R.id.imgStar);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        final ContactListView contact = getItem(position);

        holder.mName.setText(contact.getName());
        holder.mHeader.setText(contact.getHeader());
        holder.mTitle.setText(contact.getTitle());
        holder.mMinute.setText(contact.getMinute() + "");

        final boolean check = contact.isCheck();

        if (check) {
            holder.imgStar.setImageResource(R.drawable.ic_star_selected);
            i = 1;
        } else {
            holder.imgStar.setImageResource(R.drawable.ic_star);
            i = 0;
        }

        holder.imgStar.setTag(contact);
        final ViewHolder finalHolder1 = holder;

        holder.imgStar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (i % 2 == 0) {
                    ContactListView  c = (ContactListView) finalHolder1.imgStar.getTag();
                    c.setCheck(!check);
                    finalHolder1.imgStar.setImageResource(R.drawable.ic_star_selected);
                } else {
                    ContactListView  c = (ContactListView) finalHolder1.imgStar.getTag();
                    c.setCheck(!check);
                    finalHolder1.imgStar.setImageResource(R.drawable.ic_star);
                }
                i++;
            }
        });

        return view;
    }

    public class ViewHolder {
        TextView mName, mHeader, mTitle, mMinute;
        ImageView imgStar;
    }


}
