package com.hozanbaydu.adobe;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hozanbaydu.adobe.databinding.RecyclerrowBinding;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.PostHolder> {

    private ArrayList<Model> passwordArrayList;

    public Adapter(ArrayList<Model> postArrayList) {
        this.passwordArrayList = postArrayList;
    }

    class PostHolder extends RecyclerView.ViewHolder {
        RecyclerrowBinding recyclerRowBinding;

        public PostHolder(@NonNull RecyclerrowBinding recyclerRowBinding) {
            super(recyclerRowBinding.getRoot());
            this.recyclerRowBinding = recyclerRowBinding;

        }
    }

    @NonNull
    @Override
    public PostHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerrowBinding recyclerRowBinding = RecyclerrowBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new PostHolder(recyclerRowBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull PostHolder holder, int position) {

        holder.recyclerRowBinding.textView2.setText(passwordArrayList.get(position).name);
    }

    @Override
    public int getItemCount() {
        return passwordArrayList.size();
    }


}
