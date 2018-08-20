package in.atria.gov.demo;



import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.recyclerview.widget.RecyclerView;
import in.atria.gov.demo.NotificationModel;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.MyViewHolder> {

    private ArrayList<NotificationModel> dataSet;
    Context context;

    public  class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textViewid;
        TextView textViewProblem;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.textViewid = (TextView) itemView.findViewById(R.id.reportid);
            this.textViewProblem = (TextView) itemView.findViewById(R.id.problem);

            //  this.imageViewIcon = (ImageView) itemView.findViewById(R.id.imageView);
        }
    }

    public NotificationAdapter(ArrayList<NotificationModel> data,Context context) {
        this.context=context;
        this.dataSet = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                           int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_notification_rv, parent, false);


        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int listPosition) {

        TextView textViewId = holder.textViewid;
        TextView textViewProblem = holder.textViewProblem;



        //    ImageView imageView = holder.imageViewIcon;
        textViewId.setText(dataSet.get(listPosition).getId());
        textViewProblem.setText(dataSet.get(listPosition).getProblem());
        String TAG = "MyActivity";
        Log.d(TAG,"ID: "+ dataSet.get(listPosition).getId());
        Log.d(TAG,"Problem: "+ dataSet.get(listPosition).getProblem());




        //    imageView.setImageResource(dataSet.get(listPosition).getImage());
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}