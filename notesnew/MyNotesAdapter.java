package com.example.notesnew;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notesnew.databinding.EachRvBinding;

public class MyNotesAdapter extends ListAdapter<Notes_Entity, MyNotesAdapter.MyViewHolder> {

    MyNotesAdapter(){
        super(CALLBACK);
    }
    private static final DiffUtil.ItemCallback<Notes_Entity>CALLBACK = new DiffUtil.ItemCallback<Notes_Entity>() {
        @Override
        public boolean areItemsTheSame(@NonNull Notes_Entity oldItem, @NonNull Notes_Entity newItem) {
            return oldItem.getId()==newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Notes_Entity oldItem, @NonNull Notes_Entity newItem) {
            return oldItem.getTitle().equals(newItem.getTitle())
                    && oldItem.getDesc().equals(newItem.getDesc());
        }
    };



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        EachRvBinding binding = EachRvBinding.inflate(LayoutInflater.from(parent.getContext()),parent, false);
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Notes_Entity notes = getItem(position);
        holder.eachRvBinding.setNotesEntity(notes);
    }

    public Notes_Entity getPosition(int position){
        return getItem(position);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        EachRvBinding eachRvBinding;
        public MyViewHolder(@NonNull EachRvBinding eachRvBinding) {
            super(eachRvBinding.getRoot());
            this.eachRvBinding=eachRvBinding;
        }
    }

}
