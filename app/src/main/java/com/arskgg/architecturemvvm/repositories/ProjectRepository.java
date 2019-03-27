package com.arskgg.architecturemvvm.repositories;

import com.arskgg.architecturemvvm.data.remote.GitHubApi;
import com.arskgg.architecturemvvm.data.remote.model.Project;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProjectRepository {

    private GitHubApi gitHubApi;

    private static ProjectRepository projectRepository;

    private ProjectRepository() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(GitHubApi.HTTPS_API_GITHUB_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        gitHubApi = retrofit.create(GitHubApi.class);
    }

    public synchronized static ProjectRepository getInstance() {

        if (projectRepository == null) {

            projectRepository = new ProjectRepository();
        }
        return projectRepository;
    }

    public LiveData<List<Project>> getProjectList(String userName) {

        final MutableLiveData<List<Project>> data = new MutableLiveData<>();

        gitHubApi.getProjectList(userName).enqueue(new Callback<List<Project>>() {
            @Override
            public void onResponse(Call<List<Project>> call, Response<List<Project>> response) {

                if (!response.isSuccessful())
                    return;

                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Project>> call, Throwable t) {

                data.setValue(null);
            }
        });

        return data;
    }

    public LiveData<Project> getProjectDetails(String userID, String projectName) {

        final MutableLiveData<Project> data = new MutableLiveData<>();

        gitHubApi.getProjectDetails(userID, projectName).enqueue(new Callback<Project>() {
            @Override
            public void onResponse(Call<Project> call, Response<Project> response) {

                if (!response.isSuccessful())
                    return;

                simulateDelay();
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Project> call, Throwable t) {

                data.setValue(null);
            }
        });

        return data;
    }

    private void simulateDelay() {
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
