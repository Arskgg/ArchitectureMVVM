package com.arskgg.architecturemvvm.viewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class ProjectListViewModelFactory implements ViewModelProvider.Factory {

    private Application application;
    private String userName;

    public ProjectListViewModelFactory(Application application, String userName){

        this.application = application;
        this.userName = userName;

    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new ProjectListViewModel(application, userName);
    }
}
