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
import ngocthai.android.code.materialdesign.custom.ImageGrid;


/**
 * Created by NgocThai on 05/08/2016.
 */
public class AdapterGridRecycle extends RecyclerView.Adapter<AdapterGridRecycle.ViewHolder> {

    private ArrayList<ImageGrid> listContactRecycleView; // list object
    public int i;

    /*
   Class support of recycle view
   ViewHolder : Tao bo nho dem de luu duoc dia chi cua rat nhieu cac doi tuong
    */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mLike;
        public ImageView imgAvatar;

        public ViewHolder(View v) {
            super(v);
            mLike = (TextView) v.findViewById(R.id.txtLike);
            imgAvatar = (ImageView) v.findViewById(R.id.img_view);
        }
    }

    /*
 Constructor default
   */
    public AdapterGridRecycle(ArrayList<ImageGrid> ls) {
        this.listContactRecycleView = ls;
    }

    /*
    onCreateViewHolder : Ham de lay ra layout chua cac thuoc tinh duoc ViewHolder ho tro
    Sau moi lan tao lai goi lai ham nay de su dung. Custom hon la listview chi check null moi 1 lan
    Ham nay de dung cho ham onBindViewHolder de set gia tri cho no
     */
    @Override
    public AdapterGridRecycle.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view and set it to ViewHolder
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_grid_recycle, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(AdapterGridRecycle.ViewHolder holder, int position) {
        final ImageGrid imgGrid = listContactRecycleView.get(position);

        holder.mLike.setText(imgGrid.getTitle());
        holder.imgAvatar.setImageResource(imgGrid.getImage());

    }

    @Override
    public int getItemCount() {
        return listContactRecycleView.size();
    }
}
