package com.qbent.enfinsapp.adapter;

import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TextView;

import com.qbent.enfinsapp.R;
import com.qbent.enfinsapp.model.CollectionPoint;
import com.qbent.enfinsapp.model.LoanApplication;

import java.util.List;

public class LoanApplicationListRecyclerViewAdapter extends RecyclerView.Adapter<LoanApplicationListRecyclerViewAdapter.LoanApplicationListViewHolder>
{
    private List<LoanApplication> listItems;
    private final List<LoanApplication> loanApplications;

    public LoanApplicationListRecyclerViewAdapter(List<LoanApplication> loanApplications1)
    {
        this.loanApplications = loanApplications1;
    }

    @NonNull
    @Override
    public LoanApplicationListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
    {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.list_item_collection_point, viewGroup, false);
        return new LoanApplicationListRecyclerViewAdapter.LoanApplicationListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LoanApplicationListViewHolder loanApplicationListViewHolder, int i)
    {
        loanApplicationListViewHolder.mItem = loanApplications.get(i);

        loanApplicationListViewHolder.applicationNoView.setText(loanApplications.get(i).getApplicationNo());
        loanApplicationListViewHolder.applicationDateView.setText(loanApplications.get(i).getApplicationDate());
        loanApplicationListViewHolder.branchView.setText(loanApplications.get(i).getBranch());
        loanApplicationListViewHolder.borrowerNameView.setText(loanApplications.get(i).getBorrowerName());
        loanApplicationListViewHolder.coBorrowerNameView.setText(loanApplications.get(i).getCoBorrowerName());
        loanApplicationListViewHolder.loanProductView.setText(loanApplications.get(i).getLoanProduct());
        loanApplicationListViewHolder.loanPurposeView.setText(loanApplications.get(i).getLoanPurpose());
        loanApplicationListViewHolder.statusView.setText(loanApplications.get(i).getStatus());

    }

    @Override
    public int getItemCount()
    {
        return loanApplications.size();
    }

    public class LoanApplicationListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        public final View mView;
        public final TextView applicationNoView;
        public final TextView applicationDateView;
        public final TextView branchView;
        public final TextView borrowerNameView;
        public final TextView coBorrowerNameView;
        public final TextView loanProductView;
        public final TextView loanPurposeView;
        public final TextView statusView;
        public LoanApplication mItem;

        public TableLayout tableLayout;

        public LoanApplicationListViewHolder(View view) {
            super(view);
            mView = view;
            applicationNoView = (TextView) view.findViewById(R.id.textApplicationNo);
            applicationDateView = (TextView) view.findViewById(R.id.textApplicationDate);
            branchView = (TextView) view.findViewById(R.id.textBranch);
            borrowerNameView = (TextView) view.findViewById(R.id.textBorrowerName);
            coBorrowerNameView = (TextView) view.findViewById(R.id.textCoBorrowerName);
            loanProductView = (TextView) view.findViewById(R.id.textLoanProduct);
            loanPurposeView = (TextView) view.findViewById(R.id.textLoanPurpose);
            statusView = (TextView) view.findViewById(R.id.textStatus);

            tableLayout = (TableLayout) view.findViewById(R.id.tabLoanLayout);

        }



//        @Override
//        public String toString() {
//            return super.toString() + " '" + mAddressView.getText() + "'";
//        }

        @Override
        public void onClick(View view) {

        }
    }
}
