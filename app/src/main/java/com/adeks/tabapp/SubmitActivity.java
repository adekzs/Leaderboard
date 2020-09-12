package com.adeks.tabapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;

import android.graphics.drawable.VectorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.appbar.AppBarLayout;

public class SubmitActivity extends AppCompatActivity {
    public static final String dialog_name = "Hello";

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit);
        setSupportActionBar(findViewById(R.id.toolbar));

        View view = getSupportActionBar().getCustomView();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_nav_back);
        EditText firstName = findViewById(R.id.first_name_et);
        EditText lastName = findViewById(R.id.last_name_et);
        EditText email = findViewById(R.id.email_et);
        EditText githubLink = findViewById(R.id.github_et);

        Button submitButton = findViewById(R.id.submit_btn);
        submitButton.setOnClickListener(v -> {
            String firstNameStr = firstName.getText().toString();
            String lastNameStr = lastName.getText().toString();
            String emailAddr = email.getText().toString();
            String gitlink = githubLink.getText().toString();
            if (firstNameStr.isEmpty() || lastNameStr.isEmpty() || emailAddr.isEmpty() || gitlink.isEmpty()) {
                Toast.makeText(this, "You must fill in all fields", Toast.LENGTH_SHORT).show();
            } else {
                DialogFragment submitDialog = SubmitDialog.newInstance(firstNameStr, lastNameStr, emailAddr, gitlink);
                submitDialog.show(getSupportFragmentManager(), dialog_name);
            }
        });

    }
}
