package com.example.dell.github_fetcher;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.android.volley.Request;
import com.example.dell.github_fetcher.models.RepoListGitHubModel;
import com.example.dell.github_fetcher.network.VolleyRqParser;
import com.example.dell.github_fetcher.partials.adapters.AdapterListRepo;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterListRepo.NotifyItemClickedListRepos {


    List<RepoListGitHubModel> rplist = new ArrayList<RepoListGitHubModel>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final RecyclerView recyclerView;

        recyclerView = findViewById(R.id.recylcerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        final VolleyRqParser jsonParserVolley = new VolleyRqParser(this);
        jsonParserVolley.addHeader("Authorization", "Your value");
        jsonParserVolley.executeRequest(Request.Method.GET, "https://api.github.com/repositories", new VolleyRqParser.VolleyCallback() {

                    @Override
                    public void getResponse(String response) {
                        try {

                            JSONArray jsonarry = new JSONArray(response);

                            for (int i = 0; i < 50; i++) {
                                JSONObject jsonObject = jsonarry.getJSONObject(i);
                                JSONObject inner = jsonObject.getJSONObject("owner");

                                rplist.add(new RepoListGitHubModel(jsonObject.getString("name"), jsonObject.getString("full_name"),
                                        inner.getString("avatar_url")
                                ));

                            }

                            AdapterListRepo adapterListRepo = new AdapterListRepo(getBaseContext(), rplist);
                            recyclerView.setAdapter(adapterListRepo);

                        } catch (Exception e) {

                        }

                    }
                }
        );

    }

    @Override
    public void getIndexOfClickedItemListRepos(int pos) {

    }
}


