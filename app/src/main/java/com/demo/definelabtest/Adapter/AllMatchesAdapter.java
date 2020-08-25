package com.demo.definelabtest.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.demo.definelabtest.Model.Venue;
import com.demo.definelabtest.R;

import java.util.ArrayList;

public class AllMatchesAdapter extends RecyclerView.Adapter<AllMatchesAdapter.CustomViewHolder> {

    private AllMatchesAdapter.OnItemClickListenerServiceList OnItemClickListenerServiceList;
    private ArrayList<Venue> venueArrayList;
    private Context context;

    public AllMatchesAdapter(ArrayList<Venue> venueArrayList, Context context) {
        this.venueArrayList = venueArrayList;
        this.context = context;
    }

    public interface OnItemClickListenerServiceList{
        void onItemClick(int position,String id,String name);
    }

    public void setOnItemClickListener(AllMatchesAdapter.OnItemClickListenerServiceList listener) {
        OnItemClickListenerServiceList = listener;
    }

    @NonNull
    @Override
    public AllMatchesAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_matches, parent, false);

        return new CustomViewHolder(itemView,OnItemClickListenerServiceList);

    }

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    public void onBindViewHolder(@NonNull AllMatchesAdapter.CustomViewHolder holder, int position) {

        holder.tv_name.setText(venueArrayList.get(position).getName());

        if(venueArrayList.get(position).getClicked()){
            holder.iv_star.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_star_golden));
        }else {
            holder.iv_star.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_star_grey));
        }

        /*if(!venueArrayList.get(position).getClicked()){

        }*/

    }

    @Override
    public int getItemCount() {
        return venueArrayList.size();
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_name;
        private ImageView iv_star;

        public CustomViewHolder(@NonNull View itemView, final AllMatchesAdapter.OnItemClickListenerServiceList listener) {
            super(itemView);

            tv_name = itemView.findViewById(R.id.tv_name);
            iv_star = itemView.findViewById(R.id.iv_star);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null){
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            updateUI(position);
                            listener.onItemClick(position,venueArrayList.get(position).getId(),venueArrayList.get(position).getName());
                        }
                    }
                }
            });

        }

    }

    private void updateUI(int position){

          if(venueArrayList.get(position).getClicked()) {
              venueArrayList.get(position).setClicked(false);
          }else {
              venueArrayList.get(position).setClicked(true);
          }

          notifyDataSetChanged();

    }
}
