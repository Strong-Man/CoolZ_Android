package fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by 123 on 2016/11/26.
 */

public abstract class BaseFragment extends Fragment {

    protected View mFragmentView;

    protected abstract void initViews(View view, Bundle argumentBundle);

    protected abstract int getLayoutId();


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(mFragmentView == null){
            mFragmentView = inflater.inflate(getLayoutId(), container, false);
            initViews(mFragmentView, getArguments());
        }

        return mFragmentView;
    }
}
