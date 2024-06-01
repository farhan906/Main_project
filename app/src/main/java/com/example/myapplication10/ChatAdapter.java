package com.example.myapplication10;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ChatViewHolder> {

    private List<ChatItem> chatItems;

    public ChatAdapter(List<ChatItem> chatItems) {
        this.chatItems = chatItems;
    }

    @NonNull
    @Override
    public ChatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chat, parent, false);
        return new ChatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatViewHolder holder, int position) {
        ChatItem chatItem = chatItems.get(position);
        holder.userName.setText(chatItem.getUserName());
        holder.message.setText(chatItem.getMessage());
    }

    @Override
    public int getItemCount() {
        return chatItems.size();
    }

    static class ChatViewHolder extends RecyclerView.ViewHolder {
        TextView userName;
        TextView message;

        ChatViewHolder(@NonNull View itemView) {
            super(itemView);
            userName = itemView.findViewById(R.id.userName);
            message = itemView.findViewById(R.id.message);
        }
    }
}