package com.qbent.enfinsapp;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;


import com.qbent.enfinsapp.adapter.MemberRecyclerViewAdapter;
import com.qbent.enfinsapp.model.ApiRequest;
import com.qbent.enfinsapp.model.Country;
import com.qbent.enfinsapp.model.LoanProduct;
import com.qbent.enfinsapp.model.Member;
import com.qbent.enfinsapp.restapi.ApiCallback;
import com.qbent.enfinsapp.restapi.ApiHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class MemberActivity extends MainActivity implements ApiCallback {
    EditText Name;
    EditText AadharNo;
    Button Search;
    private RecyclerView recyclerView;
    private List<Member> members = new ArrayList<Member>();
    Boolean isScrolling = false;
    int currentItems, totalItems, scrollOutItems;
    LinearLayoutManager manager;

    //---Developed by Debmalya---//
//    Spinner loanProductSpinner;
//    private List<LoanProduct> loanProductLists;
//    private String loanProductId = " ";
//    HashMap<String, String> spinnerLoanProductsMap = new HashMap<String, String>();
    //---Ended by Debmalya---//

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member);

        //---Developed by Debmalya---//
//        loanProductSpinner = (Spinner) findViewById(R.id.spinnerLoanProduct);
//        loanProductLists = new ArrayList<LoanProduct>();
//
//        loanProductSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//
//                String name = loanProductSpinner.getSelectedItem().toString();
//                List<String> indexes = new ArrayList<String>(spinnerLoanProductsMap.values());
//                int a = indexes.indexOf(name);
//                loanProductId = (new ArrayList<String>(spinnerLoanProductsMap.keySet())).get(indexes.indexOf(name));
//                populateLoanProducts(loanProductId);
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//                //Yet to be completed//
//            }
//
//        });


        //---Ended by Debmalya---//

        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        //inflate your activity layout here!
        @SuppressLint("InflateParams")
        View contentView = inflater.inflate(R.layout.activity_member, null, false);
        drawer.addView(contentView, 0);
        navigationView.setCheckedItem(R.id.nav_member);

        Name = (EditText) findViewById(R.id.searchMemberName);
        AadharNo = (EditText) findViewById(R.id.searchMemberAadhar);
        Search = (Button) findViewById(R.id.searchMember);

        Search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                members.clear();
                populateMembers(0);
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), MemberDetailsActivity.class));
            }
        });


//        try {
//            //            TableLayout tableLayout = (TableLayout) findViewById(R.id.tabLayout);
////            TableRow tableRow = (TableRow) findViewById(R.id.tabRow);
////            int s = tableLayout.getChildCount();
////            for(int n = 0; n < s; ++n)
////            {
////                tableRow = (TableRow) tableLayout.getChildAt(n);
////            }
////            tableRow.setOnClickListener(new View.OnClickListener()
////            {
////                @Override
////                public void onClick(View view)
////                {
////                    startActivity(new Intent(getApplicationContext(),CreditDataCheckActivity.class));
////                }
////            });
//
////            FloatingActionButton floatingActionButton = (FloatingActionButton) findViewById(R.id.elgCheck);
////            TableLayout yourRootLayout = (TableLayout) findViewById(R.id.tabLayout);
////            try
////            {
////                int count = yourRootLayout.getChildCount();
////                for (int i = 0; i < count; i++) {
////                    View v = yourRootLayout.getChildAt(i);
////                    if (v instanceof TableRow) {
////                        TableRow row = (TableRow) v;
////                        int rowCount = row.getChildCount();
////                        for (int r = 0; r < rowCount; r++) {
////                            View v2 = row.getChildAt(r);
////                            if (v2 instanceof FloatingActionButton) {
////                                floatingActionButton = (FloatingActionButton) v2;
////                                floatingActionButton.setOnClickListener(new View.OnClickListener() {
////                                    @Override
////                                    public void onClick(View view) {
////                                        startActivity(new Intent(getApplicationContext(), CreditDataCheckActivity.class));
////                                        finish();
////                                    }
////                                });
////                            }
////                        }
////                    }
////                }
////            }
////            catch (NullPointerException np)
////            {
////                np.printStackTrace();
////            }
        //---Developed by Debmalya---//
        try
        {
            TableRow tableRow = new TableRow(MemberActivity.this);
            View.OnClickListener mListener = new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    TableRow tableRow = (TableRow) v.getParent();

                }
            };

            final Button checkButton = new Button(MemberActivity.this);
            checkButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view)
                {
                    startActivity(new Intent(getApplicationContext(),CreditDataCheckActivity.class));
                    finish();
                }
            });
            tableRow.addView(checkButton);
        }
        catch (NullPointerException npe)
        {
            npe.printStackTrace();
        }
        //---Ended by Debmalya---//


            populateMembers(0);


        }

        //---Developed by Debmalya---//
//        private void populateLoanProducts(String loanProductId) {
//            new ApiHandler.GetAsync(MemberActivity.this).execute("all-loanProduct/{"+ loanProductId +"}");
//        }
        //---Ended by Debmalya---//

    @Override
    public void onApiRequestStart() throws IOException {

    }

    @Override
    public void onApiRequestComplete(String key, String result) throws IOException {
        try {
            if (key.equals("search-member")) {

                JSONObject resultJson = new JSONObject(result);
                JSONArray jsonArray = new JSONArray(resultJson.getString("result"));
                System.out.println(jsonArray.length());
                for(int i=0;i<jsonArray.length();i++)
                {
                    JSONObject jsonObject = (JSONObject)jsonArray.get(i);
                    Member member = new Member(
                        jsonObject.getString("id"),
                        jsonObject.getString("code"),
                        jsonObject.getString("fullName"),
                        jsonObject.getLong("aadharNo"),
                        jsonObject.getString("dateOfDeath"),
                        jsonObject.getString("guardianName"),
                        jsonObject.getString("collectionPointName"),
                        jsonObject.getString("visitStatus")
                            //jsonObject.getString("actionStatus")
                    );
                    members.add(member);
                }



                recyclerView = findViewById(R.id.recyclerViewMembers);
                manager = new LinearLayoutManager(MemberActivity.this);
                recyclerView.setLayoutManager(manager);
                MemberRecyclerViewAdapter mAdapter = new MemberRecyclerViewAdapter(members);
                recyclerView.setAdapter(mAdapter);


                recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                    @Override
                    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                        super.onScrollStateChanged(recyclerView, newState);
                        if(newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL)
                        {
                            isScrolling = true;
                        }
                    }

                    @Override
                    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                        super.onScrolled(recyclerView, dx, dy);
                        currentItems = manager.getChildCount();
                        totalItems = manager.getItemCount();
                        scrollOutItems = manager.findFirstVisibleItemPosition();

                        if(isScrolling && (currentItems + scrollOutItems == totalItems))
                        {
                            isScrolling = false;
                            populateMembers(totalItems);
                        }
                    }
                });
            }
            //---Developed by Debmalya---//
//            else if(key.contains("all-loanProduct"))
//            {
//                setLoanProductsAdapter(result);
//            }
            //---Ended by Debmalya---//

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //---Developed by Debmalya---//
//    private void setLoanProductsAdapter(String result)
//    {
//        try {
//            JSONArray jsonArray = new JSONArray(result);
//            for (int i = 0; i < jsonArray.length(); i++) {
//                JSONObject jsonObject = (JSONObject) jsonArray.get(i);
//                LoanProduct loanProduct = new LoanProduct(
//                        jsonObject.getString("id"),
//                        jsonObject.getString("name")
//                );
//                loanProductLists.add(loanProduct);
//            }
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//        String[] spinnerLoanProductsArray = new String[loanProductLists.size()];
//        for (int i = 0; i < loanProductLists.size(); i++)
//        {
//            spinnerLoanProductsMap.put(loanProductLists.get(i).getId(),loanProductLists.get(i).getName());
//            spinnerLoanProductsArray[i] = loanProductLists.get(i).getName();
//        }
//
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MemberActivity.this, android.R.layout.simple_spinner_item,spinnerLoanProductsArray);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        loanProductSpinner.setAdapter(adapter);
//    }
    //---Ended by Debmalya---//

    private void populateMembers(int totalItems) {
        ApiRequest apiRequest = new ApiRequest("search-member");
        try{
            JSONObject jsonObject = new JSONObject();
            jsonObject.accumulate("fullName", Name.getText().toString());
            String test = AadharNo.getText().toString();
            if(TextUtils.isEmpty(test))
            {
                jsonObject.accumulate("aadharNo", null);
            }
            else
            {
                jsonObject.accumulate("aadharNo", Long.parseLong(AadharNo.getText().toString()));
            }
            jsonObject.accumulate("collectionPointName", "");
            jsonObject.accumulate("limit", 50);
            jsonObject.accumulate("order", "");
            jsonObject.accumulate("page", ((totalItems+50)/50));
            apiRequest.set_t(jsonObject);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        new ApiHandler.PostAsync(MemberActivity.this).execute(apiRequest);
    }


}
