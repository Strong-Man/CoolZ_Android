package activities;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.view.ViewGroup;

import com.cool.zpw10018.activities.R;

public class MainActivity extends BaseActivity {

    private DrawerLayout mDrawerLayout;
    private ViewGroup mContentLayout;
    private NavigationView mNavigationView;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mContentLayout = (ViewGroup) findViewById(R.id.main_content_view);
        mNavigationView = (NavigationView) findViewById(R.id.main_nav_view);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

}
