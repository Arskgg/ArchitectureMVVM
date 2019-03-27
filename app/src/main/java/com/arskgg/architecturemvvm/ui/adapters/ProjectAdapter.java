package com.arskgg.architecturemvvm.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.arskgg.architecturemvvm.R;
import com.arskgg.architecturemvvm.data.remote.model.Project;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ProjectAdapter extends RecyclerView.Adapter<ProjectAdapter.ProjectViewHolder> {

    List<Project> projects = new ArrayList<>();

    @NonNull
    @Override
    public ProjectViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.project_item, parent, false);

        return new ProjectViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ProjectViewHolder holder, int position) {

        Project currentProject = projects.get(position);

        holder.projectName.setText(currentProject.name);
        holder.language.setText(currentProject.language);
        holder.watchers.setText(String.valueOf(currentProject.watchers));


    }

    @Override
    public int getItemCount() {
        return projects == null ? 0 : projects.size();
    }

    class ProjectViewHolder extends RecyclerView.ViewHolder {

        TextView projectName, language, watchers;

        public ProjectViewHolder(@NonNull View itemView) {
            super(itemView);

            projectName = itemView.findViewById(R.id.projectName);
            language = itemView.findViewById(R.id.language);
            watchers = itemView.findViewById(R.id.watchers);

        }
    }

    public void setProjects(List<Project> projects) {

        this.projects = projects;
        notifyDataSetChanged();
    }
}
