package ngocthai.android.navtabsliding;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

/**
 * Created by NgocThai on 17/05/2016.
 */
public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    private ArrayList<Fragment> ls_fm = new ArrayList<>();
    private ArrayList<String> ls_title = new ArrayList<>();

    /*
    constructor default
     */
    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    /*
    Get position of list fragment
    */
    @Override
    public Fragment getItem(int position) {
        return ls_fm.get(position);
    }

    /*
    add fragment and title to list
     */
    public void addFragment(Fragment fragment, String title) {
        ls_fm.add(fragment);
        ls_title.add(title);
    }

    /*
    get size of list fragment
     */
    @Override
    public int getCount() {
        return ls_fm.size();
    }

    /*
    get page title on tab sliding
     */
    @Override
    public CharSequence getPageTitle(int position) {
        return ls_title.get(position);
    }
}
