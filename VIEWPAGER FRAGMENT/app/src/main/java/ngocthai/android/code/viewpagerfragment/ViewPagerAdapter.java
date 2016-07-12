package ngocthai.android.code.viewpagerfragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

/**
 * Created by NgocThai on 12/07/2016.
 */
public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    private ArrayList<Fragment> ls_fm;

    /*
    Get list fragment
     */
    public ArrayList<Fragment> getLs_fm() {
        return ls_fm;
    }

    /*
    Set list fragment
     */
    public void setLs_fm(ArrayList<Fragment> ls_fm) {
        this.ls_fm = ls_fm;
    }

    /*
        Constructor default
         */
    public ViewPagerAdapter(FragmentManager fm, ArrayList<Fragment> list_fm) {
        super(fm);
        this.ls_fm = list_fm;
    }

    /*
    Get pager title of viewpager
     */
    @Override
    public CharSequence getPageTitle(int position) {
        return "Pager " + (position + 1);
    }

    /*
    Get positon of list fragment
     */
    @Override
    public Fragment getItem(int position) {
        return ls_fm.get(position);
    }

    /*
    Get size of list fragment
     */
    @Override
    public int getCount() {
        return ls_fm.size();
    }
}
