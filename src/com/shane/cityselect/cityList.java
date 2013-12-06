package com.shane.cityselect;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.shane.weixin.R;

public class cityList extends Activity{

	Button exit;
	
	String citys[][];
	int cityCount=0;
	ListView lv;
	String getProvince; 
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_citylist);

        exit = (Button)findViewById(R.id.exit_selectcity);
        exit.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				cityList.this.finish();
			}});
        
        
        Intent i=getIntent();
        String provinceid=i.getStringExtra("provinceid");
        getProvince = i.getStringExtra("provincename");
        lv=(ListView)findViewById(R.id.listview_city);
        
        MyDatabase myDB = new MyDatabase(this);
        Cursor cCity = myDB.getCities(provinceid);
        cityCount=cCity.getCount();
        if(cityCount==0)
        {
        	returnResult(i.getStringExtra("provincename"));
        	
        }
        citys=new String[cityCount][2];
        
        for(int j=0;j<cityCount;j++)
        {
        	citys[j][0]=cCity.getString(0);
        	citys[j][1]=cCity.getString(1);	
        	cCity.moveToNext();
        }
        
        lv.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1,getData()));
        lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				returnResult(getProvince + "  " +citys[position][1]);
			}
		});
        
    }
	
	void returnResult(String sdata) {
		Intent result = new Intent();
		result.putExtra("cityname", sdata);
		setResult(Activity.RESULT_OK, result);
		finish();
	}

	public List<String> getData() {

		List<String> ls=new ArrayList<String>();
		ls=asList(citys);
		return ls;
	}
	
	//�ַ�����ת��list
	public List<String>  asList(String s[][]){
		List<String> l=new ArrayList<String>();
		for(int i=0;i<cityCount;i++)
		{
			if(s[i][1]!=null)
				l.add(s[i][1]);
		}
		return l;	
	}
}
