package com.example.fang.walmartproject.homePage;
import com.example.fang.walmartproject.AppController;
import com.example.fang.walmartproject.R;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.fang.walmartproject.cart.ShoppingCartActivity;
import com.example.fang.walmartproject.login.LoginActivity;
import com.example.fang.walmartproject.profile.ProfileFragment;
import com.example.fang.walmartproject.wishList.WishListFragment;

public class HomePageActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, HomePageContract.HomeView {

    HomePagePresenter homePagePresenter;
    NavigationView navigationView;

    static String TAG = HomePageActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        this.setTitle("Home");

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        checkSignIn(navigationView);
        homePagePresenter = new HomePagePresenter(this);

        this.showShopList();

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG,"home restart");
        checkSignIn(navigationView);
    }

    private void checkSignIn(NavigationView navigationView) {
        int sign = AppController.getInstance().getSignFlag();
        Log.d(TAG,"sign flg" + sign);
        if(sign == 1 ){
            navigationView.getMenu().findItem(R.id.nav_sign).setVisible(false);
            navigationView.getMenu().findItem(R.id.nav_sign_out).setVisible(true);
        }
        else if(sign == 0){
            navigationView.getMenu().getItem(4).setVisible(true);
            navigationView.getMenu().getItem(5).setVisible(false);
        }
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home_page, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        else if(id == R.id.action_cart){
            homePagePresenter.onCartOpen();
        }

        return super.onOptionsItemSelected(item);
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_userprofile) {
            // Handle the camera action
            homePagePresenter.onProfileHandled();
        } else if (id == R.id.nav_shop) {
            homePagePresenter.onShopHandled();

        } else if (id == R.id.nav_my_order) {

        } else if (id == R.id.nav_wish){
            homePagePresenter.onWishListOpen();

        } else if (id == R.id.nav_sign) {
            homePagePresenter.onSignInHandled();
        }
        else if(id == R.id.nav_sign_out){
            homePagePresenter.onSignOutHandled();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void showSignInPage() {
        Intent intent = new Intent(HomePageActivity.this,LoginActivity.class);
        startActivity(intent);
    }

    @Override
    public void showProfile() {
        Log.d(TAG,"showing");
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.home_page_content,new ProfileFragment()).addToBackStack(null).commit();

    }

    @Override
    public void showShopList() {
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.home_page_content,new HomePageFragment()).addToBackStack(null).commit();
    }

    @Override
    public void showCart() {
        Intent intent = new Intent(HomePageActivity.this,ShoppingCartActivity.class);
        startActivity(intent);
    }

    @Override
    public void signOut() {
        AppController.getInstance().unSetSignFlag();
        Toast.makeText(this,"Log out!",Toast.LENGTH_SHORT).show();
        checkSignIn(navigationView);
    }

    @Override
    public void showWishList() {
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.home_page_content,new WishListFragment()).addToBackStack(null).commit();
    }


}
