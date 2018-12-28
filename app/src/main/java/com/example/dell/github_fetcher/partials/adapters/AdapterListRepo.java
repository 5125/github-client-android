package com.example.dell.github_fetcher.partials.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.dell.github_fetcher.MainActivity;
import com.example.dell.github_fetcher.R;
import com.example.dell.github_fetcher.models.RepoListGitHubModel;
import com.example.dell.github_fetcher.ui.ActivityListContribs;

import java.util.List;

public class AdapterListRepo extends RecyclerView.Adapter<AdapterListRepo.ListRepoHolder> {
    public NotifyItemClickedListRepos ntf;
    int currentPos;
    private Context ctx;
    private List<RepoListGitHubModel> repoList;

    public AdapterListRepo(Context ctx, List<RepoListGitHubModel> repoList) {
        this.ctx = ctx;
        ntf = new MainActivity();
        this.repoList = repoList;
    }

    @Override
    public ListRepoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(ctx);
        View view = inflater.inflate(R.layout.list_repo_cardview, null);
        return new ListRepoHolder(view);
    }

    @Override
    public void onBindViewHolder(final ListRepoHolder holder, int position) {

        RepoListGitHubModel repoListGitHubModel = repoList.get(position);

        Glide.with(ctx).load(repoListGitHubModel.getImageURL()).into(holder.userPic);

        holder.user.setText(repoListGitHubModel.getUserName());
        holder.reponame.setText(repoListGitHubModel.getRepoName());


    }

    @Override
    public int getItemCount() {
        return repoList.size();
    }

    public interface NotifyItemClickedListRepos {
        public void getIndexOfClickedItemListRepos(int pos);
    }

    class ListRepoHolder extends RecyclerView.ViewHolder {

        ImageView userPic;
        TextView user, reponame;
        RelativeLayout rl;

        public ListRepoHolder(View itemView) {
            super(itemView);

            userPic = itemView.findViewById(R.id.imageView);
            user = itemView.findViewById(R.id.owner);
            reponame = itemView.findViewById(R.id.reponame);
            rl = itemView.findViewById(R.id.repoDescListContrib);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    currentPos = getAdapterPosition();
                    ntf.getIndexOfClickedItemListRepos(currentPos);

                    Intent i = new Intent(ctx, ActivityListContribs.class);
                    i.putExtra("repoNameMainActivity", String.valueOf(repoList.get(currentPos).getRepoName()));
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    ctx.startActivity(i);

                }
            });


        }
    }
}
