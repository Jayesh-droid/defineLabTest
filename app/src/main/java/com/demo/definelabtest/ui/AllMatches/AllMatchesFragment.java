package com.demo.definelabtest.ui.AllMatches;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.demo.definelabtest.Adapter.AllMatchesAdapter;
import com.demo.definelabtest.DataBase.DatabaseHandler;
import com.demo.definelabtest.Model.AllMatchesModelResponse;
import com.demo.definelabtest.Model.Response;
import com.demo.definelabtest.Model.SQLSavedMatchesModel;
import com.demo.definelabtest.Model.Venue;
import com.demo.definelabtest.R;
import com.demo.definelabtest.Utitlity.InternetConnection;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AllMatchesFragment extends Fragment {

    private Observer<AllMatchesModelResponse> responseObserver;
    private AllMatchesViewModel allMatchesViewModel;
    private RecyclerView rv_matches;
    private ArrayList<Venue> venueArrayList;
    private AllMatchesAdapter allMatchesAdapter;
    DatabaseHandler db;
    private ProgressDialog dialog;

    public AllMatchesFragment() {
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
        View view = inflater.inflate(R.layout.fragment_all_matches, container, false);

        rv_matches = view.findViewById(R.id.rv_matches);
        venueArrayList = new ArrayList<>();

        db = new DatabaseHandler(getActivity());

        allMatchesViewModel = new ViewModelProvider(this).get(AllMatchesViewModel.class);

        getMatchesData();

        return view;
    }

    private void setAdapter(){

        allMatchesAdapter = new AllMatchesAdapter(venueArrayList, getActivity());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        rv_matches.setLayoutManager(linearLayoutManager);
        rv_matches.setAdapter(allMatchesAdapter);

        allMatchesAdapter.setOnItemClickListener(new AllMatchesAdapter.OnItemClickListenerServiceList() {
            @Override
            public void onItemClick(int position,String id,String name) {

                Log.d("click data","position "+position+" Id :- "+id+" Name "+name);

                if(venueArrayList.get(position).getClicked()){
                    Log.d("Insert: ", "Inserting ..");
                    db.addMatch(new SQLSavedMatchesModel(id, name));
                }else {
                    Log.d("Delete: ","Deleting ..");
                    db.deleteMatch(new SQLSavedMatchesModel(id,name));
                }

            }
        });

    }

    private void getMatchesData() {


        if (InternetConnection.isInternetAvailable(requireActivity())) {
            dialog = new ProgressDialog(getActivity());
            dialog.setMessage("Please wait");
            dialog.setCancelable(false);
            dialog.show();

            if (responseObserver == null) {
                responseObserver = new Observer<AllMatchesModelResponse>() {
                    @Override
                    public void onChanged(AllMatchesModelResponse response) {


                        venueArrayList = new ArrayList<>();

                        Integer code = response.getMeta().getCode();

                        Log.e("ResponseCheck", String.valueOf(code));

                        venueArrayList = (ArrayList<Venue>) response.getResponse().getVenues();

                        List<SQLSavedMatchesModel> sqlSavedMatchesModels = db.getAllSQLSavedMatchesModels();

                        for (int i = 0; i < venueArrayList.size(); i++) {
                            for (SQLSavedMatchesModel matchesModel : sqlSavedMatchesModels) {
                                if (venueArrayList.get(i).getId().equalsIgnoreCase(matchesModel.getId())) {
                                    venueArrayList.get(i).setClicked(true);
                                }
                            }
                        }


                        setAdapter();

                      if (dialog != null) {
                        dialog.dismiss();
                    }

                    }
                };
            }

            allMatchesViewModel.getMatches().observe(getViewLifecycleOwner(), responseObserver);

        } else {
            Toast.makeText(getActivity(),"No Internet",Toast.LENGTH_LONG).show();
        }

    }

}