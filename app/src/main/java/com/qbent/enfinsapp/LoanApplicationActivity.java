package com.qbent.enfinsapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.qbent.enfinsapp.adapter.CollectionPointListRecyclerViewAdapter;
import com.qbent.enfinsapp.adapter.LoanApplicationListRecyclerViewAdapter;
import com.qbent.enfinsapp.model.CollectionPoint;
import com.qbent.enfinsapp.model.LoanApplication;
import com.qbent.enfinsapp.restapi.ApiCallback;
import com.qbent.enfinsapp.restapi.ApiHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LoanApplicationActivity extends MainActivity implements ApiCallback {

    private FloatingActionButton floatingActionButton;
    private RecyclerView recyclerView;
    private List<LoanApplication> loanApplications = new ArrayList<LoanApplication>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loan_application);

        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);



        //inflate your activity layout here!
        @SuppressLint("InflateParams")
        View contentView = inflater.inflate(R.layout.activity_collection_point_list, null, false);
        drawer.addView(contentView, 0);
        navigationView.setCheckedItem(R.id.nav_collection_point);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Snackbar.make(view, "Hello First Activity", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/

            }
        });
        populateloanApplications();
    }



    @Override
    public void onApiRequestStart() throws IOException
    {

    }

    @Override
    public void onApiRequestComplete(String key, String result) throws IOException
    {
        if(key.equals("all-loan-Applications"))
        {
            setLoanApplicationAdapter(result);
        }
    }

    private void setLoanApplicationAdapter(String result)
    {
        try {

                JSONArray jsonArray = new JSONArray(result);
                System.out.println(jsonArray.length());
                for(int i=0;i<jsonArray.length() - 1;i++)
                {
                    JSONObject jsonObject = (JSONObject)jsonArray.get(i);
                    LoanApplication loanApplication = new LoanApplication(
                            jsonObject.getString("applicationNo"),
                            jsonObject.getString("applicationDate"),
                            jsonObject.getString("branch"),
                            jsonObject.getString("borrowerName"),
                            jsonObject.getString("coBorrowerName"),
                            jsonObject.getString("loanProduct"),
                            jsonObject.getString("loanPurpose"),
                            jsonObject.getString("status")
                    );
                    loanApplications.add(loanApplication);
                }


                recyclerView = findViewById(R.id.recyclerViewLoanApplications);
                recyclerView.setLayoutManager(new LinearLayoutManager(LoanApplicationActivity.this));
                LoanApplicationListRecyclerViewAdapter cpAdapter = new LoanApplicationListRecyclerViewAdapter(loanApplications);
                recyclerView.setAdapter(cpAdapter);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void populateloanApplications()
    {
        new ApiHandler.GetAsync(LoanApplicationActivity.this).execute("all-loan-Applications");
    }
}
