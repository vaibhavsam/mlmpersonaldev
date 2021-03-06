package project.eviox.mlm.mlmapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;

import project.eviox.mlm.mlmapp.activities.registration.MemberRegistration;
import project.eviox.mlm.mlmapp.epinGenration.EpinGenrator;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private LinearLayout Left_memberLayout,Right_memberLayout,Direct_memberLayout,Lead_capLinearLayout;
    private TextView Left_memberTextView,Right_memberTextView,Direct_memberTextView,paymentRequestTextView,E_walletTextView;
     int count = 0;
     String user_name,user_id,member_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
         user_name = getIntent().getStringExtra("user_name");
         user_id = getIntent().getStringExtra("user_id");
         member_name = getIntent().getStringExtra("member_name");

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        initComponent();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void initComponent() {
        Left_memberLayout = findViewById(R.id.member_left_linear);
        Right_memberLayout = findViewById(R.id.right_member_linear);
        Direct_memberLayout = findViewById(R.id.direct_member_linear);
        Lead_capLinearLayout = findViewById(R.id.leadCap_linear);
        Left_memberTextView = findViewById(R.id.left_member_count_tv);
        Right_memberTextView = findViewById(R.id.right_member_count);
        Direct_memberTextView = findViewById(R.id.direct_member_count_tv);
        paymentRequestTextView = findViewById(R.id.payment_request);
        E_walletTextView = findViewById(R.id.e_cash_text_view);
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
        getMenuInflater().inflate(R.menu.main, menu);
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

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_profile) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {
            Intent intent = new Intent(MainActivity.this, EpinGenrator.class);
            intent.putExtra("user_id",user_id);
            intent.putExtra("member_name",member_name);
            intent.putExtra("user_name",user_name);
            startActivity(intent);

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_orders) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void leftMember(View view) {
        String level = "L"+count;
        Intent intent = new Intent(MainActivity.this, MemberRegistration.class);
        intent.putExtra("level",level);
        startActivity(intent);
    }

    public void rightMember(View view) {
        String level = "R"+count;
        Intent intent = new Intent(MainActivity.this, MemberRegistration.class);
        intent.putExtra("level",level);
        startActivity(intent);
    }

    public void directMember(View view) {
        String level = "D"+count;
        Intent intent = new Intent(MainActivity.this, MemberRegistration.class);
        intent.putExtra("level",level);
        startActivity(intent);
    }
}
