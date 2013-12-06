package com.shane.weixin;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.shane.cityselect.provinceList;
import com.shane.pojo.Resources;

public class MyInfoActivity extends Activity {

	RelativeLayout setMyHead, setMyName, setMyCount, 
					setMyMark, setMySex, setMyLive, setMySign;
	
	TextView getName, getCount, getSex, getLive, getSign;
	
	Button exit;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_myinfo);
		initResources();
		initListener();	
	}
	
	private class createOnClickListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			Intent myinfoIntent;
			switch(v.getId()){
			case R.id.exit_myinfo:
				MyInfoActivity.this.getIntent().putExtra("getName", getName.getText().toString());
				MyInfoActivity.this.getIntent().putExtra("getCount", getCount.getText().toString());
				MyInfoActivity.this.setResult(RESULT_OK, MyInfoActivity.this.getIntent());
				MyInfoActivity.this.finish();
			case R.id.setmyhead:
				AlertDialog setMyHeadDialog = new AlertDialog.Builder(MyInfoActivity.this)
				.setTitle("请选择更改方式：")
				.setSingleChoiceItems(new String[]{"拍照", "手机相册"}, -1, new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						switch(which){
						case 0:
							break;
						case 1:
							break;
						default:
							break;
						}
						dialog.cancel();
						Toast.makeText(MyInfoActivity.this, "此功能没有添加", Toast.LENGTH_SHORT).show();
					}
				})
				.create();
				setMyHeadDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "取消", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						
					}
				});
				setMyHeadDialog.show();
				break;
			case R.id.setmyname:
				myinfoIntent = new Intent(MyInfoActivity.this, SetMyNameActivity.class);
				MyInfoActivity.this.startActivityForResult(myinfoIntent, Resources.SendSetMyName);
				MyInfoActivity.this.overridePendingTransition(R.anim.zoomin, R.anim.zoomout);
				break;
			case R.id.setmycount:
				myinfoIntent = new Intent(MyInfoActivity.this, SetMyCountActivity.class);
				MyInfoActivity.this.startActivityForResult(myinfoIntent, Resources.SendSetMyCount);
				MyInfoActivity.this.overridePendingTransition(R.anim.zoomin, R.anim.zoomout);
				break;
			case R.id.setmymark:
				myinfoIntent = new Intent(MyInfoActivity.this, SetMyMarkActivity.class);
				myinfoIntent.putExtra("getCount", getCount.getText().toString());
				MyInfoActivity.this.startActivity(myinfoIntent);
				MyInfoActivity.this.overridePendingTransition(R.anim.zoomin, R.anim.zoomout);
				break;
			case R.id.setmysex:
				AlertDialog dialog = new AlertDialog
						.Builder(MyInfoActivity.this)
						.setTitle("性别：")
						.setSingleChoiceItems(new String[]{"男", "女"}, getSex.getText().toString()=="男"?0:1, new DialogInterface.OnClickListener(){
							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								switch(which){
								case 0:
									getSex.setText("男");
									break;
								case 1:
									getSex.setText("女");
									break;
								}
								dialog.cancel();
							}}).create();
				dialog.setButton(DialogInterface.BUTTON_POSITIVE, "确认", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						
					}
				});
				dialog.show();
				break;
			case R.id.setmylive:
				myinfoIntent = new Intent(MyInfoActivity.this, provinceList.class);
				MyInfoActivity.this.startActivityForResult(myinfoIntent, Resources.SendSetMyLive);
				MyInfoActivity.this.overridePendingTransition(R.anim.zoomin, R.anim.zoomout);
				break;
			case R.id.setmysign:
				myinfoIntent = new Intent(MyInfoActivity.this, SetMySignActivity.class);
				MyInfoActivity.this.startActivityForResult(myinfoIntent, Resources.SendSetMySign);
				MyInfoActivity.this.overridePendingTransition(R.anim.zoomin, R.anim.zoomout);
				break;
			}
		}}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		switch(requestCode){
		case Resources.SendSetMyName:
			switch(resultCode){
			case RESULT_OK:
				getName.setText(data.getStringExtra("getName"));
				break;
			default:
				break;
			}
			break;
		case Resources.SendSetMyCount:
			switch(resultCode){
			case RESULT_OK:
				getCount.setText(data.getStringExtra("getCount"));
				break;
			default:
				break;
			}
			break;
		case Resources.SendSetMyLive:
			switch(resultCode){
			case RESULT_OK:
				getLive.setText(data.getStringExtra("cityname"));
				break;
			default:
				break;
			}
		case Resources.SendSetMySign:
			switch(resultCode){
			case RESULT_OK:
				getSign.setText(data.getStringExtra("getSign"));
				break;
			default:
				break;
			}
		default:
			break;
		}
	}

	private void initListener(){
		exit.setOnClickListener(new createOnClickListener());
		setMyHead.setOnClickListener(new createOnClickListener());
		setMyName.setOnClickListener(new createOnClickListener());
		setMyCount.setOnClickListener(new createOnClickListener());
		setMyMark.setOnClickListener(new createOnClickListener());
		setMySex.setOnClickListener(new createOnClickListener());
		setMyLive.setOnClickListener(new createOnClickListener());
		setMySign.setOnClickListener(new createOnClickListener());
	}
	
	private void initResources(){
		exit = (Button)findViewById(R.id.exit_myinfo);
		
		setMyHead = (RelativeLayout)findViewById(R.id.setmyhead);
		setMyName = (RelativeLayout)findViewById(R.id.setmyname);
		setMyCount = (RelativeLayout)findViewById(R.id.setmycount);
		setMyMark = (RelativeLayout)findViewById(R.id.setmymark);
		setMySex = (RelativeLayout)findViewById(R.id.setmysex);
		setMyLive = (RelativeLayout)findViewById(R.id.setmylive);
		setMySign = (RelativeLayout)findViewById(R.id.setmysign);
		
		getName = (TextView)findViewById(R.id.setmyname_text);
		getCount = (TextView)findViewById(R.id.setmycount_text);
		getSex = (TextView)findViewById(R.id.setmysex_text);
		getLive = (TextView)findViewById(R.id.setmylive_text);
		getSign = (TextView)findViewById(R.id.setmysign_text);
	}
	
}
