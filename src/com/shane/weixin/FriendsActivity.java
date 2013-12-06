package com.shane.weixin;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class FriendsActivity extends Activity {

	private ArrayList<HashMap<String, Object>> friendsList;
	SimpleAdapter adapter;
	ListView friendsListView;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_friends);
		friendsListView = (ListView)findViewById(R.id.friendsview_listview);
		
		friendsList = new ArrayList<HashMap<String, Object>>();
		
		adapter = new SimpleAdapter(this, friendsList, 
				R.layout.item_friendsview, 
				new String[]{
				"friendimage_itemfriendview", 
				"friendname_itemfriendview", 
				"friendshuoshuo_itemfriendview"
		},  new int[]{R.id.friendimage_itemfriendview,
				R.id.friendname_itemfriendview, 
				R.id.friendshuoshuo_itemfriendview});
		friendsListView.setAdapter(adapter);
		
		
	}
	
}
