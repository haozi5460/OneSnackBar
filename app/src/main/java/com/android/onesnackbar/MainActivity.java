package com.android.onesnackbar;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.android.onesnackbarlib.OneSnackBar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final OneSnackBar oneSnackBar = OneSnackBar.make(getWindow().getDecorView(),"dsdfjsfasdfjsfjsodfjsdfjp",OneSnackBar.LENGTH_LONG,OneSnackBar.APPEAR_FROM_TOP_TO_DOWN);
        oneSnackBar.setAction("again", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        oneSnackBar.show();
                    }
                },1000);
            }
        });
        oneSnackBar.show();
    }
}
