package com.cos.javagg;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.cos.javagg.fragment.ChampionFragment;
import com.cos.javagg.fragment.CommunityFragment;
import com.cos.javagg.fragment.LoginFragment;
import com.cos.javagg.fragment.MakePostFragment;
import com.cos.javagg.fragment.RankingFragment;
import com.cos.javagg.fragment.SearchFragment;

import com.cos.javagg.listener.OnBackPressedListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import java.util.List;

import info.androidhive.fontawesome.FontTextView;

public class MainActivity extends AppCompatActivity implements OnBackPressedListener {

    private static final String TAG = "MainActivity2";
    private Context mContext = MainActivity.this;
    private BottomNavigationView bottomNavigationView;
    private DrawerLayout mDrawerLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        mDrawerLayout = findViewById(R.id.drawerLayout);     // 드로우레이아웃


        //최초 화면 밑에 네비
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new SearchFragment()).commit();

        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            Fragment selectedFragment = null;

            switch (item.getItemId()) {
                case R.id.bottom_search:
                    selectedFragment = new SearchFragment();
                    break;
                case R.id.bottom_community:
                    selectedFragment = new CommunityFragment();
                    break;
                case R.id.bottom_champion:
                    selectedFragment = new ChampionFragment();
                    break;
                case R.id.bottom_ranking:
                    selectedFragment = new RankingFragment();
                    break;
                case R.id.bottom_login:
                    selectedFragment = new LoginFragment();
                    Toast.makeText(getApplicationContext(), "회원가입페이지", Toast.LENGTH_LONG).show();

                    // 액티비티 전환 코드
                    Intent intent = new Intent(getApplicationContext(),Join.class);
                    startActivity(intent);

                    break;

            }
            //fragmanet 바꿔치기
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
            return true;
        });

    }

}