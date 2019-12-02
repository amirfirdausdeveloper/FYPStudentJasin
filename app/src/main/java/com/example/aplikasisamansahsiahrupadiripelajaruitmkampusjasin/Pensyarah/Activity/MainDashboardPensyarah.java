package com.example.aplikasisamansahsiahrupadiripelajaruitmkampusjasin.Pensyarah.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;

import com.example.aplikasisamansahsiahrupadiripelajaruitmkampusjasin.Pensyarah.Fragment.PensyarahProfilFragment;
import com.example.aplikasisamansahsiahrupadiripelajaruitmkampusjasin.Pensyarah.Fragment.PensyarahSamanFragment;
import com.example.aplikasisamansahsiahrupadiripelajaruitmkampusjasin.Pensyarah.Fragment.PensyarahSenaraiSamanFragment;
import com.example.aplikasisamansahsiahrupadiripelajaruitmkampusjasin.Pensyarah.Fragment.PensyarahUtamaFragment;
import com.example.aplikasisamansahsiahrupadiripelajaruitmkampusjasin.R;
import com.google.android.material.navigation.NavigationView;

public class MainDashboardPensyarah extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    NavigationView navigationView;
    public static String pensyarah_email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_dashboard_pensyarah);

        //SET HOME ICON
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("LAMAN UTAMA");

        //GET INTENT DARI LOGIN
        pensyarah_email = getIntent().getStringExtra("email");

        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close){

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
            }

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);

            }
        };
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        setFirstItemNavigationView();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
        }
    }

    private void setFirstItemNavigationView() {
        navigationView.setCheckedItem(R.id.nav_utama);
        navigationView.getMenu().performIdentifierAction(R.id.nav_utama, 0);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        Fragment fragment = null;

        if (id == R.id.nav_utama) {
            fragment = new PensyarahUtamaFragment();
            displaySelectedFragment(fragment);

        }else if (id == R.id.nav_profil) {
            fragment = new PensyarahProfilFragment();
            displaySelectedFragment(fragment);
        }else if (id == R.id.nav_saman) {
            fragment = new PensyarahSamanFragment();
            displaySelectedFragment(fragment);
        }else if (id == R.id.nav_senarai_saman) {
            fragment = new PensyarahSenaraiSamanFragment();
            displaySelectedFragment(fragment);
        }else if (id == R.id.nav_keluar) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void displaySelectedFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame, fragment);
        fragmentTransaction.commit();
    }
}
