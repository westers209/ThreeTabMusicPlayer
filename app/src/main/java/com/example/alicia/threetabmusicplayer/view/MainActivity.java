package com.example.alicia.threetabmusicplayer.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.alicia.threetabmusicplayer.R;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;
    static final String STATE_FRAGMENT = "state_of_fragment";
    private static final String TAG = "MainActivity";

    private boolean classicDisplayed = false;
    private boolean popDisplayed = false;
    private boolean rockDisplayed = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextMessage = findViewById(R.id.message);
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        if(savedInstanceState != null){
            classicDisplayed = savedInstanceState.getBoolean(STATE_FRAGMENT);
            popDisplayed = savedInstanceState.getBoolean(STATE_FRAGMENT);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putBoolean(STATE_FRAGMENT,classicDisplayed);
        savedInstanceState.putBoolean(STATE_FRAGMENT,popDisplayed);
        savedInstanceState.putBoolean(STATE_FRAGMENT,rockDisplayed);
        super.onSaveInstanceState(savedInstanceState);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_classic:
                    if(!classicDisplayed){
                        displayClassicFragment();
                    }
                    if(popDisplayed){
                        closePopFragment();
                    }
                    if(rockDisplayed){
                        closeRockFragment();
                    }
                    return true;
                case R.id.navigation_pop:
                    if(classicDisplayed){
                        closeClassicFragment();
                    }
                    if(!popDisplayed){
                        displayPopFragment();
                    }
                    if(rockDisplayed){
                        closeRockFragment();
                    }
                    return true;
                case R.id.navigation_rock:
                    if(classicDisplayed){
                        closeClassicFragment();
                    }
                    if(popDisplayed){
                        closePopFragment();
                    }
                    if(!rockDisplayed){
                        displayRockFragment();
                    }
                    return true;
            }
            return false;
        }
    };

    public void displayClassicFragment(){
        ClassicFragment classicFragment = ClassicFragment.newInstance();

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.add(R.id.container,classicFragment).addToBackStack(null).commit();
        classicDisplayed = true;
        Log.d(TAG,"Classic Opened");
    }

    public void closeClassicFragment(){
        FragmentManager fragmentManager = getSupportFragmentManager();
        ClassicFragment classicFragment = (ClassicFragment) fragmentManager.findFragmentById(R.id.container);
        if(classicFragment != null){
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.remove(classicFragment).commit();
        }
        classicDisplayed = false;
        Log.d(TAG,"Classic Closed");
    }

    public void displayPopFragment(){
        PopFragment popFragment = PopFragment.newInstance();

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.add(R.id.container,popFragment).addToBackStack(null).commit();
        popDisplayed = true;
        Log.d(TAG,"Pop Opened");
    }

    public void closePopFragment(){
        FragmentManager fragmentManager = getSupportFragmentManager();
        PopFragment popFragment = (PopFragment) fragmentManager.findFragmentById(R.id.container);
        if(popFragment != null){
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.remove(popFragment).commit();
        }
        popDisplayed = false;
        Log.d(TAG,"Pop Closed");

    }

    public void displayRockFragment(){
        RockFragment rockFragment = RockFragment.newInstance();

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.add(R.id.container,rockFragment).addToBackStack(null).commit();
        rockDisplayed = true;
        Log.d(TAG,"Rock Opened");
    }

    public void closeRockFragment(){
        FragmentManager fragmentManager = getSupportFragmentManager();
        RockFragment rockFragment = (RockFragment) fragmentManager.findFragmentById(R.id.container);
        if(rockFragment != null){
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.remove(rockFragment).commit();
        }
        rockDisplayed = false;
        Log.d(TAG,"Rock Closed");

    }

}
