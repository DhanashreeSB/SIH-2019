package com.example.shyam.fly;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class NotificationDisplayAdapter extends RecyclerView.Adapter<NotificationDisplayAdapter.NotificationViewHolder>{
    public Context mCtx;
    public List<NotificationDisplay> notificationList;

    public NotificationDisplayAdapter(Context mCtx, List<NotificationDisplay> notificationList) {
        this.mCtx = mCtx;
        this.notificationList = notificationList;
    }

    @NonNull
    @Override
    public NotificationDisplayAdapter.NotificationViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(mCtx);
        View view = layoutInflater.inflate(R.layout.notification_list, null);
        return new NotificationDisplayAdapter.NotificationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final NotificationViewHolder notificationViewHolder, final int i) {

        final NotificationDisplay notificationDisplay = notificationList.get(i);
        notificationViewHolder.message.setText(notificationDisplay.getNotification());
    }
    @Override
    public int getItemCount() {
        return notificationList.size();
    }

    class NotificationViewHolder extends RecyclerView.ViewHolder
    {
        TextView message;

        public NotificationViewHolder(@NonNull View itemView) {
            super(itemView);
            message = itemView.findViewById(R.id.notification);
        }
    }
}