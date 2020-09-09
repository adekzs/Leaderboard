package com.adeks.tabapp.ui.main;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.adeks.tabapp.AsyncResponse;
import com.adeks.tabapp.retrofit.DataBank;
import com.adeks.tabapp.R;
import com.adeks.tabapp.adapters.RecyclerViewAdapter;
import com.adeks.tabapp.model.StudentHrs;
import com.adeks.tabapp.model.StudentIq;

import java.util.ArrayList;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends Fragment {

    private static final String TAG = "PlaceholderFragment";
    private static final String ARG_SECTION_NUMBER = "section_number";

    private PageViewModel pageViewModel;
    private RecyclerView mRecyclerView;
    private RecyclerViewAdapter rvAdapter;
    private DataBank mDb;
    private int mIndex;
    private List<StudentIq> mStudentIqs;
    private List<StudentHrs> mStudentHrs;


    static PlaceholderFragment newInstance(int index) {
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageViewModel = ViewModelProviders.of(this).get(PageViewModel.class);
        mIndex = 1;
        if (getArguments() != null) {
            mIndex = getArguments().getInt(ARG_SECTION_NUMBER);
        }
        pageViewModel.setIndex(mIndex);
        pageViewModel.setStudentData();
        if (mIndex == 1) {
            Observer<List<StudentHrs>> stdHrObsvr = studentHrs -> {
                rvAdapter = new RecyclerViewAdapter(getContext(), studentHrs);
                mRecyclerView.setAdapter(rvAdapter);
            };
            pageViewModel.getStudentHrs().observe(this, stdHrObsvr);
        } else {
            Observer<List<StudentIq>> stdIqObsvr = studentIqs -> {
                rvAdapter = new RecyclerViewAdapter(getContext(), studentIqs, true);
                mRecyclerView.setAdapter(rvAdapter);
            };
            pageViewModel.getStudentIqs().observe(this, stdIqObsvr);
        }
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);
        mRecyclerView = root.findViewById(R.id.custom_rv);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setAdapter(rvAdapter);
        mRecyclerView.setLayoutManager(layoutManager);
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
//        mDb = new DataBank(getContext(), new AsyncResponse() {
//            @Override
//            public void processFinished(List<StudentHrs> objectList) {
//                if(mIndex == 1) {
//                    rvAdapter = new RecyclerViewAdapter(getContext(), objectList);
//                    mRecyclerView.setAdapter(rvAdapter);
//                }
//                mStudentHrs = new ArrayList<>();
//                mStudentHrs.addAll(objectList);
//            }
//
//
//            @Override
//            public void processFinished(List<StudentIq> objectList, boolean isTrue) {
//                if(mIndex == 2){
//                mStudentIqs = new ArrayList<>();
//                mStudentIqs.addAll(objectList);
//                    rvAdapter = new RecyclerViewAdapter(getContext(),mStudentIqs,true);
//                    mRecyclerView.setAdapter(rvAdapter);
//                }
//            }
//        });

    }
}