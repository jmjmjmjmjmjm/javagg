package com.cos.javagg.fragment;

import android.os.Bundle;
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
import com.cos.javagg.dto.StatusData;
import com.cos.javagg.dto.StatusDto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import lombok.SneakyThrows;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RankingFragment extends Fragment {
    private DrawerLayout mDrawerLayout;
    private ImageButton draw;
    private JoinItem joinItem;
    List<JoinData> dataInfo = new ArrayList<JoinData>();
    ArrayList<StatusDto> statisdto = new ArrayList<StatusDto>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ranked, container, false);
        mDrawerLayout = view.findViewById(R.id.drawerLayout);
        draw = view.findViewById(R.id.draw);
        drawL();    // 드로우레이아웃


        /*리사이클러뷰 */

        ApiService apiInterface = RetrofitClient.getClient().create(ApiService.class);
        Call<JoinItem> call = apiInterface.getData();
        // 리사이클러뷰에 LinearLayoutManager 객체 지정.
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        mLayoutManager.setReverseLayout(true);  // 리사이클러뷰 반전
        mLayoutManager.setStackFromEnd(true);   // 리사이클러뷰 반전
        RecyclerView recyclerView = view.findViewById(R.id.rank_rc);
        recyclerView.setLayoutManager(mLayoutManager);


        call.enqueue(new Callback<JoinItem>() {
            @Override
            public void onResponse(Call<JoinItem> call, Response<JoinItem> response) {
                joinItem = response.body();
                dataInfo = joinItem.entries;
                Log.d("엔트리값", "" + dataInfo.get(299));

                Collections.sort(dataInfo, new Comparator<JoinData>() {
                    @Override
                    public int compare(JoinData o1, JoinData o2) {
                        return Integer.compare(o1.getLeaguePoints(), o2.getLeaguePoints());  //숫자비교
                        //return o1.getLeaguePoints().compareTo(o2.getLeaguePoints());      // 문자비교
                    }
                });
                new Thread(new Runnable() {
                    @SneakyThrows
                    @Override
                    public void run() {
                        for (int i = 0; i < 20; i++) {
                            if (dataInfo.get(i).getSummonerId()!=null) {
                                icon(dataInfo.get(i).getSummonerId());
                                Log.d("엔트리" + i, "" + dataInfo.get(i).getSummonerId());
                            }
                        }
                    }
                }).start();

                // 리사이클러뷰에 SimpleTextAdapter 객체 지정.
                RankedAdapter adapter = new RankedAdapter(getActivity().getApplicationContext(), dataInfo);
                recyclerView.setAdapter(adapter);
                /*리사이클러뷰 끝*/
            }

            @Override
            public void onFailure(Call<JoinItem> call, Throwable t) {
                Log.d("MainActivity", t.toString());
            }
        });

        return view;
    }

    ApiService apiInterface = RetrofitClient.getClient().create(ApiService.class);

    public void icon(String i) {

        if (i == null) {
            Log.d("스톱!", "!!!!!!!!!!!!!!!!!!!!!!!!");
        } else {
            Call<StatusData> call = apiInterface.getStatus(i);
            call.enqueue(new Callback<StatusData>() {
                @Override
                public void onResponse(Call<StatusData> call, Response<StatusData> response) {
                    if (response.body().getProfileIconId() == null) {
                        Log.d("스톱!", "!!!!!!!!!!!!!!!!!!!!!!!!");
                    } else {
                        statisdto.add(new StatusDto(i, response.body().getProfileIconId()));
                        Log.d("아이템몇번째?", "" + statisdto);
                    }
                }

                @Override
                public void onFailure(Call<StatusData> call, Throwable t) {
                    Log.d("아이콘 배열생성", "아이콘배열생성안됨");
                }
            });
        }
    }

    public void drawL() {
        draw.setOnClickListener(v -> {
            mDrawerLayout.openDrawer(GravityCompat.START);
        });
    }
}