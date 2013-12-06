package com.shane.weixin;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class NearFriendActivity extends Activity {

	ProgressDialog dialog;
	ListView nearFriendList;
	SimpleAdapter nearFriendAdapter;
	ArrayList<HashMap<String, Object>> list;
	
	Button exit;
	Button setting;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_nearfriend);
		nearFriendList = (ListView)findViewById(R.id.list_nearfriend);
		exit = (Button)findViewById(R.id.exit_nearfriend);
		setting = (Button)findViewById(R.id.setting_nearfriend);
		dialog = new ProgressDialog(this);
		dialog.setMessage("正在搜索附近的人");
		dialog.setCancelable(false);
		
		list = new ArrayList<HashMap<String, Object>>();
		nearFriendAdapter = new SimpleAdapter(this, list, 
				R.layout.item_nearfriend_list, 
				new String[]{"image_item_nearfriend_list",
					"name_item_nearfriend_list",
					"sex_item_nearfriend_list",
					"distance_item_nearfriend_list",
					"signname_item_nearfriend_list",
					"other_item_nearfriend_list"}, 
				new int[]{R.id.image_item_nearfriend_list,
					R.id.name_item_nearfriend_list,
					R.id.sex_item_nearfriend_list,
					R.id.distance_item_nearfriend_list,
					R.id.signname_item_nearfriend_list,
					R.id.other_item_nearfriend_list});
		nearFriendList.setAdapter(nearFriendAdapter);
		
		dialog.setOnDismissListener(new OnDismissListener(){
			@Override
			public void onDismiss(DialogInterface dialog) {
				for(int i = 0; i < 20; i++){
					HashMap<String, Object> map = new HashMap<String, Object>();
					map.put("image_item_nearfriend_list", R.drawable.image);
					map.put("name_item_nearfriend_list", "张三");
					map.put("sex_item_nearfriend_list", R.drawable.ic_sex_male);
					map.put("distance_item_nearfriend_list", "100米以内");
					map.put("signname_item_nearfriend_list", "努力学习");
					map.put("other_item_nearfriend_list", R.drawable.bakchat_submenu_normal);
					list.add(map);
				}
				nearFriendAdapter.notifyDataSetChanged();
			}});
		
		exit.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				NearFriendActivity.this.finish();
			}});
		setting.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				
			}});
		new NearFriend().execute("null");
	}
	
	public class NearFriend extends AsyncTask<String, Void, Void>{

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			dialog.show();
		}

		@Override
		protected Void doInBackground(String... params) {
			
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			dialog.dismiss();
		}
	}	
}
