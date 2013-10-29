package com.an;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.an.pic.blur.BlurActivity;
import com.an.thread.asyntask.AsynTaskExecutorActivity;

public class MainActivity extends Activity implements View.OnClickListener{

    private Button threadButton;
    private Button picButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        threadButton = (Button) findViewById(R.id.thread);
        picButton = (Button) findViewById(R.id.pic);
        picButton.setOnClickListener(this);
        threadButton.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.thread:
                startActivity(new Intent(MainActivity.this,AsynTaskExecutorActivity.class));
                break;
            case R.id.pic:
            	startActivity(new Intent(MainActivity.this,BlurActivity.class));
            	break;
            default:
                break;

        }

    }
}
