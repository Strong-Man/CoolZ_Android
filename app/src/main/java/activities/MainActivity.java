package activities;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.ViewGroup;

import com.cool.zpw10018.activities.R;

import adapter.RecyclerViewListAdapter;

public class MainActivity extends BaseActivity {

    private DrawerLayout mDrawerLayout;
    private ViewGroup mContentLayout;
    private NavigationView mNavigationView;
    private TabLayout mTabView;
    private RecyclerView mRecyclerView;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mContentLayout = (ViewGroup) findViewById(R.id.main_content_view);
        mNavigationView = (NavigationView) findViewById(R.id.main_nav_view);
        mTabView = (TabLayout) findViewById(R.id.tabs);
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_content);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        test();
    }



    private void test(){
        mTabView.addTab(mTabView.newTab().setText("1"));
        mTabView.addTab(mTabView.newTab().setText("2"));
        mTabView.addTab(mTabView.newTab().setText("3"));

        mRecyclerView.setAdapter(new RecyclerViewListAdapter(this));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    }
}
