package com.example.codecentric.pma_movieapp;

import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.codecentric.pma_movieapp.adapters.DrawerListAdapter;
import com.example.codecentric.pma_movieapp.fragments.ActorFragment;
import com.example.codecentric.pma_movieapp.fragments.MovieFragment;
import com.example.codecentric.pma_movieapp.fragments.SeriesFragment;
import com.example.codecentric.pma_movieapp.model.MenuItem;

import java.util.ArrayList;

/**
 * Created by Aleksandar Ratkov on 23.5.16..
 */
public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;
    private RelativeLayout mDrawerPane;
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    private ArrayList<MenuItem> menuItems = new ArrayList<MenuItem>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        prepareMenu(menuItems);

        mTitle = mDrawerTitle = getTitle();
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mDrawerList = (ListView) findViewById(R.id.navList);

        // Populate the Navigtion Drawer with options
        mDrawerPane = (RelativeLayout) findViewById(R.id.drawerPane);
        DrawerListAdapter adapter = new DrawerListAdapter(this, menuItems);

        // set a custom shadow that overlays the main content when the drawer opens
        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
        mDrawerList.setAdapter(adapter);

        // enable ActionBar app icon to behave as action to toggle nav drawer
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final android.support.v7.app.ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setIcon(R.drawable.ic_launcher);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_drawer);
            actionBar.setHomeButtonEnabled(true);
        }

        mDrawerToggle = new ActionBarDrawerToggle(
                this,                  /* host Activity */
                mDrawerLayout,         /* DrawerLayout object */
                toolbar,  /* nav drawer image to replace 'Up' caret */
                R.string.drawer_open,  /* "open drawer" description for accessibility */
                R.string.drawer_close  /* "close drawer" description for accessibility */
        ) {
            public void onDrawerClosed(View view) {
//                getActionBar().setTitle(mTitle);
                getSupportActionBar().setTitle(mTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            public void onDrawerOpened(View drawerView) {
//                getActionBar().setTitle(mDrawerTitle);
                getSupportActionBar().setTitle("iReviewer");
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };
    }



    private void prepareMenu(ArrayList<MenuItem> mNavItems ){
        mNavItems.add(new MenuItem(getString(R.string.movies), getString(R.string.movies_long), R.drawable.ic_theater));
        mNavItems.add(new MenuItem(getString(R.string.series), getString(R.string.series_long), R.drawable.ic_sofa));
        mNavItems.add(new MenuItem(getString(R.string.actors), getString(R.string.actors_long), R.drawable.ic_action_person));
        mNavItems.add(new MenuItem(getString(R.string.settings), getString(R.string.settings_long), R.drawable.ic_action_settings));
    }

    /* The click listner for ListView in the navigation drawer */
    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItemFromDrawer(position);
        }
    }

    private void selectItemFromDrawer(int position) {
        if(position == 0){

            FragmentTransition.to(MovieFragment.newInstance(), this, false);

        }else if(position == 1){
            FragmentTransition.to(SeriesFragment.newInstance(), this, false);

        }else if(position == 2){
            FragmentTransition.to(ActorFragment.newInstance(), this, false);

        }else if(position == 3){
            Toast.makeText(getApplicationContext(),"3 toast",Toast.LENGTH_LONG).show();
            //..
        }else{
            Log.e("DRAWER", "Nesto van opsega!");
        }

        mDrawerList.setItemChecked(position, true);
        if(position != 5) // za sve osim za sync
        {
            setTitle(menuItems.get(position).getmTitle());
        }
        mDrawerLayout.closeDrawer(mDrawerPane);
    }

//    public boolean onOptionsItemSelected(android.view.MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//       /* if (id == R.id.action_settings) {
//            return true;
//        }*/
//
//        return super.onOptionsItemSelected(item);
//    }


}
