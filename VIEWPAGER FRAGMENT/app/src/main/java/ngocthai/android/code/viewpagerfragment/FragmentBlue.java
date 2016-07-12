package ngocthai.android.code.viewpagerfragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentBlue extends Fragment {

    private View view_fm_blue;

    public FragmentBlue() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view_fm_blue = inflater.inflate(R.layout.fragment_fragment_blue, container, false);

        return view_fm_blue;
    }

}
