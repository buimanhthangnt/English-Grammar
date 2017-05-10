package etspteam.android.englishgrammar.home_page;

import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

import etspteam.android.englishgrammar.R;
import etspteam.android.englishgrammar.practice.PracticeFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.in_from_left, R.anim.out_to_right);
        setContentView(R.layout.activity_home_page);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorPrimary)));
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimary));
        }

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().add(R.id.fragment, new LessonListFragment()).commit();
        }
        navigationView.setCheckedItem(R.id.lesson);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.notification) {

        }
        return true;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        String mTitle = "";
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        Fragment fragment = new Fragment();
        final FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        int id = item.getItemId();
        switch (id) {
            case R.id.today:
                fragment = new TodayFragment();
                mTitle = "Hôm nay";
                break;
            case R.id.lesson:
                fragment = new LessonListFragment();
                mTitle = "Bài học ngữ pháp";
                break;
            case R.id.practice:
                fragment = new PracticeFragment();
                mTitle = "Luyện tập tổng hợp";
                break;
            case R.id.tip:
                fragment = new TipFragment();
                mTitle = "Mẹo nhỏ";
                break;
            case R.id.entertainment:
                fragment = new EntertainmentFragment();
                mTitle = "Giải trí";
                break;
            case R.id.favorite:
                fragment = new FavoriteFragment();
                mTitle = "Mục yêu thích";
                break;
            case R.id.feedback:
                fragment = new FeedbackFragment();
                mTitle = "Ý kiến đánh giá";
                break;
            case R.id.setting:
                fragment = new SettingFragment();
                mTitle = "Cài đặt";
                break;
            case R.id.about:
                fragment = new AboutFragment();
                mTitle = "Thông tin về ứng dụng";
                break;
        }
        ft.replace(R.id.fragment, fragment).commit();
        toolbar.setTitle(mTitle);
        return true;
    }

    public void setToolbarColor(int colorId) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(colorId)));
        }
    }

    public void setToolbarTitle(String s) {
        toolbar.setTitle(s);
    }
}
