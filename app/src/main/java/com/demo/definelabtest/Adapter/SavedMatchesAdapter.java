package com.demo.definelabtest.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.demo.definelabtest.Model.SQLSavedMatchesModel;
import com.demo.definelabtest.R;

import java.util.ArrayList;

public class SavedMatchesAdapter extends RecyclerView.Adapter<SavedMatchesAdapter.CustomViewHolder> {

    private ArrayList<SQLSavedMatchesModel> sqlSavedMatchesModelArrayList;
    private Context context;

    public SavedMatchesAdapter(ArrayList<SQLSavedMatchesModel> sqlSavedMatchesModelArrayList, Context context) {
        this.sqlSavedMatchesModelArrayList = sqlSavedMatchesModelArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public SavedMatchesAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_saved_matches, parent, false);

        return new SavedMatchesAdapter.CustomViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SavedMatchesAdapter.CustomViewHolder holder, int position) {
            holder.tv_name.setText(sqlSavedMatchesModelArrayList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return sqlSavedMatchesModelArrayList.size();
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_name;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_name = itemView.findViewById(R.id.tv_name);
        }
    }
}
