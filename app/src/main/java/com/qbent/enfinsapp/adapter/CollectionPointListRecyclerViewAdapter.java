package com.qbent.enfinsapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.qbent.enfinsapp.CollectionPointDetailActivity;
import com.qbent.enfinsapp.R;
import com.qbent.enfinsapp.model.ApiRequest;
import com.qbent.enfinsapp.model.CollectionPoint;
import com.qbent.enfinsapp.restapi.ApiHandler;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CollectionPointListRecyclerViewAdapter extends RecyclerView.Adapter<CollectionPointListRecyclerViewAdapter.CollectionPointListViewHolder> {

    private List<CollectionPoint> listItems;
    private final List<CollectionPoint> collectionPoints;

    //----Developed by Debmalya---//
    private Context context;
    public CollectionPoint mItem;
    //----Ended by Debmalya---//

    public CollectionPointListRecyclerViewAdapter(List<CollectionPoint> collectionPoints) {
        this.collectionPoints = collectionPoints;
    }

    @NonNull
    @Override
    public CollectionPointListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
    {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.list_item_collection_point, viewGroup, false);
        return new CollectionPointListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CollectionPointListViewHolder collectionPointListViewHolder, int i)
    {
        collectionPointListViewHolder.mItem = collectionPoints.get(i);
        collectionPointListViewHolder.mNameView.setText(collectionPoints.get(i).getName());
        collectionPointListViewHolder.mAddressView.setText(collectionPoints.get(i).getMobileNo());
        collectionPointListViewHolder.mCollectionDayView.setText(collectionPoints.get(i).getCollectionDay());

//        final CollectionPoint listItem = listItems.get(i);
//        collectionPointListViewHolder.mNameView.setText(listItem.getName());
//        collectionPointListViewHolder.mAddressView.setText(listItem.getMobileNo());

        //---Developed by Debmalya---//
//        collectionPointListViewHolder.constraintLayout.setOnClickListener(new View.OnClickListener()
//        {
//            @Override
//            public void onClick(View view)
//            {
//                Toast.makeText(context,"You Clicked "+mItem.getName(),Toast.LENGTH_LONG).show();
//                Intent intent3 = new Intent(context,CollectionPointDetailActivity.class);
//                intent3.putExtra("emp_name",mItem.getName());
//                intent3.putExtra("emp_mobile",mItem.getMobileNo());
//                //intent3.putExtra("emp_company",listItem.getCompany());
//                context.startActivity(intent3);
//
//            }
//        });
        //---Ended by Debmalya---//

    }

    @Override
    public int getItemCount()
    {
        return collectionPoints.size();
    }

    public class CollectionPointListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        public final View mView;
        public final TextView mNameView;
        public final TextView mCollectionDayView;
        public final TextView mAddressView;
        public CollectionPoint mItem;

        //---Developed by Debmalya---//
        public ConstraintLayout constraintLayout;
        //---Ended by Debmalya---//

        public CollectionPointListViewHolder(View view) {
            super(view);
            mView = view;
            mNameView = (TextView) view.findViewById(R.id.textName);
            mCollectionDayView = (TextView) view.findViewById(R.id.textCollectionDay);
            mAddressView = (TextView) view.findViewById(R.id.textAddress);

            constraintLayout = (ConstraintLayout) itemView.findViewById(R.id.myConstraint);


        }



        @Override
        public String toString() {
            return super.toString() + " '" + mAddressView.getText() + "'";
        }

        @Override
        public void onClick(View view) {

        }
    }

    //---Developed by Debmalya---//
//    public interface OnNoteListener
//    {
//        void onItemClick();
//    }
    //---Ended by Debmalya---//
}
