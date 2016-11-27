package fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.cool.zpw10018.activities.R;

/**
 * Created by 123 on 2016/11/26.
 */

public class HomeFragment extends BaseFragment {
    private TextView mContentTv;


    public static final BaseFragment newInstance(Bundle bundle) {
        BaseFragment homeFragment = new HomeFragment();
        homeFragment.setArguments(bundle);
        return homeFragment;
    }
    @Override
    public void initViews(View view, Bundle argumentBundle) {
         mContentTv = (TextView) view.findViewById(R.id.tv_content);
    }

    @Override
    public int getLayoutId() {
        return R.layout.home_fragment_layout;
    }

}
