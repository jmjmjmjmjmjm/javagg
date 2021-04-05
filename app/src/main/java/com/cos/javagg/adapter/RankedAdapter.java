package com.cos.javagg.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.cos.javagg.R;
import com.cos.javagg.dto.JoinData;
import com.cos.javagg.dto.StatusData;
import com.cos.javagg.dto.StatusDto;

import java.util.ArrayList;
import java.util.List;

public class RankedAdapter extends RecyclerView.Adapter<RankedAdapter.ViewHolder> {

    private Context c;
    private ArrayList<JoinData> mData;
    ImageView micon;

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView mrank;
        TextView mname;
        TextView mtier;
        TextView mlp;

        int i = 1;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mrank = itemView.findViewById(R.id.rank);
            mname = itemView.findViewById(R.id.summonerName);
            mtier = itemView.findViewById(R.id.tier);
            mlp = itemView.findViewById(R.id.leaguePoints);
            micon = itemView.findViewById(R.id.rank_icon);

        }
    }

    public RankedAdapter(Context c, List<JoinData> mData) {
        this.c = c;
        this.mData = (ArrayList<JoinData>) mData;

    }

    @NonNull
    @Override
    public RankedAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.ranked_item, parent, false);
        RankedAdapter.ViewHolder vh = new RankedAdapter.ViewHolder(view);

        return vh;
    }


    @Override
    public void onBindViewHolder(@NonNull RankedAdapter.ViewHolder holder, int position) {

        Glide
                .with(micon)
                .load("http://ddragon.leagueoflegends.com/cdn/11.7.1/img/profileicon/" +4021+ ".png")
                .centerCrop()
                .into(micon);

        holder.mname.setText("" + mData.get(position).getSummonerName()); // 유저이름
        holder.mlp.setText("" + mData.get(position).getLeaguePoints());  // 리그포인트
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

}