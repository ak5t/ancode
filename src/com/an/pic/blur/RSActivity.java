package com.an.pic.blur;

import com.an.R;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

public class RSActivity extends Activity {
	
	private ImageView gaosiImageView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_rs);
		gaosiImageView = (ImageView)findViewById(R.id.gaosi_bitmap);
	}
	
	public void onResume() {
		super.onResume();
		long timeStart = SystemClock.currentThreadTimeMillis();
		Drawable image = getResources().getDrawable(R.drawable.jason_middle);
		Bitmap bitmap = ((BitmapDrawable) image).getBitmap();
		gaosiImageView.setImageBitmap(ImageUtil.getRSBlurImage(getApplicationContext(), bitmap));
		long timeEnd = SystemClock.currentThreadTimeMillis();
		Log.d("------------", timeEnd - timeStart+"ms");
		Toast.makeText(getApplicationContext(), timeEnd - timeStart+"ms", Toast.LENGTH_SHORT).show();
	}
	
	
	
	

}
