package in.atria.gov.demo.Fragments;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import in.atria.gov.demo.NotificationAdapter;
import in.atria.gov.demo.NotificationModel;
import in.atria.gov.demo.R;

import static androidx.constraintlayout.widget.Constraints.TAG;

/**
 * Created by suryamurugan on 4/4/18.
 */

public class NotificationFragment extends Fragment {

    private static RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;
    private static ArrayList<NotificationModel> data;
    private static ArrayList<String> mArrayList = new ArrayList<>();;
    static View.OnClickListener myOnClickListener;
    private static ArrayList<Integer> removedItems;
    ViewGroup rootView;
    int i;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = (ViewGroup) inflater
                .inflate(R.layout.notification_fragment, container, false);
        data = new ArrayList<NotificationModel>();
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection("users").document("9901624795").collection("reports")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                data.add(new NotificationModel(
                                        document.getId(), document.getString("problem")
                                ));

                                Log.d(TAG, document.getId() + " => "+i + document.getString("problem"));

                                i++;
                            }
                            recyclerView = (RecyclerView) rootView.findViewById(R.id.my_recycler_view);
                            recyclerView.setHasFixedSize(true);

                            layoutManager = new LinearLayoutManager(getActivity());
                            recyclerView.setLayoutManager(layoutManager);
                            recyclerView.setItemAnimator(new DefaultItemAnimator());
                            Context mcontext =getContext();
                            adapter = new NotificationAdapter(data,mcontext);
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


    }


}
