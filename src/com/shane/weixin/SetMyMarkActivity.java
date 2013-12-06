package com.shane.weixin;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

import com.google.zxing.WriterException;
import com.shane.encoding.EncodingHandler;

public class SetMyMarkActivity extends Activity {

	ImageView image;
	Button exit;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setmymark);
		String getCount = getIntent().getStringExtra("getCount");
		image = (ImageView)findViewById(R.id.mymark);
		try {
			Bitmap bm = EncodingHandler.createQRCode(getCount, 350);
			image.setImageBitmap(bm);
		} catch (WriterException e) {
			e.printStackTrace();
		}
		
		exit = (Button)findViewById(R.id.exit_setmymark);
		exit.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				SetMyMarkActivity.this.finish();
			}});
	}
}
