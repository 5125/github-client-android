package com.example.dell.github_fetcher.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.Request;
import com.example.dell.github_fetcher.R;
import com.example.dell.github_fetcher.helper.StringUtils;
import com.example.dell.github_fetcher.models.ContribListGitHubModel;
import com.example.dell.github_fetcher.models.RepoListGitHubModel;
import com.example.dell.github_fetcher.network.VolleyRqParser;
import com.example.dell.github_fetcher.partials.adapters.AdapterListRepo;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ActivityListReposEndActivity extends AppCompatActivity implements AdapterListRepo.NotifyItemClickedListRepos {

    List<RepoListGitHubModel> repoListByUsersLastAct = new ArrayList<RepoListGitHubModel>();
    TextView tv ;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.list_all_repo_by_user_last_act);
        tv = (TextView)findViewById(R.id.titleTextRepoByUser);
        final RecyclerView recyclerView;

        recyclerView = findViewById(R.id.recylcerViewRepoByUser);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Bundle getBundle = null;
        getBundle = this.getIntent().getExtras();
        final String name = getBundle.getString("repoNameAdapterListContrib");

        final VolleyRqParser jsonParserVolley = new VolleyRqParser(this);
        jsonParserVolley.addHeader("Authorization", "Your value");
        jsonParserVolley.executeRequest(Request.Method.GET, "https://api.github.com/users/" + name + "/repos", new VolleyRqParser.VolleyCallback() {

                    @Override
                    public void getResponse(String response) {
                        try {

                            JSONArray jsonarry = new JSONArray(response);

                            for (int x = 0; x < jsonarry.length(); x++) {
                                JSONObject jsonObject = jsonarry.getJSONObject(x);
                                String name = jsonObject.getString("name");
                                String full_name = jsonObject.getString("full_name");

                                JSONObject owner = jsonObject.getJSONObject("owner");
                                String avatar = owner.getString("avatar_url");

                                repoListByUsersLastAct.add(new RepoListGitHubModel(name, full_name, avatar));
                            }
                            tv.setText("Respositories of: "+name);
                            AdapterListRepo adapterListRepo = new AdapterListRepo(getBaseContext(), repoListByUsersLastAct);
                            recyclerView.setAdapter(adapterListRepo);

                        } catch (Exception e) {

                        }

                    }
                }
        );


    }

    @Override
    public void getIndexOfClickedItemListRepos(int pos) {
        Log.v("pospos", String.valueOf(pos));
    }
}
