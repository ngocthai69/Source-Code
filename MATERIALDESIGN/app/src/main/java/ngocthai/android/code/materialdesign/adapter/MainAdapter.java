package ngocthai.android.code.materialdesign.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

/**
 * Created by NgocThai on 03/08/2016.
 */
public class MainAdapter extends FragmentStatePagerAdapter {

    private ArrayList<Fragment> list_fm = new ArrayList<>();
    private ArrayList<String> list_title = new ArrayList<>();

    /*
    Constructor default of adapter
     */
    public MainAdapter(FragmentManager fm) {
        super(fm);
    }

    /*
    Return position of list fragment
     */
    @Override
    public Fragment getItem(int position) {
        return list_fm.get(position);
    }

    /*
   Return size of list fragment
    */
    @Override
    public int getCount() {
        return list_fm.size();
    }

    /*
    Add tabs and title for list of view pager
     */
    public void addTabs(Fragment fm, String title) {
        list_fm.add(fm);
        list_title.add(title);
    }

    /*
    Get page title of tabs
     */
    @Override
    public CharSequence getPageTitle(int position) {
        return list_title.get(position);
    }
}
