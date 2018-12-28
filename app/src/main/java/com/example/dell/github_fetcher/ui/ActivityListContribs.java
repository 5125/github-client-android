package com.example.dell.github_fetcher.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.Request;
import com.example.dell.github_fetcher.R;
import com.example.dell.github_fetcher.helper.StringUtils;
import com.example.dell.github_fetcher.models.ContribListGitHubModel;
import com.example.dell.github_fetcher.network.VolleyRqParser;
import com.example.dell.github_fetcher.partials.adapters.AdapterListContribs;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ActivityListContribs extends AppCompatActivity {


    String[] partNameUsernameAndRepo;
    TextView repoName, repoOwnerListContrib, repoDescListContrib;
    List<ContribListGitHubModel> listContribPojo;
    ContribListGitHubModel contribListGitHubModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_contribs);

        repoName = findViewById(R.id.repoName);
        repoOwnerListContrib = findViewById(R.id.repoOwnerListContrib);
        repoDescListContrib = findViewById(R.id.repoDescListContrib);

        Bundle getBundle = null;
        getBundle = this.getIntent().getExtras();
        String name = getBundle.getString("repoNameMainActivity");

        partNameUsernameAndRepo = StringUtils.splitByDelim(name);

        final RecyclerView recyclerView;
        recyclerView = findViewById(R.id.recylcerViewListContrib);
        GridLayoutManager mGridLayoutManager = new GridLayoutManager(ActivityListContribs.this, 4);
        recyclerView.setLayoutManager(mGridLayoutManager);

        listContribPojo = new ArrayList<>();
        final VolleyRqParser jsonParserVolley = new VolleyRqParser(this);
        jsonParserVolley.addHeader("Authorization", "Your value");
        jsonParserVolley.executeRequest(Request.Method.GET, "https://api.github.com/repos/" + partNameUsernameAndRepo[0] + "/" + partNameUsernameAndRepo[1] + "/contributors", new VolleyRqParser.VolleyCallback() {

            @Override
            public void getResponse(String response) {
                try {

                    JSONArray jsonarry = new JSONArray(response);

                    for (int x = 0; x < jsonarry.length(); x++) {
                        JSONObject obj1;
                        obj1 = jsonarry.getJSONObject(x);
                        contribListGitHubModel = new ContribListGitHubModel(obj1.getString("avatar_url"), obj1.getString("login"));
                        listContribPojo.add(contribListGitHubModel);
                    }

                    repoName.setText("Owner: "+partNameUsernameAndRepo[1]);
                    repoOwnerListContrib.setText("Repo-Name: "+partNameUsernameAndRepo[0]);

                    AdapterListContribs adapterListRepo = new AdapterListContribs(getBaseContext(), listContribPojo);
                    recyclerView.setAdapter(adapterListRepo);

                } catch (Exception e) {

                }

            }
        });

        final VolleyRqParser descCall = new VolleyRqParser(this);
        descCall.addHeader("Authorization", "Your value");
        descCall.executeRequest(Request.Method.GET, "https://api.github.com/repos/" + partNameUsernameAndRepo[0] + "/" + partNameUsernameAndRepo[1], new VolleyRqParser.VolleyCallback() {

            @Override
            public void getResponse(String response) {
                try {

                    JSONObject jsonObject = new JSONObject(response);

                    repoDescListContrib.setText("Description: "+jsonObject.getString("description"));

                } catch (Exception e) {

                }

            }
        });


    }

}
