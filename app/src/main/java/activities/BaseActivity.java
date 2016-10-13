package activities;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by zpw on 2016/9/15.
 *
 * 在API22之前我们使用标题栏基本都是在ActionBarActivity的Activity中处理的，而API22之后，谷歌遗弃了ActionBarActivity，推荐我们也可以说是强制我们使用AppCompatActivity
 */

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        int layoutId = getLayoutId();
        setContentView(layoutId);
        initView();
    }


    public abstract int getLayoutId();

    public abstract void initView();
}
