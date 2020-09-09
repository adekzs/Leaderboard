package com.adeks.tabapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.adeks.tabapp.retrofit.ProjectSubmit;
import com.adeks.tabapp.ui.main.SubmitAsyncResponse;

public class SubmitDialog extends DialogFragment {

    private static final String TAG = "SubmitDialog";
    private static final String FIRSTNAME = "com.adeks.tabapp.FIRSTNAME";
    private static final String LASTNAME = "com.adeks.tabapp.LASTNAME";
    private static final String GMAIL = "com.adeks.tabapp.GMAIL";
    private static final String GITHUBLINK = "com.adeks.tabapp.GITHUBLINK";

    //widgets
    private ImageView mFailureIcon;
    private ImageView mSuccessIcon;
    private TextView mQuestion_textView;
    private ImageView mCancelDialog;
    private Button mYesBtn;
    private TextView mUnsuccessful_text;
    private TextView mSuccess_text;


    //argument values
    private String mFirstName;
    private String mLastName;
    private String mEmail;
    private String mGithublink;

    public static SubmitDialog newInstance(String firstName, String lastName, String gmail, String githubLink) {
        SubmitDialog frag = new SubmitDialog();
        Bundle args = new Bundle();
        args.putString(FIRSTNAME, firstName);
        args.putString(LASTNAME, lastName);
        args.putString(GMAIL, gmail);
        args.putString(GITHUBLINK, githubLink);
        frag.setArguments(args);
        return frag;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null)
            getArgumentValues(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.submit_dialog, container, false);
        setupViews(v);
        mYesBtn.setOnClickListener(v1 -> {
            ProjectSubmit projectSubmit = new ProjectSubmit();
            projectSubmit.submitPoject(mFirstName, mLastName, mEmail, mGithublink, new SubmitAsyncResponse() {
                @Override
                public void onProcessCompleted() {
                    clearDialog();
                    successfulPost(true);
                    unsuccessfulPost(false);
                }

                @Override
                public void onProcessFailed() {
                    clearDialog();
                    successfulPost(false);
                    unsuccessfulPost(true);
                }
            });

        });
        mCancelDialog.setOnClickListener(v3 -> dismiss());
        return v;
    }

    private void getArgumentValues(Bundle savedInstance) {
        mFirstName = getArguments().getString(FIRSTNAME);
        mLastName = getArguments().getString(LASTNAME);
        mEmail = getArguments().getString(GMAIL);
        mGithublink = getArguments().getString(GITHUBLINK);
        Log.d(TAG, "getArgumentValues: " + mFirstName);
    }

    private void setupViews(View v) {
        mSuccess_text = v.findViewById(R.id.success_text);
        mUnsuccessful_text = v.findViewById(R.id.notsuccess_text);
        mYesBtn = v.findViewById(R.id.yes_btn);
        mCancelDialog = v.findViewById(R.id.cancel_dialog);
        mQuestion_textView = v.findViewById(R.id.question);
        mSuccessIcon = v.findViewById(R.id.success_icon);
        mFailureIcon = v.findViewById(R.id.failure_icon);
    }

    private void successfulPost(boolean isSuccessful) {
        int visible = isSuccessful ? View.VISIBLE : View.INVISIBLE;
        mSuccess_text.setVisibility(visible);
        mSuccessIcon.setVisibility(visible);
    }

    private void unsuccessfulPost(boolean isUnsuccessful) {
        int visible = isUnsuccessful ? View.VISIBLE : View.INVISIBLE;
        mFailureIcon.setVisibility(visible);
        mUnsuccessful_text.setVisibility(visible);
    }

    private void clearDialog() {
        mCancelDialog.setVisibility(View.INVISIBLE);
        mQuestion_textView.setVisibility(View.INVISIBLE);
        mYesBtn.setVisibility(View.INVISIBLE);
    }
}
