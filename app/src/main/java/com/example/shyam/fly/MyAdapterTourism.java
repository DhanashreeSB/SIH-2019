package com.example.shyam.fly;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class MyAdapterTourism extends RecyclerView.Adapter<placeViewHolder> {


    private Context mContext;
    private List<placesTourism> mplaceList;
    CardView mCardView;

    MyAdapterTourism(Context mContext, List<placesTourism> mplaceList) {
        this.mContext = mContext;
        this.mplaceList = mplaceList;
    }

    public placeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_item_tourism, parent, false);
        return new placeViewHolder(mView);
    }


    public void onBindViewHolder(final placeViewHolder holder, int position) {
        holder.mImage.setImageResource(mplaceList.get(position).getplaceImage());
        holder.mTitle.setText(mplaceList.get(position).getplaceName());
        holder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(mContext, DetailTourismActivity.class);
                mIntent.putExtra("Title", mplaceList.get(holder.getAdapterPosition()).getplaceName());
                mIntent.putExtra("Description", mplaceList.get(holder.getAdapterPosition()).getplaceDescription());
                mIntent.putExtra("Image", mplaceList.get(holder.getAdapterPosition()).getplaceImage());
                mContext.startActivity(mIntent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return mplaceList.size();
    }
}
class placeViewHolder extends RecyclerView.ViewHolder {
    CardView mCardView = itemView.findViewById(R.id.cardview);
    ImageView mImage;
    TextView mTitle;

    placeViewHolder(View itemView) {
        super(itemView);

        mImage = itemView.findViewById(R.id.ivImage);
        mTitle = itemView.findViewById(R.id.tvTitle);
    }
}
