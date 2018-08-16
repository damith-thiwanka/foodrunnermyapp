package lk.electfast.thiwanka.foodrunner_myapp;


import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.MenuInflater;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import lk.electfast.thiwanka.foodrunner_myapp.fragments.GalleryFragment;
import lk.electfast.thiwanka.foodrunner_myapp.fragments.UserRegistrationFragment;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        GalleryFragment.OnFragmentInteractionListener,
        UserRegistrationFragment.OnFragmentInteractionListener {

    private GalleryFragment gallery;
    private UserRegistrationFragment userReg;


    // private ListView menuItems;
    ArrayAdapter<String> adapter;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.menuList)
    ListView menuItems;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //menuItems = findViewById(R.id.menuList);
        ArrayList<String> items = new ArrayList<>();
        items.addAll(Arrays.asList(getResources().getStringArray(R.array.menuItems)));

        adapter = new ArrayAdapter<>(
                HomeActivity.this,
                android.R.layout.simple_list_item_1,
                items
        );

        menuItems.setAdapter(adapter);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
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
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.search_menu, menu);
        MenuItem menuItem = menu.findItem(R.id.menuList);

        SearchView searchView = (SearchView) menuItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                adapter.getFilter().filter(s);
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
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

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        FragmentManager fragmentManager = getSupportFragmentManager();

        switch (id) {
            case R.id.nav_home:
                checkfragmentContainer();
                break;
            case R.id.nav_gallery:
                checkfragmentContainer();
                gallery = GalleryFragment.newInstance();
                fragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, gallery).addToBackStack(null).commit();
                break;
            case R.id.nav_userprof:
                checkfragmentContainer();
                userReg = UserRegistrationFragment.newInstance();
                fragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, userReg).addToBackStack(null).commit();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }



    private void checkfragmentContainer() {
        if (getSupportFragmentManager().findFragmentById(R.id.fragment_container) != null) {
            getSupportFragmentManager().popBackStack();
        }
    }



    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onFragmentInteraction(String url_m) {

        System.out.println(url_m);
        Bundle bundle = new Bundle();
        bundle.putString("largeUrl", url_m);
        //UserRegistrationFragment fragobj = new UserRegistrationFragment();

        checkfragmentContainer();
        FragmentManager fragmentManager = getSupportFragmentManager();
        userReg = UserRegistrationFragment.newInstance();
        userReg.setArguments(bundle);
        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container, userReg).addToBackStack(null).commit();



    }
}
