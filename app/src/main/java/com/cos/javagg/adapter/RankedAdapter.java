package com.cos.javagg.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cos.javagg.Join;
import com.cos.javagg.R;
import com.cos.javagg.dto.JoinData;

import java.util.ArrayList;
import java.util.List;

public class RankedAdapter extends RecyclerView.Adapter<RankedAdapter.ViewHolder> {

    private Context c;
    private ArrayList<JoinData> mData;


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
        JoinData text = mData.get(position);
        // holder.mrank.setText("" + mData.get(position).getRank());
        holder.mname.setText("" + mData.get(position).getSummonerName());
        // holder.mtier.setText("" + mData.get(position).getRank());
        holder.mlp.setText("" + mData.get(position).getLeaguePoints());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


}
