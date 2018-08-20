package in.atria.gov.demo;



import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.recyclerview.widget.RecyclerView;
import in.atria.gov.demo.ReportModel;

public class ReportAdapter extends RecyclerView.Adapter<ReportAdapter.MyViewHolder> {

    private ArrayList<ReportModel> dataSet;
    Context context;

    public  class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textViewid;
        TextView textViewProblem;
        TextView textViewreportedto;
        TextView textViewupdate;
        TextView textViewvillage;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.textViewid = (TextView) itemView.findViewById(R.id.reportid);
            this.textViewProblem = (TextView) itemView.findViewById(R.id.problem);
            this.textViewreportedto = (TextView) itemView.findViewById(R.id.reportedto);
            this.textViewupdate = (TextView) itemView.findViewById(R.id.update);
            this.textViewvillage = (TextView) itemView.findViewById(R.id.villagename);

            //  this.imageViewIcon = (ImageView) itemView.findViewById(R.id.imageView);
        }
    }

    public ReportAdapter(ArrayList<ReportModel> data,Context context) {
        this.context=context;
        this.dataSet = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                           int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_report_rv, parent, false);


        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int listPosition) {

        TextView textViewId = holder.textViewid;
        TextView textViewProblem = holder.textViewProblem;
        TextView textViewreportedTo = holder.textViewreportedto;
        TextView textViewupdate = holder.textViewupdate;
        TextView textViewvillage = holder.textViewvillage;



        //    ImageView imageView = holder.imageViewIcon;
        textViewId.setText(dataSet.get(listPosition).getId());
        textViewProblem.setText(dataSet.get(listPosition).getProblem());
        textViewreportedTo.setText(dataSet.get(listPosition).getReportedTo());
        textViewupdate.setText(dataSet.get(listPosition).getUpdate());
        textViewvillage.setText(dataSet.get(listPosition).getVillage());

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