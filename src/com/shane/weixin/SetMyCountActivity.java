package com.shane.weixin;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SetMyCountActivity extends Activity {

	Button exit, save;
	EditText edit;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setmycount);
		initResources();
		initListener();
	}
	
	private void initListener() {
		exit.setOnClickListener(new createOnClickListener());
		save.setOnClickListener(new createOnClickListener());
		edit.setOnClickListener(new createOnClickListener());
	}

	private void initResources() {
		exit = (Button)findViewById(R.id.exit_setmycount);
		save = (Button)findViewById(R.id.save_setmycount);
		edit = (EditText)findViewById(R.id.edit_mycount);
	}

	private class createOnClickListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			switch(v.getId()){
			case R.id.exit_setmycount:
				SetMyCountActivity.this.setResult(RESULT_CANCELED, SetMyCountActivity.this.getIntent());
				SetMyCountActivity.this.finish();
				break;
			case R.id.save_setmycount:
				if("".equals(edit.getText().toString().trim())){
					Toast.makeText(SetMyCountActivity.this, "请输入账号", Toast.LENGTH_SHORT).show();
				}else{
					SetMyCountActivity.this.getIntent().putExtra("getCount", edit.getText().toString());
					SetMyCountActivity.this.setResult(RESULT_OK, SetMyCountActivity.this.getIntent());
					SetMyCountActivity.this.finish();
				}
				break;
			}
		}}
	
}
