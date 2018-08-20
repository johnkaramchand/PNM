package in.atria.gov.demo.Fragments;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import in.atria.gov.demo.GrivenceActivity;

import in.atria.gov.demo.R;
import in.atria.gov.demo.ReportAdapter;
import in.atria.gov.demo.ReportModel;

import static androidx.constraintlayout.widget.Constraints.TAG;

/**
 * Created by suryamurugan on 4/4/18.
 */

public class ReportFragment extends Fragment {
    private static RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;
    private static ArrayList<ReportModel> data;
    private static ArrayList<String> mArrayList = new ArrayList<>();;
    static View.OnClickListener myOnClickListener;
    private static ArrayList<Integer> removedItems;
    ViewGroup rootView;
    int i;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = (ViewGroup) inflater
                .inflate(R.layout.report_fragment, container, false);
        data = new ArrayList<ReportModel>();
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection("users").document("9901624795").collection("reports")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                data.add(new ReportModel(
                                        document.getId(), document.getString("problem"),document.getString("Reported to"),document.getString("village"),document.getString("status")
                                ));

                                Log.d(TAG, document.getId() + " => " + i + document.getString("problem")+" "+document.getString("Reported to") +" " +document.getString("village"));

                                i++;
                            }
                            recyclerView = (RecyclerView) rootView.findViewById(R.id.my_recycler_view);
                            recyclerView.setHasFixedSize(true);

                            layoutManager = new LinearLayoutManager(getActivity());
                            recyclerView.setLayoutManager(layoutManager);
                            recyclerView.setItemAnimator(new DefaultItemAnimator());
                            Context mcontext = getContext();
                            adapter = new ReportAdapter(data, mcontext);
                            recyclerView.setAdapter(adapter);

                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });


        return rootView;
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        LinearLayout linearLayout = view.findViewById(R.id.newreportbutton);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(),GrivenceActivity.class);
                startActivity(i);
            }
        });
    }
}
