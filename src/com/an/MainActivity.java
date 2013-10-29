package com.an;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

import com.an.thread.asyntask.AsynTaskExecutorActivity;

public class MainActivity extends Activity implements View.OnClickListener{

    private Button threadButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        threadButton = (Button) findViewById(R.id.thread);
        threadButton.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.thread:
                startActivity(new Intent(MainActivity.this,AsynTaskExecutorActivity.class));
                break;
            default:
                break;

        }

    }
}
