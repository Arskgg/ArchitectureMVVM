package com.arskgg.architecturemvvm.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.arskgg.architecturemvvm.R;
import com.arskgg.architecturemvvm.viewModels.ProjectListViewModel;
import com.arskgg.architecturemvvm.data.remote.model.Project;
import com.arskgg.architecturemvvm.ui.adapters.ProjectAdapter;
import com.arskgg.architecturemvvm.viewModels.ProjectListViewModelFactory;

import java.util.List;

public class GitProjectsActivity extends AppCompatActivity {

    private ProjectListViewModel projectListViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_git_projects);

        //Init RecyclerView
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        final ProjectAdapter adapter = new ProjectAdapter();
        recyclerView.setAdapter(adapter);


        //ViewModel Handling
        projectListViewModel = ViewModelProviders
                .of(GitProjectsActivity.this,
                        new ProjectListViewModelFactory(getApplication(), "Arskgg"))
                .get(ProjectListViewModel.class);

        projectListViewModel.getProjectList().observe(GitProjectsActivity.this, new Observer<List<Project>>() {
            @Override
            public void onChanged(List<Project> projects) {
                adapter.setProjects(projects);
            }
        });



    }
}
