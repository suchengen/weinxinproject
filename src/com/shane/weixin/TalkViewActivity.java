package com.shane.weixin;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;



public class TalkViewActivity extends Activity {
	
	Button exit_talkView, personInfo_talkView, send_talkView;
	EditText editMessage;
	LinearLayout scrollLinear;
	TextView friendName;
	ScrollView scroll;
	
	private static boolean Direction = true;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_talkview);
		
		crateResource();
		initListener();
		
		friendName.setText(getIntent().getStringExtra("FriendName"));
	}
	
	public class createOnClickListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			switch(v.getId()){
			case R.id.exit_talkview:
				TalkViewActivity.this.finish();
				TalkViewActivity.this.overridePendingTransition(R.anim.zoomin, R.anim.zoomout);
				break;
			case R.id.personinfo_talkview:
				break;
			case R.id.send_talkview:
				if("".equals(editMessage.getText().toString().trim())){
					Toast.makeText(TalkViewActivity.this, "请输入信息", Toast.LENGTH_SHORT).show();
				}else{
					addMessage(editMessage.getText().toString());
					
					scroll.setFocusableInTouchMode(false);
					editMessage.setText("");
				}
				break;
			default:
				break;
			}
		}}
	
	private void initListener(){
		exit_talkView.setOnClickListener(new createOnClickListener());
		personInfo_talkView.setOnClickListener(new createOnClickListener());
		send_talkView.setOnClickListener(new createOnClickListener());
	}
	
	private void crateResource(){
		exit_talkView = (Button)findViewById(R.id.exit_talkview);
		personInfo_talkView = (Button)findViewById(R.id.personinfo_talkview);
		send_talkView = (Button)findViewById(R.id.send_talkview);
		scrollLinear = (LinearLayout)findViewById(R.id.box_talkview);
		scroll = (ScrollView)findViewById(R.id.talkscroll);
		editMessage = (EditText) findViewById(R.id.editmessage_talkview);
		friendName = (TextView)findViewById(R.id.friendname_talkview);
	}
	
	private void addMessage(String message){
		DisplayMetrics dm = new DisplayMetrics();
		dm = getResources().getDisplayMetrics();
		int screenWidth = dm.widthPixels;
		float temp = (float)3/5;
		temp = temp * screenWidth;
		
		TextView text = new TextView(TalkViewActivity.this);
		
		text.setTextSize(18.0f);
		text.setMaxWidth((int)temp);
		text.setText(message);
		
		ImageView image = new ImageView(TalkViewActivity.this);
		image.setImageResource(R.drawable.image);
		
		
		LinearLayout linearMessage = new LinearLayout(TalkViewActivity.this);
		linearMessage.setOrientation(LinearLayout.HORIZONTAL);
		
		LinearLayout.LayoutParams params = new 
				LinearLayout.LayoutParams(ViewGroup
						.LayoutParams.WRAP_CONTENT, 
						ViewGroup.LayoutParams.WRAP_CONTENT);
		params.gravity = Gravity.CENTER_VERTICAL;
		
		if(Direction){
			text.setBackgroundResource(R.drawable.chatto_bg_sendding);		
			linearMessage.addView(text, params);
			linearMessage.addView(image, params);
			params.gravity = Gravity.RIGHT;
			params.rightMargin = 15;
			Direction = !Direction;
			
		}else{
			text.setBackgroundResource(R.drawable.chatfrom_bg_normal);
			linearMessage.addView(image, params);
			linearMessage.addView(text, params);
			params.gravity = Gravity.LEFT;
			params.leftMargin = 15;
			Direction = !Direction;
		}
		params.topMargin = 5;
		
		scrollLinear.addView(linearMessage, params);
		scroll.post(new Runnable(){

			@Override
			public void run() {
				scroll.fullScroll(ScrollView.FOCUS_DOWN);
			}});
	}
}
