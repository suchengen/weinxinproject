package com.shane.weixin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class LogoActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_logo);
		new Handler().postDelayed(new Thread(){
			@Override
			public void run() {
				Intent mainMenu = new Intent(LogoActivity.this, MainActivity.class);
				LogoActivity.this.startActivity(mainMenu);
				LogoActivity.this.finish();
				overridePendingTransition(R.anim.fadein, R.anim.fadeout);
			}}, 3);
	}
}
