package com.arskgg.architecturemvvm.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.arskgg.architecturemvvm.R;
import com.arskgg.architecturemvvm.data.model.Note;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> {

    private List<Note> notes = new ArrayList<>();

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.note_item, parent, false);

        return new NoteViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {

        Note currentNote = notes.get(position);

        holder.titleTxt.setText(currentNote.getTitle());
        holder.descriptionTxt.setText(currentNote.getDescription());
        holder.priorityTxt.setText(String.valueOf(currentNote.getPriority()));
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public void setNotes(List<Note> notes){

        this.notes = notes;
        notifyDataSetChanged();
    }

    public Note getNoteAt(int position){
        return notes.get(position);
    }


    class NoteViewHolder extends RecyclerView.ViewHolder{

        TextView titleTxt, descriptionTxt, priorityTxt;

        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);

            titleTxt = itemView.findViewById(R.id.textTitle);
            descriptionTxt = itemView.findViewById(R.id.textDescription);
            priorityTxt = itemView.findViewById(R.id.textPriority);
        }
    }
}
