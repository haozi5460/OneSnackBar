package com.android.onesnackbar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.android.onesnackbarlib.OneSnackBar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        OneSnackBar.make(getWindow().getDecorView(),"dsdfjsfasdfjsfjsodfjsdfjp",OneSnackBar.LENGTH_LONG,OneSnackBar.APPEAR_FROM_TOP_TO_DOWN).show();
    }
}
