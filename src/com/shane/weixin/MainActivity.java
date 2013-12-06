package com.shane.weixin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.shane.activity.CaptureActivity;
import com.shane.pinyin.ListViewAdapter;
import com.shane.pinyin.Person;
import com.shane.pinyin.PinyinArrayList;
import com.shane.pojo.Resources;
public class MainActivity extends Activity {

	private ListView listView;
	private ListViewAdapter adapter;
    private List<Person> persons = null;  
    private List<Person> newPersons;
    
    private ListView historyListView;
    private SimpleAdapter historyAdapter;
    private ArrayList<HashMap<String, Object>> historylistitem;
    private int[] listNumber;
    
	private TabHost tabHost;
	private RelativeLayout  tab3friends, tab3scanFriend, tab3flashFriend,
							tab3nearFriend, tab3floater, tab3games, 
							tab4MyInfo, tab4MyAblum, tab4MyCollect, 
							tab4MyBankCard, tab4Stroe, tab4Setting;
	TextView myinfoName, myinfoCount;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		craeteTabHost();
		initResources();
		initListener();
		initFriendList();
		initHistroyList();

	}
	
	private void initHistroyList(){
		historylistitem = new ArrayList<HashMap<String, Object>>();
//		for(int i = 0; i < 10; i++){
//			HashMap<String, Object>	map = new HashMap<String, Object>();
//			map.put("image_historylistview", R.drawable.image);
//			map.put("name_historylistview", "NULL");
//			map.put("lastmessage_historylistview", "null");
//			map.put("day_historylistview", "XX月XX日");
//			historylistitem.add(map);
//		}
		
		historyAdapter = new SimpleAdapter(this, historylistitem, 
				R.layout.item_historylistview, 
				new String[]{"image_historylistview", 
					"name_historylistview", 
					"lastmessage_historylistview", 
					"day_historylistview"}, 
				new int[]{R.id.image_historylistview,
					R.id.name_historylistview, 
					R.id.lastmessage_historylistview,
					R.id.day_historylistview});	
	
		historyListView.setAdapter(historyAdapter);
		historyListView.setOnItemClickListener(new OnItemClickListener(){
			@Override
			public void onItemClick(AdapterView<?> adapter, View view, int position,
					long id) {
				Intent intent = new Intent(MainActivity.this, TalkViewActivity.class);
				intent.putExtra("FriendName", newPersons.get(listNumber[position]).getName());
				MainActivity.this.startActivity(intent);
				MainActivity.this.overridePendingTransition(R.anim.zoomin, R.anim.zoomout);
			}});
	}
	
	public void initFriendList(){
		setData();
		newPersons = new PinyinArrayList(persons);
		
		listNumber = new int[newPersons.size()];
		for(int i = 0; i < listNumber.length; i++){
			listNumber[i] = -1;
		}
		
        adapter = new ListViewAdapter(this, newPersons);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new OnItemClickListener(){
			@Override
			public void onItemClick(AdapterView<?> adapter, View view, int position,
					long id) {
				Toast.makeText(MainActivity.this, ""+position, Toast.LENGTH_SHORT).show();
				Intent intent = new Intent(MainActivity.this, TalkViewActivity.class);
				intent.putExtra("FriendName", newPersons.get(position).getName());
				
				if(!newPersons.get(position).getIsAdd()){
					HashMap<String, Object> map = new HashMap<String, Object>();
					map.put("image_historylistview", R.drawable.image);
					map.put("name_historylistview", newPersons.get(position).getName());
					map.put("day_historylistview", DateFormat.format("MM月dd日  EEEE ", System.currentTimeMillis()));
					historylistitem.add(map);
					historyAdapter.notifyDataSetChanged();
					newPersons.get(position).setIsAdd(true);
					for(int i = 0; i < listNumber.length; i++){
						if(listNumber[i] == -1){
							listNumber[i] = position;
							break;
						}
					}
				}
				
				MainActivity.this.startActivity(intent);
				MainActivity.this.overridePendingTransition(R.anim.zoomin, R.anim.zoomout);
			}});
	}
	
	public class createOnClickListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			Intent intent;
			switch(v.getId()){
			case R.id.btn_nearfriend:
				intent = new Intent(MainActivity.this, NearFriendActivity.class);
				MainActivity.this.startActivity(intent);
				break;
			case R.id.btn_scanfriend:
				intent = new Intent(MainActivity.this, CaptureActivity.class);
				MainActivity.this.startActivity(intent);
				break;
			case R.id.btn_flashfriend:
				intent = new Intent(MainActivity.this, ShakeActivity.class);
				MainActivity.this.startActivity(intent);
				break;
			case R.id.btn_friends:
				intent = new Intent(MainActivity.this, FriendsActivity.class);
				MainActivity.this.startActivity(intent);
				break;
			case R.id.btn_myinfo:
				intent = new Intent(MainActivity.this, MyInfoActivity.class);
				MainActivity.this.startActivityForResult(intent, Resources.SendMyInfo);
				break;
			case R.id.btn_myablum:
				break;
			case R.id.btn_mycollect:
				break;
			case R.id.btn_bankcard:
				break;
			case R.id.btn_store:
				break;
			case R.id.btn_setting:
				break;
			}
		}};
	

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		switch(requestCode){
		case Resources.SendMyInfo:
			switch(resultCode){
			case RESULT_OK:
				myinfoName.setText(data.getStringExtra("getName"));
				myinfoCount.setText("微信号：" + data.getStringExtra("getCount"));
				break;
			default:
				break;
			}
		}
	}

	private void initResources(){

		tab3friends = (RelativeLayout)findViewById(R.id.btn_friends);
		tab3scanFriend = (RelativeLayout)findViewById(R.id.btn_scanfriend);
		tab3flashFriend = (RelativeLayout)findViewById(R.id.btn_flashfriend);
		tab3nearFriend = (RelativeLayout)findViewById(R.id.btn_nearfriend);
		tab3floater = (RelativeLayout)findViewById(R.id.btn_floater);
		tab3games = (RelativeLayout)findViewById(R.id.btn_games);
		
		tab4MyInfo = (RelativeLayout)findViewById(R.id.btn_myinfo); 
		tab4MyAblum = (RelativeLayout)findViewById(R.id.btn_myablum); 
		tab4MyCollect = (RelativeLayout)findViewById(R.id.btn_mycollect);
		tab4MyBankCard = (RelativeLayout)findViewById(R.id.btn_bankcard);
		tab4Stroe = (RelativeLayout)findViewById(R.id.btn_store); 
		tab4Setting = (RelativeLayout)findViewById(R.id.btn_setting);
		
		myinfoName = (TextView)findViewById(R.id.btn_myinfo_name);
		myinfoCount = (TextView)findViewById(R.id.btn_myinfo_count);
		listView = (ListView) findViewById(R.id.listView);
		
		historyListView = (ListView)findViewById(R.id.history_list);
	}
	
	private void initListener(){
		tab3friends.setOnClickListener(new createOnClickListener());
		tab3flashFriend.setOnClickListener(new createOnClickListener());
		tab3scanFriend.setOnClickListener(new createOnClickListener());
		tab3nearFriend.setOnClickListener(new createOnClickListener());
		
		tab4MyInfo.setOnClickListener(new createOnClickListener());
		tab4MyAblum.setOnClickListener(new createOnClickListener());
		tab4MyCollect.setOnClickListener(new createOnClickListener());
		tab4MyBankCard.setOnClickListener(new createOnClickListener());
		tab4Stroe.setOnClickListener(new createOnClickListener());
		tab4Setting.setOnClickListener(new createOnClickListener());
	}
	
	private void craeteTabHost(){
		tabHost = (TabHost)findViewById(R.id.tabhost);
		tabHost.setPadding(tabHost.getPaddingLeft(),
				tabHost.getPaddingTop(), tabHost.getPaddingRight(),
				tabHost.getPaddingBottom() - 5);

		tabHost.setup();
		
		LayoutInflater inflater = LayoutInflater.from(this);
		inflater.inflate(R.layout.tab1_main, tabHost.getTabContentView());
		inflater.inflate(R.layout.tab2_main, tabHost.getTabContentView());
		inflater.inflate(R.layout.tab3_main, tabHost.getTabContentView());
		inflater.inflate(R.layout.tab4_main, tabHost.getTabContentView());
		
		tabHost.addTab(tabHost.newTabSpec("tab1")
				.setIndicator("微信", MainActivity
						.this.getResources()
						.getDrawable(R.color.tab_weixin_selector))
				.setContent(R.id.tab1_main));
		
		tabHost.addTab(tabHost.newTabSpec("tab1")
				.setIndicator("通讯录", MainActivity
						.this.getResources()
						.getDrawable(R.color.tab_address_selector))
				.setContent(R.id.tab2_main));
		
		tabHost.addTab(tabHost.newTabSpec("tab1")
				.setIndicator("发现", MainActivity
						.this.getResources()
						.getDrawable(R.color.tab_find_selector))
				.setContent(R.id.tab3_main));
		
		tabHost.addTab(tabHost.newTabSpec("tab1")
				.setIndicator("我", MainActivity
						.this.getResources()
						.getDrawable(R.color.tab_setting_selector))
				.setContent(R.id.tab4_main));
		
		tabHost.setCurrentTab(1);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId()){
		case R.id.action_settings:
			this.finish();
		}
		return super.onOptionsItemSelected(item);
	}

	private void setData() {  
		persons = new ArrayList<Person>();  
	    Person p1 = new Person("耿琦"); 
	    persons.add(p1);
	    Person p2 = new Person("王宝强");  
	    persons.add(p2);
	    Person p3 = new Person("柳岩");  
	    persons.add(p3);
	    Person p4 = new Person("文章");  
	    persons.add(p4);  
	    Person p5 = new Person("马伊琍");  
	    persons.add(p5);  
	    Person p6 = new Person("李晨");  
	    persons.add(p6);  
	    Person p7 = new Person("张馨予");  
	    persons.add(p7);  
	        Person p8 = new Person("韩红");  
	        persons.add(p8);  
	        Person p9 = new Person("韩寒");  
	        persons.add(p9);  
	        Person p10 = new Person("丹丹");  
	        persons.add(p10);  
	        Person p11 = new Person("丹凤眼");  
	        persons.add(p11);  
	        Person p12 = new Person("哈哈");  
	        persons.add(p12);  
	        Person p13 = new Person("萌萌");  
	        persons.add(p13);  
	        Person p14 = new Person("蒙混");  
	        persons.add(p14);  
	        Person p15 = new Person("烟花");  
	        persons.add(p15);  
	        Person p16 = new Person("眼黑");  
	        persons.add(p16);  
	        Person p17 = new Person("许三多");  
	        persons.add(p17);  
	        Person p18 = new Person("程咬金");  
	        persons.add(p18);  
	        Person p19 = new Person("程哈哈");  
	        persons.add(p19);  
	        Person p20 = new Person("爱死你");  
	        persons.add(p20);  
	        Person p21 = new Person("阿莱");  
	        persons.add(p21);
	        Person p22 = new Person("包包");
	        persons.add(p22);
	    }  
}
