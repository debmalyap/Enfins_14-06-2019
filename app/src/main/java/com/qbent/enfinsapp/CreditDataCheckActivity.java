package com.qbent.enfinsapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.qbent.enfinsapp.model.ApiRequest;
import com.qbent.enfinsapp.model.CreditOrganization;
import com.qbent.enfinsapp.model.Installment;
import com.qbent.enfinsapp.model.LoanProduct;
import com.qbent.enfinsapp.restapi.ApiCallback;
import com.qbent.enfinsapp.restapi.ApiHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static android.R.layout.simple_spinner_item;

public class CreditDataCheckActivity extends MainActivity implements ApiCallback {

    //---Developed by Debmalya---//
    Button backToMemberListButton,saveCreditDataButton;

    Spinner creditOrganizationSpinner;
    Spinner loanProductSpinner;
    Spinner periodListSpinner;


    private List<CreditOrganization> creditOrganizationList;
    private List<LoanProduct> loanProductList;
    private List<Installment> installmentList;

    private String creditOrganizationId = " ";
    private String loanProductId = " ";
    private String appliedAmountId = " ";
    private String memberId = " ";

    HashMap<String, String> spinnerCreditOrganizationMap = new HashMap<String, String>();
    HashMap<String, String> spinnerLoanProductMap = new HashMap<String, String>();
    HashMap<String, String> installmentMap = new HashMap<String, String>();

    EditText appliedAmountField;
    EditText appliedInterestRateField;

    String id;
    Double minLoanAmount,maxLoanAmount,maxInterestRate,minInterestRate;
    //public int installmentPeriodList[];
    //---Ended by Debmalya---//

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credit_data_check);
        memberId = getIntent().getStringExtra("emp_id");

        //---Developed by Debmalya---//
        backToMemberListButton = findViewById(R.id.backToMember);


        creditOrganizationSpinner = (Spinner) findViewById(R.id.spinnerCreditOrganization);
        loanProductSpinner = (Spinner) findViewById(R.id.spinnerLoanProduct);
        periodListSpinner = (Spinner) findViewById(R.id.spinnerInstallment);

        appliedAmountField = (EditText) findViewById(R.id.editTextAppliedAmount);
        appliedInterestRateField = (EditText) findViewById(R.id.editTextInterestRate);

        creditOrganizationList = new ArrayList<CreditOrganization>();
        loanProductList = new ArrayList<LoanProduct>();
        installmentList = new ArrayList<Installment>();

        backToMemberListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent memberIntent = new Intent(getApplicationContext(),MemberActivity.class);
                startActivity(memberIntent);
            }
        });


        //---Ended by Debmalya---//

        //---Developed by Debmalya---//

//        ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(this, R.array.installmentPeriodList, android.R.layout.simple_spinner_item);
//        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        periodListSpinner.setAdapter(arrayAdapter);

        creditOrganizationSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                String name = creditOrganizationSpinner.getSelectedItem().toString();
                List<String> indexes = new ArrayList<String>(spinnerCreditOrganizationMap.values());
                int a = indexes.indexOf(name);
                creditOrganizationId = (new ArrayList<String>(spinnerCreditOrganizationMap.keySet())).get(indexes.indexOf(name));
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                //Yet to be completed//
            }
        });

        loanProductSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                String name = loanProductSpinner.getSelectedItem().toString();
                List<String> indexes = new ArrayList<String>(spinnerLoanProductMap.values());
                int a = indexes.indexOf(name);
                loanProductId = (new ArrayList<String>(spinnerLoanProductMap.keySet())).get(indexes.indexOf(name));
                populateAppliedAmount(memberId,loanProductId);
                //populateInterestRate(memberId,loanProductId);
                if(loanProductSpinner.getSelectedItemPosition() == 0)
                {
                    ArrayAdapter<CharSequence> arrayAdapter = (ArrayAdapter<CharSequence>) ArrayAdapter.createFromResource(CreditDataCheckActivity.this, R.array.bulletLoanList, android.R.layout.simple_spinner_item);
                    arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    periodListSpinner.setAdapter(arrayAdapter);
                }
                else if(loanProductSpinner.getSelectedItemPosition() == 1)
                {
                    ArrayAdapter<CharSequence> arrayAdapter = (ArrayAdapter<CharSequence>) ArrayAdapter.createFromResource(CreditDataCheckActivity.this, R.array.businessLoanList, android.R.layout.simple_spinner_item);
                    arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    periodListSpinner.setAdapter(arrayAdapter);
                }
                else if(loanProductSpinner.getSelectedItemPosition() == 2)
                {
                    ArrayAdapter<CharSequence> arrayAdapter = (ArrayAdapter<CharSequence>) ArrayAdapter.createFromResource(CreditDataCheckActivity.this, R.array.ordinaryBusinessLoanList, android.R.layout.simple_spinner_item);
                    arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    periodListSpinner.setAdapter(arrayAdapter);
                }
                else if(loanProductSpinner.getSelectedItemPosition() == 3)
                {
                    ArrayAdapter<CharSequence> arrayAdapter = (ArrayAdapter<CharSequence>) ArrayAdapter.createFromResource(CreditDataCheckActivity.this, R.array.perosnalLoanList, android.R.layout.simple_spinner_item);
                    arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    periodListSpinner.setAdapter(arrayAdapter);
                }
                else if(loanProductSpinner.getSelectedItemPosition() == 4)
                {
                    ArrayAdapter<CharSequence> arrayAdapter = (ArrayAdapter<CharSequence>) ArrayAdapter.createFromResource(CreditDataCheckActivity.this, R.array.smallBusinessLoanList, android.R.layout.simple_spinner_item);
                    arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    periodListSpinner.setAdapter(arrayAdapter);
                }
                else if(loanProductSpinner.getSelectedItemPosition() == 5)
                {
                    ArrayAdapter<CharSequence> arrayAdapter = (ArrayAdapter<CharSequence>) ArrayAdapter.createFromResource(CreditDataCheckActivity.this, R.array.smallBusinessLoanWeeklyList, android.R.layout.simple_spinner_item);
                    arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    periodListSpinner.setAdapter(arrayAdapter);
                }

            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                //Yet to be completed//
            }
        });

        periodListSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                String name = periodListSpinner.getSelectedItem().toString();

                //populateInterestRate(memberId,loanProductId);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                //Yet to be completed//
            }
        });

        populateCreditOrganization(creditOrganizationId);

        populateLoanProduct(loanProductId);
        //---Ended by Debmalya---//

    }

    //---Developed by Debmalya---//
    @Override
    public void onApiRequestStart() throws IOException {

    }
    //---Ended by Debmalya---//

    //---Developed by Debmalya---//
    @Override
    public void onApiRequestComplete(String key, String result) throws IOException
    {
        if (key.equals("get-credit-organization"))
        {
            setCreditOrganizationAdapter(result);
        }
        else if (key.equals("all-loanProduct"))
        {
            setLoanProductAdapter(result);
        }
        else if (key.contains("getLoanProductDtlByProductId"))
        {
            setLoanProductDetailsAdapter(result);
        }
    }
    //---Ended by Debmalya---//

    //---Developed by Debmalya---//
    private void populateCreditOrganization(String creditOrganizationId)
    {
        new ApiHandler.GetAsync(CreditDataCheckActivity.this).execute("get-credit-organization");
    }

    private void populateLoanProduct(String loanProductId)
    {
        new ApiHandler.GetAsync(CreditDataCheckActivity.this).execute("all-loanProduct");
    }

    private void populateAppliedAmount(String memberId,String loanProductId)
    {
        new ApiHandler.GetAsync(CreditDataCheckActivity.this).execute("getLoanProductDtlByProductId/{"+ memberId +"}/{"+ loanProductId + "}");
    }

//    private void populateInterestRate(String memberId, String loanProductId)
//    {
//        new ApiHandler.GetAsync(CreditDataCheckActivity.this).execute("getLoanProductDtlByProductId/{"+ memberId +"}/{"+ loanProductId + "}");
//    }
    //---Ended by Debmalya---//

    //---Developed by Debmalya---//
    private void setCreditOrganizationAdapter(String result)
    {
        try {
            creditOrganizationList = new ArrayList<CreditOrganization>();




            JSONArray jsonArray = new JSONArray(result);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                CreditOrganization creditOrganization = new CreditOrganization(
                        jsonObject.getString("id"),
                        jsonObject.getString("name")
                );
                creditOrganizationList.add(creditOrganization);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        String[] spinnerCreditOrganizationArray = new String[creditOrganizationList.size()];
        for (int i = 0; i < creditOrganizationList.size(); i++)
        {
            spinnerCreditOrganizationMap.put(creditOrganizationList.get(i).getId(),creditOrganizationList.get(i).getName());
            spinnerCreditOrganizationArray[i] = creditOrganizationList.get(i).getName();
        }



        ArrayAdapter<String> adapter = new ArrayAdapter<String>(CreditDataCheckActivity.this, simple_spinner_item,spinnerCreditOrganizationArray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        creditOrganizationSpinner.setAdapter(adapter);
    }


    private void setLoanProductAdapter(String result)
    {
        try {
            loanProductList = new ArrayList<LoanProduct>();
            JSONArray jsonArray = new JSONArray(result);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                LoanProduct loanProduct = new LoanProduct(
                        jsonObject.getString("id"),
                        jsonObject.getString("name")
                );
                loanProductList.add(loanProduct);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        String[] spinnerLoanProductArray = new String[loanProductList.size()];
        for (int i = 0; i < loanProductList.size(); i++)
        {
            spinnerLoanProductMap.put(loanProductList.get(i).getId(),loanProductList.get(i).getName());
            spinnerLoanProductArray[i] = loanProductList.get(i).getName();
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(CreditDataCheckActivity.this, simple_spinner_item,spinnerLoanProductArray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        loanProductSpinner.setAdapter(adapter);
    }

    private void setLoanProductDetailsAdapter(String result) {
        try {
            JSONObject jsonObject = new JSONObject(result);

            id = jsonObject.getString("id");
            minInterestRate = jsonObject.getDouble("minInterestRate");
            maxLoanAmount = jsonObject.getDouble("maxLoanAmount");
            maxInterestRate = jsonObject.getDouble("maxInterestRate");
            minLoanAmount = jsonObject.getDouble("minLoanAmount");

            appliedAmountField.setHint(minLoanAmount + " - " + maxLoanAmount);
            appliedInterestRateField.setHint(minInterestRate + " - " + maxInterestRate);

        } catch (JSONException e) {
            e.printStackTrace();
        }

//        try {
//            installmentList = new ArrayList<Installment>();
//            JSONArray jsonArray = new JSONArray(result);
//            for (int i = 0; i < jsonArray.length(); i++) {
//                JSONObject jsonObject = (JSONObject) jsonArray.get(i);
//                Installment installment = new Installment(
//                        jsonObject.getString("id"),
//                        jsonObject.getString("periods")
//                );
//                installmentList.add(installment);
//            }
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//        String[] spinnerInstallmentArray = new String[installmentList.size()];
//        for (int i = 0; i < installmentList.size(); i++)
//        {
//            installmentMap.put(installmentList.get(i).getId(),installmentList.get(i).getPeriods());
//            spinnerInstallmentArray[i] = installmentList.get(i).getPeriods();
//        }
//
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(CreditDataCheckActivity.this, simple_spinner_item,spinnerInstallmentArray);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        periodListSpinner.setAdapter(adapter);

    }
        //---Ended by Debmalya---//
}
