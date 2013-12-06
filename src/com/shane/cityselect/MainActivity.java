package com.shane.cityselect;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;

import com.shane.weixin.R;

public class MainActivity extends Activity {

	EditText etxt;
	Context mcontext;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cityselect_main);

		etxt = (EditText) findViewById(R.id.editText1);
		mcontext = this;
		etxt.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(mcontext, provinceList.class);
				startActivityForResult(i, 1);
				int version = Integer.valueOf(android.os.Build.VERSION.SDK);
				if (version >= 5) {
					overridePendingTransition(R.anim.zoomin, R.anim.zoomout); // 
					// overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
					// overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
				}
			}
		});
	}

	protected void onActivityResult(int resultCode, int re, Intent data) {
		String cityname;
		if (data == null) {
			return;
		}
		cityname = data.getStringExtra("cityname");
		etxt.setText(cityname);
	}
}
