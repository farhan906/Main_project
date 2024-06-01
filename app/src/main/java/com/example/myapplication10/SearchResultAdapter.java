package com.example.myapplication10;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SearchResultAdapter extends RecyclerView.Adapter<SearchResultAdapter.ViewHolder> {

    private List<Expert> searchResults;

    public SearchResultAdapter(List<Expert> searchResults) {
        this.searchResults = searchResults;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_result_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Expert expert = searchResults.get(position);
        holder.bind(expert);
    }

    @Override
    public int getItemCount() {
        return searchResults.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView textViewExpertName;
        private TextView textViewExpertDetails;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewExpertName = itemView.findViewById(R.id.textViewExpertName);
            textViewExpertDetails = itemView.findViewById(R.id.textViewExpertDetails);
        }

        public void bind(Expert expert) {
            textViewExpertName.setText(expert.getName());
            // Set other details as needed
        }
    }
}
