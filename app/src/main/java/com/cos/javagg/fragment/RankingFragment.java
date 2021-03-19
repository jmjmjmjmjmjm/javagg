package com.cos.javagg.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cos.javagg.R;
import com.cos.javagg.adapter.RankedAdapter;
import com.cos.javagg.dto.ApiService;
import com.cos.javagg.dto.JoinData;
import com.cos.javagg.dto.JoinItem;
import com.cos.javagg.dto.RetrofitClient;
import com.google.gson.JsonArray;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RankingFragment extends Fragment {
    private DrawerLayout mDrawerLayout;
    private ImageButton draw;
    private JoinItem joinItem;
    List<JoinData> dataInfo;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ranked,container,false);

        mDrawerLayout=view.findViewById(R.id.drawerLayout);
        draw = view.findViewById(R.id.draw);

        drawL();    // 드로우레이아웃


        /*연습*/

        ApiService apiInterface = RetrofitClient.getClient().create(ApiService.class);
        Call<JoinItem> call = apiInterface.getData();
        call.enqueue(new Callback<JoinItem>() {

            @Override
            public void onResponse(Call<JoinItem> call, Response<JoinItem> response) {
                joinItem = response.body();
                Log.d("MainActivity", joinItem.toString());
            }

            @Override
            public void onFailure(Call<JoinItem> call, Throwable t) {
                Log.d("MainActivity", t.toString());
            }
        });


        /*연습끝*/

        ArrayList<String> list = new ArrayList<>();     // 데이터담기
        for (int i=0; i<100; i++) {
            list.add(String.format("%d", i)) ;
        }

        // 리사이클러뷰에 LinearLayoutManager 객체 지정.
        RecyclerView recyclerView = view.findViewById(R.id.rank_rc) ;
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity())) ;

        // 리사이클러뷰에 SimpleTextAdapter 객체 지정.
        RankedAdapter adapter = new RankedAdapter(list) ;
        recyclerView.setAdapter(adapter) ;

        return view;
    }



    public void drawL(){
        draw.setOnClickListener(v -> {
            mDrawerLayout.openDrawer(GravityCompat.START);
        });
    }
}