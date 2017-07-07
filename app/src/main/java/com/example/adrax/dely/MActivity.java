package com.example.adrax.dely;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.adrax.dely.delivery.DeliveryOrder;
import com.example.adrax.dely.delivery.DeliveryOrderStatus;
import com.example.adrax.dely.fragments.FragmentAbout;
import com.example.adrax.dely.fragments.FragmentDelyShow;
import com.example.adrax.dely.fragments.FragmentFace;
import com.example.adrax.dely.fragments.FragmentGet;
import com.example.adrax.dely.fragments.FragmentOrder;
import com.example.adrax.dely.fragments.FragmentOrderShow;
import com.example.adrax.dely.fragments.FragmentTools;

import static com.example.adrax.dely.LoginActivity.user;


public class MActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    FragmentFace fface;
    FragmentOrder forder;
    FragmentGet fget;
    FragmentTools ftools;
    FragmentAbout fabout;
    FragmentDelyShow fdelyshow;
    FragmentOrderShow fordershow;
    // Fragments keeper
    Fragment mFragmentToSet = null;

    boolean addToStack = false;

    //static FragmentTransaction fTrans;
    public static int fragment_id=-1;

    public static DeliveryOrder[] orders;
    public static DeliveryOrder[] face_orders;
    public static DeliveryOrder face_delivery = null;
    public static int selected_id = 0;
    public static String delyDescription="Тест";
    public static String face_cur_order_text="Тест";
    public static String face_deliver_text = "На данный момент нет активных заказов"; // Текст окна заказчика в щачле

    ActionBar actBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_view);

        // ToolBar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        // Get a support ActionBar corresponding to this toolbar
        actBar = getSupportActionBar();
//getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_yourindicator);
        //So now depending upon what fragment you are in, you can change the icon of "up" button.

        /*toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Dely", Toast.LENGTH_SHORT).show();
            }
        }); */


        // Fragments Drawer
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        // Listener which helps to close Fragment correctly, in time
        drawer.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
            }
            @Override
            public void onDrawerOpened(View drawerView) {
            }
            @Override
            public void onDrawerStateChanged(int newState) {
            }
            @Override
            public void onDrawerClosed(View drawerView) {
                //Set your new fragment here
                if (mFragmentToSet != null) {
                    if (addToStack)
                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.container, mFragmentToSet)
                                .addToBackStack(null)
                                .commit();
                    else
                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.container, mFragmentToSet)
                                .commit();
                    mFragmentToSet = null;
                    addToStack = false;
                }
            }
        });
        toggle.syncState();

        // Navigation slide-panel
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        fface = new FragmentFace();
        forder = new FragmentOrder();
        fget = new FragmentGet();
        fabout = new FragmentAbout();
        ftools = new FragmentTools();
        fdelyshow = new FragmentDelyShow();
        fordershow = new FragmentOrderShow();
        // загружаем заказы/доставки
        orders_update();

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fface);
        transaction.commit();
    }
    //загрузка заказов/доставок
    public static void orders_update(){
        face_orders = user.currentOrders();
        face_delivery = user.currentDelivery();
    }

    // Oбновление доставки
    public static void update_face(){
        if (face_delivery!=null)
            if (user.orderStatus(face_delivery)!= DeliveryOrderStatus.DELIVERED && user.orderStatus(face_delivery)!= DeliveryOrderStatus.DELIVERY_DONE){
                StringBuilder sb = new StringBuilder();
                sb.append("Название: ");
                sb.append(face_delivery.getName());
                sb.append("\n");
                sb.append("Откуда: ");
                sb.append(face_delivery.getFrom());
                sb.append("\n");
                sb.append("Куда: ");
                sb.append(face_delivery.getTo());
                sb.append("\n");
                sb.append("Заказчик: ");
                sb.append(face_delivery.getCustomer());
                sb.append("\n");
                sb.append("Номер телефона: ");
                sb.append(face_delivery.getTelephoneNumber());
                sb.append("\n");
                sb.append("Оплата: ");
                sb.append(face_delivery.getPayment());
                sb.append("\n");
                if (!face_delivery.getCost().equals("")) {
                    sb.append("Аванс: ");
                    sb.append(face_delivery.getCost());
                    sb.append("руб. \n");
                }
                if (!face_delivery.getWeight().equals("")) {
                    sb.append("Вес: ");
                    sb.append(face_delivery.getWeight());
                    sb.append("\n");
                }
                if (!face_delivery.getSize().equals("")) {
                    sb.append("Размер: ");
                    sb.append(face_delivery.getSize());
                    sb.append("\n");
                }
                if (!face_delivery.getCode().equals("")) {
                    sb.append("Код домофона: ");
                    sb.append(face_delivery.getCode());
                    sb.append("\n");
                }
                if (!face_delivery.getEntrance().equals("")) {
                    sb.append("Подъезд: ");
                    sb.append(face_delivery.getEntrance());
                    sb.append("\n");
                }
                if (!face_delivery.getFloor().equals("")) {
                    sb.append("Этаж: ");
                    sb.append(face_delivery.getFloor());
                    sb.append("\n");
                }
                face_deliver_text = sb.toString();
            }
            else {
                face_deliver_text = "На данный момент нет активных заказов";
            }
    }

    public void Tostic() {
        Toast.makeText(getBaseContext(), "Hi", Toast.LENGTH_LONG).show();
    }



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {                                                 // Close NavBar if it opened
            drawer.closeDrawer(GravityCompat.START);
        } else if (getSupportFragmentManager().getBackStackEntryCount() > 0) {                                 // Clean stack of fragments
            getSupportFragmentManager().popBackStack();
        } else if (!drawer.isDrawerOpen(GravityCompat.START)) drawer.openDrawer(GravityCompat.START);   // Open NavBar if it closed
        // super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.m, menu);

        MenuItem refreshItem = menu.findItem(R.id.action_refresh);
        // SearchView searchView =
        //        (SearchView) MenuItemCompat.getActionView(searchItem);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here.
        switch (item.getItemId()) {
            case R.id.action_settings:
                // User chose the "Settings" item, show the app settings UI...
                return true;

            case R.id.action_refresh:
                if (fragment_id == R.id.frag_get_id) fget.update(this);
                else if (fragment_id == R.id.frag_face_id) update_face();
                Toast.makeText(getApplicationContext(),"Информация обновлена!", Toast.LENGTH_LONG)
                        .show();
                return true;
            case android.R.id.home: // we get back here


            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);
        }
    }


    public void DelyShowFragment(){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fdelyshow);
        transaction.addToBackStack(null);
        transaction.commit();

        fragment_id = 1050;
    }

    public void OrderShowFragment(){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fordershow);
        transaction.addToBackStack(null);
        transaction.commit();

        fragment_id = 5010;
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here

        int id = item.getItemId();
        // Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        // ActionBar bar = getActionBar()

        // Clean stack
        if (getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStack();

        }

        //actBar.setDisplayHomeAsUpEnabled(false); // hide '<-' from action bar
        fragment_id = id;

        if (id == R.id.frag_face_id) {
            mFragmentToSet = fface;
            // Handle the camera action
        } else if (id == R.id.frag_order_id) {
            //bar.hide();
            mFragmentToSet = forder;
        } else if (id == R.id.frag_get_id) {
            mFragmentToSet = fget;

        } else if (id == R.id.frag_tools_id) {
            mFragmentToSet = ftools;

        } else if (id == R.id.frag_about_id) {
            mFragmentToSet = fabout;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}