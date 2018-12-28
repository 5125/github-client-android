package com.example.dell.github_fetcher.partials.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.dell.github_fetcher.R;
import com.example.dell.github_fetcher.models.ContribListGitHubModel;
import com.example.dell.github_fetcher.ui.ActivityListContribs;
import com.example.dell.github_fetcher.ui.ActivityListReposEndActivity;

import java.util.List;

public class AdapterListContribs extends RecyclerView.Adapter<AdapterListContribs.ListContribs> {

    int currentPos;
    private Context mContext;
    private List<ContribListGitHubModel> mListContrib;


    public AdapterListContribs(Context mContext, List<ContribListGitHubModel> mListContrib) {
        this.mContext = mContext;
        this.mListContrib = mListContrib;
    }

    @Override
    public ListContribs onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_contribs_cardview, parent, false);
        return new ListContribs(mView);
    }

    @Override
    public void onBindViewHolder(final ListContribs holder, int position) {

        ContribListGitHubModel contribListGitHubModel = mListContrib.get(position);
        Glide.with(mContext).load(contribListGitHubModel.getCardViewImageListRepo()).into(holder.cardViewImageListRepo);
        holder.cardViewTitleListRepo.setText(mListContrib.get(position).getCardViewTitleListRepo());

        holder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mContext, ActivityListReposEndActivity.class);
                String payload = mListContrib.get(holder.getAdapterPosition()).getCardViewTitleListRepo();
                Log.v("payload",payload);
                i.putExtra("repoNameAdapterListContrib",payload);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mListContrib.size();
    }

    class ListContribs extends RecyclerView.ViewHolder {

        ImageView cardViewImageListRepo;
        TextView cardViewTitleListRepo;
        CardView mCardView;

        public ListContribs(View itemView) {
            super(itemView);
            cardViewImageListRepo = itemView.findViewById(R.id.cardViewImageListRepo);
            cardViewTitleListRepo = itemView.findViewById(R.id.cardViewTitleListRepo);
            mCardView = itemView.findViewById(R.id.cardview);

        }
    }

}
