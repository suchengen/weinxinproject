package com.shane.weixin;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SetMyNameActivity extends Activity {
	
	Button exit, save;
	EditText edit;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setmyname);
		initResources();
		initListener();
	}
	
	private void initListener() {
		exit.setOnClickListener(new createOnClickListener());
		save.setOnClickListener(new createOnClickListener());
		edit.setOnClickListener(new createOnClickListener());
	}

	private void initResources() {
		exit = (Button)findViewById(R.id.exit_setmyname);
		save = (Button)findViewById(R.id.save_setmyname);
		edit = (EditText)findViewById(R.id.edit_myname);
	}

	private class createOnClickListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			switch(v.getId()){
			case R.id.exit_setmyname:
				SetMyNameActivity.this.setResult(RESULT_CANCELED, SetMyNameActivity.this.getIntent());
				SetMyNameActivity.this.finish();
				break;
			case R.id.save_setmyname:
				if("".equals(edit.getText().toString().trim())){
					Toast.makeText(SetMyNameActivity.this, "请输入名字", Toast.LENGTH_SHORT).show();
				}else{
					SetMyNameActivity.this.getIntent().putExtra("getName", edit.getText().toString());
					SetMyNameActivity.this.setResult(RESULT_OK, SetMyNameActivity.this.getIntent());
					SetMyNameActivity.this.finish();
				}
				break;
			}
		}}
}
