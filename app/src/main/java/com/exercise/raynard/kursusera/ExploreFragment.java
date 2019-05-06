package com.exercise.raynard.kursusera;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ExploreFragment extends Fragment {
    //    private static String course;
    private List<CourseData> courses;
    private List<CourseData> courses2;
    private FirebaseDatabase myDB;
    private RecyclerView rv;
    private RVAdapter adapter;
    private RecyclerView rv2;
    private RVAdapter adapter2;
    private ProgressDialog progressDialog;

    public ExploreFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        courses = new ArrayList<>();
        courses2 = new ArrayList<>();

        myDB = FirebaseDatabase.getInstance();

        View view = inflater.inflate(R.layout.fragment_explore, container, false);

        // 1st Cardview list
        rv = (RecyclerView) view.findViewById(R.id.rv_certification);
        LinearLayoutManager llm = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);

        rv.setLayoutManager(llm);
        progressDialog = new ProgressDialog(getContext());

        progressDialog.setMessage("Loading Data from Firebase Database");

        progressDialog.show();

        // Retrieve data from firebase
        final DatabaseReference certificationDbRef = myDB.getReference("data").child("course").child("Certification");
        certificationDbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()) {
                    courses.add(new CourseData(CourseData.COURSE_HEADER, "", dataSnapshot.getKey(), ""));
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        CourseDetails course = snapshot.getValue(CourseDetails.class);
                        courses.add(new CourseData(CourseData.COURSE_DETAIL, course.getUrlImage(), course.getTitle(), course.getDescription()));
                    }
                    adapter = new RVAdapter(courses, getContext());
                    rv.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        rv2 = (RecyclerView) view.findViewById(R.id.rv_certification2);

        adapter2 = new RVAdapter(courses, getContext());
        LinearLayoutManager llm2 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);

        rv2.setLayoutManager(llm2);

        final DatabaseReference itDbRef = myDB.getReference("data").child("course").child("Information Technology");
        itDbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.d("TAG", dataSnapshot.getKey());
                if (dataSnapshot.exists()) {
                    courses2.add(new CourseData(CourseData.COURSE_HEADER, "", dataSnapshot.getKey(), ""));
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        CourseDetails course = snapshot.getValue(CourseDetails.class);
                        courses2.add(new CourseData(CourseData.COURSE_DETAIL, course.getUrlImage(), course.getTitle(), course.getDescription()));
                    }
                    adapter2 = new RVAdapter(courses2, getContext());
                    rv2.setAdapter(adapter2);
                    adapter2.notifyDataSetChanged();
                    progressDialog.dismiss();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        // pos 0
//        courses.add(new CourseData(CourseData.COURSE_HEADER, 0, "Certification", ""));

        // pos 1
//        courses.add(new CourseData(CourseData.COURSE_DETAIL, R.drawable.aws, "", "AWS"));

        //pos 2
//        courses.add(new CourseData(CourseData.COURSE_DETAIL, R.drawable.oracle, "Oracle Certification", "Oracle"));

//        // pos 0
//        courses2.add(new CourseData(CourseData.COURSE_HEADER, 0, "Certification", ""));
//
//        // pos 1
//        courses2.add(new CourseData(CourseData.COURSE_DETAIL, R.drawable.aws, "title", "description"));
//
//        //pos 2
//        courses2.add(new CourseData(CourseData.COURSE_DETAIL, R.drawable.oracle, "Oracle Certification", "Oracle"));
//        adapter2.notifyDataSetChanged();
        return view;
    }

}
