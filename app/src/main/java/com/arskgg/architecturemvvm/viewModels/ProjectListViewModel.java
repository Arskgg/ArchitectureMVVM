package com.arskgg.architecturemvvm.viewModels;

import android.app.Application;

import com.arskgg.architecturemvvm.data.remote.model.Project;
import com.arskgg.architecturemvvm.repositories.ProjectRepository;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class ProjectListViewModel extends AndroidViewModel {

    private LiveData<List<Project>> projectList;

    public ProjectListViewModel(@NonNull Application application, String userName) {
        super(application);

        projectList = ProjectRepository.getInstance().getProjectList(userName);
    }

    public LiveData<List<Project>> getProjectList() {
        return projectList;
    }

}



