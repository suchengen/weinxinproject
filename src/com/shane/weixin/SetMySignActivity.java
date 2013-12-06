package com.shane.weixin;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SetMySignActivity extends Activity {

	Button exit, save;
	EditText edit;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setmysign);
		initResources();
		initListener();
	}

	private class createOnClickListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			switch(v.getId()){
			case R.id.exit_setmysign:
				SetMySignActivity.this.setResult(RESULT_CANCELED, SetMySignActivity.this.getIntent());
				SetMySignActivity.this.finish();
				break;
			case R.id.save_setmysign:
				if("".equals(edit.getText().toString().trim())){
					Toast.makeText(SetMySignActivity.this, "请输入个性签名", Toast.LENGTH_SHORT).show();
				}else{
					SetMySignActivity.this.getIntent().putExtra("getSign", edit.getText().toString());
					SetMySignActivity.this.setResult(RESULT_OK, SetMySignActivity.this.getIntent());
					SetMySignActivity.this.finish();
				}
				break;
			}
		}}
	
	private void initListener() {
		exit.setOnClickListener(new createOnClickListener());
		save.setOnClickListener(new createOnClickListener());
		edit.setOnClickListener(new createOnClickListener());
	}

	private void initResources() {
		exit = (Button)findViewById(R.id.exit_setmysign);
		save = (Button)findViewById(R.id.save_setmysign);
		edit = (EditText)findViewById(R.id.edit_mysign);
	}
}
