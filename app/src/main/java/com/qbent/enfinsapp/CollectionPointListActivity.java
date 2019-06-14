package com.qbent.enfinsapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.qbent.enfinsapp.adapter.CollectionPointListRecyclerViewAdapter;
import com.qbent.enfinsapp.model.CollectionPoint;
import com.qbent.enfinsapp.restapi.ApiCallback;
import com.qbent.enfinsapp.restapi.ApiHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CollectionPointListActivity extends MainActivity implements ApiCallback{


    private FloatingActionButton floatingActionButton;
    private RecyclerView recyclerView;
    private List<CollectionPoint> collectionPoints = new ArrayList<CollectionPoint>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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
                startActivity(new Intent(getApplicationContext(), CollectionPointDetailActivity.class));
            }
        });

        //---Developed by Debmalya---//
//        floatingActionButton = findViewById(R.id.fabButton);
//        floatingActionButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                  ApiRequest apiRequest = new ApiRequest("collection-point/edit");
//
//                    try{
//                        JSONObject jsonObject = new JSONObject();
//
//                        jsonObject.accumulate("name",userNameField.getText().toString());
//                        jsonObject.accumulate("formationDate",formationDateField.getText().toString());
//                        jsonObject.accumulate("collectionDay",collectionDays.getSelectedItemPosition());
//                        jsonObject.accumulate("address",userAddressField.getText().toString());
//                        jsonObject.accumulate("pincode",userPincodeField.getText().toString());
//                        jsonObject.accumulate("place",userPlaceField.getText().toString());
//                        jsonObject.accumulate("mobileNo",userMobileField.getText().toString());
//                        jsonObject.accumulate("countryId",(new ArrayList<String>(spinnerCountriesMap.keySet())).get(new ArrayList<String>(spinnerCountriesMap.values()).indexOf(countriesSpinner.getSelectedItem().toString())).toString());
//                        jsonObject.accumulate("stateId",(new ArrayList<String>(spinnerStatesMap.keySet())).get(new ArrayList<String>(spinnerStatesMap.values()).indexOf(statesSpinner.getSelectedItem().toString())).toString());
//                        jsonObject.accumulate("districtId",(new ArrayList<String>(spinnerDistrictsMap.keySet())).get(new ArrayList<String>(spinnerDistrictsMap.values()).indexOf(distrcitSpinner.getSelectedItem().toString())).toString());
//                        jsonObject.accumulate("municipalityId",(new ArrayList<String>(spinnerMunicipalitiesMap.keySet())).get(new ArrayList<String>(spinnerMunicipalitiesMap.values()).indexOf(municipalitySpinner.getSelectedItem().toString())).toString());
//                        jsonObject.accumulate("wardId",(new ArrayList<String>(spinnerWardsMap.keySet())).get(new ArrayList<String>(spinnerWardsMap.values()).indexOf(wardSpinner.getSelectedItem().toString())).toString());
//                        jsonObject.accumulate("blockId",(new ArrayList<String>(spinnerBlocksMap.keySet())).get(new ArrayList<String>(spinnerBlocksMap.values()).indexOf(blockSpinner.getSelectedItem().toString())).toString());
//                        jsonObject.accumulate("gramPanchayatId",(new ArrayList<String>(spinnerGramPanchayetMap.keySet())).get(new ArrayList<String>(spinnerGramPanchayetMap.values()).indexOf(gramPanchayetSpinner.getSelectedItem().toString())).toString());
//                        jsonObject.accumulate("villageId",(new ArrayList<String>(spinnerVillageMap.keySet())).get(new ArrayList<String>(spinnerVillageMap.values()).indexOf(villageSpinner.getSelectedItem().toString())).toString());
//
//
//                        apiRequest.set_t(jsonObject);
//                    }
//                    catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                    new ApiHandler.PostAsync(CollectionPointDetailActivity.this).execute(apiRequest);
//            }
//        });
        //---Ended by Debmalya---//

        populateCollectionPoints();
    }

    @Override
    public void onApiRequestStart() throws IOException {

    }

    @Override
    public void onApiRequestComplete(String key, String result) throws IOException {
        try {
            if (key.equals("collection-points")) {

                JSONArray jsonArray = new JSONArray(result);
                System.out.println(jsonArray.length());
                for(int i=0;i<jsonArray.length() - 1;i++)
                {
                    JSONObject jsonObject = (JSONObject)jsonArray.get(i);
                    CollectionPoint collectionPoint = new CollectionPoint(
                            jsonObject.getString("name"),
                            jsonObject.getString("address"),
                            jsonObject.getString("mobileNo"),
                            jsonObject.getString("collectionDayName")
                    );
                    collectionPoints.add(collectionPoint);
                }


                recyclerView = findViewById(R.id.recyclerViewCollectionPoints);
                recyclerView.setLayoutManager(new LinearLayoutManager(CollectionPointListActivity.this));
                CollectionPointListRecyclerViewAdapter cpAdapter = new CollectionPointListRecyclerViewAdapter(collectionPoints);
                recyclerView.setAdapter(cpAdapter);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void populateCollectionPoints() {
        new ApiHandler.GetAsync(CollectionPointListActivity.this).execute("collection-points");
    }

    //----Developed by Debmalya---//
//    private void setEditCollectionPointsAdapter(String result)
//    {
//        Toast.makeText(getApplicationContext(),"Successfully Edited", Toast.LENGTH_SHORT).show();
//        Intent intent = new Intent(getApplicationContext(),CollectionPointListActivity.class);
//        startActivity(intent);
//    }
    //---Ended by Debmalya---//

}
