package com.demo.definelabtest.ui.AllMatches;

import android.annotation.SuppressLint;
import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.demo.definelabtest.Model.AllMatchesModelResponse;
import com.demo.definelabtest.RetroFit.APIClient;
import com.demo.definelabtest.RetroFitInterface.APIInterface;

import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class AllMatchesViewModel extends AndroidViewModel {

    public AllMatchesViewModel(@NonNull Application application) {
        super(application);
    }

    MutableLiveData<AllMatchesModelResponse> MatchesAllMatchesModelResponse=new MutableLiveData<>();

    @SuppressLint("CheckResult")
    public LiveData<AllMatchesModelResponse> getMatches(){

        APIInterface client = APIClient.getInstance().create(APIInterface.class);

        client.getAreaData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<AllMatchesModelResponse>() {

                    @Override
                    public void onSuccess(AllMatchesModelResponse AllMatchesModelResponse) {
                        MatchesAllMatchesModelResponse.postValue(AllMatchesModelResponse);
                    }

                    @Override
                    public void onError(Throwable e) {
                        MatchesAllMatchesModelResponse.postValue(null);
                    }

                });

        return  MatchesAllMatchesModelResponse;

    }

}
