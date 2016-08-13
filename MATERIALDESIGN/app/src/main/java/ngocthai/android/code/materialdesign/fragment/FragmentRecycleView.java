package ngocthai.android.code.materialdesign.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

import ngocthai.android.code.materialdesign.R;
import ngocthai.android.code.materialdesign.adapter.AdapterRecycleView;
import ngocthai.android.code.materialdesign.custom.ContactRecycleView;
import ngocthai.android.code.materialdesign.custom.DividerItemDecoration;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentRecycleView extends Fragment {

    private View view_recycle_view;
    private RecyclerView mRecycleView;
    private ArrayList<ContactRecycleView> listContactRecycleView;
    private AdapterRecycleView adapter;

    public FragmentRecycleView() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view_recycle_view = inflater.inflate(R.layout.fragment_recycle_view, container, false);

        mRecycleView = (RecyclerView) view_recycle_view.findViewById(R.id.my_recycle_view);

        /*
        Lam cho size cua recycle view luon co dinh khong thay doi khi ma xoa item cua no di
         */
        mRecycleView.setHasFixedSize(true);

        listContactRecycleView = CreateListData();

        adapter = new AdapterRecycleView(listContactRecycleView);

        // Horizontal layout
//        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);

//        StaggeredGridLayoutManager sgm = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);

        GridLayoutManager glm = new GridLayoutManager(getActivity(), 2);
        mRecycleView.setLayoutManager(layoutManager);
        mRecycleView.setItemAnimator(new DefaultItemAnimator());

        // Them diveder cho recycleview bang cach ve canvas va path
        mRecycleView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        mRecycleView.setAdapter(adapter);

        mRecycleView.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), mRecycleView, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
                ContactRecycleView c = listContactRecycleView.get(position);
                Toast.makeText(getActivity(), "" + c.getId(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        return view_recycle_view;
    }


    /*
   Create data for list contact
    */
    private ArrayList<ContactRecycleView> CreateListData() {
        ArrayList<ContactRecycleView> ls = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            ls.add(new ContactRecycleView(i, "Hoang Ngoc Thai " + i, "Header " + i, "I'll be in your neighborhood doing extras " + i, 15 + i, false));
        }
        return ls;
    }

    /*
    TODO TAO INTERFACE DE BAT SU KIEN
     */
    public interface ClickListener {
        void onClick(View view, int position);

        void onLongClick(View view, int position);
    }

    public static class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

        /*
        Lop thong bao cho nguoi dung su kien xay ra tren man hinh
         */
        private GestureDetector gestureDetector;
        private ClickListener clickListener;

        public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final ClickListener clickListener) {
            this.clickListener = clickListener;
            gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                    if (child != null && clickListener != null) {
                        clickListener.onLongClick(child, recyclerView.getChildPosition(child));
                    }
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {

            View child = rv.findChildViewUnder(e.getX(), e.getY());
            if (child != null && clickListener != null && gestureDetector.onTouchEvent(e)) {
                clickListener.onClick(child, rv.getChildPosition(child));
            }
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {
        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }

    }
}
