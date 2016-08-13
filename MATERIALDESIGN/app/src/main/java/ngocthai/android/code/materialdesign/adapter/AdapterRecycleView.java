package ngocthai.android.code.materialdesign.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import ngocthai.android.code.materialdesign.R;
import ngocthai.android.code.materialdesign.custom.ContactRecycleView;

/**
 * Created by NgocThai on 05/08/2016.
 */
public class AdapterRecycleView extends RecyclerView.Adapter<AdapterRecycleView.ViewHolder> {

    private ArrayList<ContactRecycleView> listContactRecycleView; // list object
    public int i;

    /*
    Class support of recycle view
    ViewHolder : Tao bo nho dem de luu duoc dia chi cua rat nhieu cac doi tuong
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mName, mHeader, mTitle, mMinute;
        public ImageView imgStar;

        public ViewHolder(View v) {
            super(v);
            mName = (TextView) v.findViewById(R.id.txtName);
            mHeader = (TextView) v.findViewById(R.id.txtHeader);
            mTitle = (TextView) v.findViewById(R.id.txtTitle);
            mMinute = (TextView) v.findViewById(R.id.txtMinute);
            imgStar = (ImageView) v.findViewById(R.id.imgStar);
        }
    }

    /*
  Constructor default
    */
    public AdapterRecycleView(ArrayList<ContactRecycleView> ls) {
        this.listContactRecycleView = ls;
    }

    /*
    onCreateViewHolder : Ham de lay ra layout chua cac thuoc tinh duoc ViewHolder ho tro
    Sau moi lan tao lai goi lai ham nay de su dung. Custom hon la listview chi check null moi 1 lan
    Ham nay de dung cho ham onBindViewHolder de set gia tri cho no
     */
    @Override
    public AdapterRecycleView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view and set it to ViewHolder
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_recycle_view, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    /*
   Ham dung de xac dinh vi tri khi dang tao tung view
   Su khac biet voi listview la khong goi di goi lai ham nay nhieu lan
    */
    @Override
    public void onBindViewHolder(final AdapterRecycleView.ViewHolder holder, int position) {
        final ContactRecycleView contactRecycleView = listContactRecycleView.get(position);

        holder.mName.setText(contactRecycleView.getName());
        holder.mHeader.setText(contactRecycleView.getHeader());
        holder.mTitle.setText(contactRecycleView.getTitle());
        holder.mMinute.setText(contactRecycleView.getMinute() + "m");

        final boolean check = contactRecycleView.isCheck(); // gia tri check cua sao

        if (check) {
            holder.imgStar.setImageResource(R.drawable.ic_star_selected);
            i = 1;
        } else {
            holder.imgStar.setImageResource(R.drawable.ic_star);
            i = 0;
        }

        holder.imgStar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (i % 2 == 0) {
                    holder.imgStar.setImageResource(R.drawable.ic_star_selected);
                    contactRecycleView.setCheck(!check);
                } else {
                    holder.imgStar.setImageResource(R.drawable.ic_star);
                    contactRecycleView.setCheck(!check);
                }
                i++;
            }
        });


    }

    @Override
    public int getItemCount() {
        return listContactRecycleView.size();
    }
}
