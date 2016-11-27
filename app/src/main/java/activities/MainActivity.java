package activities;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.ViewGroup;

import com.cool.zpw10018.activities.R;

import java.util.ArrayList;

import adapter.RecyclerViewListAdapter;
import fragment.BaseFragment;
import fragment.HomeFragment;
import utils.Utils;

public class MainActivity extends BaseActivity {

    private DrawerLayout mDrawerLayout;
    private ViewGroup mContentLayout;
    private NavigationView mNavigationView;
    private TabLayout mTabView;
    private ViewPager mContentVp;

    private ArrayList mContentList = new ArrayList();
    private ArrayList<BaseFragment> mFragmentList = new ArrayList<>();
    private FragmentPagerAdapter mViewPageAdapter;

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
        mContentVp = (ViewPager) findViewById(R.id.view_pager);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setFragmentsData();
    }


    ///现在还没用接口
    private void setFragmentsData(){
        mContentList.clear();
       for(int i = 0; i < 16; i++){
           String testContentItem = "模块" + i;
           mContentList.add(testContentItem);
       }

        createFragments(mContentList);
    }

    private void createFragments(ArrayList list){

         if(Utils.isListHavaData(list)){
             for(int i = 0; i < list.size(); i++){
                 Bundle bundle = new Bundle();
                 bundle.putString("textContent", (String)list.get(i));
                 mFragmentList.add(HomeFragment.newInstance(bundle));
             }
         }

        setViewPageData();
    }

    private void setViewPageData(){
        if(mViewPageAdapter == null){
            mViewPageAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
                @Override
                public Fragment getItem(int position) {
                    return mFragmentList.get(position);
                }

                @Override
                public int getCount() {
                    return mFragmentList.size();
                }
            };
        }
    }
}
