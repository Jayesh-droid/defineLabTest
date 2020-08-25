package com.demo.definelabtest.ui.SavedMatches;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.demo.definelabtest.Adapter.AllMatchesAdapter;
import com.demo.definelabtest.Adapter.SavedMatchesAdapter;
import com.demo.definelabtest.DataBase.DatabaseHandler;
import com.demo.definelabtest.Model.SQLSavedMatchesModel;
import com.demo.definelabtest.R;

import java.util.ArrayList;
import java.util.List;


public class SavedMatchesFragment extends Fragment {

    private DatabaseHandler db;
    private RecyclerView rv_saved_matches;
    private SavedMatchesAdapter savedMatchesAdapter;

    public SavedMatchesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_saved_matches, container, false);

        rv_saved_matches = view.findViewById(R.id.rv_saved_matches);

        db = new DatabaseHandler(getActivity());

        // Reading all matches
        Log.d("Reading: ", "Reading all matches..");
        List<SQLSavedMatchesModel> sqlSavedMatchesModels = db.getAllSQLSavedMatchesModels();

        for (SQLSavedMatchesModel cn : sqlSavedMatchesModels) {
            String log = "Id: " + cn.getId() + " ,Name: " + cn.getName();
            // Writing Contacts to log
            Log.d("Name: ", log);
        }

        savedMatchesAdapter = new SavedMatchesAdapter((ArrayList<SQLSavedMatchesModel>) sqlSavedMatchesModels, getActivity());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        rv_saved_matches.setLayoutManager(linearLayoutManager);
        rv_saved_matches.setAdapter(savedMatchesAdapter);

        return view;
    }
}