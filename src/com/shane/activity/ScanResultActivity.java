package com.shane.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.shane.weixin.R;

public class ScanResultActivity extends Activity {

	TextView scanResult;
	Button exit; 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_getscanresult);
		scanResult = (TextView)findViewById(R.id.scanresult);
		scanResult.setText(getIntent().getStringExtra("ScanResult"));
		exit = (Button)findViewById(R.id.exit_scanresult);
		exit.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				ScanResultActivity.this.finish();
			}});
	}
}
