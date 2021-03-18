package com.cos.javagg.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.cos.javagg.R;
import com.cos.javagg.SearchName;

public class SearchFragment extends Fragment {

    private DrawerLayout mDrawerLayout;
    private ImageButton draw;
    private Vibrator vibrator;
    private ImageButton btn1;
    private Button search_name;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search,container,false);

        mDrawerLayout=view.findViewById(R.id.drawerLayout);
        draw = view.findViewById(R.id.draw);
        vibrator = (Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);
        btn1=view.findViewById(R.id.img_button);
        search_name=view.findViewById(R.id.serarch_name);

        drawL();    // 드로우레이아웃
        lolclick(); // 롤 이미지클릭
        name();     // 소환사검색 클릭
        
        return view;
    }

    public void languge(){
        draw.setOnClickListener(v -> {

        });
    }


    public void lolclick() {
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vibrator.vibrate(100);
            }
        });
    }

    public void name(){
        search_name.setOnClickListener(v -> {
            getActivity().startActivity(new Intent(getActivity(), SearchName.class));
        });
    }

    public void drawL(){
        draw.setOnClickListener(v -> {
            mDrawerLayout.openDrawer(GravityCompat.START);
        });
    }
}