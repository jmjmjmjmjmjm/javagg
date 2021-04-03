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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RankingFragment extends Fragment {
    private DrawerLayout mDrawerLayout;
    private ImageButton draw;
    private JoinItem joinItem;
    List<JoinData> dataInfo;
    StatusData statusData;
    ArrayList<String> statusInfo;

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
        statusInfo=new ArrayList<>();


        call.enqueue(new Callback<JoinItem>() {

            @Override
            public void onResponse(Call<JoinItem> call, Response<JoinItem> response) {
                joinItem = response.body();
                dataInfo = joinItem.entries;


                Log.d("MainActivity", ""+dataInfo.size());

                for (int i =0; i<dataInfo.size();i++) {
                    Log.d("몇번째?", "" + i);
                    Call<StatusData> call2 = apiInterface.getStatus(dataInfo.get(i).getSummonerId());
                    call2.enqueue(new Callback<StatusData>() {
                        @Override
                        public void onResponse(Call<StatusData> call, Response<StatusData> response) {
                            statusInfo.add(response.body().getProfileIconId());
                            Log.d("아이콘넘버", "" + response.body().getProfileIconId());
                        }

                        @Override
                        public void onFailure(Call<StatusData> call, Throwable t) {
                            Log.d("아이콘불러오기 이상해", "");
                        }
                    });
                }



                Collections.sort(dataInfo, new Comparator<JoinData>() {
                    @Override
                    public int compare(JoinData o1, JoinData o2) {
                        return Integer.compare(o1.getLeaguePoints(), o2.getLeaguePoints());  //숫자비교
                        //return o1.getLeaguePoints().compareTo(o2.getLeaguePoints());      // 문자비교
                    }
                });


                // 리사이클러뷰에 SimpleTextAdapter 객체 지정.
                RankedAdapter adapter = new RankedAdapter(getActivity().getApplicationContext(), dataInfo);
                recyclerView.setAdapter(adapter);

            }


            @Override
            public void onFailure(Call<JoinItem> call, Throwable t) {
                Log.d("MainActivity", t.toString());
            }
        });

        /*리사이클러뷰 끝*/

        return view;
    }


    public void drawL() {
        draw.setOnClickListener(v -> {
            mDrawerLayout.openDrawer(GravityCompat.START);
        });
    }
}