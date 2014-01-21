package com.an.pic.blur;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;

import com.an.R;

public class BlurActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_blur_navi);
		findViewById(R.id.java_method).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View arg0) {

						startActivity(new Intent(BlurActivity.this,
								GaosiActivity.class));
					}
				});

		findViewById(R.id.renderscript_method).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View arg0) {

						startActivity(new Intent(BlurActivity.this,
								RSActivity.class));
					}
				});
		findViewById(R.id.renderscript_method).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View arg0) {

						startActivity(new Intent(BlurActivity.this,
								RSActivity.class));
					}
				});
		
		findViewById(R.id.native_method).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View arg0) {

						startActivity(new Intent(BlurActivity.this,
								NativeBlurActivity.class));
					}
				});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
